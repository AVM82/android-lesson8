package org.avm.lesson8;

import android.graphics.Color;

import java.util.Random;

public class Utils {
    static Random random = new Random();

    public static int getColorRandom() {
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
