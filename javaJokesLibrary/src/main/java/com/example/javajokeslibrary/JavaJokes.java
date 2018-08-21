package com.example.javajokeslibrary;

/* Jokes from
 * https://www.journaldev.com/240/my-25-favorite-programming-quotes-that-are-funny-too
 * https://howtodoinjava.com/for-fun-only/some-java-programmers-jokes/
 */
public class JavaJokes {

    private String[] jokes = {
            "The best thing about a boolean is even if you are wrong, you are only off by a bit. ",
            "Without requirements or design, programming is the art of adding bugs to an empty text file. (Louis Srygley)",
            "Whats the object-oriented way to become wealthy? Inheritence.",
            "If Java is the answer, it must have been a really verbose question.",
            "To understand what recursion is, you must first understand recursion.",
            "Q: How many programmers does it take to screw in a light bulb?\nA: None. It's a hardware problem.",
            "A programmer puts two glasses on his bedside table before going to sleep. A full one, in case he gets thirsty, and an empty one, in case he doesn’t."
    };

    public String getRandomJoke() {
        int index = (int) Math.floor(jokes.length * Math.random());

        return jokes[index];
    }
}
