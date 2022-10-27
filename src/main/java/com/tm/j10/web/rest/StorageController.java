package com.tm.j10.web.rest;

import com.tm.j10.service.StorageService;
import com.tm.j10.web.rest.vm.StorageVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/storages")
public class StorageController {
    private StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PatchMapping("/capacity")
    public ResponseEntity<String> addCapacity(
        @RequestBody StorageVM storageVM){
        storageService.addCapacity(storageVM);
        return ResponseEntity.ok("Input capacity succeed");
    }
}
