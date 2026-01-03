package com.catcheat.api.menu.category;

import com.catcheat.api.menu.about.MenuRequestDto;
import com.catcheat.api.menu.about.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu_category")
@RequiredArgsConstructor
public class MenuCategoryController {

    private final MenuCategoryServiceImpl menuCategoryServiceImpl;

    @PostMapping
    public MenuResponseDto createMenuCategory(@PathVariable Long storeId, @RequestBody MenuRequestDto dto) {
        return menuCategoryServiceImpl.create(storeId, dto);
    }
}