package pl.javastart.springjpamtm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javastart.springjpamtm.dao.ClientDao;
import pl.javastart.springjpamtm.dao.OrderDao;
import pl.javastart.springjpamtm.dao.ProductDao;
import pl.javastart.springjpamtm.model.Client;
import pl.javastart.springjpamtm.model.Order;
import pl.javastart.springjpamtm.model.Product;

@SpringBootApplication
public class SpringJpaMtmApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJpaMtmApplication.class, args);

        Client client = new Client("Jan", "Kowalski", "Krakowskie przedmieście 23, Warszawa");
        ClientDao clientDao = ctx.getBean(ClientDao.class);
        clientDao.save(client);
        System.out.println(client);

        Order order = new Order("z dostawą do domu");
        order.setClient(client);
        OrderDao orderDao = ctx.getBean(OrderDao.class);
        orderDao.save(order);

        Product product1 = new Product("Telewizor LG 42'", 4800.0, "dolby surround");
        Product product2 = new Product("Telefon APple iPhone SE", 2200.0, "pokrowiec gratis");
        ProductDao productDao = ctx.getBean(ProductDao.class);
        productDao.save(product1);
        productDao.save(product2);

        orderDao.addProductsToOrder(order.getId(), product1, product2);

        ctx.close();


    }

}
