package secretcloset.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class DiscountedProduct extends Product {
    private int discount;
    private int newPrice;

    public DiscountedProduct(String name, int price, int discount, int newPrice) {
        super(name, price);
        this.discount = discount;
        this.newPrice = newPrice;
    }
}