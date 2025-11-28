package com.catcheat.api.store.operation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "매장 운영시간 조회 응답 DTO")
public class StoreOperationHourResponseDto {

    @Schema(description = "운영시간 PK", example = "12")
    private Long storeOperationHourId;

    @Schema(description = "요일 (MONDAY ~ SUNDAY)", example = "MONDAY")
    private String dayOfWeek;

    @Schema(description = "영업 시작 시간 (HH:mm)", example = "11:30")
    private String openTime;

    @Schema(description = "영업 종료 시간 (HH:mm)", example = "22:00")
    private String closeTime;

    @Schema(description = "브레이크타임 시작 (HH:mm)", example = "15:00")
    private String breakStart;

    @Schema(description = "브레이크타임 종료 (HH:mm)", example = "17:00")
    private String breakEnd;

    @Schema(description = "라스트오더 시간 (HH:mm)", example = "21:30")
    private String lastOrderTime;

    @Schema(description = "해당 요일 휴무 여부", example = "false")
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
