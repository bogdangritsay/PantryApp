package com.uktech.kladovka.bootstrap;


import com.uktech.pantry.domain.Pantry;
import com.uktech.pantry.domain.User;
import com.uktech.pantry.repository.*;
import org.springframework.boot.CommandLineRunner;


//@Component
public class TestDataLoader implements CommandLineRunner {

    private UserRepository userRepository;
    private PantryRepository pantryRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private OrderDetailsRepository orderDetailsRepository;

    public TestDataLoader(UserRepository userRepository, PantryRepository pantryRepository,
                          ProductRepository productRepository, OrderRepository orderRepository,
                          OrderDetailsRepository orderDetailsRepository) {
        this.userRepository = userRepository;
        this.pantryRepository = pantryRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       /* User user = loadUserData();
        Pantry pantry = loadPantryData();
        loadProductsData(user, pantry);
        loadOrderData();
        */
    }

    private void loadOrderData() {
        /*
        Order order = new Order();
        order.setOrderName("Test Order");
        order.setOrderTotalAmount(0);
        order.setOrderStatus(1); // Active
        order.setShopLink("https://rozetka.com.ua/");
        order.setUserid(1L);

        orderRepository.save(order);

        Product product1 = productRepository.findAll().iterator().next();// getFirst;

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrder(order);
        orderDetails.setProduct(product1);
        orderDetails.setOrderPrice(product1.getPrice());
        orderDetails.setOrderAmount(1);
        orderDetails.setCurrentOrderTime(LocalDateTime.now());

        orderDetailsRepository.save(orderDetails);
        */
    }

    private void loadProductsData(User user, Pantry pantry) {
/*
        Product product1 = new Product();
        product1.setName("Таблетки для посудомоечных машин FINISH Quantum Ultimate 40 шт");
        product1.setAmount("1");
        product1.setCategory_id(1);
        product1.setDistanceDays(90);
        product1.setPantryDayLeft(85);
        product1.setPantry(pantry);
        //product1.setOwner(user);

        productRepository.save(product1);


        Product product2 = new Product();
        product2.setName("Бумажные полотенца Ruta Soft & Strong 87 отрывов 3 слоя 8 рулонов Белые");
        product2.setAmount("4");
        product2.setCategory_id(2);
        product2.setDistanceDays(30);
        product2.setPantryDayLeft(30);
        product2.setPantry(pantry);
        //product2.setOwner(user);

        productRepository.save(product2);


*/


    }

    private Pantry loadPantryData() {
       /* Pantry pantry = new Pantry();
        pantry.setStatus(1); // Active
        pantry.setUserid(1L);
        pantry.setDescription("test pantry");
        return pantryRepository.save(pantry);*/

        return null;
    }


    private User loadUserData() {
        /*User testUser = new User();
        testUser.setActive(true);
        testUser.setUsername("test");
        testUser.setPassword("test");
        testUser.setPassword2("test");
        testUser.getRoles().add(Role.USER);
        return userRepository.save(testUser);
        */
        return null;
    }
}
