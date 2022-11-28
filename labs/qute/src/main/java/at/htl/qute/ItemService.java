package at.htl.qute;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;

@ApplicationScoped
public class ItemService {

    public Item findItem(int id) {

        return switch (id) {
            case (4) -> new Item(new BigDecimal("10.20"), "Apfelkuchen");
            case (2) -> new Item(new BigDecimal("129.99"), "Mango");
            default -> throw new RuntimeException("Wrong Item Id");
        };
    }

}
// https://www.netiq.com/documentation/cloud-manager-2-5/ncm-reference/data/bexyssf.html