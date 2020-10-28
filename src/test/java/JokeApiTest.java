import config.ApplicationProperties;
import joke.ImageJoke;
import joke.Joke;
import joke.JokeApi;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

public class JokeApiTest {

    @Test
    void getRandomJokeTest() throws IOException, ConfigurationException {
        new ApplicationProperties().setProperties();
        JokeApi jokeApi = new JokeApi();
        var obj = jokeApi.getRandomJoke();
        assertNotNull(obj);
        assertEquals(Joke.class, obj.getClass());
    }

    @Test
    void getJokeByIdTest() throws ConfigurationException, IOException {
        new ApplicationProperties().setProperties();
        JokeApi jokeApi = new JokeApi();
        var obj = jokeApi.getJokeById("b2614048d4464a27bda0d4dd67ae4e30");
        assertNotNull(obj);
        assertEquals(Joke.class, obj.getClass());
    }

    @Test
    void getImageJokeTest() throws ConfigurationException, IOException {
        new ApplicationProperties().setProperties();
        ImageJoke image = new ImageJoke();
        var url = image.getImageJoke("Test image joke");
        assertNotNull(url);
        assertEquals(String.class, url.getClass());
    }

}
