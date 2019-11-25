package com.pos.teksystems.model.goods;

import com.pos.teksystems.model.category.base.Category;

import java.math.BigDecimal;

public interface Good {

  boolean isSalesTaxable();

  boolean isImportTaxable();

  boolean isTaxable();

  BigDecimal getPrice();

  BigDecimal getPricePlusTax();

  BigDecimal getApplicableTax();

  Category getCategory();

  String getName();

  boolean isImported();

  boolean isOnlySalesTaxable();

  boolean isTaxExempted();
}
