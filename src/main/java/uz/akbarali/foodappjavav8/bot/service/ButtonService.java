package uz.akbarali.foodappjavav8.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import uz.akbarali.foodappjavav8.bot.dto.UserActivityDto;
import uz.akbarali.foodappjavav8.common.GetAllData;
import uz.akbarali.foodappjavav8.projection.CategoryProductBotProjection;
import uz.akbarali.foodappjavav8.projection.ProductCardProjection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static uz.akbarali.foodappjavav8.bot.util.MessageText.*;

@Service
public class ButtonService {
    final
    GetAllData getAllData;

    public ButtonService(GetAllData getAllData) {
        this.getAllData = getAllData;
    }

    public ReplyKeyboard cardButtons(List<CategoryProductBotProjection> allCard) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineBtns = new ArrayList<>();
        List<InlineKeyboardButton> inlineBtnsRow1 = new ArrayList<>();
//        inlineBtns.add(inlineBtnsRow1);
        InlineKeyboardButton button = new InlineKeyboardButton();
        for (CategoryProductBotProjection category : allCard) {
            for (ProductCardProjection food : category.getFoods()) {
                button = new InlineKeyboardButton();
                button.setText("➕");
                button.setCallbackData("+"+food.getCardId());
                inlineBtnsRow1.add(button);
                button=new InlineKeyboardButton();
                button.setText(food.getName());
                button.setCallbackData("empty");
                inlineBtnsRow1.add(button);
                button=new InlineKeyboardButton();
                button.setText("➖");
                button.setCallbackData("-"+food.getCardId());
                inlineBtnsRow1.add(button);
                inlineBtns.add(inlineBtnsRow1);
                inlineBtnsRow1 = new ArrayList<>();
            }
        }
        inlineBtnsRow1 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("✅ Buyurtmani tasdiqlash");
        button.setCallbackData("success");
        inlineBtnsRow1.add(button);
        inlineBtns.add(inlineBtnsRow1);
        inlineBtnsRow1 = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("\uD83D\uDCE5Yana buyurma berish");
        button.setCallbackData("add");
        inlineBtnsRow1.add(button);
        inlineBtns.add(inlineBtnsRow1);
        inlineKeyboardMarkup.setKeyboard(inlineBtns);
        return inlineKeyboardMarkup;

    }
    public ReplyKeyboard photoInlineButtons(UUID productId, int count, boolean addedCard) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineBtns = new ArrayList<>();
        List<InlineKeyboardButton> inlineBtnsRow1 = new ArrayList<>();
        inlineBtns.add(inlineBtnsRow1);
        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText("-");
        button.setCallbackData("-" + productId);
        inlineBtnsRow1.add(button);

        button = new InlineKeyboardButton();
        button.setText(String.valueOf(count));
        button.setCallbackData("empty");
        inlineBtnsRow1.add(button);

        button = new InlineKeyboardButton();
        button.setText("+");
        button.setCallbackData("+" + productId);
        inlineBtnsRow1.add(button);

        button = new InlineKeyboardButton();
        if (addedCard) {
            button.setText("\uD83D\uDCE5 Savatga qo'shish");
            button.setCallbackData("addCard" + productId);
        } else {
            button.setText("❌ Savatdan olish");
            button.setCallbackData("remove" + productId);
        }
        inlineBtnsRow1 = new ArrayList<>();
        inlineBtnsRow1.add(button);
        inlineBtns.add(inlineBtnsRow1);

        inlineKeyboardMarkup.setKeyboard(inlineBtns);
        return inlineKeyboardMarkup;
    }

    public ReplyKeyboard categoryButtons(Set<String> categories) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
//        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardMarkup.setKeyboard(keyboardRows);
        KeyboardRow row = new KeyboardRow();
        row.add(myCardUz);
        keyboardRows.add(row);
        row = new KeyboardRow();
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
                String orderId = "12345";
//                WebAppInfo webAppInfo = new WebAppInfo("https://food-react-bot.herokuapp.com/");
                WebAppInfo webAppInfo = new WebAppInfo("https://food-react-app-bot.herokuapp.com/order?orderId=" + orderId);
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
        keyboardMarkup.setSelective(true);
//        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardMarkup.setKeyboard(keyboardRows);
        KeyboardRow row = new KeyboardRow();
        row.add(myCardUz);
        keyboardRows.add(row);
        row = new KeyboardRow();
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
