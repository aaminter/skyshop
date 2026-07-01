package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.searchresult.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {

        return storageService.getAllSearchable().stream()
                .filter(s -> s.getName().toLowerCase().contains(pattern.toLowerCase()))
                .map(s -> SearchResult.fromSearchable(s, s.getClass().getSimpleName().toUpperCase()))
                .collect(Collectors.toList());
    }
}