import joke.JokeApi;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        JokeApi joke = new JokeApi();
        System.out.println(joke.getRandomJoke());
    }
}
