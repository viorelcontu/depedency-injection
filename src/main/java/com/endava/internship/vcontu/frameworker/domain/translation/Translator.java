package com.endava.internship.vcontu.frameworker.domain.translation;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;

public interface Translator {

    String translate (final OriginalStory story);
    String introductionMessage (final String author);
}
