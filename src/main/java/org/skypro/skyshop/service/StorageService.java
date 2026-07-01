package org.skypro.skyshop.service;

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
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        initData();
    }

    private void initData() {

        Product p1 = new Product(UUID.randomUUID(), "Milk", 120);
        Product p2 = new Product(UUID.randomUUID(), "Apple", 50);

        Article a1 = new Article(UUID.randomUUID(), "Milk benefits", "text");
        Article a2 = new Article(UUID.randomUUID(), "Apple guide", "text");

        products.put(p1.getId(), p1);
        products.put(p2.getId(), p2);

        articles.put(a1.getId(), a1);
        articles.put(a2.getId(), a2);
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
}