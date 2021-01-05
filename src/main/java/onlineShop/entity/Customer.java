package onlineShop.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    // this number does not matter
    private static final long serialVersionUID = 2652327633296064143L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // will auto generate id for this class, default as ascending order
    private int id;
    private String firstName;
    private String lastName;
    private String customerPhone;

    // Cascade: when save this class, will save all these cascade attributes
    // fetch LAZY vs EAGER
    // Lazy: when pick entity, don't get associate class until user ask
    // Eager: get associate class information immediately
    // here, SA will get information directly !!
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ShippingAddress shippingAddress;

    //map customer with billing address
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BillingAddress billingAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cart cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
