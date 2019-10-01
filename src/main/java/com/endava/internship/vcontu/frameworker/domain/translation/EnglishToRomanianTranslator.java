package com.endava.internship.vcontu.frameworker.domain.translation;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;

public class EnglishToRomanianTranslator implements Translator {

    public String translate(final OriginalStory story) {
        final String originalMessage = story.getOriginalMessage();

        switch (originalMessage.toLowerCase()) {
            case "hello":
                return "Buna ziua";
            case "good news":
                return "Vesti bune";
            case "bad news":
                return "Vesti si mai bune";
            default:
                return "Moldova - istorie de succes";
        }
    }

    @Override
    public String introductionMessage(String author) {
        return "Avem o noutate noua pentru dumneavoastra de la autorul: " + author;
    }
}
