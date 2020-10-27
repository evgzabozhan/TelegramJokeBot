package joke;

import config.ApplicationProperties;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class ImageJoke {

    public String getImageJoke(String text) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/?text=" + text + "&fcolor=FFFFFF&bcolor=000000&font=comic&size=13&type=png")
                .get()
                .addHeader("x-rapidapi-host", "img4me.p.rapidapi.com")
                .addHeader("x-rapidapi-key", ApplicationProperties.getApiKey())
                .build();

        Response response = client.newCall(request).execute();

        String url = Objects.requireNonNull(response.body()).string();

        return url;
    }

}
