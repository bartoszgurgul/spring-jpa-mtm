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

        Order order = new Order("z dostawą do domu");
        Product product1 = new Product("Telewizor LG 42'", 4800.0, "dolby surround");
        Product product2 = new Product("Telefon APple iPhone SE", 2200.0, "pokrowiec gratis");
        order.getProducts().add(product1);
        order.getProducts().add(product2);
        client.addOrder(order);


        clientDao.save(client);

        Client getClient = clientDao.get(client.getId());
        System.out.println(getClient);

        clientDao.remove(client);

        ctx.close();


    }

}
