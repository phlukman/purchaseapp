package com.pos.teksystems;

import com.pos.teksystems.model.Purchase;
import com.pos.teksystems.model.category.Books;
import com.pos.teksystems.model.category.Foods;
import com.pos.teksystems.model.category.Music;
import com.pos.teksystems.model.category.base.Category;
import com.pos.teksystems.model.goods.Good;
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

/** Unit test for simple PurchaseApp. */
public class FirstPurchaseTest {
  private Good book;

  private Good musicCD;
  private Good chocolateBar;
  private Purchase firstPurchase;

  final String BOOK_PRICE = "12.49";
  final String MUSIC_CD_PRICE = "14.99";
  final String CHOCOLATE_BAR_PRICE = "0.85";
  final Utils purchaseUtils = new PurchaseUtils();

  @Before
  public void setup() {

    book =
        TaxExemptGood.builder()
            .price(new BigDecimal(BOOK_PRICE))
            .name("1 book")
            .category(new Books())
            .purchaseUtils(purchaseUtils)
            .build();
    musicCD =
        OnlySalesTaxableGood.builder()
            .price(new BigDecimal(MUSIC_CD_PRICE))
            .name("1 music cd")
            .category(new Music())
            .purchaseUtils(purchaseUtils)
            .build();
    chocolateBar =
        TaxExemptGood.builder()
            .price(new BigDecimal(CHOCOLATE_BAR_PRICE))
            .name("Chocolate Bar")
            .category(new Foods())
            .purchaseUtils(purchaseUtils)
            .build();
    firstPurchase = new Purchase("First purchase", purchaseUtils);
    firstPurchase.addGood(book);
    firstPurchase.addGood(musicCD);
    firstPurchase.addGood(chocolateBar);
  }

  @After
  public void teardown() {

    book = null;
    musicCD = null;
    chocolateBar = null;
    firstPurchase = null;
  }

  @Test
  public void assertGoodsPerBookCategory() {
    int NUMBER_OF_BOOKS = 1;
    assertThat(firstPurchase.getGoods(Category.NAME.BOOKS), is(notNullValue()));
    assertThat(firstPurchase.getGoods(Category.NAME.BOOKS).size(), is(NUMBER_OF_BOOKS));
  }

  @Test
  public void assertGoodsPerMusicCategory() {
    int NUMBER_OF_CDS = 1;
    assertThat(firstPurchase.getGoods(Category.NAME.MUSIC), is(notNullValue()));
    assertThat(firstPurchase.getGoods(Category.NAME.MUSIC).size(), is(NUMBER_OF_CDS));
  }

  @Test
  public void assertGoodsPerFoodCategory() {
    int NUMBER_OF_FOODS = 1;
    assertThat(firstPurchase.getGoods(Category.NAME.FOODS), is(notNullValue()));
    assertThat(firstPurchase.getGoods(Category.NAME.FOODS).size(), is(NUMBER_OF_FOODS));
  }

  @Test
  public void assertPurchaseCalculation() {

    BigDecimal expectedTotalSalesTax = new BigDecimal("1.50");
    BigDecimal expectedTotalPrice = new BigDecimal("29.83");

    firstPurchase.calculateSale();
    assertThat(firstPurchase.getPurchaseTax(), is(expectedTotalSalesTax));
    assertThat(firstPurchase.getTotalPrice(), is(expectedTotalPrice));
  }
}
