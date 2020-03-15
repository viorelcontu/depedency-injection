package com.endava.internship.vcontu.frameworker.domain.publishing;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;
import com.endava.internship.vcontu.frameworker.domain.translation.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class ConsolePublisher implements Publisher {

    private Translator translator;

    @Autowired
    public ConsolePublisher(Translator translator) {
        this.translator = translator;
    }

    public void publish (final OriginalStory story, final String translatedStory) {
        publishToConsole(story, translatedStory);
    }

    private void publishToConsole(final OriginalStory story, final String translatedStory) {
        final String introductoryMessage = ofNullable(translator)
                .map(t -> t.introductionMessage(story.getAuthor()))
                .orElse("");

        System.out.println(introductoryMessage);
        System.out.println(translatedStory);
    }
}
