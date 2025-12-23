package com.catcheat.api.menu.about;

public interface MenuService {
    MenuResponseDto create(Long storeId, MenuRequestDto dto);
}