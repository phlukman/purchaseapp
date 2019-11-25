package com.pos.teksystems.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PurchaseUtils {

    private static String pattern = "#0.00";

    public static DecimalFormat getPriceFormatter() {
        DecimalFormat df = new DecimalFormat(pattern);
        return df;
    }


    public static BigDecimal round(BigDecimal value) {
        BigDecimal increment = new BigDecimal("0.05");
        if (increment.signum() == 0) {
            // 0 increment does not make much sense, but prevent division by 0
            return value;
        } else {
            BigDecimal divided = value.divide(increment, 0, RoundingMode.UP);
            BigDecimal result = divided.multiply(increment);
            return result;
        }
    }

}
