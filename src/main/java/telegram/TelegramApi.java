package telegram;

import config.ApplicationProperties;
import joke.ImageJoke;
import joke.Joke;
import joke.JokeApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelegramApi extends TelegramLongPollingBot {

    private SendMessage sendJoke(Joke joke, Long chatId) throws IOException {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton upvote = new InlineKeyboardButton();
        InlineKeyboardButton downvote = new InlineKeyboardButton();

        upvote.setText(String.valueOf(joke.getUpvotes()));
        upvote.setCallbackData(String.valueOf(joke.getUpvotes() + 1));
        downvote.setText(String.valueOf(joke.getDownvotes()));
        downvote.setCallbackData(String.valueOf(joke.getDownvotes() + 1));

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(upvote);
        keyboardButtonsRow1.add(downvote);
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(keyboardButtonsRow1);
        buttons.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(buttons);
        return new SendMessage().setChatId(chatId)
                .setText(joke.getContent() + "\n" + new ImageJoke().getImageJoke(joke.getContent()))
                .setReplyMarkup(inlineKeyboardMarkup);
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        Joke joke = null;
        try {
            joke = new JokeApi().getRandomJoke();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert joke != null;
            execute(sendJoke(joke, update.getMessage().getChatId()));
            } catch (TelegramApiException | IOException e) {
                e.printStackTrace();
            }

    }

    @Override
    public String getBotUsername() {
        return ApplicationProperties.getTelegramBotName();
    }

    @Override
    public String getBotToken() {
        return ApplicationProperties.getTelegramBotToken();
    }
}
