package com.uktech.kladovka.controllers.productcatalog;

import com.uktech.kladovka.controllers.productcatalog.ProductCatalogController;
import com.uktech.kladovka.model.domain.*;
import com.uktech.kladovka.service.pantry.CategoryService;
import com.uktech.kladovka.service.pantry.OrderService;
import com.uktech.kladovka.service.pantry.ProductService;
import com.uktech.kladovka.service.pantry.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProductCatalogControllerTest {

    @Mock
    private CategoryService categoryService;
    @Mock
    private StoreService storeService;
    @Mock
    private ProductService productService;
    @Mock
    private OrderService orderService;
    @Mock
    private Model model;

    @InjectMocks
    private ProductCatalogController productCatalogController;

    @BeforeEach
    public void setup() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCurrentOrderPage() {
        List<Product> allProducts = new ArrayList<>();
        when(productService.findAll()).thenReturn(allProducts);

        String result = productCatalogController.currentOrderPage(model);

        verify(productService).findAll();
        verify(model).addAttribute(eq("products"), eq(allProducts));
        assertEquals("catalog/product_catalog", result);
    }

    @Test
    public void testAddProductToOrder() {
        Long productId = 1L;
        User user = new User();
        user.setId(1L); // Встановлення значення ідентифікатора

        Product product = new Product();
        product.setItemPrice(10.10D);
        List<Product> products = new ArrayList<>();
        products.add(product);
        Order order = new Order();
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        when(productService.findById(productId)).thenReturn(product);
        when(orderService.findActiveOrderOrCreateDefault(eq(OrderStatus.ACTIVE), anyLong())).thenReturn(orderList);

        String result = productCatalogController.addProductToOrder(productId, user, model);

        verify(productService).findById(productId);
        verify(orderService).findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId());
        verify(orderService).addProductToOrder(eq(order), eq(products), eq(user), eq(1), eq(product.getItemPrice()));
        assertEquals("redirect:/catalog", result);
    }


    @Test
    public void testAddNewProductPage() {
        List<Category> categories = new ArrayList<>();
        List<Store> stores = new ArrayList<>();
        when(categoryService.findAll()).thenReturn(categories);
        when(storeService.findAll()).thenReturn(stores);

        String result = productCatalogController.addNewProduct(model);

        verify(categoryService).findAll();
        verify(storeService).findAll();
        verify(model).addAttribute(eq("categories"), eq(categories));
        verify(model).addAttribute(eq("stores"), eq(stores));
        assertEquals("catalog/add_product_to_catalog", result);
    }

    @Test
    public void testAddNewProduct() throws MalformedURLException {
        String name = "Test Product";
        Long categoryId = 1L;
        String description = "Test Description";
        float itemPrice = 9.99f;
        Long storeId = 1L;
        String link = "http://example.com";
        Category category = new Category();
        Store store = new Store();
        when(categoryService.findById(categoryId)).thenReturn(category);
        when(storeService.findById(storeId)).thenReturn(store);

        String result = productCatalogController.addNewProduct(name, categoryId, description, itemPrice, storeId, link, model);

        verify(categoryService).findById(categoryId);
        verify(storeService).findById(storeId);
        verify(productService).saveProduct(argThat(product ->
                product.getName().equals(name) &&
                        product.getCategory().equals(category) &&
                        product.getDescription().equals(description) &&
                        product.getItemPrice().equals(Double.valueOf(itemPrice)) &&
                        product.getStore().equals(store))
        );
        assertEquals("redirect:/catalog", result);
    }

}
