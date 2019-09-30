package com.endava.internship.vcontu.frameworker;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class NewsPublisher {
    private String originalMessage;
    private String author;

    public NewsPublisher(String originalMessage, String author) {
        if (Objects.isNull(originalMessage) || originalMessage.length() <= 3) {
            throw new IllegalArgumentException("This is not a valid story");
        }
        this.originalMessage = originalMessage;

        requireNonNull(author);
        this.author = author;
    }

    public void publishNews() {
        final String translatedNews = translate(originalMessage);
        final String editedNews = edit(translatedNews);
        publishToConsole(editedNews);
    }

    private String translate(String message) {
        //translation logic
        switch (message.toLowerCase()) {
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

    private String edit(String message) {
        //editor takes the translated text and makes a few changes
        StringBuilder story = new StringBuilder(message);
        if (story.indexOf("bune") >= 0) {
            story.append("!");
        } else if (story.indexOf("success") >= 0) {
            story.append("...");
        } else {
            story.append(".");
        }
        return story.toString();
    }

    private void publishToConsole(String story) {
        //takes the story and publishes it
        System.out.println("Here is a new story for you by " + author + ":");
        System.out.println(story);
    }
}
