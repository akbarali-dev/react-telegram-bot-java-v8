package uz.akbarali.foodappjavav8.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.akbarali.foodappjavav8.bot.service.UserManageService;
import uz.akbarali.foodappjavav8.bot.util.Util;
import uz.akbarali.foodappjavav8.model.*;
import uz.akbarali.foodappjavav8.repository.*;
import static uz.akbarali.foodappjavav8.bot.util.Util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {
    final
    RestTemplate restTemplate;

    @Value("${spring.sql.init.mode}")
    String initMode;
    final
    CategoryRepository categoryRepository;

    final
    ProductRepository productRepository;

    final
    AttachmentRepository attachmentRepository;

    final
    AttachmentContentRepository attachmentContentRepository;
    final
    UserManageService userManageService;

    final
    Util userUtil;

    final
    CardRepository cardRepository;

    final
    UserActivityRepository userActivityRepository;

    final
    UserRepository userRepository;

    public DataLoader(RestTemplate restTemplate, CategoryRepository categoryRepository, ProductRepository productRepository, AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository, UserManageService userManageService, Util userUtil, UserActivityRepository userActivityRepository, CardRepository cardRepository, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
        this.userManageService = userManageService;
        this.userUtil = userUtil;
        this.userActivityRepository = userActivityRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        final Optional<User> byId = userRepository.findById(UUID.fromString("0392354d-e541-4af7-aca8-f1ddf8e9e506"));
//        final Optional<Product> byId1 = productRepository.findById(UUID.fromString("2ea099e0-8ff0-4693-a2c6-30165443767a"));
//        cardRepository.save(new Card(byId1.get(), byId.get(), 1, 100, false, false, null));
//        for (UserActivity userActivity : userActivityRepository.findAll()) {
//            System.out.println(userActivity.getChatId() + "; time: " + userActivity.getCreatedAt());
//        }
//        System.out.println("start");
//
//        UserActivityDto userActivityDto = userManageService.alreadyExistFromService(234L);
//        System.out.println(userActivityDto.getRound());
//        userActivityDto.setRound(12);
//        System.out.println(userUtil.activityMap.get(234L).getRound());
//        Thread.sleep(700);
//        System.out.println("700 thread ended ...");
//        System.out.println(userUtil.activityMap.get(234L).getRound());

        if (initMode.equals("always")) {
            final Category saveCategoryFood = categoryRepository.save(new Category("Food", "food"));
            final Category saveCategoryWater = categoryRepository.save(new Category("Water", "water"));
            final Category saveCategoryDessert = categoryRepository.save(new Category("Dessert", "dessert"));

            byte[] byteBurger = savedImage("src/main/resources/images/burger.png");
            byte[] byteCoca = savedImage("src/main/resources/images/coca.png");
            byte[] byteIceCream = savedImage("src/main/resources/images/icecream.png");
            byte[] byteKebab = savedImage("src/main/resources/images/kebab.png");
            byte[] bytePizza = savedImage("src/main/resources/images/pizza.png");
            byte[] byteSalad = savedImage("src/main/resources/images/salad.png");
            byte[] byteWater = savedImage("src/main/resources/images/water.png");

            final Attachment saveBurgerAtt = attachmentRepository.save(new Attachment("burger", "png", 1L));
            final Attachment saveCocaAtt = attachmentRepository.save(new Attachment("coca", "png", 1L));
            final Attachment saveIceCreamAtt = attachmentRepository.save(new Attachment("iceCream", "png", 1L));
            final Attachment saveKebabAtt = attachmentRepository.save(new Attachment("kebab", "png", 1L));
            final Attachment savePizzaAtt = attachmentRepository.save(new Attachment("pizza", "png", 1L));
            final Attachment saveSaladAtt = attachmentRepository.save(new Attachment("salad", "png", 1L));
            final Attachment saveWaterAtt = attachmentRepository.save(new Attachment("water", "png", 1L));

            final AttachmentContent saveContentBurger = attachmentContentRepository.save(new AttachmentContent(byteBurger, saveBurgerAtt));
            final AttachmentContent saveContentCoca = attachmentContentRepository.save(new AttachmentContent(byteCoca, saveCocaAtt));
            final AttachmentContent saveContentIceCream = attachmentContentRepository.save(new AttachmentContent(byteIceCream, saveIceCreamAtt));
            final AttachmentContent saveContentKebab = attachmentContentRepository.save(new AttachmentContent(byteKebab, saveKebabAtt));
            final AttachmentContent saveContentPizza = attachmentContentRepository.save(new AttachmentContent(bytePizza, savePizzaAtt));
            final AttachmentContent saveContentSalad = attachmentContentRepository.save(new AttachmentContent(byteSalad, saveSaladAtt));
            final AttachmentContent saveContentWater = attachmentContentRepository.save(new AttachmentContent(byteWater, saveWaterAtt));

            final Product saveBurger = productRepository.save(new Product("Burger", "Burger", "Burger DescUz", "Burger  DescRu", 10, saveBurgerAtt, saveCategoryFood));
            final Product saveCoca = productRepository.save(new Product("Coca", "Coca", "Coca DescUz", "Coca  DescRu", 10, saveCocaAtt, saveCategoryWater));
            final Product saveIceCream = productRepository.save(new Product("IceCream", "IceCream", "IceCream DescUz", "IceCream  DescRu", 10, saveIceCreamAtt, saveCategoryDessert));
            final Product saveKebab = productRepository.save(new Product("Kebab", "Kebab", "Kebab DescUz", "Kebab  DescRu", 10, saveKebabAtt, saveCategoryFood));
            final Product savePizza = productRepository.save(new Product("Pizza", "Pizza", "Pizza DescUz", "Pizza  DescRu", 10, savePizzaAtt, saveCategoryFood));
            final Product saveSalad = productRepository.save(new Product("Salad", "Salad", "Salad DescUz", "Salad  DescRu", 10, saveSaladAtt, saveCategoryFood));
            final Product saveWater = productRepository.save(new Product("Water", "Water", "Water DescUz", "Water  DescRu", 10, saveWaterAtt, saveCategoryWater));
        }
        getAllData();
        runner();
    }
    public void getAllData(){
        getAllData = productRepository.getAll();
    }

    private void runner() throws InterruptedException {
//        String forObjectTest = restTemplate.getForObject(
//                "https://t.me/mr_doppi/443",
//                String.class
//        );
//
//        System.out.println(forObjectTest);
        while (true) {
//            String forObject = restTemplate.getForObject(
//                    "https://food-telegram-bot-java.herokuapp.com/api/test/hello",
//                    String.class
//            );
            restTemplate.getForObject(
                    "https://food-react-bot.herokuapp.com/",
                    String.class
            );
//            System.out.println(forObject);
////            System.out.println(forObject2);
//            WebAppInfo webAppInfo = new WebAppInfo();
//            webAppInfo.setUrl("https://food-react-app-bot.herokuapp.com/");
            Thread.sleep(50000);

        }
    }

    public byte[] savedImage(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
