package com.endava.internship.vcontu.frameworker.domain;

import com.endava.internship.vcontu.frameworker.domain.editing.Editor;
import com.endava.internship.vcontu.frameworker.domain.editing.PunctuationEditor;
import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;
import com.endava.internship.vcontu.frameworker.domain.publishing.ConsolePublisher;
import com.endava.internship.vcontu.frameworker.domain.publishing.Publisher;
import com.endava.internship.vcontu.frameworker.domain.translation.EnglishToRomanianTranslator;
import com.endava.internship.vcontu.frameworker.domain.translation.Translator;

public class NewsPublisher {

    private final Translator translator;
    private final Editor editor;
    private final Publisher publisher;

    public NewsPublisher() {
        this.translator = new EnglishToRomanianTranslator(); //We are still somewhat coupled with implementations!
        this.editor = new PunctuationEditor();
        this.publisher = new ConsolePublisher();
    }

    public void processStory(final OriginalStory originalStory) {
        final String translatedStory = translator.translate(originalStory);
        final String editedStory = editor.editStory(translatedStory);
        publisher.publish(originalStory, editedStory);

    }
}
