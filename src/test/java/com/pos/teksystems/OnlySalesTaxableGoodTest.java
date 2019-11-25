package com.pos.teksystems;

import com.pos.teksystems.exception.GoodCreationException;
import com.pos.teksystems.model.category.Fragance;
import com.pos.teksystems.model.category.MedicalSupplies;
import com.pos.teksystems.model.goods.Good;
import com.pos.teksystems.model.goods.ImportedGood;
import com.pos.teksystems.model.goods.OnlySalesTaxableGood;
import com.pos.teksystems.utils.PurchaseUtils;
import com.pos.teksystems.utils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/** Unit test for simple PurchaseApp. */
public class OnlySalesTaxableGoodTest {

  public static final BigDecimal EXPECTED_PARFUME_PRICE = new BigDecimal("18.99");
  public static final String EXPECTED_PERFUM_NAME = "Channel 5";
  private Good good;
  private Utils purchaseUtils = new PurchaseUtils();

  @Before
  public void setup() {

    good =
        OnlySalesTaxableGood.builder()
            .price(EXPECTED_PARFUME_PRICE)
            .name(EXPECTED_PERFUM_NAME)
            .category(new Fragance())
            .purchaseUtils(purchaseUtils)
            .build();
  }

  @After
  public void teardown() {
    good = null;
  }

  @Test
  public void assertPriceWithTaxIsCorrect() {
    BigDecimal expectedPricePlusTax = new BigDecimal("20.89");
    assertThat(good.getPricePlusTax(), is(equalTo(expectedPricePlusTax)));
  }

  @Test
  public void assertApplicableTax() {
    BigDecimal expectedApplicableTax = new BigDecimal("1.90");
    assertThat(good.getApplicableTax(), is(equalTo(expectedApplicableTax)));
  }

  @Test
  public void assertGoodIsTaxable() {
    assertFalse(good.isImportTaxable());
    assertTrue(good.isSalesTaxable());
    assertTrue(good.isTaxable());
  }

  @Test
  public void assertSuccessfulImportedGoodCreation() {


    assertThat(good.getPrice(), is(equalTo(EXPECTED_PARFUME_PRICE)));
    assertThat(good.getName(), is(equalTo(EXPECTED_PERFUM_NAME)));
    assertThat(good.isImported(), is(false));
    assertThat(good.isOnlySalesTaxable(), is(true));


  }

  @Test(expected = GoodCreationException.class)
  public void assertThrowExceptionWhenPassingWrongCategory() {

    ImportedGood.builder()
        .price(EXPECTED_PARFUME_PRICE)
        .name(EXPECTED_PERFUM_NAME)
        .category(new MedicalSupplies())
        .build();
  }
}
