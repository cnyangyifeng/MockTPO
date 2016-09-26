package com.mocktpo.util;

import com.verhas.licensor.HardwareBinder;

public class HardwareBinderUtils {

    public static String generate() {
        HardwareBinder binder = new HardwareBinder();
        try {
            return binder.getMachineIdString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
