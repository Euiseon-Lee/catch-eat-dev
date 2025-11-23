package com.catcheat.api.store.operation;

import java.util.List;

public interface StoreOperationHourService {

    StoreOperationHourResponseDto create(Long storeId, StoreOperationHourRequestDto dto);

    List<StoreOperationHourResponseDto> getByStore(Long storeId);

    StoreOperationHourResponseDto update(Long storeOperationHourId, StoreOperationHourRequestDto dto);

    void delete(Long storeOperationHourId);
}