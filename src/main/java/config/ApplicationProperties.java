package config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ApplicationProperties {
    private static String apiKey;
    private static String telegramBotName;
    private static String telegramBotToken;

    public static String getApiKey() {
        return apiKey;
    }

    public static String getTelegramBotName() {
        return telegramBotName;
    }

    public static String getTelegramBotToken() {
        return telegramBotToken;
    }

    public void setProperties() throws ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration();
        config.load("application.properties");
        apiKey = config.getString("ApiKey");
        telegramBotName = config.getString("TelegramBotName");
        telegramBotToken = config.getString("TelegramBotToken");
    }
}
