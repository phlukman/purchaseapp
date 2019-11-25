package com.pos.teksystems;

import com.pos.teksystems.exception.GoodCreationException;
import com.pos.teksystems.model.category.ImportedFoods;
import com.pos.teksystems.model.category.MedicalSupplies;
import com.pos.teksystems.model.category.base.Category;
import com.pos.teksystems.model.goods.Good;
import com.pos.teksystems.model.goods.ImportedGood;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/** Unit test for simple PurchaseApp. */
public class ImportedFoodTest {

  public static final BigDecimal EXPECTED_CHOCOLATE_PRICE = new BigDecimal("10.00");
  public static final String BOX_OF_CHOCOLATE = "Box of chocolate";
  private Good good;

  @Before
  public void setup() {

    good =
        ImportedGood.builder()
            .price(EXPECTED_CHOCOLATE_PRICE)
            .name(BOX_OF_CHOCOLATE)
            .category(new ImportedFoods())
            .build();
  }

  @After
  public void teardown() {
    good = null;
  }

  @Test
  public void assertPriceWithTaxIsCorrect() {
    assertThat(good.getPrice(), is(equalTo(EXPECTED_CHOCOLATE_PRICE)));
    BigDecimal expectedTotalPrice = new BigDecimal("10.50");
    assertThat(good.getPricePlusTax(), is(equalTo(expectedTotalPrice)));
  }

  @Test
  public void assertApplicableTax() {
    BigDecimal expectedTotalTax = new BigDecimal("0.50");
    assertThat(good.getApplicableTax(), is(equalTo(expectedTotalTax)));
  }

  @Test
  public void assertGoodIsTaxable() {
    assertTrue(good.isImportTaxable());
    assertFalse(good.isSalesTaxable());
    assertTrue(good.isTaxable());
  }

  @Test
  public void assertSuccessfulImportedGoodCreation() {

    Good importedGood =
        ImportedGood.builder()
            .price(EXPECTED_CHOCOLATE_PRICE)
            .name(BOX_OF_CHOCOLATE)
            .category(new ImportedFoods())
            .build();
    assertThat(importedGood.getPrice(), is(equalTo(EXPECTED_CHOCOLATE_PRICE)));
    assertThat(importedGood.getName(), is(equalTo(BOX_OF_CHOCOLATE)));
    assertThat(importedGood.isImported(), is(true));
    assertThat(importedGood.isOnlySalesTaxable(), is(false));
    assertThat(importedGood.isTaxExempted(), is(false));

  }

  @Test(expected = GoodCreationException.class)
  public void assertThrowExceptionWhenPassingWrongCategory() {
    final Category WRONG_CATEGORY = new MedicalSupplies();
    ImportedGood.builder()
        .price(EXPECTED_CHOCOLATE_PRICE)
        .name(BOX_OF_CHOCOLATE)
        .category(WRONG_CATEGORY)
        .build();
  }
}
