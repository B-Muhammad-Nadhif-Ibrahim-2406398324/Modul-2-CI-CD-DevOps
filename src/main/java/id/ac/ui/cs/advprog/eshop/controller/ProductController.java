package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;



@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;
    private final long serviceStartTime;

    @SuppressWarnings("PMD")
    @Autowired
    public ProductController(final ProductService service) { // NOPMD
        this.service = Objects.requireNonNull(service, "ProductService must not be null");
        this.serviceStartTime = System.currentTimeMillis();
    }

    @GetMapping("/create")
    public String createProductPage(final Model model) {
        final Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute final Product product, final Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(final Model model) {
        final List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable("productId") final String productId, final Model model) {
        final Product product = service.findById(productId);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute("product") final Product product) {
        service.update(product);
        return "redirect:list";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable final String productId) {
        service.delete(productId);
        return "redirect:../list";
    }
}