package uz.akbarali.foodappjavav8.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import uz.akbarali.foodappjavav8.bot.dto.UserActivityDto;
import uz.akbarali.foodappjavav8.common.GetAllData;
import uz.akbarali.foodappjavav8.projection.CategoryProjection;
import uz.akbarali.foodappjavav8.projection.ProductBotProjection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static uz.akbarali.foodappjavav8.bot.util.MessageText.*;

@Service
public class ButtonService {
    final
    GetAllData getAllData;

    public ButtonService(GetAllData getAllData) {
        this.getAllData = getAllData;
    }

    public ReplyKeyboard categoryButtons(Set<String> categories) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardMarkup.setKeyboard(keyboardRows);
        KeyboardRow row = new KeyboardRow();

        int i = 0;
        for (String s : categories) {
            if (i != 0 && i % 2 == 0) {
                keyboardRows.add(row);
                row = new KeyboardRow();
            }
            i++;
            row.add(s);
        }
//        for (int i = 0; i < allCategory.size(); i++) {
//            if (i % 2 == 0) {
//                keyboardRows.add(row);
//                row = new KeyboardRow();
//            }
//            row.add(categories.get(i).getName());
//        }
        if (categories.size() % 2 == 1)
            keyboardRows.add(row);
        row = new KeyboardRow();
        row.add(mainUz);
        keyboardRows.add(row);
        return keyboardMarkup;
    }

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

    public ReplyKeyboard productButton(List<String> products) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardMarkup.setKeyboard(keyboardRows);
        KeyboardRow row = new KeyboardRow();

        for (int i = 0; i < products.size(); i++) {
            if (i != 0 && i % 2 == 0) {
                keyboardRows.add(row);
                row = new KeyboardRow();
            }
            row.add(products.get(i));
        }
//        if (products.size() % 2 == 1)
        keyboardRows.add(row);
        mainAndBack(keyboardRows, row);
        return keyboardMarkup;
    }

    private void mainAndBack(List<KeyboardRow> keyboardRows, KeyboardRow row) {
        row = new KeyboardRow();
        row.add(mainUz);
        row.add(backUz);
        keyboardRows.add(row);
    }
}
