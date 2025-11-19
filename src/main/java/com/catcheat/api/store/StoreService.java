package com.catcheat.api.store;

import java.util.List;

public interface StoreService {

    StoreResponseDto create(StoreRequestDto requestDto);

    StoreResponseDto getById(Long id);

    StoreResponseDto getByStoreCode(String storeCode);

    List<StoreResponseDto> getAll();
}
