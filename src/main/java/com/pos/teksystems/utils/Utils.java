package com.pos.teksystems.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public interface Utils {


    String format(BigDecimal value);

    BigDecimal round(BigDecimal value);
}
