package com.pos.teksystems.model.category.base;

/** Goods belonging to this category are exempt of sales taxes */
public abstract class ExemptTaxableCategory implements Category {

  @Override
  public boolean isImportTaxable() {
    return false;
  }

  @Override
  public boolean isSalesTaxable() {
    return false;
  }
}
