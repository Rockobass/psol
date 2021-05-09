package org.orz.psol.utils;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class SavaPic {

    public static void main(String[] args) throws IOException {
        String path = "C:\\MyProjects\\psol\\src\\main\\resources\\static\\pics\\products";

        File dir = new File(path);


        while (true) {
            String prodId = UUIDGenerator.genProductID();
            String proPath = path + "\\" +prodId;
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入商品名,输入q退出");
            String s = scanner.next();
            if (s.equals("q"))
                break;

            String name = s;
            File file = new File(proPath);
            file.mkdir();

//            String coverPath = proPath + "\\" + pr
            Desktop.getDesktop().open(new File(proPath));

            System.out.println("输入任意键继续");
            s = scanner.next();
            System.out.println();



            System.out.println();





        }
    }
}
