package uz.pdp.util;
//Sevinch Abdisattorova 02/14/2022 12:07 AM


import uz.pdp.model.User;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Constants {
    public static int number_of_elements_in_1_page = 3;
    public static String path = "S:\\IdeaProjects\\Spring\\spring-mvc-example\\spring-mvc-example\\src\\main\\resources/";


    public static void getUserWithImageUrl(User user) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path + user.getImageUrl()));

            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image, "png", base);
            base.flush();
            byte[] imageInByteArray = base.toByteArray();
            base.close();

            String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

            user.setImageUrl(b64);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
