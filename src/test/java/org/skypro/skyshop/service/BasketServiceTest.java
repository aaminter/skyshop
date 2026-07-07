package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {

        UUID id = UUID.randomUUID();

        when(storageService.getProductById(id))
                .thenThrow(new NoSuchProductException("Not found"));

        assertThrows(
                NoSuchProductException.class,
                () -> basketService.addProduct(id)
        );

        verify(productBasket, never()).addProduct(any());
    }

    @Test
    void shouldAddProductWhenProductExists() {

        UUID id = UUID.randomUUID();

        Product product = new Product(
                id,
                "Phone",
                1000
        );

        when(storageService.getProductById(id))
                .thenReturn(product);

        basketService.addProduct(id);

        verify(productBasket, times(1))
                .addProduct(id);
    }

    @Test
    void shouldReturnEmptyBasket() {

        when(productBasket.getProducts())
                .thenReturn(Map.of());

        UserBasket basket = basketService.getUserBasket();

        assertThat(basket.getItems()).isEmpty();
        assertThat(basket.getTotal()).isZero();
    }

    @Test
    void shouldReturnFilledBasket() {

        UUID id = UUID.randomUUID();

        Product product = new Product(
                id,
                "Phone",
                1000
        );

        when(productBasket.getProducts())
                .thenReturn(Map.of(id, 3));

        when(storageService.getProductById(id))
                .thenReturn(product);

        UserBasket basket = basketService.getUserBasket();

        assertThat(basket.getItems())
                .hasSize(1);

        assertThat(basket.getItems().get(0).getQuantity())
                .isEqualTo(3);

        assertThat(basket.getItems().get(0).getProduct())
                .isEqualTo(product);

        assertThat(basket.getTotal())
                .isEqualTo(3000);
    }
}