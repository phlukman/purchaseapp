package com.pos.teksystems.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class PurchaseUtils implements Utils {

    private static String pattern = "#0.00";
    private DecimalFormat df = new DecimalFormat(pattern);


    @Override
    public String format(BigDecimal value){
        return df.format(value);
    }

    @Override
    public  BigDecimal round(BigDecimal value) {
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
