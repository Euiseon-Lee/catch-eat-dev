CREATE TABLE store_operation_hour (
    store_operation_hour_id     BIGSERIAL PRIMARY KEY
    , store_id                  BIGINT       NOT NULL
    , day_of_week               VARCHAR(10)  NOT NULL

    , open_time                 TIME         NULL
    , close_time                TIME         NULL
    , break_start               TIME         NULL
    , break_end                 TIME         NULL
    , last_order_time           TIME         NULL
    , closed_yn                 BOOLEAN      NOT NULL DEFAULT FALSE

    , created_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , created_by        VARCHAR(50)     DEFAULT 'SYSTEM'
    , updated_at        TIMESTAMP       NOT NULL DEFAULT NOW()
    , updated_by        VARCHAR(50)     DEFAULT 'SYSTEM'
);

ALTER TABLE store_operation_hour
    ADD CONSTRAINT fk_store_operation_hour_store
        FOREIGN KEY (store_id)
        REFERENCES store(store_id)
        ON DELETE CASCADE;

CREATE INDEX idx_store_operation_hour_store_day ON store_operation_hour (store_operation_hour_id, day_of_week);

/** sample data
 */
INSERT INTO store_operation_hour (
    store_id, day_of_week, open_time, close_time, break_start, break_end, last_order_time, closed_yn
) VALUES
-- Store 1
(1,'MONDAY','12:00','22:00','15:00','17:00','20:00',false)
, (1,'TUESDAY','12:00','22:00','15:00','17:00','20:00',false)
, (1,'WEDNESDAY','12:00','22:00','15:00','17:00','20:00',false)
, (1,'THURSDAY',NULL,NULL,NULL,NULL,NULL,true)
, (1,'FRIDAY','12:00','22:00','15:00','17:00','20:00',false)
, (1,'SATURDAY','12:00','22:00','15:00','17:00','20:00',false)
, (1,'SUNDAY','12:00','22:00','15:00','17:00','20:00',false)

-- Store 2
, (2,'MONDAY','12:00','20:00','15:00','17:00','21:30',false)
, (2,'TUESDAY','12:00','20:00','15:00','17:00','21:30',false)
, (2,'WEDNESDAY','12:00','20:00','15:00','17:00','21:30',false)
, (2,'THURSDAY','12:00','20:00','15:00','17:00','21:30',false)
, (2,'FRIDAY','12:00','22:00','15:00','17:00','21:30',false)
, (2,'SATURDAY','12:00','22:00','15:00','17:00','21:30',false)
, (2,'SUNDAY',NULL,NULL,NULL,NULL,NULL,true)

-- Store 3
, (3,'MONDAY','11:00','20:00','15:30','17:00','21:00',false)
, (3,'TUESDAY',NULL,NULL,NULL,NULL,NULL,true)
, (3,'WEDNESDAY','11:00','20:00','15:30','17:00','21:00',false)
, (3,'THURSDAY','11:00','20:00','15:30','17:00','21:00',false)
, (3,'FRIDAY','11:00','20:00','15:30','17:00','21:00',false)
, (3,'SATURDAY','11:00','20:00','15:30','17:00','21:00',false)
, (3,'SUNDAY','11:00','20:00','15:30','17:00','21:00',false)

-- Store 4
, (4,'MONDAY',NULL,NULL,NULL,NULL,NULL,true)
, (4,'TUESDAY',NULL,NULL,NULL,NULL,NULL,true)
, (4,'WEDNESDAY','12:00','22:00','15:00','17:00','21:30',false)
, (4,'THURSDAY','12:00','22:00','15:00','17:00','21:30',false)
, (4,'FRIDAY','12:00','22:00','15:00','17:00','21:30',false)
, (4,'SATURDAY','12:00','22:00','15:00','17:00','21:30',false)
, (4,'SUNDAY','12:00','22:00','15:00','17:00','21:30',false)

-- Store 5
, (5,'MONDAY','11:30','20:00','15:00','16:30','20:30',false)
, (5,'TUESDAY','11:30','20:00','15:00','16:30','20:30',false)
, (5,'WEDNESDAY','11:30','20:00','15:00','16:30','20:30',false)
, (5,'THURSDAY','11:30','20:00','15:00','16:30','20:30',false)
, (5,'FRIDAY','11:30','20:00','15:00','16:30','20:30',false)
, (5,'SATURDAY','11:30','20:00','15:00','16:30','20:30',false)
, (5,'SUNDAY',NULL,NULL,NULL,NULL,NULL,true);
