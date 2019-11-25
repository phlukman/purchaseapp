package com.pos.teksystems;

import com.pos.teksystems.model.Purchase;
import com.pos.teksystems.model.category.*;
import com.pos.teksystems.model.goods.Good;
import com.pos.teksystems.model.goods.ImportedGood;
import com.pos.teksystems.model.goods.OnlySalesTaxableGood;
import com.pos.teksystems.model.goods.TaxExemptGood;
import com.pos.teksystems.utils.PurchaseUtils;
import com.pos.teksystems.utils.Utils;

import java.math.BigDecimal;

public class PurchaseApp {

  public static void main(String[] args) {
    Utils purchaseUtils = new PurchaseUtils();
    Purchase purchase = buildFirstPurchase(purchaseUtils);
    purchase.calculateSale();
    purchase.printReceipt();

    purchase = buildSecondtPurchase(purchaseUtils);
    purchase.calculateSale();
    purchase.printReceipt();

    purchase = buildThirdPurchase(purchaseUtils);
    purchase.calculateSale();
    purchase.printReceipt();
  }

  private static Purchase buildFirstPurchase(Utils purchaseUtils) {

    String BOOK_PRICE = "12.49";
    String MUSIC_CD_PRICE = "14.99";
    String CHOCOLATE_BAR_PRICE = "0.85";
    Good book =
        TaxExemptGood.builder()
            .price(new BigDecimal(BOOK_PRICE))
            .name("1 book")
            .category(new Books())
            .purchaseUtils(purchaseUtils)
            .build();
    Good musicCD =
        OnlySalesTaxableGood.builder()
            .price(new BigDecimal(MUSIC_CD_PRICE))
            .name("1 music CD")
            .category(new Music())
            .purchaseUtils(purchaseUtils)
            .build();
    Good chocolateBar =
        TaxExemptGood.builder()
            .price(new BigDecimal(CHOCOLATE_BAR_PRICE))
            .name("1 chocolate bar")
            .category(new Foods())
            .purchaseUtils(purchaseUtils)
            .build();
    Purchase firstPurchase = new Purchase("Purchase #1", purchaseUtils);
    firstPurchase.addGood(book);
    firstPurchase.addGood(musicCD);
    firstPurchase.addGood(chocolateBar);
    return firstPurchase;
  }

  private static Purchase buildSecondtPurchase(Utils purchaseUtils) {

    String IMPORTED_FOOD_PRICE = "10.00";
    String IMPORTED_FRAGANCE_PRICE = "47.50";
    Good importedFood =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_FOOD_PRICE))
            .name("1 box of chocolates")
            .category(new ImportedFoods())
            .purchaseUtils(purchaseUtils)
            .build();
    Good importedFragance =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_FRAGANCE_PRICE))
            .name("1 imported fragance")
            .category(new ImportedFragances())
            .purchaseUtils(purchaseUtils)
            .build();
    Purchase purchase = new Purchase("Purchase #2", purchaseUtils);
    purchase.addGood(importedFood);
    purchase.addGood(importedFragance);

    return purchase;
  }

  private static Purchase buildThirdPurchase(Utils purchaseUtils) {

    String IMPORTED_FRAGANCE_PRICE = "27.99";
    String FRAGANCE_PRICE = "18.99";
    String HEADACHE_PILLS_PRICE = "9.75";
    String IMPORTED_CHOCOLATE_PRICE = "11.25";

    Good importedFragance =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_FRAGANCE_PRICE))
            .name("1 imported bottle of perfume")
            .category(new ImportedFragances())
            .purchaseUtils(purchaseUtils)
            .build();
    Good fragance =
        OnlySalesTaxableGood.builder()
            .price(new BigDecimal(FRAGANCE_PRICE))
            .name("1 bottle of perfume")
            .category(new Fragance())
            .purchaseUtils(purchaseUtils)
            .build();
    Good headachePills =
        TaxExemptGood.builder()
            .price(new BigDecimal(HEADACHE_PILLS_PRICE))
            .name("1 packet of headache pills:")
            .category(new MedicalSupplies())
            .purchaseUtils(purchaseUtils)
            .build();
    Good importedChocolate =
        ImportedGood.builder()
            .price(new BigDecimal(IMPORTED_CHOCOLATE_PRICE))
            .name("1 imported box of chocolates")
            .category(new ImportedFoods())
            .purchaseUtils(purchaseUtils)
            .build();

    Purchase purchase = new Purchase("Purchase #3", purchaseUtils);
    purchase.addGood(fragance);
    purchase.addGood(importedFragance);
    purchase.addGood(headachePills);
    purchase.addGood(importedChocolate);

    return purchase;
  }
}
