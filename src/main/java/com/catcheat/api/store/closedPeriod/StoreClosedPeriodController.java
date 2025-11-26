package com.catcheat.api.store.closedPeriod;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores/{storeId}/closed-periods")
@Tag(name = "Store Closed Period API", description = "매장 휴점 기간 관리 API")
public class StoreClosedPeriodController {

    private final StoreClosedPeriodService closedPeriodService;

    public StoreClosedPeriodController(StoreClosedPeriodService closedPeriodService) {
        this.closedPeriodService = closedPeriodService;
    }

    /** 해당 매장의 휴점 이력을 신규 등록한다.
     * 비즈니스 규칙:
     * - storeStatus: "02"(단기 휴점), "03"(장기 휴점)
     * - endDt 가 null 인 경우, 종료일 미정 상태로 간주
     * - storeId 는 반드시 존재하는 매장이어야 함
     *
     * @param dto       휴점 정보 요청 바디 (storeId 포함)
     * @return          생성된 휴점 이력 정보
     * @throws IllegalArgumentException storeId 에 해당하는 매장이 없을 경우
     */
    @PostMapping
    @Operation(summary = "휴점 기간 등록", description = "특정 매장의 휴점 기간을 신규 등록한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "휴점 기간 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 또는 매장 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public StoreClosedPeriodResponseDto create(
            @Parameter(description = "매장 ID", required = true)
            @PathVariable Long storeId,
            @RequestBody StoreClosedPeriodRequestDto dto
    ) {
        return closedPeriodService.create(storeId, dto);
    }

    /** 해당 매장의 전체 휴점 이력 목록을 조회한다.
     *
     * @param storeId 매장 PK
     * @return 최신 시작일 기준 내림차순 휴점 이력 리스트
     */
    @GetMapping
    @Operation(summary = "매장 휴점 이력 조회", description = "특정 매장의 전체 휴점 이력 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 매장"),
    })
    public List<StoreClosedPeriodResponseDto> getByStore(
            @Parameter(description = "매장 ID", required = true)
            @PathVariable Long storeId
    ) {
        return closedPeriodService.getByStore(storeId);
    }

    /** 오늘 기준으로 해당 매장이 현재 휴점 중인지 조회한다.
     *
     * @param storeId 매장 PK
     * @return Optional — 현재 휴점 중이면 1건 반환, 아니면 empty
     */
    @GetMapping("/active")
    @Operation(summary = "현재 휴점 상태 조회", description = "오늘 기준으로 해당 매장이 휴점 중인지 확인한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 매장")
    })
    public Optional<StoreClosedPeriodResponseDto> getActive(
            @Parameter(description = "매장 ID", required = true)
            @PathVariable Long storeId
    ) {
        return closedPeriodService.getActiveClosedPeriod(storeId);
    }

    /** 휴점 이력을 수정한다.
     *
     * @param closedPeriodId 수정 대상 휴점 이력 PK
     * @param dto 수정 내용 DTO
     * @return 수정된 휴점 이력 DTO
     */
    @PutMapping("/{closedPeriodId}")
    @Operation(summary = "휴점 이력 수정", description = "휴점 이력의 기간 또는 상태를 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 휴점 이력"),
    })
    public StoreClosedPeriodResponseDto update(
            @Parameter(description = "휴점 이력 ID", required = true)
            @PathVariable Long closedPeriodId,
            @RequestBody StoreClosedPeriodRequestDto dto
    ) {
        return closedPeriodService.update(closedPeriodId, dto);
    }

    /** 휴점 이력을 삭제한다.
     *
     * @param closedPeriodId 삭제 대상 휴점 이력 PK
     */
    @DeleteMapping("/{closedPeriodId}")
    @Operation(summary = "휴점 이력 삭제", description = "특정 휴점 이력 기록을 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 휴점 이력")
    })
    public void delete(
            @Parameter(description = "휴점 이력 ID", required = true)
            @PathVariable Long closedPeriodId
    ) {
        closedPeriodService.delete(closedPeriodId);
    }
}
