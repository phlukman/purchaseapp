package com.pos.teksystems.model.goods;

import com.pos.teksystems.exception.GoodCreationException;
import com.pos.teksystems.model.category.base.Category;
import com.pos.teksystems.utils.PurchaseUtils;
import com.pos.teksystems.utils.Utils;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * Represents a good where the tax applicable to its price are the sales tax (10%) and the import
 * tax (5%)
 *
 * @author patria.lukman
 */
public class ImportedGood extends BaseGood implements Good {

  private final String ERROR_MESSGE = "Creating a imported good with wrong category ";

  @Builder(builderMethodName = "builder")
  public ImportedGood(BigDecimal price, Category category, String name, Utils purchaseUtils) {
    super(price, category, name,purchaseUtils);
    if (!Category.importedTaxableCategories.contains(category.getName()))
      throw new GoodCreationException(ERROR_MESSGE.concat(category.toString()));
  }

  @Override
  public BigDecimal getPricePlusTax() {

    return getPrice().add(getApplicableTax());
  }

  @Override
  public BigDecimal getApplicableTax() {

    BigDecimal salesTax = new BigDecimal("0.00");
    BigDecimal importTax = getPurchaseUtils().round(getPrice().multiply(Category.IMPORT_TAX));
    if (isSalesTaxable())
        salesTax = getPurchaseUtils().round(getPrice().multiply(Category.SALES_TAX));
    return getPurchaseUtils().round(new BigDecimal(getPurchaseUtils().format(importTax.add(salesTax))));
  }



}
