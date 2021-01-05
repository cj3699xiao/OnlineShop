package onlineShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineShop.dao.ProductDao;
import onlineShop.entity.Product;

@Service
public class ProductService {

    //Map<Integer, Product> cache;

    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProducts() {
        // if we have cache, we could check cache here, if available, no needs to
        // get data from DB. Else we need to get from DB.
        return productDao.getAllProducts();
    }

    public Product getProductById(int productId) {
        return productDao.getProductById(productId);
    }

    public void deleteProduct(int productId) {
        productDao.deleteProduct(productId);
    }

    public void addProduct(Product product){
        productDao.addProduct(product);
    }

    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }
}
