package com.catcheat.api.store.operation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreOperationHourRepository extends JpaRepository<StoreOperationHour, Long> {

    List<StoreOperationHour> findByStoreStoreIdOrderByDayOfWeekAsc(Long storeId);
    void deleteByStoreStoreId(Long storeId);
}
