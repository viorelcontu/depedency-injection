package com.endava.internship.vcontu.frameworker;

public class Main {
    public static void main(String[] args) {
        String originalStory1 = "good news";
        System.out.println("publishing original English News: " + originalStory1 );
        NewsPublisher goodNews = new NewsPublisher(originalStory1, "Bill Woods");
        goodNews.publishNews();

        System.out.println("------------\n");

        String originalStory2 = "Hello";
        System.out.println("publishing original English News: " + originalStory2 );
        NewsPublisher helloNews = new NewsPublisher(originalStory2, "John Doe");
        helloNews.publishNews();
    }
}
