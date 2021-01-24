package pl.javastart.springjpamtm.dao;

import org.springframework.stereotype.Repository;
import pl.javastart.springjpamtm.model.Client;
import pl.javastart.springjpamtm.model.Order;


import javax.transaction.Transactional;

@Repository
@Transactional
public class ClientDao extends GenericDao<Client, Long> {

    /* sama metoda nie dziala poniewaz obiekty orders dalej posidaja wartosc client
    poprawa metody w składnie
    setClient(null) również nic nie da ponieważ powstanie nam jedynie osierocony (orphan ) rekord
    aby ta metoda dawała rade trzeba w encji dodac adnotacje okreslajaca co sie dzieje z encja
    orphanRemove
     */
    public void removeAllOrders(Client client){
        Client managedClient = get(client.getId());
        for(Order order: managedClient.getOrders())
            order.setClient(null);
        managedClient.getOrders().clear();

    }
}
