package at.htl.qute;

import java.math.BigDecimal;

public class Item {

    public BigDecimal price;
    public String name;

    public Item(BigDecimal price, String name) {
        this.price = price;
        this.name = name;
    }

}
