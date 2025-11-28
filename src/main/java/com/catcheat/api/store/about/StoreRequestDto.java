package com.catcheat.api.store.about;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 *  클라이언트가 서버로 보낼 때 쓰는 데이터 그릇
 *  주로 POST / PUT / PATCH 요청에 사용
 */
@Getter
@Setter
@Schema(description = "매장 등록/수정 요청 DTO")
public class StoreRequestDto {

    @Schema(description = "매장 코드 (예: OM000001)", example = "OM000001")
    private String storeCode;

    @Schema(description = "매장명", example = "스시렌")
    private String storeName;

    @Schema(description = "매장 상태 코드 (01 정상 / 02 단기휴점 / 03 장기휴점)", example = "01")
    private String storeStatus;

    @Schema(description = "매장 설명", example = "프리미엄 오마카세 전문점")
    private String storeDesc;

    @Schema(description = "주소 전체", example = "서울특별시 강남구 선릉로146길 27-8")
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

    @Schema(description = "위도 (Y)", example = "37.517500")
    private Double latitude;

    @Schema(description = "경도 (X)", example = "127.044000")
    private Double longitude;
}
