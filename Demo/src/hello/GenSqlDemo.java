package hello;

import java.util.Random;

public class GenSqlDemo {

    public static void main(String[] args) {
        Random random = new Random();
        
        for (int i = 1; i < 599; i++) {
            System.out.println("UPDATE `sakila`.`customer` SET `store_id` = '" +
                    (1 + random.nextInt(10)) + "' WHERE (`customer_id` = '" + i + "');");
        }
    }
}
