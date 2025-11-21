package com.catcheat.api.store;

import java.util.List;

public interface StoreService {

    StoreResponseDto create(StoreRequestDto requestDto);

    StoreResponseDto getByStoreId(Long storeId);

    StoreResponseDto getByStoreCode(String storeCode);

    List<StoreResponseDto> getAll();
}
