package com.uktech.kladovka.service.pantry;


import com.uktech.pantry.domain.Product;
import com.uktech.pantry.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public Iterable<Product> filterProduct(String filter) {
        if (filter != null && !filter.isEmpty()) {
           return productRepository.findByNameLike(filter);
        } else {
            return  productRepository.findAll();
        }
    }

    //TODO use this function then we recive  order and mov all data to pantry
    public void calculateAmounts(Product product, int orderAmount, boolean addOrDelete) {
        /* TODO: getPantry amount doesn't exist in new db model
        int pantryAmount = product.getPantryAmount();
        if(addOrDelete)
        {
            pantryAmount =  pantryAmount + orderAmount;

        } else
        {
            if (pantryAmount >= orderAmount) {
                pantryAmount = pantryAmount - orderAmount;
            }
            else {
                pantryAmount = pantryAmount = 0;
            }
            product.setPantryAmount(pantryAmount);
        }

        product.setPantryAmount(pantryAmount);
      //  product.setOrderAmount(orderAmount);
         */
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Iterable<Product> findAll() {
       return productRepository.findAll();
    }

    public Product findById(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    public void updateProduct(Product editedProduct, String orderPrice, String orderAmount) {
        if(!orderPrice.isEmpty() && !orderAmount.isEmpty())
        {
            double newOrderPrice = Double.parseDouble(orderPrice);
            int newOrderAmount = Integer.parseInt(orderAmount);
           // editedProduct.setOrderPrice(newOrderPrice);
            //editedProduct.setOrderAmount(newOrderAmount);
            productRepository.save(editedProduct);
        }
    }
}
