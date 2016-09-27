package com.mocktpo.util;

import java.awt.*;
import java.io.InputStream;

public class FontsUtils {

    public static void loadFonts() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            /* Cambria */
            InputStream cambria = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria-Regular.ttf");
            InputStream cambriaBold = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria-Bold.ttf");
            InputStream cambriaBoldItalic = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria-BoldItalic.ttf");
            InputStream cambriaItalic = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria-Italic.ttf");
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambria));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambriaBold));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambriaBoldItalic));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambriaItalic));
            /* Roboto */
            InputStream RobotoRegular = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Medium.ttf");
            InputStream RobotoBold = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Bold.ttf");
            InputStream RobotoBoldItalic = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-BoldItalic.ttf");
            InputStream RobotoItalic = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Italic.ttf");
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, RobotoRegular));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, RobotoBold));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, RobotoBoldItalic));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, RobotoItalic));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
