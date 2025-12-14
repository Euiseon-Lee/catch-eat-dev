-- 매장 메뉴
CREATE TABLE menu_info (
    menu_id             BIGSERIAL       PRIMARY KEY
    , store_id          BIGINT          NOT NULL

    , menu_name         VARCHAR(150)    NOT NULL
    , menu_desc         VARCHAR(1000)   NULL

    , base_price        INT             NOT NULL                -- 정가
    , discount_price    INT             NULL                    -- 할인가(이벤트 시)
    , discount_start_dt TIMESTAMP
    , discount_end_dt   TIMESTAMP

    , menu_status       VARCHAR(2)      NOT NULL DEFAULT '01'   -- 01 판매중 / 02 품절 / 03 판매중단
    , is_recommended    BOOLEAN         NOT NULL DEFAULT FALSE
    , sort_order        INT             NOT NULL DEFAULT 0

    , created_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , created_by        VARCHAR(50)     NOT NULL DEFAULT 'SYSTEM'
    , updated_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , updated_by        VARCHAR(50)     NOT NULL DEFAULT 'SYSTEM'

    , CONSTRAINT fk_menu_info_store FOREIGN KEY (store_id)
        REFERENCES store(store_id) ON DELETE CASCADE

    , CONSTRAINT ck_menu_info_base_price_non_negative
        CHECK (base_price >= 0)

    , CONSTRAINT ck_menu_info_discount_price_non_negative
        CHECK (discount_price IS NULL OR discount_price >= 0)

    , CONSTRAINT ck_menu_info_discount_logic
        CHECK (discount_price IS NULL OR discount_price <= base_price)
);

-- 조회용 인덱스
CREATE INDEX idx_menu_info_store_id ON menu_info (store_id);

CREATE INDEX idx_menu_info_menu_status ON menu_info (menu_status);

CREATE INDEX idx_menu_info_sort_order ON menu_info (sort_order);