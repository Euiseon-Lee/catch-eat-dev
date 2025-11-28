package com.catcheat.api.store.closedPeriod;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "매장 휴점 기간 등록/수정 요청 DTO")
public class StoreClosedPeriodRequestDto {

    @Schema(
            description = "매장 ID (store.store_id). 반드시 존재하는 매장이어야 함",
            example = "1",
            required = true
    )
    private Long storeId;

    @Schema(
            description = """
                    휴점 상태 코드 (store.store_status 동일 규칙)
                    - 02: 단기 휴점
                    - 03: 장기 휴점
                    """,
            example = "02",
            required = true
    )
    private String storeStatus;

    @Schema(
            description = "휴점 사유 (공사, 리뉴얼, 방역 등). 선택값",
            example = "매장 내부 리뉴얼 공사"
    )
    private String closedReason;

    @Schema(
            description = "휴점 시작일 (YYYY-MM-DD)",
            example = "2025-12-01",
            required = true
    )
    private String startDt;

    @Schema(
            description = "휴점 종료일 (YYYY-MM-DD) — 종료일 미정인 경우 null",
            example = "2025-12-20",
            nullable = true
    )
    private String endDt;
}
