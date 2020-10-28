import config.ApplicationProperties;
import org.apache.commons.configuration.ConfigurationException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.TelegramApi;

public class Application {
    public static void main(String[] args) throws ConfigurationException {
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.setProperties();

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new TelegramApi());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
