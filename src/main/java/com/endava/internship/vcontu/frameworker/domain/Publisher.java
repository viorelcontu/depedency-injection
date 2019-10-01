package com.endava.internship.vcontu.frameworker.domain;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;

public class Publisher {

    public void publish (final OriginalStory story, final String translatedStory) {
        publishToConsole(story, translatedStory);
    }

    private void publishToConsole(final OriginalStory story, final String translatedStory) {
        System.out.println("Here is a new story for you by " + story.getAuthor() + ":");
        System.out.println(translatedStory);
    }
}
