package joke;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class JokeApi {

    private static final String apiKey = "";

    public Joke getRandomJoke() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://joke3.p.rapidapi.com/v1/joke")
                .get()
                .addHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .build();

        Response response = client.newCall(request).execute();

        return getJokeFromJson(Objects.requireNonNull(response.body()).string());
    }

    public Joke getJokeById(String id) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://joke3.p.rapidapi.com/v1/joke/" + id)
                .get()
                .addHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .build();

        Response response = client.newCall(request).execute();

        return getJokeFromJson(Objects.requireNonNull(response.body()).string());
    }

    public void upVoteJoke(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://joke3.p.rapidapi.com/v1/joke/" + id + "/upvote")
                .post(null)
                .addHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
    }

    public void downVoteJoke(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://joke3.p.rapidapi.com/v1/joke/" + id + "/downvote")
                .post(null)
                .addHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
    }

    private Joke getJokeFromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, Joke.class);
    }
}
