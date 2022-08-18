package uz.akbarali.foodappjavav8.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.akbarali.foodappjavav8.bot.service.ButtonService;
@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    String botUsername;


    @Value("${telegram.bot.token}")
    String botToken;

    @Autowired
    ButtonService buttonService;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        final String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("select menu");
        sendMessage.setReplyMarkup(buttonService.buttons());
        sendMessage.setChatId(chatId);
        System.out.println(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
