package com.catcheat.api.store;

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
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // 매장 생성
    @PostMapping
    public StoreResponseDto create(@RequestBody StoreRequestDto requestDto) {
        return storeService.create(requestDto);
    }

    // 매장 단건 조회 (id 기준)
    @GetMapping("/{id}")
    public StoreResponseDto getById(@PathVariable Long id) {
        return storeService.getById(id);
    }

    // 매장 단건 조회 (storeCode 기준)
    @GetMapping("/code/{storeCode}")
    public StoreResponseDto getByStoreCode(@PathVariable String storeCode) {
        return storeService.getByStoreCode(storeCode);
    }

    // 전체 매장 조회
    @GetMapping
    public List<StoreResponseDto> getAll() {
        return storeService.getAll();
    }
}
