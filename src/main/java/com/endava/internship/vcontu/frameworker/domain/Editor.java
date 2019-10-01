package com.endava.internship.vcontu.frameworker.domain;

public class Editor {

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
