package com.catcheat.api.store.about;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * [클라이언트] JSON 요청
 *    ↓
 * [Controller] StoreRequestDto 로 바인딩
 *    ↓
 * [Service] Store 엔티티 변환 → 저장
 *    ↓
 * [DB] store 테이블 insert
 *    ↓
 * [Service] Store → StoreResponseDto 변환
 *    ↓
 * [Controller] JSON 응답으로 반환
 *    ↓
 * [클라이언트] 화면 표시
 */
@RestController
@RequestMapping("/api/stores")
@Tag(name = "Store API", description = "매장 기본 정보 관리 API")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /** 매장 생성 */
    @PostMapping
    @Operation(summary = "매장 생성", description = "새로운 매장을 등록한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "매장 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public StoreResponseDto create(@RequestBody StoreRequestDto requestDto) {
        return storeService.create(requestDto);
    }

    /** storeId 기준 단건 조회 */
    @GetMapping("/{storeId}")
    @Operation(summary = "매장 단건 조회 (storeId)", description = "매장 식별자(storeId)를 기준으로 매장 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 매장"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public StoreResponseDto getById(
            @Parameter(description = "매장 ID", required = true)
            @PathVariable Long storeId
    ) {
        return storeService.getByStoreId(storeId);
    }

    /** storeCode 기준 단건 조회 */
    @GetMapping("/code/{storeCode}")
    @Operation(summary = "매장 단건 조회 (storeCode)", description = "매장 코드(storeCode)를 기준으로 매장 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 매장"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public StoreResponseDto getByStoreCode(
            @Parameter(description = "매장 코드", required = true)
            @PathVariable String storeCode
    ) {
        return storeService.getByStoreCode(storeCode);
    }

    /** 전체 조회 */
    @GetMapping
    @Operation(summary = "전체 매장 조회", description = "등록된 모든 매장을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public List<StoreResponseDto> getAll() {
        return storeService.getAll();
    }
}