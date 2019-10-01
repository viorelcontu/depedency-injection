package com.endava.internship.vcontu.frameworker;

import com.endava.internship.vcontu.frameworker.domain.NewsPublisher;
import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;

public class Main {
    public static void main(String[] args) {
        NewsPublisher newsPublisher = new NewsPublisher();

        OriginalStory originalStory1 = new OriginalStory("good news", "Bill woods");

        System.out.println("publishing original English News:\n" + originalStory1.getOriginalMessage());
        newsPublisher.processStory(originalStory1);

        System.out.println("------------\n");

        OriginalStory originalStory2 = new OriginalStory("Hello", "John Doe");
        System.out.println("publishing original English News:\n" + originalStory2.getOriginalMessage());
        newsPublisher.processStory(originalStory2);
    }
}
