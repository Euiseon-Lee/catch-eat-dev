-- 메뉴 카테고리 (공용)
CREATE TABLE menu_category (
    menu_category_id     BIGSERIAL       PRIMARY KEY
    , category_code      VARCHAR(20)     NOT NULL            -- 예: LUNCH, DINNER, COURSE, DRINK
    , category_name      VARCHAR(100)    NOT NULL            -- 예: 런치, 디너, 코스, 주류
    , sort_order         INT             NOT NULL DEFAULT 0

    , created_at         TIMESTAMP       NOT NULL DEFAULT NOW()
    , created_by         VARCHAR(50)     DEFAULT 'SYSTEM'
    , updated_at         TIMESTAMP       NOT NULL DEFAULT NOW()
    , updated_by         VARCHAR(50)     DEFAULT 'SYSTEM'
);

-- 조회용 인덱스
CREATE INDEX idx_menu_category_store ON menu_category (category_code);