package pl.javastart.springjpamtm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client implements Serializable {
    private static final Long serialUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    /* ustawienie cascade persist pozwoli ze podczas zapisywania klienta wsystkie powiązanie biekty zostaną zapisane kaskadowo

     */
    @OneToMany(
            mappedBy = "client", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
    orphanRemoval = true) // metoda mowi co robi z powstałymi sieroami tej relacji
    private List<Order> orders = new ArrayList<>();

    /* W JPA nalezy zadbac nad dwukierunkowym przepływem danych
    poniewaz podczas zapisywania kaskadowego nie zaostał przypisany order realcja do jakiego klienta nalezy
    client -> order -> product ( brakuje tutaj przypisania id do order do client id )
    aby tego dokonac nalezy zapisywac metode addXXX gdzie xxx jest obiektem w tej relacji

    W sumie ciekawe dlaczego nie mozna zwyklego seta zmienić pewnie dlatego ze ogolnie nie powinno sie tego poruszac
    takie umowne ze generowane metody powinny takie pozostac...
     */
    public void addOrder(Order order){
        order.setClient(this); // ustawienie relacji jakiej nam brakowało
        getOrders().add(order);
    }

    public Client() {
    }

    public Client(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", address=" + address + orders.size()
                + ",\n orders=" + orders + "]";
    }
}
