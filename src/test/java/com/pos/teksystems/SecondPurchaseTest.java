package com.pos.teksystems;

import com.pos.teksystems.model.Purchase;
import com.pos.teksystems.model.category.ImportedFoods;
import com.pos.teksystems.model.category.ImportedFragances;
import com.pos.teksystems.model.category.base.Category;
import com.pos.teksystems.model.goods.Good;
import com.pos.teksystems.model.goods.ImportedGood;
import com.pos.teksystems.utils.PurchaseUtils;
import com.pos.teksystems.utils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Unit test for simple PurchaseApp.
 *
 * <p>Input 2: • 1 imported box of chocolates at 10.00 • 1 imported bottle of perfume at 47.50
 */
public class SecondPurchaseTest {
  private Good importedFood;
  private Good importedFragance;

  private final String IMPORTED_FOOD_PRICE = "10.00";
  private final String IMPORTED_FRAGANCE_PRICE = "47.50";
  private Purchase purchase;
  private Utils purchaseUtils = new PurchaseUtils();

  @Before
  public void setup() {

    importedFood =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_FOOD_PRICE))
            .name("1 box of chocolates")
            .category(new ImportedFoods())
            .purchaseUtils(purchaseUtils)
            .build();
    importedFragance =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_FRAGANCE_PRICE))
            .name("Imported Fragance")
            .category(new ImportedFragances())
            .purchaseUtils(purchaseUtils)
            .build();
    purchase = new Purchase("Purchase #2", purchaseUtils);
    purchase.addGood(importedFood);
    purchase.addGood(importedFragance);
  }

  @After
  public void teardown() {

    importedFood = null;
    importedFragance = null;
    purchase = null;
  }

  @Test
  public void assertGoodsPerBookCategory() {
    int NUMBER_OF_GOODS = 1;
    assertThat(purchase.getGoods(Category.NAME.IMPORTED_FOODS), is(notNullValue()));
    assertThat(purchase.getGoods(Category.NAME.IMPORTED_FOODS).size(), is(NUMBER_OF_GOODS));
  }

  @Test
  public void asserGoodsPerImportedFragances() {
    int NUMBER_OF_GOODS = 1;
    assertThat(purchase.getGoods(Category.NAME.IMPORTED_FRAGANCES), is(notNullValue()));
    assertThat(purchase.getGoods(Category.NAME.IMPORTED_FRAGANCES).size(), is(NUMBER_OF_GOODS));
  }

  @Test
  public void assertPurchaseCalculation() {

    BigDecimal expectedTotalSalesTax = new BigDecimal("7.65");
    BigDecimal expectedTotalPrice = new BigDecimal("65.15");
    purchase.calculateSale();
    assertThat(purchase.getPurchaseTax(), is(expectedTotalSalesTax));
    assertThat(purchase.getTotalPrice(), is(expectedTotalPrice));
  }
}
