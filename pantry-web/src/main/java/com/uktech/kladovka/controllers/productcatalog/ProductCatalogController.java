package com.uktech.kladovka.controllers.productcatalog;

import com.uktech.kladovka.model.domain.*;
import com.uktech.kladovka.service.pantry.CategoryService;
import com.uktech.kladovka.service.pantry.OrderService;
import com.uktech.kladovka.service.pantry.ProductService;
import com.uktech.kladovka.service.pantry.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


@Controller
@Slf4j
public class ProductCatalogController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/catalog")
    public String currentOrderPage(Model model) {
           // List<Product> allProducts = (List<Product>) productService.filterProduct(filter);
            List<Product> allProducts = (List<Product>) productService.findAll();
            model.addAttribute("products", allProducts);
        return "catalog/product_catalog";
    }

    @PostMapping("/catalog-add")
    public String addProductToOrder(@RequestParam Long id,
                                    @AuthenticationPrincipal User user,
                                    Model model) {
        Product product = productService.findById(id);
        List<Product> products = new ArrayList<>();
        products.add(product);
        Order currentOrder = orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId()).stream().findFirst().get();

        orderService.addProductToOrder(currentOrder, products, user, 1, product.getItemPrice());
        return "redirect:/catalog";
    }



    @GetMapping("/catalog/add-product")
    public String addNewProduct(Model model) {
        List<Category> allCategories = (List<Category>) categoryService.findAll();
        List<Store> allStores = (List<Store>) storeService.findAll();
        model.addAttribute("categories", allCategories);
        model.addAttribute("stores", allStores);
        return "catalog/add_product_to_catalog";
    }

    @PostMapping("/catalog/add-product")
    public String addNewProduct(@RequestParam String name,
                                @RequestParam Long category,
                                @RequestParam String description,
                                @RequestParam float itemPrice,
                                @RequestParam Long store,
                                @RequestParam String link,
                                Model model) throws MalformedURLException {
        Category categoryObj = categoryService.findById(category);
        Store storeObj = storeService.findById(store);
        Product newProduct = new Product(name, description, categoryObj, Double.valueOf(itemPrice), null, storeObj, new URL(link));
        productService.saveProduct(newProduct);
        return "redirect:/catalog";
    }

}
