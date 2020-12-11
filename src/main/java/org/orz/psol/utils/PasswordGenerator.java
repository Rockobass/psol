package org.orz.psol.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Scanner;

// 生成加密后的密码
public class PasswordGenerator {
    public static String genPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = new Scanner(System.in).next();
        pass = encoder.encode(pass);
        System.out.println(pass);
    }
}
