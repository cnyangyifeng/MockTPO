package com.mocktpo.util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontsUtils {

    public static void loadFonts() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            /* Cambria */
            InputStream cambria = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria.ttf");
            InputStream cambriab = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria Bold.ttf");
            InputStream cambriai = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria Italic.ttf");
            InputStream cambriabi = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Cambria Bold Italic.ttf");
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambria));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambriab));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambriai));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, cambriabi));
            /* Impact */
            InputStream impact = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Impact.ttf");
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, impact));
            /* Roboto */
            InputStream roboto = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Regular.ttf");
            InputStream robotob = FontsUtils.class.getResourceAsStream(GlobalConstants.FONTS_DIR + "Roboto-Bold.ttf");
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, roboto));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, robotob));
        } catch (FontFormatException ffe) {
            ffe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
