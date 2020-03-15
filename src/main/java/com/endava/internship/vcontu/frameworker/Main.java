package com.endava.internship.vcontu.frameworker;

import com.endava.internship.vcontu.frameworker.domain.context.ApplicationContext;
import com.endava.internship.vcontu.frameworker.domain.context.AutomaticApplicationContext;
import com.endava.internship.vcontu.frameworker.domain.context.ManualApplicationContext;
import com.endava.internship.vcontu.frameworker.domain.editing.PunctuationEditor;
import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;
import com.endava.internship.vcontu.frameworker.domain.news.LocalNewsMaker;
import com.endava.internship.vcontu.frameworker.domain.news.NewsMaker;
import com.endava.internship.vcontu.frameworker.domain.publishing.ConsolePublisher;
import com.endava.internship.vcontu.frameworker.domain.translation.EnglishToRomanianTranslator;

public class Main {

    public static final String GOOD_NEWS = "good news";
    public static final String HELLO_NEWS = "Hello";
    public static final String BILL_WOODS_AUTHOR = "Bill Woods";
    public static final String JOHN_DOE_AUTHOR = "John Doe";
    public static final String ORIGINAL_STORY_GREETING = "Original story (english): ";

    public static void main(String[] args) {

        final NewsMaker romanianNewsMaker = buildRomanianNewsMaker();
        final OriginalStory originalStory1 = new OriginalStory(GOOD_NEWS, BILL_WOODS_AUTHOR);
        greeting(originalStory1);
        romanianNewsMaker.processStory(originalStory1);

        System.out.println("------------\n");

        final NewsMaker russianNewsMaker = buildRussianNewsMaker();
        final OriginalStory originalStory2 = new OriginalStory(HELLO_NEWS, JOHN_DOE_AUTHOR);
        greeting(originalStory2);
        russianNewsMaker.processStory(originalStory2);
    }

    //Set-up the english to romanian newsmaker
    private static NewsMaker buildRomanianNewsMaker() {
        ApplicationContext englishContext = new AutomaticApplicationContext(
                EnglishToRomanianTranslator.class,
                PunctuationEditor.class,
                ConsolePublisher.class,
                LocalNewsMaker.class);

        return englishContext.getBean(LocalNewsMaker.class);
    }

    //Set-up the english to russian newsmaker
    private static NewsMaker buildRussianNewsMaker() {
        ApplicationContext russianContext = new ManualApplicationContext();
        return russianContext.getBean(LocalNewsMaker.class);
    }

    private static void greeting(final OriginalStory story) {
        System.out.println(ORIGINAL_STORY_GREETING + story.getOriginalMessage());
    }
}
