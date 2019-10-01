package com.endava.internship.vcontu.frameworker.domain;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;

public class NewsPublisher {

    private final Translator translator;
    private final Editor editor;
    private final Publisher publisher;

    public NewsPublisher() {
        this.translator = new Translator();
        this.editor = new Editor();
        this.publisher = new Publisher();
    }

    public void processStory(final OriginalStory originalStory) {
        final String translatedStory = translator.translate(originalStory);
        final String editedStory = editor.editStory(translatedStory);
        publisher.publish(originalStory, editedStory);

    }
}
