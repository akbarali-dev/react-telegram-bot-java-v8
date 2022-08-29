package uz.akbarali.foodappjavav8.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.akbarali.foodappjavav8.bot.dto.UserActivityDto;
import static uz.akbarali.foodappjavav8.bot.util.MessageText.*;
@Service
public class UserService {

    final
    ButtonService buttonService;

    public UserService(ButtonService buttonService) {
        this.buttonService = buttonService;
    }

    public void main(Update update, SendMessage sendMessage, UserActivityDto userActivityDto) {
        if (update.hasMessage()) {
            mainMessage(update, sendMessage, userActivityDto);
        } else if (update.hasCallbackQuery()) {

        }
    }

    private void mainMessage(Update update, SendMessage sendMessage, UserActivityDto userActivityDto) {
        Message message = update.getMessage();
        if (message.hasText()) {
            mainText(update, sendMessage, userActivityDto);
        }
    }

    private void mainText(Update update, SendMessage sendMessage, UserActivityDto userActivityDto) {
        final Long chatId = update.getMessage().getChatId();
        final String text = update.getMessage().getText();
        sendMessage.setChatId(chatId.toString());
        if (text.equals("/start")) {
            mainPage(sendMessage, userActivityDto);
            return;
        }
        switch (text) {
            case menuUz:
                sendMessage.setText(menuUz + " hali ish jarayonida");
                break;
            case informationUz:
                sendMessage.setText(informationUz + " hali ish jarayonida");
                break;
            case contactUz:
                sendMessage.setText(contactUz + " hali ish jarayonida");
                break;
            case languageUz:
            case languageRu:
                sendMessage.setText(languageRu + " hali ish jarayonida");
                break;
        }
    }

    private void mainPage(SendMessage sendMessage, UserActivityDto userActivityDto) {
        sendMessage.setReplyMarkup(buttonService.buttons(userActivityDto));
        sendMessage.setText("Bo'limlardan birini tanlang");
//        label:
//        {
//            if (userActivityDto == null) {
//                return;
//            } else {
//                break label;
//            }
//        }

    }
}
