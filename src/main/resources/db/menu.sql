-- 매장 메뉴
CREATE TABLE menu (
    menu_id             BIGSERIAL       PRIMARY KEY
    , store_id          BIGINT          NOT NULL                -- 논리적 FK (store.store_id)

    , menu_name         VARCHAR(150)    NOT NULL
    , menu_desc         VARCHAR(1000)   NULL

    , base_price        INT             NOT NULL                -- 정가
    , discount_price    INT             NULL                    -- 할인가(이벤트 시)
    , discount_start_dt TIMESTAMP       NULL
    , discount_end_dt   TIMESTAMP       NULL

    , menu_status       VARCHAR(2)      NOT NULL DEFAULT '01'   -- 01 판매중 / 02 품절 / 03 판매중단
    , is_recommended    BOOLEAN         NOT NULL DEFAULT FALSE
    , sort_order        INT             NOT NULL DEFAULT 0

    , created_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , created_by        VARCHAR(50)     NOT NULL DEFAULT 'SYSTEM'
    , updated_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , updated_by        VARCHAR(50)     NOT NULL DEFAULT 'SYSTEM'

    , CONSTRAINT ck_menu_base_price_non_negative
        CHECK (base_price >= 0)

    , CONSTRAINT ck_menu_discount_price_non_negative
        CHECK (discount_price IS NULL OR discount_price >= 0)

    , CONSTRAINT ck_menu_discount_logic
        CHECK (discount_price IS NULL OR discount_price <= base_price)

    , CONSTRAINT ck_menu_discount_period_logic
        CHECK (
            discount_price IS NULL
            OR (
                discount_start_dt IS NOT NULL
                AND discount_end_dt IS NOT NULL
                AND discount_start_dt <= discount_end_dt
            )
        )
);

CREATE INDEX idx_menu_store_id ON menu (store_id);

CREATE INDEX idx_menu_menu_status ON menu (menu_status);

CREATE INDEX idx_menu_store_sort ON menu (store_id, sort_order, menu_id);
