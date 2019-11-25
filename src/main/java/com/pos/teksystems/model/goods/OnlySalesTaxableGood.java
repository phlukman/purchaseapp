package com.pos.teksystems.model.goods;

import com.pos.teksystems.exception.GoodCreationException;
import com.pos.teksystems.model.category.base.Category;
import com.pos.teksystems.utils.PurchaseUtils;
import com.pos.teksystems.utils.Utils;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * Represents a good where the only tax applicable to its price is the sales tax which amounts to
 * 10%.
 *
 * @see Category
 * @author patria.lukman
 */
public class OnlySalesTaxableGood extends BaseGood implements Good {

  private final String ERROR_MESSAGE="Creating an only sales taxable good with wrong category ";

  @Builder(builderMethodName = "builder")
  public OnlySalesTaxableGood(BigDecimal price, Category category, String name, Utils purchaseUtils) {
    super(price, category, name,purchaseUtils);
    if (!Category.onlySalesTaxableCategories.contains(category.getName()))
      throw new GoodCreationException(ERROR_MESSAGE.concat(category.toString()));
  }

  @Override
  public BigDecimal getPricePlusTax() {

    BigDecimal pricePlusTax = getPrice().add(getApplicableTax());
    return pricePlusTax;
  }

  @Override
  public BigDecimal getApplicableTax() {

    return getPurchaseUtils().round(getPrice().multiply(Category.SALES_TAX));
  }


}
