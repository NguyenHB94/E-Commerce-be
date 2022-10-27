package com.tm.j10;

import com.tm.j10.domain.Category;
import com.tm.j10.repository.CategoryRepository;
import com.tm.j10.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class BasicTest {

    @Mock
    CategoryRepository categoryRepository;


    @Test
    public void testAddTwoLongInCategoryService() {
        // Given
        Long fakeNumberOne = 20L;
        Long fakeNumberTwo = 42L;
        Long expectedResult = 62L;

        // When
        CategoryService testService = new CategoryService(categoryRepository);
        var result = testService.addTwoLong(fakeNumberOne, fakeNumberTwo);

        // Then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testAddTwoLongInCategoryServiceWhenOneIsZero() {
        // Given
        Long fakeNumberOne = 0L;
        Long fakeNumberTwo = 42L;
        Long expectedResult = 2L;

        // When
        CategoryService testService = new CategoryService(categoryRepository);
        var result = testService.addTwoLong(fakeNumberOne, fakeNumberTwo);

        // Then
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateCategoryHappyCase() {
        // Given
        Long fakeId = 1L;
        Category fakeCategory = new Category();
        fakeCategory.setCategoryName("Phake");
        fakeCategory.setId(1L);
        fakeCategory.setIsEnable(true);

        CategoryRepository fakeRepo = Mockito.mock(CategoryRepository.class);
        Mockito.when(fakeRepo.findById(Mockito.any())).thenReturn(Optional.of(fakeCategory));
        Mockito.when(fakeRepo.save(fakeCategory)).thenReturn(new Category());

        // When


        CategoryService testService = new CategoryService(fakeRepo);
        var result = testService.updateCategory(fakeId, fakeCategory);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void testUpdateCategoryWhenNoCategoryFound() {
        // Given
        Long fakeId = 1L;
        Category fakeCategory = new Category();
        fakeCategory.setCategoryName("Phake");
        fakeCategory.setId(1L);
        fakeCategory.setIsEnable(true);

        CategoryRepository fakeRepo = Mockito.mock(CategoryRepository.class);
        Mockito.when(fakeRepo.findById(Mockito.any())).thenReturn(Optional.empty());

        // When
        CategoryService testService = new CategoryService(fakeRepo);
        var exception = Assertions.assertThrows(
            RuntimeException.class,
            () -> testService.updateCategory(fakeId, fakeCategory));

        // Then
        Assertions.assertEquals("Category not found!", exception.getMessage());
    }
}
