package telegram;

import config.ApplicationProperties;
import joke.ImageJoke;
import joke.Joke;
import joke.JokeApi;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelegramApi extends TelegramLongPollingBot {

    private InlineKeyboardMarkup changeMessageText(Joke joke){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton upvote = new InlineKeyboardButton();
        InlineKeyboardButton downvote = new InlineKeyboardButton();

        upvote.setText(String.valueOf(joke.getUpvotes()) + '\uD83D' + '\uDC4D');
        upvote.setCallbackData("/upv " + joke.getId());
        downvote.setText(String.valueOf(joke.getDownvotes()) + '\uD83D' + '\uDC4E');
        downvote.setCallbackData("/dwn " + joke.getId());

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

        keyboardButtonsRow1.add(upvote);
        keyboardButtonsRow1.add(downvote);
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(keyboardButtonsRow1);
        inlineKeyboardMarkup.setKeyboard(buttons);

        return inlineKeyboardMarkup;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Joke joke = null;

        if (update.hasCallbackQuery()) {
            try {
                execute(jokeVote(update));
            } catch (TelegramApiException | IOException e) {
                e.printStackTrace();
            }
        } else if (update.getMessage().getText().toLowerCase().contains("show me")) {
            String messageText = update.getMessage().getText();
            int value = Integer.parseInt(messageText.replaceAll("[a-zA-Z ]+", ""));
            for (int i = 0; i < value; i++) {
                try {
                    joke = new JokeApi().getRandomJoke();

                    execute(new SendMessage().setChatId(update.getMessage().getChatId())
                            .setText(joke.getContent() + "\n" + new ImageJoke().getImageJoke(joke.getContent()))
                            .setReplyMarkup(changeMessageText(joke)));

                } catch (TelegramApiException | IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasMessage()) {
            try {
                joke = new JokeApi().getRandomJoke();
                assert joke != null;
                execute(new SendMessage().setChatId(update.getMessage().getChatId())
                        .setText(joke.getContent() + "\n" + new ImageJoke().getImageJoke(joke.getContent()))
                        .setReplyMarkup(changeMessageText(joke)));
            } catch (TelegramApiException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private EditMessageText jokeVote(Update update) throws IOException {
        JokeApi api = new JokeApi();
        String callBackData = update.getCallbackQuery().getData();
        String[] values = callBackData.split(" ");
        try {
            if (values[0].equals("/upv")) {
               api.upVoteJoke(values[1]);
            } else if (values[0].equals("/dwn")) {
                api.downVoteJoke(values[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new EditMessageText().setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                .setReplyMarkup(changeMessageText(api.getJokeById(values[1])))
                .setText(update.getCallbackQuery().getMessage().getText());
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
