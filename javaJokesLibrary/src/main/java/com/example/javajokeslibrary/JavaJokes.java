package com.example.javajokeslibrary;

public class JavaJokes {

    private String[] jokes = {
            "Joke #1", "Joke #2", "Joke #3"
    };

    public String getRandomJoke() {
        int index = (int) Math.floor(jokes.length * Math.random());

        return jokes[index];
    }
}
