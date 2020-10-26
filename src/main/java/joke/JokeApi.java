package joke;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class JokeApi {
    
    private static final String apiKey = "";

    public String getRandomJoke() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://joke3.p.rapidapi.com/v1/joke")
                .get()
                .addHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public String getJokeById(String id) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://joke3.p.rapidapi.com/v1/joke/" + id)
                .get()
                .addHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .build();

        Response response = client.newCall(request).execute();


        return response.body().string();
    }

    public void upVoteJoke(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://joke3.p.rapidapi.com/v1/joke/%7Bid%7D/upvote")
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
                .url("https://joke3.p.rapidapi.com/v1/joke/%7Bid%7D/downvote")
                .post(null)
                .addHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
    }

    private Joke getJokeFromJson(String json){

        return new Joke("1","2",3,4);
    }
}
