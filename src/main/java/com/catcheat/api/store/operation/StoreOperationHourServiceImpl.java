package com.catcheat.api.store.operation;

import com.catcheat.api.store.about.Store;
import com.catcheat.api.store.about.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreOperationHourServiceImpl implements StoreOperationHourService {

    private final StoreRepository storeRepository;
    private final StoreOperationHourRepository operationHourRepository;

    public StoreOperationHourServiceImpl(StoreRepository storeRepository, StoreOperationHourRepository operationHourRepository) {
        this.storeRepository = storeRepository;
        this.operationHourRepository = operationHourRepository;
    }

    @Override
    public StoreOperationHourResponseDto create(Long storeId, StoreOperationHourRequestDto dto) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found: " + storeId));

        StoreOperationHour hour = StoreOperationHour.builder()
                .store(store)
                .dayOfWeek(DayOfWeek.valueOf(dto.getDayOfWeek().toUpperCase()))
                .openTime(parse(dto.getOpenTime()))
                .closeTime(parse(dto.getCloseTime()))
                .breakStart(parse(dto.getBreakStart()))
                .breakEnd(parse(dto.getBreakEnd()))
                .lastOrderTime(parse(dto.getLastOrderTime()))
                .closedYn(dto.getClosedYn() != null ? dto.getClosedYn() : false)
                .build();

        StoreOperationHour saved = operationHourRepository.save(hour);

        return StoreOperationHourResponseDto.from(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreOperationHourResponseDto> getByStore(Long storeId) {

        return operationHourRepository.findByStoreStoreIdOrderByDayOfWeekAsc(storeId)
                .stream()
                .map(StoreOperationHourResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public StoreOperationHourResponseDto update(Long hourId, StoreOperationHourRequestDto dto) {

        StoreOperationHour hour = operationHourRepository.findById(hourId)
                .orElseThrow(() -> new IllegalArgumentException("StoreOperationHour not found: " + hourId));

        hour.setDayOfWeek(DayOfWeek.valueOf(dto.getDayOfWeek().toUpperCase()));
        hour.setOpenTime(parse(dto.getOpenTime()));
        hour.setCloseTime(parse(dto.getCloseTime()));
        hour.setBreakStart(parse(dto.getBreakStart()));
        hour.setBreakEnd(parse(dto.getBreakEnd()));
        hour.setLastOrderTime(parse(dto.getLastOrderTime()));
        hour.setClosedYn(dto.getClosedYn() != null && dto.getClosedYn());

        return StoreOperationHourResponseDto.from(hour);
    }

    @Override
    public void delete(Long hourId) {
        operationHourRepository.deleteById(hourId);
    }

    private LocalTime parse(String time) {
        return (time == null ? null : LocalTime.parse(time));
    }
}
