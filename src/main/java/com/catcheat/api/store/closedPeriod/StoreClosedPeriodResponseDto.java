package com.catcheat.api.store.closedPeriod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "매장 휴점 기간 조회 응답 DTO")
public class StoreClosedPeriodResponseDto {

    @Schema(description = "휴점 이력 PK", example = "10")
    private Long closedPeriodId;

    @Schema(description = "매장 ID", example = "1")
    private Long storeId;

    @Schema(
            description = """
                    휴점 상태 코드
                    - 02: 단기 휴점
                    - 03: 장기 휴점
                    """,
            example = "03"
    )
    private String storeStatus;

    @Schema(
            description = "휴점 사유",
            example = "전면 리뉴얼 공사"
    )
    private String closedReason;

    @Schema(
            description = "휴점 시작일 (YYYY-MM-DD)",
            example = "2025-12-01"
    )
    private String startDt;

    @Schema(
            description = "휴점 종료일 (YYYY-MM-DD). null 가능",
            example = "2025-12-20",
            nullable = true
    )
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
