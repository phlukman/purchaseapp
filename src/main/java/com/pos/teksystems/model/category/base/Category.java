package com.pos.teksystems.model.category.base;

import java.math.BigDecimal;
import java.util.EnumSet;

public interface Category {

  enum NAME {
    BOOKS,
    FOODS,
    IMPORTED_FOODS,
    MEDICAL_SUPPLIES,
    MUSIC,
    FRAGANCES,
    IMPORTED_FRAGANCES
  }

  EnumSet<Category.NAME> onlySalesTaxableCategories = EnumSet.of(NAME.MUSIC, NAME.FRAGANCES);
  EnumSet<Category.NAME> notTaxableCategories = EnumSet.of(NAME.BOOKS, NAME.FOODS, NAME.MEDICAL_SUPPLIES);
  EnumSet<Category.NAME> importedTaxableCategories = EnumSet.of(NAME.IMPORTED_FOODS, NAME.IMPORTED_FRAGANCES);

  BigDecimal SALES_TAX = new BigDecimal("0.10");
  BigDecimal IMPORT_TAX = new BigDecimal("0.05");

  boolean isImportTaxable();

  boolean isSalesTaxable();

  NAME getName();
}
