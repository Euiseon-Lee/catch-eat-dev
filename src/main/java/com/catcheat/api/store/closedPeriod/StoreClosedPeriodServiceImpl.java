package com.catcheat.api.store.closedPeriod;

import com.catcheat.api.store.about.Store;
import com.catcheat.api.store.about.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreClosedPeriodServiceImpl implements StoreClosedPeriodService {

    private final StoreRepository storeRepository;
    private final StoreClosedPeriodRepository closedPeriodRepository;

    /** 생성자 기반 의존성 주입 (권장 방식)
     *  - storeRepository : 매장 존재 검증 및 store 엔티티 조회
     *  - closedPeriodRepository : 휴점 이력 CRUD 처리
     */
    public StoreClosedPeriodServiceImpl(StoreRepository storeRepository, StoreClosedPeriodRepository closedPeriodRepository) {

        this.storeRepository = storeRepository;
        this.closedPeriodRepository = closedPeriodRepository;
    }

    /** 휴점 이력 등록
     * 1) storeId 로 매장 존재 여부 확인
     * 2) DTO → 엔티티 변환
     * 3) DB 저장
     * 4) 엔티티 → ResponseDto 변환 후 반환
     */
    @Override
    public StoreClosedPeriodResponseDto create(Long storeId, StoreClosedPeriodRequestDto dto) {

        // 1) 매장 존재 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found: " + storeId));

        // 2) 요청 DTO → 엔티티 변환
        //    - 문자열 날짜는 LocalDate 로 파싱
        //    - store 엔티티를 그대로 연결하여 FK 설정
        StoreClosedPeriod entity = StoreClosedPeriod.builder()
                .store(store)
                .storeStatus(dto.getStoreStatus())
                .closedReason(dto.getClosedReason())
                .startDt(parseDate(dto.getStartDt()))
                .endDt(parseDate(dto.getEndDt()))
                .build();

        // 3) 엔티티 저장 후 ResponseDto 로 변환하여 반환
        return StoreClosedPeriodResponseDto.from(closedPeriodRepository.save(entity));
    }

    /** 특정 매장의 전체 휴점 이력 조회
     * Repository 메서드명 기반 쿼리 자동 생성
     * startDt DESC 정렬로 최신 휴점 이력 먼저 반환
     */
    @Override
    @Transactional(readOnly = true) // readOnly=true → 성능 최적화 (Dirty Checking 비활성화)
    public List<StoreClosedPeriodResponseDto> getByStore(Long storeId) {
        return closedPeriodRepository.findByStoreStoreIdOrderByStartDtDesc(storeId)
                .stream()
                .map(StoreClosedPeriodResponseDto::from)
                .collect(Collectors.toList());
    }


    /** 휴점 이력 수정
     * 1) 휴점 이력 엔티티 조회 (없으면 예외)
     * 2) 엔티티 필드 setter 로 값 변경
     * 3) save(entity) 로 명시적 update
     */
    @Override
    public StoreClosedPeriodResponseDto update(Long closedPeriodId, StoreClosedPeriodRequestDto dto) {

        StoreClosedPeriod entity = closedPeriodRepository.findById(closedPeriodId)
                .orElseThrow(() -> new IllegalArgumentException("StoreClosedPeriod not found: " + closedPeriodId));

        entity.setStoreStatus(dto.getStoreStatus());
        entity.setClosedReason(dto.getClosedReason());
        entity.setStartDt(parseDate(dto.getStartDt()));
        entity.setEndDt(parseDate(dto.getEndDt()));

        // 휴점 이력 수정
        // JPA 영속성 컨텍스트에 의해 자동 UPDATE 되지만
        // 명시적인 save() 호출로 업데이트 흐름을 명확히 함
        StoreClosedPeriod updated = closedPeriodRepository.save(entity);
        return StoreClosedPeriodResponseDto.from(updated);
    }

    /** 휴점 이력 삭제
     * Repository.deleteById() 가 바로 DELETE SQL 실행
     */
    @Override
    public void delete(Long closedPeriodId) {
        closedPeriodRepository.deleteById(closedPeriodId);
    }


    /** 현재 진행 중인 휴점 이력 조회
     * today 기준으로 시작되었고(endDt >= today 또는 null)
     * JPQL 기반 복합 조건
     * 오늘 날짜에 해당하는 휴점 이력이 없을 수도 있기 때문에 Optional 처리
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StoreClosedPeriodResponseDto> getActiveClosedPeriod(Long storeId) {
        return closedPeriodRepository.findActiveClosedPeriod(storeId, LocalDate.now())
                .map(StoreClosedPeriodResponseDto::from);
    }

    /**
     * "YYYY-MM-DD" → LocalDate 변환
     * endDt 는 null 가능하므로 null 처리 필수
     */
    private LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        return LocalDate.parse(dateStr); // ISO-8601 "YYYY-MM-DD"
    }
}