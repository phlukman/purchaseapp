package com.pos.teksystems.model.goods;

import com.pos.teksystems.model.category.base.Category;
import com.pos.teksystems.utils.PurchaseUtils;
import com.pos.teksystems.utils.Utils;
import lombok.Getter;

import java.math.BigDecimal;

public abstract class BaseGood implements Good {

  @Getter protected BigDecimal price;
  @Getter protected Category category;
  @Getter protected String name;
  @Getter private Utils purchaseUtils;


  public BaseGood(BigDecimal price, Category category, String name, Utils purchaseUtils) {

    this.price = price;
    this.category = category;
    this.name = name;
    this.purchaseUtils = purchaseUtils;
  }

  @Override
  public boolean isSalesTaxable() {
    return getCategory().isSalesTaxable();
  }

  @Override
  public boolean isImportTaxable() {
    return getCategory().isImportTaxable();
  }

  @Override
  public boolean isTaxable() {
    return isImportTaxable() || isSalesTaxable();
  }

  @Override
  public boolean isImported() {
    return Category.importedTaxableCategories.contains(category.getName());
  }

  @Override
  public boolean isOnlySalesTaxable() {
    return Category.onlySalesTaxableCategories.contains(category.getName());
  }

  @Override
  public boolean isTaxExempted() {
    return Category.notTaxableCategories.contains(category.getName());
  }

  @Override
  public String toString() {
    return String.format("%s     %10s",name, purchaseUtils.format(getPricePlusTax()));
  }


}
