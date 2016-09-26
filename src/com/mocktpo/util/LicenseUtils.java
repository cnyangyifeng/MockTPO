package com.mocktpo.util;

import com.verhas.licensor.License;

public class LicenseUtils {

    public static String encode(String email, String uuid) {
        License lic = new License();
        try {
            lic.setLicense(email);
            return lic.encodeLicense(LicenseUtils.class.getResource(GlobalConstants.APPLICATION_DIR + GlobalConstants.PUBLIC_KEY_FILE).getFile());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void decode(String code) {
        License lic = new License();
        try {
            lic.loadKeyRingFromResource(GlobalConstants.APPLICATION_DIR + GlobalConstants.PUBLIC_KEY_FILE, null);
            lic.setLicenseEncoded(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
