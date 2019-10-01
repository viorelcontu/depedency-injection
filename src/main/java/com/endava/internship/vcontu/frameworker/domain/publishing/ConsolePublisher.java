package com.endava.internship.vcontu.frameworker.domain.publishing;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;
import com.endava.internship.vcontu.frameworker.domain.translation.Translator;

public class ConsolePublisher implements Publisher {

    private final Translator translator;

    public ConsolePublisher(Translator translator) {
        this.translator = translator;
    }

    public void publish (final OriginalStory story, final String translatedStory) {
        publishToConsole(story, translatedStory);
    }

    private void publishToConsole(final OriginalStory story, final String translatedStory) {
        System.out.println(translator.introductionMessage(story.getAuthor()));
        System.out.println(translatedStory);
    }
}
