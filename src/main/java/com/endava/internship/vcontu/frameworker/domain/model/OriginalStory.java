package com.endava.internship.vcontu.frameworker.domain.model;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class OriginalStory {
    private String originalMessage;
    private String author;

    public OriginalStory(String originalMessage, String author) {
        this.originalMessage = originalMessage;
        this.author = author;
        if (Objects.isNull(originalMessage) || originalMessage.length() <= 3) {
            throw new IllegalArgumentException("This is not a valid story");
        }
        this.originalMessage = originalMessage;

        requireNonNull(author);
        this.author = author;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public String getAuthor() {
        return author;
    }
}
