package com.catcheat.api.menu.about;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponseDto {
    private Long menuId;
    private String menuCode;
    private String menuName;
    private String menuDesc;
    private Integer basePrice;
    private Integer discountPrice;
    private String menuStatus;
    private Boolean isRecommended;
    private Integer sortOrder;

    public static MenuResponseDto from(Menu entity) {
        return MenuResponseDto.builder()
                .menuId(entity.getMenuId())
                .menuName(entity.getMenuName())
                .menuDesc(entity.getMenuDesc())
                .basePrice(entity.getBasePrice())
                .discountPrice(entity.getDiscountPrice())
                .menuStatus(entity.getMenuStatus())
                .isRecommended(entity.isRecommended())
                .sortOrder(entity.getSortOrder())
                .build();
    }
}