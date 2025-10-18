package ru.bitrix24.api.deals;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeInfo {
    private long start;
    private double finish;
    private double duration;
    private double processing;
    private String dateStart;
    private String dateFinish;
    private long operatingResetAt;
    private double operating;
}
