package onlineShop.dao;

import onlineShop.entity.CartItem;
import onlineShop.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.entity.SalesOrder;

@Repository
public class SalesOrderDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ProductDao productDao;

    public void addSalesOrder(SalesOrder salesOrder) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            //maybe change later, not standard, to add missing information
            salesOrder.setCustomer(salesOrder.getCart().getCustomer());
            salesOrder.setBillingAddress(salesOrder.getCart().getCustomer().getBillingAddress());
            salesOrder.setShippingAddress(salesOrder.getCart().getCustomer().getShippingAddress());
            // To change the quality left, not currency safe!!!
            for(CartItem cur : salesOrder.getCart().getCartItem()) {
                Product product = cur.getProduct();
                Long newUnit = product.getUnitStock();
                newUnit -= cur.getQuantity();
                product.setUnitStock(newUnit);
                // delete if none left
                if(newUnit > 0) {
                    productDao.updateProduct(product);
                } else if(1 > newUnit){
                    productDao.deleteProduct(product.getId());
                }
            }

            //
            session.save(salesOrder);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}