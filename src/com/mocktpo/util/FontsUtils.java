package com.mocktpo.util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontsUtils {

    public static void loadFonts() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            /* Cambria */
            InputStream Cambria = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria-Regular.ttf");
            InputStream CambriaBold = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria-Bold.ttf");
            InputStream CambriaBoldItalic = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria-BoldItalic.ttf");
            InputStream CambriaItalic = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria-Italic.ttf");
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Cambria));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, CambriaBold));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, CambriaBoldItalic));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, CambriaItalic));
            /* Roboto */
            InputStream RobotoRegular = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Regular.ttf");
            InputStream RobotoBold = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Bold.ttf");
            InputStream RobotoBoldItalic = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-BoldItalic.ttf");
            InputStream RobotoItalic = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Italic.ttf");
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, RobotoRegular));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, RobotoBold));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, RobotoBoldItalic));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, RobotoItalic));
        } catch (FontFormatException ffe) {
            ffe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
