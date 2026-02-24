package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    public ProductRepository() {
        super();
    }

    public Product create(final Product product) {
        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    public Product findById(final String productId) {
        return productData.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public Product update(final Product updatedProduct) {
        final Product product = findById(updatedProduct.getProductId());
        Product updatedResult = null;

        if (product != null) {
            product.setProductName(updatedProduct.getProductName());
            product.setProductQuantity(updatedProduct.getProductQuantity());
            updatedResult = product;
        }

        return updatedResult;
    }

    public void delete(final String productId) {
        productData.removeIf(p -> p.getProductId().equals(productId));
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}