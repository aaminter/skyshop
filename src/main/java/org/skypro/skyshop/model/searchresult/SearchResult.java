package org.skypro.skyshop.model.searchresult;

import org.skypro.skyshop.model.search.Searchable;

public class SearchResult {

    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable, String contentType) {
        return new SearchResult(
                searchable.getId().toString(),
                searchable.getName(),
                contentType
        );
    }
}