package com.catcheat.api.menu.about;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuRequestDto {
    private Long menuCategoryId;
    private String menuName;
    private String menuDesc;
    private Integer basePrice;
    private Integer discountPrice;
    private LocalDateTime discountStartDt;
    private LocalDateTime discountEndDt;
    private String menuStatus;
    private Boolean isRecommended;
    private Integer sortOrder;
}