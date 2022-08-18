package uz.akbarali.foodappjavav8.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;
import java.util.List;
@Service
public class ButtonService {
    public ReplyKeyboard buttons() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardMarkup.setKeyboard(keyboardRows);
        KeyboardRow row = new KeyboardRow();

        KeyboardButton btn = new KeyboardButton();
        WebAppInfo webAppInfo = new WebAppInfo("https://food-react-app-bot.herokuapp.com/");
        btn.setWebApp(webAppInfo);
        btn.setText("foods");
        row.add(btn);

        KeyboardButton btn2 = new KeyboardButton();
        btn2.setWebApp(new WebAppInfo("https://food-react-app-java.herokuapp.com/api/v1/category-test"));
        btn2.setText("test");
        row.add(btn2);

        keyboardRows.add(row);
        return keyboardMarkup;
    }
}
