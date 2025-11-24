package com.catcheat.api.store.closedPeriod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 1) Repository 인터페이스 정의
 *       ↓
 * 2) Spring Data JPA가 런타임에 프록시 구현체 생성
 *       ↓
 * 3) @Query 문장을 JPQL 로 저장
 *       ↓
 * 4) Repository 메서드 호출 → EntityManager.createQuery 실행
 *       ↓
 * 5) JPQL 이 Hibernate 에 의해 SQL 로 변환
 *       ↓
 * 6) PreparedStatement 로 DB 실행
 *       ↓
 * 7) ResultSet → 엔티티 매핑(객체)
 *       ↓
 * 8) Optional 로 감싸 컨트롤러까지 전달
 */
public interface StoreClosedPeriodRepository extends JpaRepository<StoreClosedPeriod, Long> {

    // 매장별 휴점 이력 조회 (최신시작일 우선)
    List<StoreClosedPeriod> findByStoreStoreIdOrderByStartDtDesc(Long storeId);

    /** 현재 진행 중인 휴점 이력 조회
     *
     * @param storeId
     * @param today
     * @return
     *
     * @Query는 JPA가 엔티티 메타데이터(EntityManager + Hibernate)를 이용해 JPQL → SQL로 변환하고,
     * PreparedStatement로 실행한 뒤, 결과를 엔티티로 매핑하는 자동화된 과정
     * JPA의 메서드 이름 기반 쿼리로 표현하기 어려운 로직이 있을 때 사용
     *  : JPQL은 엔티티 기준 쿼리이며 SQL이 아님 → JPA가 실행 시점에 실제 SQL로 변환함
     */
    @Query("""
        select s
          from StoreClosedPeriod s
         where s.store.storeId = :storeId
           and s.startDt <= :today
           and (s.endDt is null or s.endDt >= :today)
         order by s.startDt desc
    """)
    Optional<StoreClosedPeriod> findActiveClosedPeriod(
            @Param("storeId") Long storeId,
            @Param("today") LocalDate today
    );
}