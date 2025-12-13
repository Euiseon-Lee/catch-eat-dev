-- 메뉴 카테고리
CREATE TABLE menu_category (
    menu_category_id     BIGSERIAL       PRIMARY KEY
    , store_id           BIGINT          NOT NULL
    , category_code      VARCHAR(20)     NOT NULL            -- 예: LUNCH, DINNER, COURSE, DRINK
    , category_name      VARCHAR(100)    NOT NULL            -- 예: 런치, 디너, 코스, 주류
    , sort_order         INT             NOT NULL DEFAULT 0

    , created_at         TIMESTAMP       NOT NULL DEFAULT NOW()
    , created_by         VARCHAR(50)     DEFAULT 'SYSTEM'
    , updated_at         TIMESTAMP       NOT NULL DEFAULT NOW()
    , updated_by         VARCHAR(50)     DEFAULT 'SYSTEM'
);

ALTER TABLE menu_category ADD CONSTRAINT fk_menu_category_store
FOREIGN KEY (store_id) REFERENCES store(store_id) ON DELETE CASCADE;

-- 같은 매장 내 카테고리 코드 중복 방지
CREATE UNIQUE INDEX ux_menu_category_store_code ON menu_category (store_id, category_code);

CREATE INDEX idx_menu_category_store ON menu_category (store_id);