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
