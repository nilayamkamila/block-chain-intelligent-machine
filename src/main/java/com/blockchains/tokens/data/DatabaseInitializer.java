package com.blockchains.tokens.data;

import com.blockchains.tokens.data.interfaces.Book;
import com.blockchains.tokens.data.interfaces.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

//@Component
/*@ConditionalOnProperty(
        name = "db.init.enabled",
        havingValue = "true",
        matchIfMissing = false
)*/
//@Profile("dev")
//@ConditionalOnBean(BookController.class)
//@ConditionalOnMissingBean(BookController.class)
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;

    //@Autowired
    //private Environment env;

    @Override
    public void run(String... args) {

        /*if ("true".equals(env.getProperty("db.init.enabled"))) {
            System.out.println("This runs when 'db.init.enabled' property is true.");
        }*/

        bookRepository.save(
                new Book("Book A",
                        BigDecimal.valueOf(9.99),
                        new Date())
        );

        System.out.println("Database initialized!");

    }
}
