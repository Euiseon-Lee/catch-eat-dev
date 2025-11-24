-- 매장 휴점 관리 테이블
CREATE TABLE store_closed_period (
    closed_period_id    BIGSERIAL       PRIMARY KEY
    , store_id          BIGINT          NOT NULL
    , store_status      VARCHAR(2)      NOT NULL    -- store.status 코드 재사용 (02 단기휴점, 03 장기휴점)
    , closed_reason     VARCHAR(255)    NULL        -- 공사, 리뉴얼, 방역 등
    , start_dt          DATE            NOT NULL
    , end_dt            DATE            NULL        -- 종료일 미정이면 NULL

    , created_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , created_by        VARCHAR(50)     DEFAULT 'SYSTEM'
    , updated_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , updated_by        VARCHAR(50)     DEFAULT 'SYSTEM'
);

ALTER TABLE store_closed_period
    ADD CONSTRAINT fk_closed_period_store
        FOREIGN KEY (store_id)
        REFERENCES store(store_id)
        ON DELETE CASCADE;