package uz.akbarali.foodappjavav8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodAppJavaV8Application {

    public static void main(String[] args) {
//        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//            public void run() {
//                System.out.println("ishladi  In shutdown hook");
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("ishladi  In shutdown hook");
//            }
//        }, "Shutdown-thread"));
        SpringApplication.run(FoodAppJavaV8Application.class, args);
    }


}
