package com.catcheat.api.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  서버가 클라이언트로 돌려주는 데이터 그릇
 *  주로 GET / POST 결과 응답에서 사용
 *  엔티티(Store)를 그대로 노출하지 않고, 필요한 데이터만 가공해서 내보냄
 *  보안, 응답 일관성, 확장성 측면에서 훨씬 안전함
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponseDto {
    private Long storeId;
    private String storeCode;
    private String storeName;
    private String storeStatus;
    private String storeDesc;

    private String address;
    private String province;
    private String city;
    private String district;
    private String roadAddress;
    private String lotAddress;

    private Double latitude;
    private Double longitude;

    // (선택) 나중에 근처 매장 조회 시 사용할 거리 정보
    private Double distance; // km 단위, 일반 조회에서는 null

    public static StoreResponseDto from(Store store) {
        return StoreResponseDto.builder()
                .storeId(store.getStoreId())
                .storeCode(store.getStoreCode())
                .storeName(store.getStoreName())
                .storeStatus(store.getStoreStatus())
                .storeDesc(store.getStoreDesc())
                .address(store.getAddress())
                .province(store.getProvince())
                .city(store.getCity())
                .district(store.getDistrict())
                .roadAddress(store.getRoadAddress())
                .lotAddress(store.getLotAddress())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .build();
    }
    public static StoreResponseDto from(Store store, Double distanceKm) {
        StoreResponseDto dto = from(store);
        dto.setDistance(distanceKm);
        return dto;
    }
}