package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements IRepository<Product> { // Implementasi Interface
    private final List<Product> productData = new ArrayList<>();

    @Override
    public Product create(final Product product) {
        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public Product findById(final String productId) {
        return productData.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product update(final String productId, final Product updatedProduct) {
        final Product product = findById(productId);

        if (product != null) {
            product.setProductName(updatedProduct.getProductName());
            product.setProductQuantity(updatedProduct.getProductQuantity());
            return product;
        }

        return null;
    }

    @Override
    public void delete(final String productId) {
        productData.removeIf(p -> p.getProductId().equals(productId));
    }
}