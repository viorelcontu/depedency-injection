package com.endava.internship.vcontu.frameworker.domain.news;

import com.endava.internship.vcontu.frameworker.domain.editing.Editor;
import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;
import com.endava.internship.vcontu.frameworker.domain.publishing.Publisher;
import com.endava.internship.vcontu.frameworker.domain.translation.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalNewsMaker implements NewsMaker {

    private final Translator translator;
    private final Editor editor;
    private final Publisher publisher;

    @Autowired
    public LocalNewsMaker(Translator translator, Editor editor, Publisher publisher) {
        this.translator = translator;
        this.editor = editor;
        this.publisher = publisher;
    }

    @Override
    public void processStory(final OriginalStory originalStory) {
        final String translatedStory = translator.translate(originalStory);
        final String editedStory = editor.editStory(translatedStory);
        publisher.publish(originalStory, editedStory);
    }
}
