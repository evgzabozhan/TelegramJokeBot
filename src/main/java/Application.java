import joke.JokeApi;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.TelegramApi;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        JokeApi joke = new JokeApi();
        System.out.println(joke.getRandomJoke().getContent());

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new TelegramApi());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
