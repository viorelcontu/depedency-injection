package com.endava.internship.vcontu.frameworker;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;
import com.endava.internship.vcontu.frameworker.domain.news.NewsMaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Main {

    public static final String GOOD_NEWS = "good news";
    public static final String HELLO_NEWS = "Hello";
    public static final String BILL_WOODS_AUTHOR = "Bill Woods";
    public static final String JOHN_DOE_AUTHOR = "John Doe";
    public static final String ORIGINAL_STORY_GREETING = "Original story (english): ";

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        final NewsMaker romanianNewsMaker = context.getBean("localNewsMaker",NewsMaker.class);
        final OriginalStory originalStory1 = new OriginalStory(GOOD_NEWS, BILL_WOODS_AUTHOR);
        greeting(originalStory1);
        romanianNewsMaker.processStory(originalStory1);

        System.out.println("------------\n");

        final NewsMaker russianNewsMaker = context.getBean("localNewsMaker",NewsMaker.class);
        final OriginalStory originalStory2 = new OriginalStory(HELLO_NEWS, JOHN_DOE_AUTHOR);
        greeting(originalStory2);
        russianNewsMaker.processStory(originalStory2);
    }

    private static void greeting(final OriginalStory story) {
        System.out.println(ORIGINAL_STORY_GREETING + story.getOriginalMessage());
    }
}
