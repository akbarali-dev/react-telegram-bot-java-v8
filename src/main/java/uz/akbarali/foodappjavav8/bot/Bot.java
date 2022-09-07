package uz.akbarali.foodappjavav8.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.akbarali.foodappjavav8.bot.service.ButtonService;
import uz.akbarali.foodappjavav8.bot.service.UserManageService;

@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${telegram.bot.username}")
    String botUsername;


    @Value("${telegram.bot.token}")
    String botToken;

    final
    ButtonService buttonService;
    final UserManageService userManageService;

    public Bot(ButtonService buttonService, UserManageService userManageService) {
        this.buttonService = buttonService;
        this.userManageService = userManageService;
    }


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
        SendMessage sendMessage = new SendMessage();
        SendPhoto sendPhoto = new SendPhoto();
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        EditMessageCaption editMessageCaption = new EditMessageCaption();
        DeleteMessage deleteMessage = new DeleteMessage();
        userManageService.manage(update, sendMessage, null,
                deleteMessage, null,
                sendPhoto, null, editMessageReplyMarkup, editMessageCaption);
        try {
            if (sendMessage.getChatId() != null && !sendMessage.getChatId().equals("0")) {
                execute(sendMessage);
            }
            if (sendPhoto.getChatId()!=null && !sendPhoto.getChatId().equals("0"))
                execute(sendPhoto);
            if (editMessageReplyMarkup.getChatId() != null && !editMessageReplyMarkup.getChatId().equals("0")) {
                execute(editMessageReplyMarkup);
            }
            if (editMessageCaption.getChatId()!=null && !editMessageCaption.getChatId().equals("0"))
                execute(editMessageCaption);
            if (deleteMessage.getMessageId()!=null && !deleteMessage.getChatId().equals("0"))
                execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

//        final String chatId = String.valueOf(update.getMessage().getChatId());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setText("select menu");
//        sendMessage.setReplyMarkup(buttonService.buttons());
//        sendMessage.setChatId(chatId);
//        System.out.println(chatId);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
    }
}
