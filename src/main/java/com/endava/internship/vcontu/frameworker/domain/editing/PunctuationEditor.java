package com.endava.internship.vcontu.frameworker.domain.editing;

public class PunctuationEditor implements Editor {

    public String editStory(final String message) {

        StringBuilder builder = new StringBuilder(message);
        if (builder.indexOf("bune") >= 0) {
            builder.append("!");
        } else if (builder.indexOf("success") >= 0) {
            builder.append("...");
        } else {
            builder.append(".");
        }
        return builder.toString();
    }
}
