package com.uktech.kladovka.service.pantry;


import com.uktech.pantry.domain.*;
import com.uktech.pantry.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class DeliveryService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PantryService pantryService;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public void deliveredProductsSaveInPantry(@AuthenticationPrincipal User user, Pantry pCurrentPantry, Order orderInDelivery) {

        //TODO: commented while moving to new db model
        /*
        List<Product> deliveredProducts = orderDetailsRepository.findProductByorder(orderInDelivery.getOrderId().longValue());
        Set<Product> pantryProducts = pCurrentPantry.getProducts();

        List<Product> changed = deliveredProducts.stream().filter(pantryProducts::contains).collect(Collectors.toList());
        changed.forEach(dproduct -> {
                    int pAmount = dproduct.getPantryAmount();
                    dproduct.setPantryAmount(orderDetailsRepository.findOrderAmountByid(dproduct.getId().longValue()) + pAmount);
                    dproduct.setPrice(orderDetailsRepository.findOrderPriceByid(dproduct.getId().longValue()));
                   // orderService.calculateDistance(dproduct);
                    //dproduct.setOwner(user);
                    dproduct.setDescription("delivered in " + orderInDelivery.getOrderName());
                    pantryService.addProductToPantry(dproduct);
                }
        );
        */
    }

    public void moveOrderInDeliveryToHistory(Order orderInDelivery) {
        orderInDelivery.setOrderDescription(orderInDelivery.getOrderName() + " deleivered " + LocalDateTime.now());
        orderInDelivery.setDeliveryDate(LocalDateTime.now());
        moveOrderToHistory();
        orderService.changeStatusAndSave(orderInDelivery, OrderStatus.COMPLETED);
    }

    public void moveOrderToHistory() {

    }
}
