package com.catcheat.api.store.closedPeriod;

import java.util.List;
import java.util.Optional;

public interface StoreClosedPeriodService {

    StoreClosedPeriodResponseDto create(Long storeId, StoreClosedPeriodRequestDto dto);

    List<StoreClosedPeriodResponseDto> getByStore(Long storeId);

    StoreClosedPeriodResponseDto update(Long closedPeriodId, StoreClosedPeriodRequestDto dto);

    void delete(Long closedPeriodId);

    Optional<StoreClosedPeriodResponseDto> getActiveClosedPeriod(Long storeId);
}