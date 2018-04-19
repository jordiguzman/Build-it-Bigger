package appkite.jordiguzman.com.libjokes;

import java.util.Random;

public class Jokes {

    private String[] jokes;
    private Random random;

    public Jokes() {

        jokes = new String[4];
        jokes[0] = "Thanks to autocorrect, 1 in 5 children will be getting a visit from Satan this Christmas.";
        jokes[1] = "What does a king computer do?\n Execute his programs!";
        jokes[2] = "I just got fired from my job at the keyboard factory. They told me I wasn\'t putting in enough shifts.";
        jokes[3] = "I saw a driver texting and driving.\nIt made me so mad I threw my beer at him.";
        random = new Random();
    }

    public String getRandomJoke() {
        return jokes[random.nextInt(jokes.length)];
    }
}
