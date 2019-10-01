package com.endava.internship.vcontu.frameworker.domain.publishing;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;

public interface Publisher {

    void publish (final OriginalStory story, final String translatedStory);
}
