package com.uktech.kladovka.service.pantry;




import com.uktech.pantry.domain.*;
import com.uktech.pantry.repository.PantryItemRepository;
import com.uktech.pantry.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PantryService {

    @Autowired
    private PantryRepository pantryRepository;

    @Autowired
    private PantryItemRepository pantryItemRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;


  public Pantry createDefaultPantry(User user) {
      Pantry pantry = new Pantry(new HashSet<PantryItems>(), user );
       pantry.setStatus(PantryStatus.ACTIVE); //active status = 1 , not closed = 2
      pantryRepository.save(pantry);
      return pantry;
   }

    //TODO проверить тег ниже , должно работать по расписанию б переделать позже
   // @Scheduled(initialDelay = 8 * 1000L, fixedDelayString = "PT24H")
    void calculatePantryDistance()
    {
        /*
        Pantry currentPantry = getPCurrentPantry();
        Set<Product> products = currentPantry.getProducts();
        Order currentActiveOrder = orderService.findOrderByOrderStatus(1);
        Set<Product> addedProducts = new HashSet<>();
        LocalDateTime now = LocalDateTime.now();

        for(Product pantryProduct :products) {
            LocalDateTime currentOrderTime = pantryProduct.getCurrentOrderTime();
            int distance = pantryProduct.getDistanceDays();
            int dayLeft = pantryProduct.getPantryDayLeft();

            if (dayLeft <= 0)
            {
                if(pantryProduct.getPantryAmount() == 0) {
                    //TODO recalculate distance according to everage time from all orders
                    int orderAm = pantryProduct.getOrderAmount();
                    //как посчитать сколько заказывать товара ?
                    pantryProduct.setOrderAmount(orderAm + 1);
                    pantryProduct.setOrderPrice(pantryProduct.getPrice());
                    addedProducts.add(pantryProduct);

                    productService.saveProduct(pantryProduct);
                }
                else
                {
                    int orderAm = pantryProduct.getOrderAmount();
                    int pantryAm = pantryProduct.getPantryAmount();

                    pantryProduct.setOrderAmount(orderAm +1);
                    pantryProduct.setPantryAmount( pantryAm -1);

                    pantryProduct.setPantryDayLeft(distance);
                    pantryProduct.setOrderPrice(pantryProduct.getPrice());
                    addedProducts.add(pantryProduct);

                    productService.saveProduct(pantryProduct);
                }

            }else
            {
                LocalDateTime nextDayToBuy = currentOrderTime.plusDays(distance);
                int daysLeft = Period.between(nextDayToBuy.toLocalDate(), now.toLocalDate() ).getDays();
                pantryProduct.setPantryDayLeft(daysLeft);
            }
        }

        if(!addedProducts.isEmpty())
        {
            currentActiveOrder.setProducts(addedProducts);
            //TODO do we need to save products also
            orderService.saveOrder(currentActiveOrder);
        }

         */
    }

    public int countPantries() {
        return pantryRepository.findAll().size();
    }

    public Pantry getPCurrentPantry(User user) {
          return  pantryRepository.findPantryByUser(user);
    }

    public void addProductToPantry(Product product, User user) {
        Pantry pantry = getPCurrentPantry(user);
        Set<PantryItems> pantryItemsSet = pantry.getProductDetails();
        PantryItems pantryItem = new PantryItems(1, pantry, product);
        pantryItemsSet.add(pantryItem);
        pantry.setProductDetails(pantryItemsSet);

        pantryRepository.save(pantry);
    }

    public Iterable<PantryItems> filterByProductName(String filter) {
        if (filter != null && !filter.isEmpty()) {
            return pantryItemRepository.findPantryItemsByProductLike(filter);
        } else {
            return  pantryItemRepository.findAll();
        }
    }
}
