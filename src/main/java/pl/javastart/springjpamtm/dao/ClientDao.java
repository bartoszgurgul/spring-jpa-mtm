package pl.javastart.springjpamtm.dao;

import org.springframework.stereotype.Repository;
import pl.javastart.springjpamtm.model.Client;


import javax.transaction.Transactional;

@Repository
@Transactional
public class ClientDao extends GenericDao<Client, Long> {
    @Override
    public Client get(Long key) {
        Client client = super.get(key);
        client.getOrders().size();
        return client;
    }
}
