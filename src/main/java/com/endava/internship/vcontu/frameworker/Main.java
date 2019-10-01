package com.endava.internship.vcontu.frameworker;

import com.endava.internship.vcontu.frameworker.domain.NewsPublisher;
import com.endava.internship.vcontu.frameworker.domain.editing.PunctuationEditor;
import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;
import com.endava.internship.vcontu.frameworker.domain.publishing.ConsolePublisher;
import com.endava.internship.vcontu.frameworker.domain.translation.EnglishToRomanianTranslator;
import com.endava.internship.vcontu.frameworker.domain.translation.EnglishToRussianTranslator;

public class Main {

    public static final String GOOD_NEWS = "good news";
    public static final String HELLO = "Hello";
    public static final String BILL_WOODS_AUTHOR = "Bill Woods";
    public static final String JOHN_DOE_AUTHOR = "John Doe";
    public static final String ORIGINAL_STORY_GREETING = "publishing original English News:\n";

    public static void main(String[] args) {
        //Set-up the first english to romanian publisher
        NewsPublisher standardPublisher = new NewsPublisher(
            new EnglishToRomanianTranslator(),
            new PunctuationEditor(),
            new ConsolePublisher());

        NewsPublisher alternativePublisher = new NewsPublisher(
            new EnglishToRussianTranslator(),
            new PunctuationEditor(),
            new ConsolePublisher());

        //Story 1 published twice: EN-RO, EN-RU
        OriginalStory originalStory1 = new OriginalStory(GOOD_NEWS, BILL_WOODS_AUTHOR);
        greeting(originalStory1);
        standardPublisher.processStory(originalStory1);
        alternativePublisher.processStory(originalStory1);

        System.out.println("------------\n");

        //Story 2 published twice: EN-RO, EN-RU
        OriginalStory originalStory2 = new OriginalStory(HELLO, JOHN_DOE_AUTHOR);
        greeting(originalStory2);
        standardPublisher.processStory(originalStory2);
        alternativePublisher.processStory(originalStory2);
    }

    private static void greeting (final OriginalStory story) {
        System.out.println(ORIGINAL_STORY_GREETING + story.getOriginalMessage());
    }
}
