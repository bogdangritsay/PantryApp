package com.uktech.kladovka.service.pantry;


import com.uktech.kladovka.service.mail.EmailService;
import com.uktech.pantry.domain.*;
import com.uktech.pantry.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private EmailService emailService;


    public void submitOrder(Order activeOrder, User currentUser) {
            activeOrder.setOrderStatus(OrderStatus.IN_DELIVERY);
            activeOrder.setDateOfSubmit(LocalDateTime.now());

            generateAndSendNoteToUser(activeOrder, currentUser);

            orderRepository.save(activeOrder);

            //TODO: fix that
            //emailService.sendSimpleEmail(currentUser.getEmail(), "PantryApp - Order " + activeOrder.getOrderName(), "Order " + activeOrder.getOrderName() + " has submitted!");

            createDefaultOrderForUser(currentUser);
    }

    private void generateAndSendNoteToUser(Order submittedOrder, User currentUser) {
        //TODO SEND notification to user (EMAIL, SMS, Telegram or Viber)
    }


    //TODO  need to think how we can get necessary orderdetail commented for now
    /*
    public void calculateDistance(Product product) {
        Set<OrderDetails> orderDetails = product.getOrderDetails();
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime previousTime = product.getPrevOrderTime();
        LocalDateTime currentTime = product.getCurrentOrderTime();

        //TODO save prev time to history
        product.setPrevOrderTime(currentTime);
        product.setCurrentOrderTime(now);
        int days = Period.between(currentTime.toLocalDate(), now.toLocalDate()).getDays();
        int orderAmount = product.getOrderAmount();
        if (orderAmount > 0) {
            int newDistance = (int) Math.round(days / orderAmount);
            product.setDistanceDays(newDistance);
        } else {
            product.setDistanceDays(days);
        }
    }
    */

    public void updateOrderPrice(Order order) {
        Double price = calculateOrderPrice(order);
        order.setTotalOrderPrice(price);
        orderRepository.save(order);
    }


    private Double calculateOrderPrice(Order order) {
        Collection<OrderItem> orderItems = orderItemService.findOrderItemsByOrder(order);
        Set<Double> setOfSum = orderItems.stream()
                .map(orderItem -> orderItemService.findOrderPriceById(orderItem.getId()))
                .collect(Collectors.toSet());
        return setOfSum.stream()
                .mapToDouble(Double::valueOf).sum();
    }

    public int totalOrderAmount(List<Product> products) {
        return products.stream().
                mapToInt(product -> orderItemService.findOrderAmountById(product.getId())).
                sum();
    }

    public void createOrder(User user) {
        //TODO move this part then uer tried to navigate to pantry????
        //or create def user
/*        long userID = 15L;
        if (user != null) {
            userID = user.getId();
        }

        Order order = new Order(userID, "http://testlink.com", OrderStatus.ACTIVE);
        order.setMaxPrice(userSettings.getDefaultMaxPrice()); //default price 220k
        order.setTotalOrderPrice(0.00);

        //try to find a better way for id generation
        saveOrder(order);

        Long orderID = order.getId();
        order.setOrderName("order " + orderID);

        orderItemService.createNewEmptyOrderDetails(order);
        saveOrder(order);*/
    }

    public void removeOrderItemFromOrder(Long id, Long orderId) {
       /* Optional<Product> optionalProduct = productRepository.findById(id);
        Optional<Order> optionalOrder = orderRepository.findOrderById(orderId);
        Product removedProduct = optionalProduct.orElse(null);
        Order order = optionalOrder.orElse(null);

        List<Product> products = orderDetailsService.getProductByOrderID(orderId);
        if (!products.contains(removedProduct)) {
            return;
        }
        products.remove(removedProduct);
        orderDetailsService.setProducts(products, order);
        order.setTotalOrderPrice(calculateOrderPrice(products));

        saveOrder(order);
        productRepository.save(removedProduct);*/
    }

    //TODO: commented by Bohdan Hrytsai for moving to new model
    public Product editProductOnOrder(Integer id, Order currentOrder) {
       /* return orderDetailsService.getProductByOrderID(currentOrder.getId()).
                stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);*/
        return null;
    }

    //TODO REFACTOR THISSS!!!!
    public void addProductToOrder(Order currentOrder, List<Product> products,
                                  User user, int amount, double price) {
        Set<OrderItem> orderItems = currentOrder.getOrderDetails();
        for (Product addedProduct : products) {

            //addedProduct.setOwner(user);
            //category ? 1 = household chemicals
            //addedProduct.setCategory_id(1);

            //ToDo use calculate dinstance instead
            //addedProduct.setDistanceDays(15);
            //TODO copy description or make it as notes from user?
            addedProduct.setDescription("Product added to order " + currentOrder.getId());

            // TODO проблема , если продуктов два разніх но с одинаковім именем .
            // то цена в количество будут у них одинаковіе


            //TODO: calculate new amount

            OrderItem newOrderItem = new OrderItem(currentOrder, addedProduct, 1, addedProduct.getItemPrice());
           // Double currentOrderTotalPrice = calculateOrderPrice(currentOrder) + newOrderItem.getProduct().getItemPrice();
            boolean isExists = false;
            if (orderItems != null && orderItems.size() != 0) {
               for (OrderItem orderItem : orderItems) {
                   Product product = orderItem.getProduct();
                   if (product.equals(addedProduct)) {
                       orderItem.setOrderAmount(orderItem.getOrderAmount() + 1);
                       orderItem.setTotalPrice(orderItem.getTotalPrice() + addedProduct.getItemPrice());
                       orderItemService.addOrderItem(orderItem);
                       isExists = true;
                       break;
                   }
               }

               if (!isExists) {
                   orderItemService.addOrderItem(newOrderItem);
               }

            } else {
                orderItemService.addOrderItem(newOrderItem);
            }

            Double currentOrderTotalPrice = calculateOrderPrice(currentOrder);
            currentOrder.setTotalOrderPrice(currentOrderTotalPrice);
            saveOrder(currentOrder);

        }

     }


    public int countOrders() {
        return findAllOrders().size();
    }

    public List<Order> findAllOrders() {
        List<Order> all = orderRepository.findAll();
        if (all.isEmpty()) {
            return new ArrayList<>();
        } else {
            return all;
        }
    }

    public List<Order> findActiveOrderOrCreateDefault(OrderStatus orderStatus, long userId) {
        List<Order> orders = orderRepository.findOrderByOrderStatusAndUserId(orderStatus, userId);
        if (orders == null || orders.size() == 0) {
            Order newOrder = createDefaultOrderForUser(userService.findUserById(userId));
            if (newOrder != null) {
                orders.add(newOrder);
            }
            return orders;
        } else {
            return orders;
        }
    }

    public List<Order> findOrderByStatus(OrderStatus orderStatus, long userId) {
        return orderRepository.findOrderByOrderStatusAndUserId(orderStatus, userId);
    }

    public void changeStatusAndSave(Order order, OrderStatus status) {
        order.setOrderStatus(status); // 4= delivered , moved to history
        saveOrder(order);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order createDefaultOrderForUser(User user) {
        Double userMaxPrice = user.getDefaultMaxPrice();
        Order order = new Order("", null, user.getId(), OrderStatus.ACTIVE, userMaxPrice);
        order.setOrderStatus(OrderStatus.ACTIVE);
        order.setTotalOrderPrice(0.00);
        orderRepository.save(order);
        order.setOrderName("#" + order.getId());
        orderRepository.save(order);
        return order;
    }
}
