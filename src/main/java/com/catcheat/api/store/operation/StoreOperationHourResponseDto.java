package com.catcheat.api.store.operation;

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
public class StoreOperationHourResponseDto {

    private Long storeOperationHourId;
    private String dayOfWeek;
    private String openTime;
    private String closeTime;
    private String breakStart;
    private String breakEnd;
    private String lastOrderTime;
    private boolean closedYn;

    public static StoreOperationHourResponseDto from(StoreOperationHour entity) {
        return StoreOperationHourResponseDto.builder()
                .storeOperationHourId(entity.getStoreOperationHourId())
                .dayOfWeek(entity.getDayOfWeek().name())
                .openTime(entity.getOpenTime() != null ? entity.getOpenTime().toString() : null)
                .closeTime(entity.getCloseTime() != null ? entity.getCloseTime().toString() : null)
                .breakStart(entity.getBreakStart() != null ? entity.getBreakStart().toString() : null)
                .breakEnd(entity.getBreakEnd() != null ? entity.getBreakEnd().toString() : null)
                .lastOrderTime(entity.getLastOrderTime() != null ? entity.getLastOrderTime().toString() : null)
                .closedYn(entity.isClosedYn())
                .build();
    }
}
