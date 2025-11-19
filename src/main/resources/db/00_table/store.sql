/**
    매장 분류              Prefix  store_code 예시
    Fine Dining         FD      FD000001
    Omakase             OM      OM000001
    Family Restaurant   FR      FR000001
    Sushi/Sashimi       SS      SS000001
    Cafe                CF      CF000001
    Bar/Lounge          BR      BR000001
    Bakery              BK      BK000001
    Fast Food           FF      FF000001

    매장 상태
    00  폐업
    01  영업 중
    02  휴무
    03  임시 휴무
    04  기타
*/
CREATE TABLE store (
    id              BIGSERIAL        PRIMARY KEY
    , store_code    VARCHAR(20)      NOT NULL UNIQUE        -- 매장 코드 (FD/OM/FR + 6자리 번호)
    , name          VARCHAR(100)     NOT NULL               -- 매장명
    , store_status  VARCHAR(2)       NOT NULL DEFAULT '01'  -- 00~04 상태코드

    , address       VARCHAR(255)
    , province      VARCHAR(50)
    , city          VARCHAR(50)
    , district      VARCHAR(50)

    , road_address  VARCHAR(255)
    , lot_address   VARCHAR(255)

    , latitude      DOUBLE PRECISION
    , longitude     DOUBLE PRECISION
);

-- 조회용 인덱스 (매장 코드로 단건 조회)
CREATE UNIQUE INDEX idx_store_code
    ON store (store_code);

-- 지역 검색 최적화 인덱스 (도 + 시 기준)
CREATE INDEX idx_store_region
    ON store (province, city);

-- 좌표 기반 근처 매장 추천용 인덱스
CREATE INDEX idx_store_latitude
    ON store (latitude);
CREATE INDEX idx_store_longitude
    ON store (longitude);

/** Sample */
INSERT INTO store (
      store_code
    , name
    , store_status
    , address
    , province
    , city
    , district
    , road_address
    , lot_address
    , latitude
    , longitude
) VALUES
(
      'OM000001'
    , '스시렌'
    , '01'
    , '서울특별시 강남구 선릉로146길 27-8'
    , '서울특별시'
    , '강남구'
    , '청담동'
    , '선릉로146길 27-8'
    , NULL
    , 37.517500  -- 추정
    , 127.044000 -- 추정
),
(
      'OM000002'
    , '스시코지마'
    , '01'
    , '서울특별시 강남구 압구정로60길 21'
    , '서울특별시'
    , '강남구'
    , '청담동'
    , '압구정로60길 21'
    , NULL
    , 37.522000  -- 추정
    , 127.034000 -- 추정
),
(
      'OM000003'
    , '스시조'
    , '01'
    , '서울특별시 중구 소공로 106 서울 웨스틴조선호텔 20층'
    , '서울특별시'
    , '중구'
    , '소공동'
    , '소공로 106'
    , NULL
    , 37.561200  -- 추정
    , 126.977500 -- 추정
),
(
      'OM000004'
    , '스시코우지'
    , '01'
    , '서울특별시 강남구 도산대로 318'
    , '서울특별시'
    , '강남구'
    , '논현동'
    , '도산대로 318'
    , NULL
    , 37.525100  -- 추정
    , 127.029200 -- 추정
),
(
      'OM000005'
    , '스시메르'
    , '01'
    , '서울특별시 종로구 우정국로 26 센트로폴리스 2층'
    , '서울특별시'
    , '종로구'
    , NULL
    , '우정국로 26 센트로폴리스 2층'
    , NULL
    , 37.571000  -- 추정
    , 126.976000 -- 추정
);
