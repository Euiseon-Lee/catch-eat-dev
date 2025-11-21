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
    01  정상 영업
    02  단기 임시 휴점 (몇 일~1주 정도)
    03  장기 임시 휴점 (공사/리뉴얼  등 수 주 ~ 수 개월)
    04  기타
*/
CREATE TABLE store (
    store_id            BIGSERIAL       PRIMARY KEY
    , store_code        VARCHAR(20)     NOT NULL UNIQUE        -- 매장 코드 (FD/OM/FR + 6자리 번호)
    , store_name        VARCHAR(100)    NOT NULL               -- 매장명
    , store_desc        VARCHAR(1000)   NULL
    , store_status      VARCHAR(2)      NOT NULL DEFAULT '01'  -- 00~04 상태코드
    , closed_start_date DATE            NULL
    , closed_end_date   DATE            NULL
    , address           VARCHAR(255)
    , province          VARCHAR(50)
    , city              VARCHAR(50)
    , district          VARCHAR(50)

    , road_address      VARCHAR(255)
    , lot_address       VARCHAR(255)

    , latitude          DOUBLE PRECISION
    , longitude         DOUBLE PRECISION

    , created_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , created_by        VARCHAR(50)     DEFAULT 'SYSTEM'
    , updated_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , updated_by        VARCHAR(50)     DEFAULT 'SYSTEM'
);

-- 조회용 인덱스 (매장 코드로 단건 조회)
CREATE UNIQUE INDEX idx_store_code ON store (store_code);

-- 지역 검색 최적화 인덱스 (도 + 시 기준)
CREATE INDEX idx_store_region ON store (province, city);

-- 좌표 기반 근처 매장 추천용 인덱스
CREATE INDEX idx_store_latitude ON store (latitude);
CREATE INDEX idx_store_longitude ON store (longitude);

/** Sample */
INSERT INTO store (
    store_code
    , store_name
    , store_status
    , store_desc
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
    , '이성준 세프의 하이엔드 스시 오마카세'
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
    , '정통 에도마에식 스시 명가'
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
    , '일식에 현대적인 감각을 불어넣은 정통 스시 명가'
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
    , '도쿄 미슐랭 레스토랑 출신 셰프가 선보이는 하이엔드 오마카세'
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
    , '스시메르 종로'
    , '01'
    , '최상의 식재료만을 엄선해서 셰프의 섬세하고 정교한 터치로 다듬어진 정통 일식'
    , '서울특별시 종로구 우정국로 26 센트로폴리스 2층'
    , '서울특별시'
    , '종로구'
    , NULL
    , '우정국로 26 센트로폴리스 2층'
    , NULL
    , 37.571000  -- 추정
    , 126.976000 -- 추정
);
