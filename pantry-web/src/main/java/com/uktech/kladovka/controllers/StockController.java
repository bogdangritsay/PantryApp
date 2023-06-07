package com.uktech.kladovka.controllers;


import com.uktech.kladovka.service.pantry.PantryService;
import com.uktech.kladovka.service.pantry.ProductService;

import com.uktech.kladovka.model.domain.PantryItems;
import com.uktech.kladovka.model.domain.Product;
import com.uktech.kladovka.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StockController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PantryService pantryService;

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal User user, @RequestParam(required = false, defaultValue = "") String filter,
                           Model model) {

        if (user != null ) {
            int count = pantryService.countPantries();
            if(count == 0) {
                pantryService.createDefaultPantry(user);
            }
        }

        Iterable<PantryItems> pantryItems = pantryService.filterByProductName(filter);
        model.addAttribute("products", pantryItems);
        model.addAttribute("filter", filter);
        model.addAttribute("isActive" , "active");
        return"pantrylist";
    }

    @GetMapping(value = "/main/product/{id}")
    public String getOrderProductById(Model model, @PathVariable Integer id) {
        //TODO: commented while moving to new db model
        /*
        Pantry pantry = pantryService.getPCurrentPantry();
        Stream<Product> stream = pantry.getProducts().stream();
        Optional<Product> first = stream.filter(product -> product.getId().equals(id)).findFirst();
        Product product = null;
        try {
           product =  first.get();
        } catch (NoSuchElementException ex)
        {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("product", product);
        model.addAttribute("allowDelete", false);
        model.addAttribute("isPantry", true);
         */

        return "viewproduct";
    }


    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String name,
                      @RequestParam(defaultValue = "0") String amount, //liters , kg etc
                      @RequestParam(defaultValue = "0") int pantryAmount, //количество штук
                      @RequestParam double price, Model model){

        //TODO add product creation to Service
        Product product =  new Product(name, null, null, price, null, null,null);

        pantryService.addProductToPantry(product, user);

        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "pantrylist";
    }
}
