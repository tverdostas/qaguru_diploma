package ru.bitrix24.api.deals;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DealResponse {
    private Deal result;
    private TimeInfo time;
}
