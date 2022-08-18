package uz.akbarali.foodappjavav8.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import uz.akbarali.foodappjavav8.model.Attachment;
import uz.akbarali.foodappjavav8.model.AttachmentContent;
import uz.akbarali.foodappjavav8.model.Category;
import uz.akbarali.foodappjavav8.model.Product;
import uz.akbarali.foodappjavav8.repository.AttachmentContentRepository;
import uz.akbarali.foodappjavav8.repository.AttachmentRepository;
import uz.akbarali.foodappjavav8.repository.CategoryRepository;
import uz.akbarali.foodappjavav8.repository.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RestTemplate restTemplate;

    @Value("${spring.sql.init.mode}")
    String initMode;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository  attachmentContentRepository;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            final Category saveCategoryFood = categoryRepository.save(new Category("food", "food"));
            final Category saveCategoryWater = categoryRepository.save(new Category("water", "water"));
            final Category saveCategoryDessert = categoryRepository.save(new Category("dessert", "dessert"));

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

            final Product saveBurger = productRepository.save(new Product("Burger","Burger","Burger DescUz", "Burger  DescRu", 10, saveBurgerAtt, saveCategoryFood));
            final Product saveCoca = productRepository.save(new Product("Coca","Coca","Coca DescUz", "Coca  DescRu", 10, saveCocaAtt, saveCategoryWater));
            final Product saveIceCream = productRepository.save(new Product("IceCream","IceCream","IceCream DescUz", "IceCream  DescRu", 10, saveIceCreamAtt, saveCategoryDessert));
            final Product saveKebab = productRepository.save(new Product("Kebab","Kebab","Kebab DescUz", "Kebab  DescRu", 10, saveKebabAtt, saveCategoryFood));
            final Product savePizza = productRepository.save(new Product("Pizza","Pizza","Pizza DescUz", "Pizza  DescRu", 10, savePizzaAtt, saveCategoryFood));
            final Product saveSalad = productRepository.save(new Product("Salad","Salad","Salad DescUz", "Salad  DescRu", 10, saveSaladAtt, saveCategoryFood));
            final Product saveWater = productRepository.save(new Product("Water","Water","Water DescUz", "Water  DescRu", 10, saveWaterAtt, saveCategoryWater));
        }
//        runner();
    }

    private void runner() throws InterruptedException {
//        String forObjectTest = restTemplate.getForObject(
//                "https://t.me/mr_doppi/443",
//                String.class
//        );
//
//        System.out.println(forObjectTest);
        while (true) {
            String forObject = restTemplate.getForObject(
                    "https://food-react-app-java.herokuapp.com/api/test/hello",
                    String.class
            );
            restTemplate.getForObject(
                    "https://food-react-app-bot.herokuapp.com/",
                    String.class
            );
            System.out.println(forObject);
//            System.out.println(forObject2);
            WebAppInfo webAppInfo = new WebAppInfo();
            webAppInfo.setUrl("https://food-react-app-bot.herokuapp.com/");
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
