package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.searchresult.SearchResult;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void shouldReturnEmptyCollectionWhenStorageIsEmpty() {

        when(storageService.getAllSearchable())
                .thenReturn(List.of());

        Collection<SearchResult> results =
                searchService.search("Test");

        assertThat(results).isEmpty();
    }

    @Test
    void shouldReturnEmptyCollectionWhenNothingMatches() {

        Product product =
                new Product(
                        UUID.randomUUID(),
                        "Phone",
                        1000
                );

        when(storageService.getAllSearchable())
                .thenReturn(List.<Searchable>of(product));

        Collection<SearchResult> results =
                searchService.search("Notebook");

        assertThat(results).isEmpty();
    }

    @Test
    void shouldReturnMatchingProduct() {

        Product product =
                new Product(
                        UUID.randomUUID(),
                        "TestProduct",
                        1500
                );

        when(storageService.getAllSearchable())
                .thenReturn(List.<Searchable>of(product));

        Collection<SearchResult> results =
                searchService.search("Test");

        assertThat(results).hasSize(1);

        SearchResult searchResult =
                results.iterator().next();

        assertThat(searchResult.getName())
                .isEqualTo("TestProduct");
    }

    @Test
    void shouldFindIgnoringCase() {

        Product product =
                new Product(
                        UUID.randomUUID(),
                        "Notebook",
                        2000
                );

        when(storageService.getAllSearchable())
                .thenReturn(List.<Searchable>of(product));

        Collection<SearchResult> results =
                searchService.search("note");

        assertThat(results).hasSize(1);
    }
}