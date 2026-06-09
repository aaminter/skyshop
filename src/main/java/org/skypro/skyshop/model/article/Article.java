package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {

    private final UUID id;
    private final String name;
    private final String content;

    public Article(UUID id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @JsonIgnore
    public String getSearchTerm() {
        return name;
    }

    @JsonIgnore
    public String getContentType() {
        return "ARTICLE";
    }
}