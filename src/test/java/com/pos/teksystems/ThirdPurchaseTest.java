package com.pos.teksystems;

import com.pos.teksystems.model.Purchase;
import com.pos.teksystems.model.category.Fragance;
import com.pos.teksystems.model.category.ImportedFoods;
import com.pos.teksystems.model.category.ImportedFragances;
import com.pos.teksystems.model.category.MedicalSupplies;
import com.pos.teksystems.model.category.base.Category;
import com.pos.teksystems.model.goods.Good;
import com.pos.teksystems.model.goods.ImportedGood;
import com.pos.teksystems.model.goods.OnlySalesTaxableGood;
import com.pos.teksystems.model.goods.TaxExemptGood;
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
 * <p>Input 3: • 1 imported bottle of perfume at 27.99 • 1 bottle of perfume at 18.99 • 1 packet of
 * headache pills at 9.75 • 1 box of imported chocolates at 11.25
 *
 * <p>Output 3: • 1 imported bottle of perfume: 32.19 • 1 bottle of perfume: 20.89 • 1 packet of
 * headache pills: 9.75 • 1 imported box of chocolates: 11.85 • Sales Taxes: 6.70 Total: 74.68
 */
public class ThirdPurchaseTest {
  private Good fragance;
  private Good importedFragance;
  private Good headachePills;
  private Good importedChocolate;

  private final String IMPORTED_FRAGANCE_PRICE = "27.99";
  private final String FRAGANCE_PRICE = "18.99";
  private final String HEADACHE_PILLS_PRICE = "9.75";
  private final String IMPORTED_CHOCOLATE_PRICE = "11.25";

  private Purchase purchase;
  private Utils purchaseUtils = new PurchaseUtils();

  @Before
  public void setup() {

    importedFragance =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_FRAGANCE_PRICE))
            .name("Imported Fragance")
            .category(new ImportedFragances())
            .purchaseUtils(purchaseUtils)
            .build();
    fragance =
        OnlySalesTaxableGood.builder()
            .price(new BigDecimal(FRAGANCE_PRICE))
            .name("Fragance")
            .category(new Fragance())
            .purchaseUtils(purchaseUtils)
            .build();
    headachePills =
        TaxExemptGood.builder()
            .price(new BigDecimal(HEADACHE_PILLS_PRICE))
            .name("Headache pills")
            .category(new MedicalSupplies())
            .purchaseUtils(purchaseUtils)
            .build();
    importedChocolate =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_CHOCOLATE_PRICE))
            .name("Imported ChocolateBox")
            .category(new ImportedFoods())
            .purchaseUtils(purchaseUtils)
            .build();

    purchase = new Purchase("Purchase #3",purchaseUtils);
    purchase.addGood(fragance);
    purchase.addGood(importedFragance);
    purchase.addGood(headachePills);
    purchase.addGood(importedChocolate);
  }

  @After
  public void teardown() {

    fragance = null;
    importedFragance = null;
    headachePills = null;
    importedChocolate = null;
    purchase = null;
  }

  @Test
  public void assertGoodsPerImportedFragance() {
    int NUMBER_OF_GOODS = 1;
    assertThat(purchase.getGoods(Category.NAME.IMPORTED_FRAGANCES), is(notNullValue()));
    assertThat(purchase.getGoods(Category.NAME.IMPORTED_FRAGANCES).size(), is(NUMBER_OF_GOODS));
  }

  @Test
  public void assertGoodsPerFragance() {
    int NUMBER_OF_GOODS = 1;
    assertThat(purchase.getGoods(Category.NAME.FRAGANCES), is(notNullValue()));
    assertThat(purchase.getGoods(Category.NAME.FRAGANCES).size(), is(NUMBER_OF_GOODS));
  }

  @Test
  public void assertGoodsPerMedicalSupplies() {
    int NUMBER_OF_FOODS = 1;
    assertThat(purchase.getGoods(Category.NAME.MEDICAL_SUPPLIES), is(notNullValue()));
    assertThat(purchase.getGoods(Category.NAME.MEDICAL_SUPPLIES).size(), is(NUMBER_OF_FOODS));
  }

  @Test
  public void showPurchaseDetails() {

    purchase.printReceipt();
  }

  @Test
  public void assertPurchaseCalculation() {

    BigDecimal expectedTotalSalesTax = new BigDecimal("6.70");
    BigDecimal expectedTotalPrice = new BigDecimal("74.68");
    purchase.calculateSale();
    assertThat(purchase.getPurchaseTax(), is(expectedTotalSalesTax));
    assertThat(purchase.getTotalPrice(), is(expectedTotalPrice));
  }
}
