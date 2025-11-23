package com.catcheat.api.store.operation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stores/{storeId}/operation-hours")
public class StoreOperationHourController {

    private final StoreOperationHourService service;

    public StoreOperationHourController(StoreOperationHourService service) {
        this.service = service;
    }

    @PostMapping
    public StoreOperationHourResponseDto create(@PathVariable Long storeId, @RequestBody StoreOperationHourRequestDto dto) {

        return service.create(storeId, dto);
    }

    @GetMapping
    public List<StoreOperationHourResponseDto> getByStore(@PathVariable Long storeId) {
        return service.getByStore(storeId);
    }

    @PutMapping("/{hourId}")
    public StoreOperationHourResponseDto update(@PathVariable Long hourId, @RequestBody StoreOperationHourRequestDto dto) {
        return service.update(hourId, dto);
    }

    @DeleteMapping("/{hourId}")
    public void delete(@PathVariable Long hourId) {
        service.delete(hourId);
    }
}