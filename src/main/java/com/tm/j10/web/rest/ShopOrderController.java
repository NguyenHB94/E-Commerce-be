package com.tm.j10.web.rest;

import com.tm.j10.domain.ShopOrder;
import com.tm.j10.domain.enumeration.OrderStatus;
import com.tm.j10.service.ShopOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.tm.j10.web.rest.vm.OderVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/public/orders")
public class ShopOrderController {
    private final ShopOrderService shopOrderService;

    public ShopOrderController(ShopOrderService shopOrderService) {
        this.shopOrderService = shopOrderService;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> closeOrder(@PathVariable Long id) {
        try {
            this.shopOrderService.closeOrder(id);
            return ResponseEntity.ok("Close order succeed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> OrderProduct(@RequestBody OderVM oderVM) {
        try {
            this.shopOrderService.createOrderProduct(oderVM);
            return ResponseEntity.ok("Order complete");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/admin/status")
    public ResponseEntity<String> CloseOrderByAdmin(@PathVariable Long id) {
        try {
            this.shopOrderService.closeOrderByAdmin(id);
            return ResponseEntity.ok("Close order succeed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<ShopOrder>> getAllByCustomerIdAndStatus(
        @PathVariable("customerId") Long customerId,
        @RequestParam(value = "status", required = false) OrderStatus orderStatus,
        @RequestParam("pageNo") Optional<Integer> pageNo,
        @RequestParam("pageSize") Optional<Integer> pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo.orElse(0), pageSize.orElse(10));
        if (orderStatus != null) {
            return ResponseEntity.ok(this.shopOrderService.getAllByCustomerIdAndOrderStatus(customerId, orderStatus, pageable));
        } else {
            return ResponseEntity.ok(this.shopOrderService.getAllByCustomerId(customerId, pageable));
        }
    }

    @PatchMapping("/{id}/process")
    public ResponseEntity<String> processOrder(@PathVariable Long id) {
        try {
            this.shopOrderService.processOrder(id);
            return ResponseEntity.ok("Process order succeed");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<ShopOrder>> getAllShopOrderByAdmin(
        @RequestParam(value = "status", required = false) OrderStatus orderStatus,
        @RequestParam("pageNo") Optional<Integer> pageNo,
        @RequestParam("pageSize") Optional<Integer> pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo.orElse(0), pageSize.orElse(10));
        if (orderStatus != null) {
            return ResponseEntity.ok(this.shopOrderService.getAllShopOrderByOrderStatus(orderStatus, pageable));
        } else {
            return ResponseEntity.ok(this.shopOrderService.getAllShopOrderByAdmin(pageable));
        }
    }
}

