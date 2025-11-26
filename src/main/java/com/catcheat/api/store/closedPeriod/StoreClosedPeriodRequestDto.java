package com.catcheat.api.store.closedPeriod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreClosedPeriodRequestDto {
    private Long storeId;
    private String storeStatus;
    private String closedReason;
    private String startDt;   // "YYYY-MM-DD"
    private String endDt;     // null 가능
}