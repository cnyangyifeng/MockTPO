package com.mocktpo.util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontsUtils {

    public static void loadFonts() {
        try {
            InputStream cambria = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria.ttf");
            InputStream cambriab = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria Bold.ttf");
            InputStream cambriai = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria Italic.ttf");
            InputStream cambriabi = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria Bold Italic.ttf");

            InputStream impact = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Impact.ttf");

            InputStream roboto = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Regular.ttf");
            InputStream robotob = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Bold.ttf");


            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();


            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambria));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambriab));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambriai));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambriabi));

            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, impact));

            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, roboto));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, robotob));

        } catch (FontFormatException ffe) {

        } catch (IOException ioe) {

        }
    }
}
