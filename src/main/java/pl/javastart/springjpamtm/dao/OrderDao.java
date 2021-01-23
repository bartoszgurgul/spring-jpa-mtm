package pl.javastart.springjpamtm.dao;

import org.springframework.stereotype.Repository;
import pl.javastart.springjpamtm.model.Order;
import pl.javastart.springjpamtm.model.Product;


import javax.transaction.Transactional;

@Repository
@Transactional
public class OrderDao extends GenericDao<Order, Long> {

    public void addProductsToOrder(Long orderId, Product... products){
        Order order = get(orderId);
        if (order!=null){
            for (Product product : products) {
                order.getProducts().add(product);
            }
        }
    }
}
