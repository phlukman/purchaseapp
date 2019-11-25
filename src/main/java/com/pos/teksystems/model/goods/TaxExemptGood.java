package com.pos.teksystems.model.goods;

import com.pos.teksystems.exception.GoodCreationException;
import com.pos.teksystems.model.category.base.Category;
import lombok.Builder;

import java.math.BigDecimal;

import static com.pos.teksystems.model.category.base.Category.notTaxableCategories;


public class TaxExemptGood extends BaseGood implements Good {


    private final String ERROR_MESSAGE="Creating a tax exempt good with wrong category ";

    @Builder(builderMethodName = "builder")
    public TaxExemptGood(BigDecimal price, Category category, String name) {
        super(price,category,name);
        if (!notTaxableCategories.contains(category.getName()))
            throw new GoodCreationException(ERROR_MESSAGE.concat(category.toString()));

    }



    @Override
    public BigDecimal getPricePlusTax() {

        return getPrice();
    }

    @Override
    public BigDecimal getApplicableTax() {
        return new BigDecimal(0.00);
    }



}
