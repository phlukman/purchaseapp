package com.pos.teksystems;

import com.pos.teksystems.model.Purchase;
import com.pos.teksystems.model.category.*;
import com.pos.teksystems.model.goods.Good;
import com.pos.teksystems.model.goods.ImportedGood;
import com.pos.teksystems.model.goods.OnlySalesTaxableGood;
import com.pos.teksystems.model.goods.TaxExemptGood;

import java.math.BigDecimal;

public class PurchaseApp {

  public static void main(String[] args) {

    Purchase purchase = buildFirstPurchase();
    purchase.calculateSale();
    purchase.printReceipt();

    purchase = buildSecondtPurchase();
    purchase.calculateSale();
    purchase.printReceipt();

    purchase = buildThirdPurchase();
    purchase.calculateSale();
    purchase.printReceipt();
  }

  private static Purchase buildFirstPurchase() {

    String BOOK_PRICE = "12.49";
    String MUSIC_CD_PRICE = "14.99";
    String CHOCOLATE_BAR_PRICE = "0.85";
    Good book =
        TaxExemptGood.builder()
            .price(new BigDecimal(BOOK_PRICE))
            .name("1 book")
            .category(new Books())
            .build();
    Good musicCD =
        OnlySalesTaxableGood.builder()
            .price(new BigDecimal(MUSIC_CD_PRICE))
            .name("1 music CD")
            .category(new Music())
            .build();
    Good chocolateBar =
        TaxExemptGood.builder()
            .price(new BigDecimal(CHOCOLATE_BAR_PRICE))
            .name("1 chocolate bar")
            .category(new Foods())
            .build();
    Purchase firstPurchase = new Purchase("Purchase #1");
    firstPurchase.addGood(book);
    firstPurchase.addGood(musicCD);
    firstPurchase.addGood(chocolateBar);
    return firstPurchase;
  }

  private static Purchase buildSecondtPurchase() {

    String IMPORTED_FOOD_PRICE = "10.00";
    String IMPORTED_FRAGANCE_PRICE = "47.50";
    Good importedFood =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_FOOD_PRICE))
            .name("1 box of chocolates")
            .category(new ImportedFoods())
            .build();
    Good importedFragance =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_FRAGANCE_PRICE))
            .name("1 imported fragance")
            .category(new ImportedFragances())
            .build();
    Purchase purchase = new Purchase("Purchase #2");
    purchase.addGood(importedFood);
    purchase.addGood(importedFragance);

    return purchase;
  }

  private static Purchase buildThirdPurchase() {

    String IMPORTED_FRAGANCE_PRICE = "27.99";
    String FRAGANCE_PRICE = "18.99";
    String HEADACHE_PILLS_PRICE = "9.75";
    String IMPORTED_CHOCOLATE_PRICE = "11.25";


    Good importedFragance =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_FRAGANCE_PRICE))
            .name("1 imported bottle of perfume")
            .category(new ImportedFragances())
            .build();
    Good fragance =
        OnlySalesTaxableGood.builder()
            .price(new BigDecimal(FRAGANCE_PRICE))
            .name("1 bottle of perfume")
            .category(new Fragance())
            .build();
    Good headachePills =
        TaxExemptGood.builder()
            .price(new BigDecimal(HEADACHE_PILLS_PRICE))
            .name("1 packet of headache pills:")
            .category(new MedicalSupplies())
            .build();
    Good importedChocolate =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_CHOCOLATE_PRICE))
            .name("1 imported box of chocolates")
            .category(new ImportedFoods())
            .build();

    Purchase purchase = new Purchase("Purchase #3");
    purchase.addGood(fragance);
    purchase.addGood(importedFragance);
    purchase.addGood(headachePills);
    purchase.addGood(importedChocolate);

    return purchase;
  }
}
