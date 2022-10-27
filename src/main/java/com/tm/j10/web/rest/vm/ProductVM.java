package com.tm.j10.web.rest.vm;

import com.tm.j10.domain.Color;
import com.tm.j10.domain.Product;
import com.tm.j10.domain.ProductSize;
import com.tm.j10.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVM extends Product {

    List<StorageVM> storageVMS;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StorageVM{
        private Store store;
        private ProductSize productSize;
        private Color color;
        private Long capacity;
    }

}
