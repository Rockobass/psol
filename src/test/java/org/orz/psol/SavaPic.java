package org.orz.psol;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;
import org.orz.psol.mapper.InsertMapper;
import org.orz.psol.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.parser.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@SpringBootTest
public class SavaPic {
    @Autowired
    InsertMapper insertMapper;

    @Test
    void saveCarousel() {
        String path = "C:\\MyProjects\\psol\\src\\main\\resources\\static\\pics\\carousel";

        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file: files) {
            String newName = UUIDGenerator.genPicID() + "."+ file.getName().split("\\.")[1];
            String full = path + "\\" + newName;
            File newFile = new File(full);
            file.renameTo(newFile);
            String url = "/static/carousel/" + newName;
            int count = insertMapper.insertIntoCarousel(url);

            System.out.println(count);
        }
    }

    @Test
    void tttt() throws FileNotFoundException {
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(new FileReader("C:\\MyProjects\\psol\\src\\main\\resources\\json\\pop1.json"));
        JsonArray jsonArray = object.getAsJsonObject("data").getAsJsonArray("list");
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject obj = jsonArray.get(i).getAsJsonObject();
            System.out.println(obj.get("iid").getAsString());
        }

    }



}
