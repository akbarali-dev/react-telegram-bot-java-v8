package uz.akbarali.foodappjavav8.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.akbarali.foodappjavav8.bot.dto.UserActivityDto;
import uz.akbarali.foodappjavav8.bot.util.Util;
import uz.akbarali.foodappjavav8.model.User;
import uz.akbarali.foodappjavav8.model.UserActivity;
import uz.akbarali.foodappjavav8.model.enums.Role;
import uz.akbarali.foodappjavav8.repository.UserActivityRepository;
import uz.akbarali.foodappjavav8.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class UserManageService {
    @Autowired
    Util userUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserActivityRepository userActivityRepository;

    @Autowired
    UserService userService;

    public void manage(Update update, SendMessage sendMessage,
                       EditMessageText editMessageText, DeleteMessage deleteMessage,
                       SendLocation sendLocation, SendPhoto sendPhoto, SendDocument sendDocument, EditMessageReplyMarkup editMessageReplyMarkup, EditMessageCaption editMessageCaption) {
        if (update.hasMessage() || update.hasCallbackQuery()) {
            UserActivityDto userActivityDto = null;
            if (update.hasMessage()) {
                userActivityDto = alreadyExistFromService(update.getMessage().getChatId());
            } else if (update.hasCallbackQuery()) {
                userActivityDto = alreadyExistFromService(update.getCallbackQuery().getMessage().getChatId());
            }
            if (userActivityDto.getRole().equals(Role.USER)) {
                userService.main(update, sendMessage, userActivityDto, sendPhoto, editMessageReplyMarkup, editMessageCaption);
            }
        }
    }

    public UserActivityDto isAlreadyExist(Long chatId) {
        Optional<UserActivity> byChatId = userActivityRepository.findByChatId(chatId);
        if (byChatId.isPresent()) {
            System.out.println("databaseda mavjud");
            return currentUserAddServer(byChatId.get());
        }
        System.out.println("databaseda mavjud emas");
        User user = userRepository.save(new User(Role.USER));
        UserActivity userActivity = new UserActivity(chatId, user);
        UserActivity save = userActivityRepository.save(userActivity);
        return currentUserAddServer(save);
    }

    public UserActivityDto currentUserAddServer(UserActivity userActivity) {
        System.out.println("add server ...");
        UserActivityDto userActivityDto = new UserActivityDto(userActivity.getId(),
                userActivity.getChatId(),
                userActivity.getRound(),
                userActivity.getUser().getId(),
                userActivity.getUser().getRole()
        );
        Map<Long, UserActivityDto> activityMap = userUtil.activityMap;
        activityMap.put(userActivityDto.getChatId(), userActivityDto);

        new Thread(() -> {
            try {
                System.out.println("thread start....");
                Thread.sleep(60 * 10000);
                System.out.println("thread end....");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("o'chirilishdan oldigi round  " + userActivityDto.getRound());
            userActivity.setRound(userActivityDto.getRound());
            userActivityRepository.save(userActivity);
            activityMap.remove(userActivityDto.getChatId());
        }).start();

        return userActivityDto;

    }

    public UserActivityDto alreadyExistFromService(Long chatId) {
        Map<Long, UserActivityDto> activityMap = userUtil.activityMap;
        if (activityMap.containsKey(chatId)) {
            System.out.println("map da bor");
            return activityMap.get(chatId);
        }
        System.out.println("birinchi qadam");
        return isAlreadyExist(chatId);
    }

//    public UserActivityDto alreadyExistDb(Long chatId, Map<Long, UserActivityDto> activityMap){
//        userActivityRepository.
//    }


}
