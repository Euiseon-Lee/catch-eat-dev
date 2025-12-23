package com.catcheat.api.menu.about;

import com.catcheat.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menu")
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @Column(name = "menu_name", nullable = false, length = 150)
    private String menuName;

    @Column(name = "menu_desc", length = 1000)
    private String menuDesc;

    @Column(name = "base_price", nullable = false)
    private Integer basePrice;

    @Column(name = "discount_price")
    private Integer discountPrice;

    @Column(name = "discount_start_dt")
    private LocalDateTime discountStartDt;

    @Column(name = "discount_end_dt")
    private LocalDateTime discountEndDt;

    @Column(name = "menu_status", nullable = false, length = 2)
    private String menuStatus;

    @Column(name = "is_recommended", nullable = false)
    private boolean isRecommended;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder;

    // builder / getters / constructor 생략(롬복 쓰면 @Builder @Getter 등)
}
