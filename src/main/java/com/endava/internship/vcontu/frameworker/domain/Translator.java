package com.endava.internship.vcontu.frameworker.domain;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;

public class Translator {

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
}
