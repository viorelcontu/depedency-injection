package com.endava.internship.vcontu.frameworker.domain.translation;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;
import org.springframework.stereotype.Component;

@Component
public class EnglishToRussianTranslator implements Translator {

    public String translate(final OriginalStory story) {
        final String originalMessage = story.getOriginalMessage();

        switch (originalMessage.toLowerCase()) {
            case "hello":
                return "Добрый день";
            case "good news":
                return "Хорошие новости";
            case "bad news":
                return "Новости еще по лучше";
            default:
                return "Молдавия - история успеха";
        }
    }

    @Override
    public String introductionMessage(String author) {
        return "Новая новость для вас от автора: " + author;
    }
}
