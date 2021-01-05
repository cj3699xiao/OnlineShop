package onlineShop.dao;

import onlineShop.entity.Authorities;
import onlineShop.entity.Customer;
import onlineShop.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
// like @component, will under control of spring
public class CustomerDao {
    //Data access object == DAO
    @Autowired
    private SessionFactory sessionFactory;

    public void addCustomer(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setEmailId(customer.getUser().getEmailId());
        authorities.setAuthorities("ROLE_USER");

        Session session = null;

        try {
            session = sessionFactory.openSession();
            //transaction: atomic, process all or abort all, if fail do catch
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit(); // this is end of beginTransaction
        } catch (Exception ex) {
            session.getTransaction().rollback(); // if fail, roll back to beginTransaction
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    //username == email
    public Customer getCustomerByUserName(String userName) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            //try with () : when finished, inside () will closed after try, Session need to implement AutoCloseable interface

            //could add search query into Criteria, like MySQL with select xx where xx
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("emailId", userName));
            user = (User) criteria.uniqueResult();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (user != null) {
            return user.getCustomer();
        }

        return null;
    }
}
