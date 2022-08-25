package uz.akbarali.foodappjavav8.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import uz.akbarali.foodappjavav8.bot.dto.UserActivityDto;

import java.util.ArrayList;
import java.util.List;

import static uz.akbarali.foodappjavav8.bot.util.MessageText.*;

@Service
public class ButtonService {
    public ReplyKeyboard buttons(UserActivityDto userActivityDto) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardMarkup.setKeyboard(keyboardRows);
        KeyboardRow row = new KeyboardRow();

        switch (userActivityDto.getRound()) {
            case 0:
                row = new KeyboardRow();
                String orderId = "fssdf";
                WebAppInfo webAppInfo = new WebAppInfo("https://food-react-bot.herokuapp.com/");
//                WebAppInfo webAppInfo = new WebAppInfo("https://food-react-app-bot.herokuapp.com/order?orderId=" + orderId);
                KeyboardButton btn = new KeyboardButton();
                btn.setWebApp(webAppInfo);
                btn.setText(menuNewUz);
                row.add(btn);
                keyboardRows.add(row);
                row = new KeyboardRow();
                row.add(menuUz);
                row.add(informationUz);
                keyboardRows.add(row);
                row = new KeyboardRow();
                row.add(contactUz);
                row.add(languageUz);
                keyboardRows.add(row);
                break;
        }
        return keyboardMarkup;
    }
}
