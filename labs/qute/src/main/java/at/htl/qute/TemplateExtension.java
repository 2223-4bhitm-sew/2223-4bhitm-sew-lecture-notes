package at.htl.qute;

import io.smallrye.common.constraint.NotNull;

import java.math.BigDecimal;

@io.quarkus.qute.TemplateExtension
public class TemplateExtension {

    public static BigDecimal discountedPrice(Item item) {
        return item.price.multiply(new BigDecimal("0.9"));
    }

}
