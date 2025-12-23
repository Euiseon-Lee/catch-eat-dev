package com.catcheat.api.menu.about;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores/{storeId}/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public MenuResponseDto createMenu(@PathVariable Long storeId,
                                      @RequestBody MenuRequestDto dto) {
        return menuService.create(storeId, dto);
    }
}