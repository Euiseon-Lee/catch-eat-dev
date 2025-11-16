/**
가게 분류	            Prefix	예시
Fine Dining	        FD	    FD000001
Omakase	            OM	    OM000001
Family Restaurant	FR	    FR000001
Sushi/Sashimi	    SS	    SS000001
Cafe	            CF	    CF000001
Bar/Lounge	        BR	    BR000001
Bakery	            BK	    BK000001
Fast Food	        FF	    FF000001
 */

CREATE TABLE store (
    id              BIGSERIAL        PRIMARY KEY
    , code          VARCHAR(20)      NOT NULL UNIQUE
    , name          VARCHAR(100)     NOT NULL
    , address       VARCHAR(255)
    , opened        BOOLEAN          NOT NULL DEFAULT TRUE

    , province      VARCHAR(50)
    , city          VARCHAR(50)
    , district      VARCHAR(50)

    , road_address  VARCHAR(255)
    , lot_address   VARCHAR(255)

    , latitude      DOUBLE PRECISION
    , longitude     DOUBLE PRECISION
);
-- 조회용 인덱스
CREATE UNIQUE INDEX idx_store_code ON store (code);

-- 지역 검색 최적화 인덱스
CREATE INDEX idx_store_region ON store (province, city);

-- 좌표 기반 근처 매장 추천용 인덱스
CREATE INDEX idx_store_latitude ON store (latitude);
CREATE INDEX idx_store_longitude ON store (longitude);

/** Sample
INSERT INTO store (
      code
    , name
    , address
    , opened
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
    , '서울특별시 강남구 선릉로146길 27-8'
    , TRUE
    , '서울특별시'
    , '강남구'
    , '청담동'
    , '선릉로146길 27-8'
    , NULL
    , 37.517500  /* 추정 */
    , 127.044000 /* 추정 */
),
(
      'OM000002'
    , '스시코지마'
    , '서울특별시 강남구 압구정로60길 21'
    , TRUE
    , '서울특별시'
    , '강남구'
    , '청담동'
    , '압구정로60길 21'
    , NULL
    , 37.522000  /* 추정 */
    , 127.034000 /* 추정 */
),
(
      'OM000003'
    , '스시조'
    , '서울특별시 중구 소공로 106 서울 웨스틴조선호텔 20층'
    , TRUE
    , '서울특별시'
    , '중구'
    , '소공동'
    , '소공로 106'
    , NULL
    , 37.561200  /* 추정 */
    , 126.977500 /* 추정 */
),
(
      'OM000004'
    , '스시코우지'
    , '서울특별시 강남구 도산대로 318'
    , TRUE
    , '서울특별시'
    , '강남구'
    , '논현동'
    , '도산대로 318'
    , NULL
    , 37.525100  /* 추정 */
    , 127.029200 /* 추정 */
),
(
      'OM000005'
    , '스시메르'
    , '서울특별시 종로구 우정국로 26 센트로폴리스 2층'
    , TRUE
    , '서울특별시'
    , '종로구'
    , NULL
    , '우정국로 26 센트로폴리스 2층'
    , NULL
    , 37.571000  /* 추정 */
    , 126.976000 /* 추정 */
);
 */