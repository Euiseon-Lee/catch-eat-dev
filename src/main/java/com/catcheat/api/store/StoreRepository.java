package com.catcheat.api.store;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 🏪 StoreRepository
 *  - Store 엔티티와 DB 간의 CRUD(등록·조회·수정·삭제) 작업을 담당하는 인터페이스
 *  - JpaRepository<Store, Long> 을 상속하면, 구현 코드 없이 DB 접근 메서드들이 자동 생성됨
 *
 * 📘 주요 기본 메서드 설명
 *  1. save(Store entity)
 *     - 새 엔티티면 INSERT, 기존 엔티티면 UPDATE 실행
 *     - storeId가 null → 신규 저장 / storeId 존재 → 해당 storeId의 row를 수정
 *     - 예: storeRepository.save(new Store(...));
 *
 *  2. findById(Long storeId)
 *     - PK(기본키) 기준으로 단건 조회
 *     - Optional<Store> 로 반환되어, 값이 없을 수도 있음
 *     - 예: storeRepository.findById(1L).orElseThrow(...)
 *
 *  3. findAll()
 *     - store 테이블 전체 조회
 *     - List<Store> 로 반환됨
 *     - 예: List<Store> stores = storeRepository.findAll();
 *
 *  4. deleteById(Long storeId)
 *     - 해당 PK를 가진 row를 삭제
 *     - 존재하지 않는 storeId를 삭제해도 에러는 발생하지 않음
 *     - 예: storeRepository.deleteById(3L);
 *
 *  5. count()
 *     - 전체 row(매장 수) 개수를 반환
 *     - 예: long total = storeRepository.count();
 *
 *  6. existsById(Long storeId)
 *     - 해당 storeId가 DB에 존재하는지 true/false 반환
 *     - 예: if(storeRepository.existsById(1L)) { ... }
 *
 * ⚙️ 동작 원리
 *  - Spring Data JPA가 런타임에 StoreRepository의 구현체를 자동 생성
 *  - 내부적으로 EntityManager를 사용하여 SQL을 실행
 *  - 엔티티 이름(Store)과 필드명(store_code, store_name, ...)으로 쿼리 자동 매핑
 *
 * 🧩 커스텀 메서드도 가능 (메서드명만 작성하면 자동 쿼리 생성)
 *  - Optional<Store> findByStoreCode(String storeCode);
 *  - List<Store> findByOpenedTrue();
 *
 * 💡 요약
 *  - 별도의 구현 클래스 필요 없음
 *  - Service 계층에서 바로 storeRepository.save(), findAll(), findById() 등 호출 가능
 *  - SQL 작성 없이 객체 지향적으로 DB 조작 가능
 */
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByStoreCode(String storeCode);
}
