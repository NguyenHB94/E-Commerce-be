package com.tm.j10.web.rest;

import com.tm.j10.domain.Store;
import com.tm.j10.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/stores")


public class AdminStoreController {
    private StoreService storeService;

    public AdminStoreController(StoreService storeService) {
        this.storeService = storeService;
    }


    @GetMapping("")
    public ResponseEntity<List<Store>> getAll() {
        var ret = this.storeService.getAllStore();
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/{storesId}")
    public ResponseEntity<Store> getOneStore(@PathVariable("storesId") long storesId) {
        Optional<Store> store = this.storeService.getOneId(storesId);
        if (store.isPresent()) {
            return ResponseEntity.ok(store.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Store> createStore(@RequestBody Store newStores){
        var ret = this.storeService.createStore(newStores);
        if (ret != null){
            return ResponseEntity.created(URI.create("/")).body(null);
        }else {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PatchMapping("/{storeId}")
    public ResponseEntity<Void> updateStoreById(@PathVariable("storeId") long storeId, @RequestBody Store newStore) {
        var ret = this.storeService.update(storeId, newStore);
        if (ret) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.internalServerError().body(null);
        }
    }


    @DeleteMapping("/{storesId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("storesId") long customerId) {
        this.storeService.deleteStore(customerId);
        return ResponseEntity.ok().build();
    }

}
