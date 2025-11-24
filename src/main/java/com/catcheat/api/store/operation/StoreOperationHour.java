package com.catcheat.api.store.operation;

import com.catcheat.common.BaseEntity;
import com.catcheat.api.store.about.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "store_operation_hour")
public class StoreOperationHour extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_operation_hour_id")
    private Long storeOperationHourId;

    // N:1 관계 - 운영시간은 항상 어떤 매장에 소속
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false, length = 10)
    private DayOfWeek dayOfWeek;   // MONDAY ~ SUNDAY

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "break_start")
    private LocalTime breakStart;

    @Column(name = "break_end")
    private LocalTime breakEnd;

    @Column(name = "last_order_time")
    private LocalTime lastOrderTime;

    @Column(name = "closed_yn", nullable = false)
    private boolean closedYn;
}
