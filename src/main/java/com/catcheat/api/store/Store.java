package com.catcheat.api.store;

import com.catcheat.api.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;       // DB가 관리하는 불변 PK (내부 식별자)

    @Column(name = "store_code", nullable = false, unique = true)
    private String storeCode;   // 매장 코드 (예: CE000001) -> 매장을 나타내는 의미 있는 비즈니스 키 (외부에 보여지는 식별자)

    @Column(name = "store_name", nullable = false)
    private String storeName;        // 매장명

    @Column(name = "store_status", nullable = false)
    private String storeStatus;  // 00~04 상태코드

    @Column(name = "store_desc")
    private String storeDesc;

    @Column(name = "address", nullable = false)
    private String address;     // 주소

    @Column(name = "province")
    private String province;    // 도/광역시/특별시

    @Column(name = "city")
    private String city;        // 시/구/군

    @Column(name = "district")
    private String district;    // 동/읍/면

    @Column(name = "road_address")
    private String roadAddress; // 도로명 주소

    @Column(name = "lot_address")
    private String lotAddress;  // 지번 주소 (선택)

    @Column(name = "latitude")
    private Double latitude;    // 위도 (lat, Y)

    @Column(name = "longitude")
    private Double longitude;   // 경도 (lng, X)
}
