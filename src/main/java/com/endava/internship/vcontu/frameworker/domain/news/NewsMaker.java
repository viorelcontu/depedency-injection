package com.endava.internship.vcontu.frameworker.domain.news;

import com.endava.internship.vcontu.frameworker.domain.model.OriginalStory;

public interface NewsMaker {
    void processStory(OriginalStory originalStory);
}
