package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        products = new HashMap<>();
        articles = new HashMap<>();
        initData();
    }

    private void initData() {

        Product milk = new Product(UUID.randomUUID(), "Milk", 120);
        Product apple = new Product(UUID.randomUUID(), "Apple", 50);

        Article article1 =
                new Article(UUID.randomUUID(),
                        "Milk benefits",
                        "Milk is useful");

        Article article2 =
                new Article(UUID.randomUUID(),
                        "Apple guide",
                        "Apple is tasty");

        products.put(milk.getId(), milk);
        products.put(apple.getId(), apple);

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchable() {

        List<Searchable> result = new ArrayList<>();

        result.addAll(products.values());
        result.addAll(articles.values());

        return result;
    }

    public Product getProductById(UUID id) {
        Product product = products.get(id);

        if (product == null) {
            throw new NoSuchProductException(
                    "Товар с id " + id + " не найден"
            );
        }

        return product;
    }

}