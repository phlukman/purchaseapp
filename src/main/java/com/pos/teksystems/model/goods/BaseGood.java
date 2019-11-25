package com.pos.teksystems.model.goods;

import com.pos.teksystems.model.category.base.Category;
import com.pos.teksystems.utils.PurchaseUtils;
import lombok.Getter;

import java.math.BigDecimal;

public abstract class BaseGood implements Good {

  @Getter protected BigDecimal price;
  @Getter protected Category category;
  @Getter protected String name;

  public BaseGood(BigDecimal price, Category category, String name) {

    this.price = price;
    this.category = category;
    this.name = name;
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
    return String.format("%s     %10s",name, PurchaseUtils.getPriceFormatter().format(getPricePlusTax()));
  }


}
