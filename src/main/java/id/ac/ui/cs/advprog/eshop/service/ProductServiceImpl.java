package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final IRepository<Product> productRepository;

    @Autowired
    public ProductServiceImpl(final IRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(final Product product) {
        return productRepository.create(product);
    }

    @Override
    public List<Product> findAll() {
        final Iterator<Product> productIterator = productRepository.findAll();
        final List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findById(final String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product update(final String productId, final Product product) {
        return productRepository.update(productId, product);
    }

    @Override
    public void delete(final String productId) {
        productRepository.delete(productId);
    }
}