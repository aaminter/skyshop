package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.skypro.skyshop.exception.NoSuchProductException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket productBasket,
                         StorageService storageService) {

        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        storageService.getProductById(id);
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {

        List<BasketItem> items =
                productBasket
                        .getProducts()
                        .entrySet()
                        .stream()
                        .map(entry -> {

                            Product product = storageService.getProductById(entry.getKey());

                            return new BasketItem(
                                    product,
                                    entry.getValue()
                            );

                        })
                        .collect(Collectors.toList());

        return new UserBasket(items);
    }

}