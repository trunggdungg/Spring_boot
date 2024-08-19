package com.example.ktra_lab.controller;

import com.example.ktra_lab.database.ProductDB;
import com.example.ktra_lab.model.PageResponse;
import com.example.ktra_lab.model.PageResponseIMPL;
import com.example.ktra_lab.model.Product;
import com.example.ktra_lab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllProduct(Model model, @RequestParam(required = false) String name) {
        List<Product> newProducts;
        if (name != null && !name.isEmpty()) {
            newProducts = ProductDB.products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .toList();
        } else {
            newProducts = ProductDB.products;
        }
        model.addAttribute("products", newProducts);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String getProductById(Model model, @PathVariable int id) {
        Product product = productService.findProcuctById(id);

        model.addAttribute("products", product);
        return "productDetail";
    }

    //phân trang
    @GetMapping("/products")
    public String getProduct(Model model,
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "5") int size) {

        PageResponse<Product> pageResponse = PageResponseIMPL.<Product>builder()
            .currentPage(page)
            .pageSize(size)
            .data(ProductDB.products)
            .build();

        model.addAttribute("pageResponse", pageResponse);

        return "products";
    }


    @GetMapping("/product/sortByPrice")
    public String sortByPrice(Model model) {
        List<Product> products = productService.sortByPrice();
        model.addAttribute("products", products);
        return "index";

    }


    @GetMapping("/product/fromPrice/{fromPrice}/toPrice/{toPrice}")
    public String getProductByPrice(Model model,
                                    @PathVariable int fromPrice, @PathVariable int toPrice) {
        List<Product> products = productService.findProductByPrice(fromPrice, toPrice);
        model.addAttribute("products", products);
        return "index";
    }


    @GetMapping("/product/name/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        return productService.findProductByName(name);
    }


}
