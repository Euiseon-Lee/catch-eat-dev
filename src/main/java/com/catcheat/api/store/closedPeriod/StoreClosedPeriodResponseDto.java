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
public class StoreClosedPeriodResponseDto {

    private Long closedPeriodId;
    private Long storeId;

    private String storeStatus;
    private String closedReason;

    private String startDt;
    private String endDt;

    public static StoreClosedPeriodResponseDto from(StoreClosedPeriod entity) {
        return StoreClosedPeriodResponseDto.builder()
                .closedPeriodId(entity.getClosedPeriodId())
                .storeId(entity.getStore().getStoreId())
                .storeStatus(entity.getStoreStatus())
                .closedReason(entity.getClosedReason())
                .startDt(entity.getStartDt().toString())
                .endDt(entity.getEndDt() != null ? entity.getEndDt().toString() : null)
                .build();
    }
}