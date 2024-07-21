package ru.cft.statictics;

import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString(callSuper = true)
public class StringStatistic extends Statistic<BigDecimal> {

    public StringStatistic() {
        super(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    @Override
    public void refresh(List<String> data) {
        BigDecimal tempMaxLength = data.stream()
                .map(String::length)
                .max(Integer::compareTo)
                .map(integer -> new BigDecimal(String.valueOf(integer)))
                .orElse(BigDecimal.ZERO);
        BigDecimal tempMinLength = data.stream()
                .map(String::length)
                .min(Integer::compareTo)
                .map(integer -> new BigDecimal(String.valueOf(integer)))
                .orElse(BigDecimal.ZERO);
        BigDecimal tempCount = new BigDecimal(String.valueOf(data.size()));
        if (getMaxValue().compareTo(tempMaxLength) < 0)
            maxValue = tempMaxLength;
        if (getMinValue().compareTo(tempMinLength) > 0)
            minValue = tempMinLength;
        count = getCount().add(tempCount);
    }
}
