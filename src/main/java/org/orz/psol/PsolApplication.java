package org.orz.psol;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "org.orz.psol")
@SpringBootApplication
public class PsolApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsolApplication.class, args);
    }

}
