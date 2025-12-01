package com.catcheat.api.store.about;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * 매장 생성
     * - storeStatus가 null이면 기본값 "01"(영업 중)으로 설정
     */
    @Override
    public StoreResponseDto create(StoreRequestDto requestDto) {
        String status = requestDto.getStoreStatus();
        if (status == null || status.isBlank()) {
            status = "01"; // 기본: 영업 중
        }

        Store store = Store.builder()
                .storeCode(requestDto.getStoreCode())
                .storeName(requestDto.getStoreName())
                .storeStatus(status)
                .storeDesc(requestDto.getStoreDesc())
                .address(requestDto.getAddress())
                .province(requestDto.getProvince())
                .city(requestDto.getCity())
                .district(requestDto.getDistrict())
                .roadAddress(requestDto.getRoadAddress())
                .lotAddress(requestDto.getLotAddress())
                .latitude(requestDto.getLatitude())
                .longitude(requestDto.getLongitude())
                .build();

        Store saved = storeRepository.save(store);
        return StoreResponseDto.from(saved);
    }

    /**
     * ID 기준 단건 조회
     */
    @Override
    @Transactional(readOnly = true)
    public StoreResponseDto getByStoreId(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found. storeId = " + storeId));
        return StoreResponseDto.from(store);
    }

    /**
     * store_code 기준 단건 조회
     */
    @Override
    @Transactional(readOnly = true)
    public StoreResponseDto getByStoreCode(String storeCode) {
        Store store = storeRepository.findByStoreCode(storeCode)
                .orElseThrow(() -> new IllegalArgumentException("Store not found. storeCode = " + storeCode));
        return StoreResponseDto.from(store);
    }

    /**
     * 전체 매장 조회
     */
    @Override
    @Transactional(readOnly = true)
    public List<StoreResponseDto> getAll() {
        return storeRepository.findAll().stream()
                .map(StoreResponseDto::from)
                .collect(Collectors.toList());
    }


    /**
     * 매장 정보 수정
     * - 여기서는 "전체 필드 수정" 방식으로 가정.
     *   (즉, dto 에 들어온 값으로 해당 필드를 덮어쓴다)
     * - 부분 업데이트(PATCH 스타일)로 바꾸고 싶으면,
     *   dto 값이 null 인 필드는 건너뛰도록 if 체크 추가하면 된다.
     */
    @Override
    public StoreResponseDto update(Long storeId, StoreRequestDto dto) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found: " + storeId));

        // 코드 변경 허용 여부는 정책에 따라 결정.
        store.setStoreCode(dto.getStoreCode());
        store.setStoreName(dto.getStoreName());

        if (dto.getStoreStatus() != null && !dto.getStoreStatus().isBlank()) {
            store.setStoreStatus(dto.getStoreStatus());
        }

        store.setStoreDesc(dto.getStoreDesc());

        store.setAddress(dto.getAddress());
        store.setProvince(dto.getProvince());
        store.setCity(dto.getCity());
        store.setDistrict(dto.getDistrict());
        store.setRoadAddress(dto.getRoadAddress());
        store.setLotAddress(dto.getLotAddress());

        store.setLatitude(dto.getLatitude());
        store.setLongitude(dto.getLongitude());

        Store updated = storeRepository.save(store);
        return StoreResponseDto.from(updated);
    }
}
