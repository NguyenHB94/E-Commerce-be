package com.tm.j10.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tm.j10.domain.enumeration.VoucherStatus;
import com.tm.j10.domain.enumeration.VoucherValueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "voucher")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "voucher_id", length = 20, nullable = false)
    private String voucherId;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "valid_to", nullable = false)
    private Long validTo;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "valid_from", nullable = false)
    private Long validFrom;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "voucher_value", nullable = false)
    private Double voucherValue;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "min_value", nullable = false)
    private Double minValue;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "max_value", nullable = false)
    private Double maxValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "voucher_status", nullable = false)
    private VoucherStatus voucherStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "value_type", nullable = false)
    private VoucherValueType valueType;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "voucher_quantity", nullable = false)
    private Long voucherQuantity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_voucher",
        joinColumns = @JoinColumn(name = "voucher_id"),
        inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    @JsonIgnoreProperties(value = {"user", "shopOrders", "province", "district", "ward", "vouchers"}, allowSetters = true)
    private Set<Customer> customers = new HashSet<>();

    @Size(max = 200)
    @Column(name = "created_by", length = 200)
    private String createdBy;

    @Column(name = "created_date")
    private Long createdDate;

    @Size(max = 200)
    @Column(name = "modified_by", length = 200)
    private String modifiedBy;

    @Column(name = "modified_date")
    private Long modifiedDate;

    @Override
    public String toString() {
        return "Voucher{" +
            "id=" + id +
            ", voucherId='" + voucherId + '\'' +
            ", validTo=" + validTo +
            ", validFrom=" + validFrom +
            ", voucherValue=" + voucherValue +
            ", minValue=" + minValue +
            ", maxValue=" + maxValue +
            ", voucherStatus=" + voucherStatus +
            ", valueType=" + valueType +
            ", voucherQuantity=" + voucherQuantity +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", modifiedBy='" + modifiedBy + '\'' +
            ", modifiedDate=" + modifiedDate +
            '}';
    }
}
