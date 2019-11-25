package com.pos.teksystems;

import com.pos.teksystems.exception.GoodCreationException;
import com.pos.teksystems.model.category.Books;
import com.pos.teksystems.model.category.MedicalSupplies;
import com.pos.teksystems.model.goods.Good;
import com.pos.teksystems.model.goods.ImportedGood;
import com.pos.teksystems.model.goods.TaxExemptGood;
import com.pos.teksystems.utils.PurchaseUtils;
import com.pos.teksystems.utils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/** Validates that a good being a book, a medical supply or a non-imported food are not taxable */
public class TaxExemptGoodTest {

  public static final BigDecimal EXPECTED_PRICE = new BigDecimal(10.50);
  public static final String EXPECTED_TITLE = "First title";
  public static final Books EXPECTED_CATEGORY = new Books();
  private Good good;
  private Utils purchaseUtils = new PurchaseUtils();

  @Before
  public void setup() {

    good =
        TaxExemptGood.builder()
            .price(EXPECTED_PRICE)
            .name(EXPECTED_TITLE)
            .category(EXPECTED_CATEGORY)
            .purchaseUtils(purchaseUtils)
            .build();
  }

  @After
  public void teardown() {
    good = null;
  }

  @Test
  public void assertPriceWithTaxIsCorrect() {
    BigDecimal expectedPrice = new BigDecimal(10.50);
    assertThat(good.getPrice(), is(equalTo(expectedPrice)));
    assertThat(good.getPricePlusTax(), is(equalTo(expectedPrice)));
  }

  @Test
  public void assertApplicableTax() {
    BigDecimal expectedApplicableTax = new BigDecimal(0.00);
    assertThat(good.getApplicableTax(), is(equalTo(expectedApplicableTax)));
  }

  @Test
  public void assertGoodIsTaxable() {
    assertFalse(good.isImportTaxable());
    assertFalse(good.isSalesTaxable());
    assertFalse(good.isTaxable());
  }

  @Test
  public void assertSuccessfulImportedGoodCreation() {

    assertThat(good.getPrice(), is(equalTo(EXPECTED_PRICE)));
    assertThat(good.getName(), is(equalTo(EXPECTED_TITLE)));
    assertThat(good.isImported(), is(false));
    assertThat(good.isOnlySalesTaxable(), is(false));
    assertThat(good.isTaxExempted(), is(true));
  }

  @Test(expected = GoodCreationException.class)
  public void assertThrowExceptionWhenPassingWrongCategory() {

    ImportedGood.builder()
        .price(EXPECTED_PRICE)
        .name(EXPECTED_TITLE)
        .category(new MedicalSupplies())
        .build();
  }
}
