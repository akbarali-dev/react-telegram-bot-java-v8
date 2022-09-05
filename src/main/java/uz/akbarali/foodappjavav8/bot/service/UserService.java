package uz.akbarali.foodappjavav8.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import uz.akbarali.foodappjavav8.bot.dto.UserActivityDto;
import uz.akbarali.foodappjavav8.common.GetAllData;
import uz.akbarali.foodappjavav8.projection.ProductCategoryBotProjection;
import uz.akbarali.foodappjavav8.repository.CardRepository;
import uz.akbarali.foodappjavav8.repository.CategoryRepository;
import uz.akbarali.foodappjavav8.repository.ProductRepository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static uz.akbarali.foodappjavav8.bot.util.MessageText.*;

@Service
public class UserService {
    final
    GetAllData getAllData;

    final
    ButtonService buttonService;
    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;

    final
    CardRepository cardRepository;

    public UserService(ButtonService buttonService, CategoryRepository categoryRepository,
                       ProductRepository productRepository,
                       GetAllData getAllData, CardRepository cardRepository) {
        this.buttonService = buttonService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.getAllData = getAllData;
        this.cardRepository = cardRepository;
    }

    public void main(Update update, SendMessage sendMessage, UserActivityDto userActivityDto, SendPhoto sendPhoto, EditMessageReplyMarkup editMessageReplyMarkup, EditMessageCaption editMessageCaption) {
        if (update.hasMessage()) {
            mainTextMessage(update, sendMessage, userActivityDto, sendPhoto);
        } else if (update.hasCallbackQuery()) {
            mainCallBackMessage(update, sendMessage, userActivityDto, editMessageReplyMarkup, editMessageCaption);
        }
    }

    private void mainCallBackMessage(Update update, SendMessage sendMessage, UserActivityDto userActivityDto, EditMessageReplyMarkup editMessageReplyMarkup, EditMessageCaption editMessageCaption) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String data = callbackQuery.getData();
        Integer messageId = callbackQuery.getMessage().getMessageId();
        Long chatId = callbackQuery.getMessage().getChatId();
//        sendMessage.setChatId(chatId.toString());
        if (userActivityDto.getRound() == 2) {
            editMessageReplyMarkup.setMessageId(messageId);
            editMessageCaption.setParseMode("markdown");
//            editMessageReplyMarkup.setChatId(chatId.toString());
//            sendMessage.setText("0");
            if (data.startsWith("+")) {
                editMessageCaption.setChatId(chatId.toString());
                editMessageCaption.setMessageId(messageId);
                sendMessage.setText("+");
                UUID productId = UUID.fromString(data.substring(1));
                System.out.println(data.substring(1));
                Integer count = cardRepository.getUserOrderCountIncrement(chatId, productId);
                editMessageCaption.setCaption(photoCaption(getAllData.getProductById(productId), count));
//                editMessageReplyMarkup.setReplyMarkup((InlineKeyboardMarkup) buttonService.photoInlineButtons(productId, count));
                editMessageCaption.setReplyMarkup((InlineKeyboardMarkup) buttonService.photoInlineButtons(productId, count));
                return;
            } else if (data.startsWith("-")) {
                editMessageCaption.setChatId(chatId.toString());
                editMessageCaption.setMessageId(messageId);
                UUID productId = UUID.fromString(data.substring(1));
                System.out.println(data.substring(1));
                Integer count = cardRepository.getUserOrderCountDecrement(chatId, productId);
                editMessageCaption.setCaption(photoCaption(getAllData.getProductById(productId), count));
                editMessageCaption.setReplyMarkup((InlineKeyboardMarkup) buttonService.photoInlineButtons(productId, count));
//                editMessageReplyMarkup.setReplyMarkup();
                return;

            } else if (data.startsWith("empty")) {
                return;
            } else if (data.startsWith("addCard")) {
                sendMessage.setText("addCard");
                return;
            }
        }
    }

    private void mainTextMessage(Update update, SendMessage sendMessage, UserActivityDto userActivityDto, SendPhoto sendPhoto) {
        Message message = update.getMessage();
        if (message.hasText()) {
            mainText(update, sendMessage, userActivityDto, sendPhoto);
        }
    }

    private void mainText(Update update, SendMessage sendMessage, UserActivityDto userActivityDto, SendPhoto sendPhoto) {
        final Long chatId = update.getMessage().getChatId();
        final String text = update.getMessage().getText();
        sendMessage.setChatId(chatId.toString());
        if (text.equals("/start") || text.equals(mainUz)) {
            mainPage(sendMessage, userActivityDto);
            return;
        }
        switch (text) {
            case menuUz:
                showCategory(sendMessage, userActivityDto);
                return;
            case informationUz:
                sendMessage.setText(informationUz + " hali ish jarayonida");
                return;
            case contactUz:
                sendMessage.setText(contactUz + " hali ish jarayonida");
                return;
            case languageUz:
            case languageRu:
                sendMessage.setText(languageRu + " hali ish jarayonida");
                return;
        }
        switch (userActivityDto.getRound()) {
            case 1:
                final List<String> productByCategoryName = getAllData.gelAllProductByCategoryName(text);
                if (productByCategoryName.size() == 0) {
                    sendMessage.setText(emptyProductUz);
                    return;
                }
                userActivityDto.setRound(2);
                sendMessage.setReplyMarkup(buttonService.productButton(productByCategoryName));
                sendMessage.setText(selectMenuUz);
                return;
            case 2:
                final ProductCategoryBotProjection productByName = getAllData.getProductByName(text);
                if (productByName == null) {
                    sendMessage.setText(errorMessageUz);
                    return;
                }
                InputStream inputStream = new ByteArrayInputStream(productByName.getImage());
                sendPhoto.setChatId(chatId.toString());
                sendPhoto.setPhoto(new InputFile(inputStream, "image"));
                int count = cardRepository.getUserOrderCountIncrement(chatId, productByName.getId());
                String caption = photoCaption(productByName, count);
                sendPhoto.setCaption(caption);
                sendPhoto.setParseMode("markdown");
                sendPhoto.setReplyMarkup(buttonService.photoInlineButtons(productByName.getId(), count));
                sendMessage.setChatId("0");
                return;
        }

    }

    private String photoCaption(ProductCategoryBotProjection product, int count) {
        String caption = "*Categoriya:* " + product.getCategoryName() + "\n" +
                "*Nomi:* " + product.getProductName() + "\n" +
                "*Narxi*: " + count + " x " + product.getPrice() + " = " + ((float) count * product.getPrice());
        return caption;
    }

    private void showCategory(SendMessage sendMessage, UserActivityDto userActivityDto) {
        if (userActivityDto.getRound() != 0) {
            sendMessage.setText(errorMessageUz);
            return;
        }
        final Set<String> allCategory = getAllData.getAllCategory();
        if (allCategory.size() == 0) {
            sendMessage.setText(emptyCategoryUz);
            return;
        }
        userActivityDto.setRound(1);
        sendMessage.setReplyMarkup(buttonService.categoryButtons(allCategory));
        sendMessage.setText(menuUz);
    }

    private void mainPage(SendMessage sendMessage, UserActivityDto userActivityDto) {

        userActivityDto.setRound(0);
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
