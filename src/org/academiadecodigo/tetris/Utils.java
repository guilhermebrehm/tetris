package org.academiadecodigo.tetris;

/**
 * Created by codecadet on 14/11/2017.
 */
public class Utils {

    public static boolean isNumber(String text) {

        return text.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean areNumbers(String[] texts) {

        for (String str : texts) {

            if(!isNumber(str)) {

                return false;
            }
        }

        return true;
    }
}
