package com.tm.j10.web.rest;

import com.tm.j10.domain.Voucher;
import com.tm.j10.domain.enumeration.VoucherStatus;
import com.tm.j10.domain.enumeration.VoucherValueType;
import com.tm.j10.service.VoucherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/public/vouchers")
public class VoucherController {
    private VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/{voucherId}")
    public ResponseEntity<Voucher> getById(@PathVariable("voucherId") Long voucherId) {
        var ret = this.voucherService.getById(voucherId);
        return ResponseEntity.ok(ret);
    }

    @GetMapping("")
    public ResponseEntity<Page<Voucher>> getAll(@RequestParam(name = "voucherStatus", required = false) VoucherStatus voucherStatus, @RequestParam(name = "valueType", required = false) VoucherValueType valueType, @RequestParam("pageNo") Optional<Integer> pageNo, @RequestParam("pageSize") Optional<Integer> pageSize) {
        Pageable pageable = PageRequest.of(pageNo.orElse(0), pageSize.orElse(10));

        var ret = this.voucherService.searchVouchers(voucherStatus, valueType, pageable);
        return ResponseEntity.ok(ret);
    }

    @PostMapping
    public ResponseEntity<String> addNewVoucher(@RequestBody Voucher newVoucher) {
        this.voucherService.createNewVoucher(newVoucher);
        return ResponseEntity.ok("Created new voucher succeed");
    }

    @PatchMapping("/{voucherId}/customers")
    public ResponseEntity<String> addCustomers(@PathVariable("voucherId") Long voucherId, @RequestBody List<Long> listCustomerId) {
        this.voucherService.addCustomers(voucherId, listCustomerId);
        return ResponseEntity.ok("Add list customer succeed");
    }

    @PatchMapping("/{voucherId}")
    public ResponseEntity<String> editVoucher(@PathVariable("voucherId") Long voucherId, @RequestBody Voucher newVoucher) {
        this.voucherService.editVoucher(voucherId, newVoucher);
        return ResponseEntity.ok("Edit voucher succeed");
    }

    @DeleteMapping("/{voucherId}")
    public ResponseEntity<String> deleteVoucher(@PathVariable("voucherId") Long voucherId) {
        this.voucherService.deleteVoucher(voucherId);
        return ResponseEntity.ok("Delete voucher succeed");
    }
}
