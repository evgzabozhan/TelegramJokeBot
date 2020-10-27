package joke;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.ApplicationProperties;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class JokeApi {

    private static final String apiKey = ApplicationProperties.getApiKey();

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

    public String upVoteJoke(String id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://joke3.p.rapidapi.com/v1/joke/" + id + "/upvote")
                .method("POST", body)
                .addHeader("x-rapidapi-key", apiKey)
                .build();
        Response response = client.newCall(request).execute();

        return "upvoted!";
    }

    public String downVoteJoke(String id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://joke3.p.rapidapi.com/v1/joke/" + id + "/downvote")
                .method("POST", body)
                .addHeader("x-rapidapi-key", apiKey)
                .build();
        Response response = client.newCall(request).execute();

        return "downvoted!";
    }

    private Joke getJokeFromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, Joke.class);
    }
}
