package com.catcheat.api.store.closedPeriod;

import com.catcheat.api.store.about.Store;
import com.catcheat.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "store_closed_period")
public class StoreClosedPeriod extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closed_period_id")
    private Long closedPeriodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "store_status", nullable = false, length = 2)
    private String storeStatus; // 02 단기, 03 장기

    @Column(name = "closed_reason")
    private String closedReason;

    @Column(name = "start_dt", nullable = false)
    private LocalDate startDt;

    @Column(name = "end_dt")
    private LocalDate endDt;
}