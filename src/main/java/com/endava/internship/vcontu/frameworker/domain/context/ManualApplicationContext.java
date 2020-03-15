package com.endava.internship.vcontu.frameworker.domain.context;

import com.endava.internship.vcontu.frameworker.domain.editing.Editor;
import com.endava.internship.vcontu.frameworker.domain.editing.PunctuationEditor;
import com.endava.internship.vcontu.frameworker.domain.news.LocalNewsMaker;
import com.endava.internship.vcontu.frameworker.domain.news.NewsMaker;
import com.endava.internship.vcontu.frameworker.domain.publishing.ConsolePublisher;
import com.endava.internship.vcontu.frameworker.domain.publishing.Publisher;
import com.endava.internship.vcontu.frameworker.domain.translation.EnglishToRomanianTranslator;
import com.endava.internship.vcontu.frameworker.domain.translation.EnglishToRussianTranslator;
import com.endava.internship.vcontu.frameworker.domain.translation.Translator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Optional.ofNullable;

public class ManualApplicationContext implements ApplicationContext{

    private final Map<Class<?>, Object> context = new HashMap<>();

    public ManualApplicationContext() {
        Editor editor = new PunctuationEditor();
        Translator translator = new EnglishToRussianTranslator();
        Publisher publisher = new ConsolePublisher(translator);
        NewsMaker newsMaker = new LocalNewsMaker(translator, editor, publisher);

        context.put(EnglishToRussianTranslator.class, translator);
        context.put(ConsolePublisher.class, publisher);
        context.put(PunctuationEditor.class, editor);
        context.put(LocalNewsMaker.class, newsMaker);
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return ofNullable(context.get(clazz))
                .filter(Objects::nonNull)
                .filter(object -> object.getClass() == clazz)
                .map(clazz::cast)
                .orElseThrow(() -> new RuntimeException("No such bean found"));
    }


}
