package com.pos.teksystems.model;

import com.pos.teksystems.model.category.base.Category;
import com.pos.teksystems.model.goods.Good;
import com.pos.teksystems.utils.PurchaseUtils;
import com.pos.teksystems.utils.Utils;
import lombok.Getter;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Purchase {

  @Getter
  private final String title;
  private final Utils purchaseUtils;
  private List<Good> goods = new ArrayList<>();
  private Set<Category> categories = new HashSet<Category>();
  private BigDecimal purchaseTax = new BigDecimal("0.00");
  private BigDecimal purchaseSale = new BigDecimal("0.00");



  public Purchase(String s, Utils purchaseUtils) {
    title = s;
    this.purchaseUtils = purchaseUtils;

  }

  public void addGood(Good good) {
    goods.add(good);
    categories.add(good.getCategory());
  }

  /**
   * Calculates the price of all items contained in the purchase as well as the corresponding taxes
   */
  public void calculateSale() {

    goods.stream()
        .forEach(
            good -> {
              purchaseTax = purchaseTax.add(good.getApplicableTax());
            });

    goods.stream()
        .forEach(
            good -> {
              purchaseSale = purchaseSale.add(good.getPricePlusTax());
            });
  }



  /**
   * Retrieves list of goods per category. If there're no goods, it returns an empty list.
   *
   * @param category
   * @return
   */
  public List<Good> getGoods(Category.NAME category) {
    List<Good> result =
        goods.stream()
            .filter(good -> (category == good.getCategory().getName()))
            .collect(Collectors.toList());
    return result;
  }


  public void printReceipt() {
    System.out.println(getTitle());
    System.out.println("---------------------------------------------");
    categories.forEach((c) -> getGoods(c.getName()).forEach(System.out::println));
    System.out.format("Sales taxes: %5s   Total: %5s \n", purchaseUtils.format(getPurchaseTax()), purchaseUtils.format(getTotalPrice()));
    System.out.println("---------------------------------------------");
  }

  /**
   * Retrieves the total sales tax of the purchase
   *
   * @return total sales tax
   */
  public BigDecimal getPurchaseTax() {

    return purchaseUtils.round(purchaseTax);
  }

  /**
   * Retrieves the total sales, including the total sales tax of the purchase
   *
   * @return total sales plus the corresponding taxes if applicable
   */
  public BigDecimal getTotalPrice() {
    return new BigDecimal(purchaseUtils.format(purchaseSale));
  }
}
