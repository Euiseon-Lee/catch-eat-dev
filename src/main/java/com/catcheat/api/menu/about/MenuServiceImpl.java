package com.catcheat.api.menu.about;

import com.catcheat.api.store.about.Store;
import com.catcheat.api.store.about.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/*
 * ✅ Menu 생성 설계 요약
 *
 * 🔒 무결성 전략(실무형, FK 미사용 가정)
 *  - DB FK 대신 서비스 레이어에서 무결성 보장
 *  - 모든 단건/수정/삭제는 "storeId + menuId"로 먼저 조회해서 소유권 검증
 *
 * 🧠 핵심 흐름
 *  1) storeId 존재 검증 (StoreRepository.existsById or findById)
 *  2) Menu 저장 (storeId는 단순 값으로 들고 가거나, Store 프록시로 연결)
 *  3) 응답 DTO 변환 (entity -> dto)
 *
 * ⚠️ 주의 포인트
 *  - menuCode를 유지한다면 count()+1 방식은 동시성 중복 위험 💥
 *    -> (권장) DB 시퀀스/별도 채번 테이블/락 기반 채번으로 전환
 *  - 할인 이벤트는 basePrice/discountPrice + 기간(start/end) 일관성 검증 필요
 *    -> discountPrice != null 이면 start/end 모두 필수, start <= end 보장
 *
 * 🚀 성능 포인트
 *  - 단건 조회는 findByMenuIdAndStoreId 로 인덱스 타게 설계
 *  - 목록 조회는 storeId + sortOrder 정렬 인덱스 고려
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;
    //private final MenuCategoryRepository menuCategoryRepository;

    @Transactional
    public MenuResponseDto create(Long storeId, MenuRequestDto dto) {
        if (!storeRepository.existsById(storeId)) {
            throw new IllegalArgumentException("store not found. storeId=" + storeId);
        }

        validateDiscount(dto.getBasePrice(), dto.getDiscountPrice(), dto.getDiscountStartDt() , dto.getDiscountEndDt());

        Menu menu = Menu.builder()
            .storeId(storeId)
            .menuName(dto.getMenuName())
            .menuDesc(dto.getMenuDesc())
            .basePrice(dto.getBasePrice())
            .discountPrice(dto.getDiscountPrice())
            .discountStartDt(dto.getDiscountStartDt())
            .discountEndDt(dto.getDiscountEndDt())
            .menuStatus(dto.getMenuStatus())
            .isRecommended(Boolean.TRUE.equals(dto.getIsRecommended()))
            .sortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 0)
            .build();

        return MenuResponseDto.from(menuRepository.save(menu));
    }

    private void validateDiscount(Integer basePrice, Integer discountPrice, LocalDateTime start, LocalDateTime end) {
        if (basePrice == null || basePrice < 0) {
            throw new IllegalArgumentException("basePrice must be >= 0.");
        }

        if (discountPrice == null) {
            if (start != null || end != null) {
                throw new IllegalArgumentException("discount period requires discountPrice.");
            }
            return;
        }

        if (discountPrice < 0) {
            throw new IllegalArgumentException("discountPrice must be >= 0.");
        }
        if (discountPrice > basePrice) {
            throw new IllegalArgumentException("discountPrice must be <= basePrice.");
        }
        if (start == null || end == null) {
            throw new IllegalArgumentException("discountStartDt and discountEndDt are required when discountPrice exists.");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("discountStartDt must be <= discountEndDt.");
        }
    }
}
