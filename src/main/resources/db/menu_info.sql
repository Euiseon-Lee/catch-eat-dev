-- 매장 메뉴
CREATE TABLE menu_info (
    menu_id             BIGSERIAL       PRIMARY KEY
    , store_id           BIGINT          NOT NULL
    , menu_category_id   BIGINT          NULL

    , menu_code          VARCHAR(30)     NOT NULL               -- 예: MN000001
    , menu_name          VARCHAR(150)    NOT NULL
    , menu_desc          VARCHAR(1000)   NULL

    , price              INT             NULL                   -- 원 단위(예: 45000)
    , menu_status        VARCHAR(2)      NOT NULL DEFAULT '01'  -- 01 판매중 / 02 품절 / 03 판매중단
    , is_recommended     BOOLEAN         NOT NULL DEFAULT FALSE
    , sort_order         INT             NOT NULL DEFAULT 0

    , created_at         TIMESTAMP       NOT NULL DEFAULT NOW()
    , created_by         VARCHAR(50)     DEFAULT 'SYSTEM'
    , updated_at         TIMESTAMP       NOT NULL DEFAULT NOW()
    , updated_by         VARCHAR(50)     DEFAULT 'SYSTEM'
);

ALTER TABLE menu_info ADD CONSTRAINT fk_menu_info_store
FOREIGN KEY (store_id) REFERENCES store(store_id) ON DELETE CASCADE;

ALTER TABLE menu_info ADD CONSTRAINT fk_menu_info_category
FOREIGN KEY (menu_category_id) REFERENCES menu_category(menu_category_id) ON DELETE SET NULL;

-- 조회용 인덱스
CREATE UNIQUE INDEX ux_menu_info_code ON menu_info (menu_code);

CREATE INDEX idx_menu_info_store ON menu_info (store_id);

CREATE INDEX idx_menu_info_category ON menu_info (menu_category_id);

CREATE INDEX idx_menu_info_store_status ON menu_info (store_id, menu_status);