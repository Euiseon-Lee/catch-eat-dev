package com.catcheat.api.store.about;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "매장 조회 응답 DTO")
public class StoreResponseDto {
    @Schema(description = "매장 PK", example = "1")
    private Long storeId;

    @Schema(description = "매장 코드", example = "OM000001")
    private String storeCode;

    @Schema(description = "매장명", example = "스시렌")
    private String storeName;

    @Schema(description = "매장 상태 코드", example = "01")
    private String storeStatus;

    @Schema(description = "매장 설명", example = "프리미엄 오마카세 전문점")
    private String storeDesc;

    @Schema(description = "전체 주소", example = "서울특별시 강남구 선릉로146길 27-8")
    private String address;

    @Schema(description = "도/광역시", example = "서울특별시")
    private String province;

    @Schema(description = "시/구/군", example = "강남구")
    private String city;

    @Schema(description = "동/읍/면", example = "청담동")
    private String district;

    @Schema(description = "도로명 주소", example = "선릉로146길 27-8")
    private String roadAddress;

    @Schema(description = "지번 주소", example = "청담동 123-4")
    private String lotAddress;

    @Schema(description = "위도", example = "37.517500")
    private Double latitude;

    @Schema(description = "경도", example = "127.044000")
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