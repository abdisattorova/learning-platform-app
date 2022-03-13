package uz.pdp.util;
//Sevinch Abdisattorova 02/14/2022 12:07 AM


import uz.pdp.dto.CourseDto;
import uz.pdp.model.User;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public interface Constants {
    int number_of_elements_in_1_page = 3;
    String path = "S:\\PC items\\Desktop\\learning-platform-app\\src\\main\\resources\\photos/";

    String promoteMessage = "promote";

    static void getUserWithImageUrl(User user) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path + user.getImageUrl()));

            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", base);
            base.flush();
            byte[] imageInByteArray = base.toByteArray();
            base.close();

            String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

            user.setImageUrl(b64);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    static void getCourseWithImageUrl(CourseDto course) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path + course.getImageUrl()));

            ByteArrayOutputStream base = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", base);
            base.flush();
            byte[] imageInByteArray = base.toByteArray();
            base.close();

            String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

            course.setImageUrl(b64);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
