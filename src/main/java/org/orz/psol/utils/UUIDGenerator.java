package org.orz.psol.utils;
import java.util.UUID;


public class UUIDGenerator {
    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }

    public static String genProductID(){
        String s = UUID.randomUUID().toString();
        return s.substring(19,23)+s.substring(24,36);
    }

    public static String getStoreID(){
        String s = UUID.randomUUID().toString();
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,22);
    }

    public static String genPicID(){
        String s = UUID.randomUUID().toString();
        return s.substring(19,23)+s.substring(24,30);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(getStoreID());
        }
    }

}
