package com.endava.internship.vcontu.frameworker.domain.context;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Supplier;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.*;

public class AutomaticApplicationContext implements ApplicationContext {

    private static final String CONTEXT_ERROR = "Cannot build application context";
    private static final String ONE_CONSTRUCTOR_PER_CLASS_ALLOWED = "Build limitation: only one constructor per class allowed";
    private static final String ONLY_IMPLEMENTATIONS_ALLOWED = "Only implementation classes allowed as argument";
    private static final Supplier<RuntimeException> RUNTIME_EXCEPTION_SUPPLIER = () -> new RuntimeException(CONTEXT_ERROR);

    private Map<Class<?>, Object> context = new HashMap<>();
    private final Map<Class<?>, Class<?>> interfaceMap;
    private final Map<Class<?>, Constructor<?>> constructorMap;
    private final Map<Class<?>, List<Class<?>>> dependencyMap;
    private final List<Class<?>> buildOrder;

    public AutomaticApplicationContext(Class<?>... classes) {

        if (stream(classes).anyMatch(Class::isInterface))
            throw new IllegalArgumentException(ONLY_IMPLEMENTATIONS_ALLOWED);

        interfaceMap = mapInterfaces(classes);
        constructorMap = mapConstructors(classes);
        dependencyMap = mapDependencies(classes);
        buildOrder = dependencyResolution();
        buildContext();
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return ofNullable(context.get(clazz))
                .filter(Objects::nonNull)
                .filter(object -> object.getClass() == clazz)
                .map(clazz::cast)
                .orElseThrow(() -> new RuntimeException("No such bean found"));
    }

    private Map<Class<?>, Class<?>> mapInterfaces(Class<?>[] classes) {
        final Map<Class<?>, Class<?>> map = new HashMap<>();

        for (Class<?> clazz : classes) {
            for (Class<?> anInterface : clazz.getInterfaces()) {
                map.put(anInterface, clazz);
            }
        }
        return map;
    }

    private Map<Class<?>, Constructor<?>> mapConstructors(Class<?>[] classes) {
        Map<Class<?>, Constructor<?>> map = new HashMap<>();

        for (Class<?> clazz : classes) {
            if (clazz.getConstructors().length > 1)
                throw new IllegalArgumentException(ONE_CONSTRUCTOR_PER_CLASS_ALLOWED);

            Constructor<?> constructor = clazz.getConstructors()[0];
            map.put(constructor.getDeclaringClass(), constructor);
        }
        return map;
    }

    private Map<Class<?>, List<Class<?>>> mapDependencies(Class<?>[] classes) {
        Map<Class<?>, List<Class<?>>> map = new HashMap<>();

        for (Class<?> clazz : classes) {
            List<Class<?>> parameters = new ArrayList<>();
            for (Class<?> param : constructorMap.get(clazz).getParameterTypes()) {
                parameters.add(param.isInterface() ? interfaceMap.get(param) : param);
            }
            map.put(clazz, parameters);
        }
        return map;
    }

    private List<Class<?>> dependencyResolution() {
        final List<Class<?>> orderList = new LinkedList<>();
        final Set<Class<?>> remainingSet = new HashSet<>();

        for (Map.Entry<Class<?>, List<Class<?>>> entry : dependencyMap.entrySet()) {
            if (entry.getValue().size() == 0) {
                orderList.add(entry.getKey());
            } else {
                remainingSet.add(entry.getKey());
            }
        }

         while (!remainingSet.isEmpty()) {
            final List<Class<?>> candidates = remainingSet.stream()
                    .filter(clazz -> orderList.containsAll(dependencyMap.get(clazz)))
                    .collect(toList());

            if (candidates.isEmpty())
                throw RUNTIME_EXCEPTION_SUPPLIER.get();

            orderList.addAll(candidates);
            remainingSet.removeAll(candidates);
        }
        return orderList;
    }

    private void buildContext() {
        for (Class<?> clazz : buildOrder) {
            final Constructor<?> constructor = constructorMap.get(clazz);

            final Object[] params = dependencyMap.get(clazz)
                    .stream()
                    .map(this::getBean)
                    .toArray();

            buildInstance(constructor, params);
        }
    }

    private void buildInstance(Constructor<?> constructor, Object[] params) {
        try {
            final Object instance = constructor.newInstance(params);
            final Class<?> clazz = instance.getClass();
            context.put(clazz, instance);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            throw RUNTIME_EXCEPTION_SUPPLIER.get();
        }
    }
}
