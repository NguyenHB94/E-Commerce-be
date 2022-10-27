package com.tm.j10.web.rest;

import com.tm.j10.domain.Store;
import com.tm.j10.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/stores")
public class StoreController {
    private StoreService storeService;

    public StoreController (StoreService storeService){
        this.storeService =storeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Store>> getAllStore(){
        var ret = this.storeService.getAllStore();
        return ResponseEntity.ok(ret);
    }

}
