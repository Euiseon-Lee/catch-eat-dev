package com.catcheat.api.store.operation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores/{storeId}/operation-hours")
@Tag(name = "Store Operation Hour API", description = "매장 운영시간 관리 API")
public class StoreOperationHourController {

    private final StoreOperationHourService service;

    public StoreOperationHourController(StoreOperationHourService service) {
        this.service = service;
    }

    /** 운영시간 등록 */
    @PostMapping
    @Operation(summary = "운영시간 등록", description = "특정 매장의 요일별 운영시간을 등록한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "운영시간 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 매장"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public StoreOperationHourResponseDto create(
            @Parameter(description = "매장 ID", required = true) @PathVariable Long storeId,
            @RequestBody StoreOperationHourRequestDto dto
    ) {
        return service.create(storeId, dto);
    }

    /** 특정 매장의 운영시간 전체 조회 */
    @GetMapping
    @Operation(summary = "운영시간 조회", description = "특정 매장의 모든 운영시간 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 매장"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<StoreOperationHourResponseDto> getByStore(
            @Parameter(description = "매장 ID", required = true) @PathVariable Long storeId
    ) {
        return service.getByStore(storeId);
    }

    /** 운영시간 수정 */
    @PutMapping("/{hourId}")
    @Operation(summary = "운영시간 수정", description = "특정 운영시간 데이터를 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 운영시간"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public StoreOperationHourResponseDto update(
            @Parameter(description = "운영시간 ID", required = true) @PathVariable Long hourId,
            @RequestBody StoreOperationHourRequestDto dto,
            @PathVariable String storeId) {
        return service.update(hourId, dto);
    }

    /** 운영시간 삭제 */
    @DeleteMapping("/{hourId}")
    @Operation(summary = "운영시간 삭제", description = "특정 운영시간 데이터를 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 운영시간"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public void delete(
            @Parameter(description = "운영시간 ID", required = true) @PathVariable Long hourId
    ) {
        service.delete(hourId);
    }
}
