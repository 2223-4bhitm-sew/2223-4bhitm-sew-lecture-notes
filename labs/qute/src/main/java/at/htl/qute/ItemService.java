package at.htl.qute;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;

@ApplicationScoped
public class ItemService {

    public Item findItem(int id) {
        return new Item(new BigDecimal("10.20"), "Apfelkuchen");
    }

}
