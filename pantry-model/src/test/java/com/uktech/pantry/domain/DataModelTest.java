
package com.uktech.pantry.domain;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataModelTest {

    @Test
    public void checkModel() throws MalformedURLException, MalformedURLException {

        User pantryUser = new User();
        pantryUser.setActive(true);
        pantryUser.setUsername("pantryUser");
        pantryUser.setPassword("test");
        pantryUser.setPassword2("test");

        assertEquals(0,  pantryUser.getRoles().size());


        pantryUser.getRoles().add(Role.USER);

        assertEquals(1, pantryUser.getRoles().size());


        User pantryAdmin = new User();
        pantryAdmin.setActive(true);
        pantryAdmin.setPassword("testAdmin");
        pantryAdmin.setPassword2("testAdmin");
        pantryAdmin.getRoles().add(Role.ADMIN);

        User inactiveUser = new User();
        inactiveUser.setActive(false);
        inactiveUser.getRoles().add(Role.USER);



        Store amazonStore = new Store();
        amazonStore.setName("Amazon");
        amazonStore.setLinkToStore(new URL("https://www.amazon.com/"));

        Store rozetkaStore = new Store();
        rozetkaStore.setName("Rozetka");
        rozetkaStore.setLinkToStore(new URL("https://rozetka.com.ua"));

        Category oralCareCategory = new Category();
        oralCareCategory.setName("Oral care");

        Category skinCareCategory = new Category();
        skinCareCategory.setName("Skin Care");

        Category toothbrushesCategoryAndAccessories = new Category();
        toothbrushesCategoryAndAccessories.setName("Toothbrushes & Accessories");
        toothbrushesCategoryAndAccessories.setParentCategory(oralCareCategory);

        Category poweredToothbrushes = new Category();
        poweredToothbrushes.setName("Powered Toothbrushes & Accessories");
        poweredToothbrushes.setParentCategory(toothbrushesCategoryAndAccessories);

        Product crestProduct = new Product();
        crestProduct.setName("Crest 3D White Toothpaste Radiant Mint");
        crestProduct.setStore(amazonStore);
        crestProduct.setItemPrice(9.18);
        crestProduct.setLinkToProduct(new URL("https://www.amazon.com/Crest-White-Toothpaste-Radiant-Mint/dp/B01KZOTRG8/"));

        Product doveWithPump = new Product();
        doveWithPump.setName("Dove Body Wash with Pump");
        doveWithPump.setCategory(skinCareCategory);
        doveWithPump.setStore(amazonStore);
        doveWithPump.setLinkToProduct(new URL("https://www.amazon.com/Dove-Body-Wash-Pump-Moisture/dp/B00MEDOY2G/"));
        doveWithPump.setItemPrice(8.94);


        Product philipsSonicare = new Product();
        philipsSonicare.setName("Philips Sonicare HX9023/65 Genuine C2 Optimal Plaque Control Toothbrush Head, 3 Pack, White");
        philipsSonicare.setStore(rozetkaStore);
        philipsSonicare.setItemPrice(1999D);
        philipsSonicare.setLinkToProduct(new URL("https://bt.rozetka.com.ua/24956641/p24956641/"));
        philipsSonicare.setCategory(poweredToothbrushes);


        Pantry activePantry = new Pantry();
        activePantry.setStatus(PantryStatus.ACTIVE);

        Set<PantryItems> productDetails = activePantry.getProductDetails();
        productDetails.add(new PantryItems(crestProduct, 1));
        productDetails.add(new PantryItems(doveWithPump, 2));
        productDetails.add(new PantryItems(philipsSonicare, 4));
        activePantry.setProductDetails(productDetails);



        assertEquals(activePantry.getStatus(), PantryStatus.ACTIVE);
        assertEquals(activePantry.getProductDetails().size(), 3);






    }


}
