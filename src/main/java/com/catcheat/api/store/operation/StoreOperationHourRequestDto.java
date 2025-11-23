package com.catcheat.api.store.operation;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreOperationHourRequestDto {

    private String dayOfWeek;       // "MONDAY" ~ "SUNDAY"
    private String openTime;        // "17:00" 형식, 없으면 null
    private String closeTime;       // "22:00"
    private String breakStart;      // "15:00"
    private String breakEnd;        // "17:00"
    private String lastOrderTime;   // "21:30"
    private Boolean closedYn;       // true면 휴무, null이면 false로 처리
}
