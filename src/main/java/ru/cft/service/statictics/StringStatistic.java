package ru.cft.service.statictics;

import lombok.NonNull;
import lombok.ToString;
import ru.cft.entity.StringType;

import java.math.BigDecimal;
import java.util.List;

@ToString(callSuper = true)
public class StringStatistic extends Statistic<BigDecimal> {

    public StringStatistic(StringType type) {
        super(type, BigDecimal.ZERO);
    }

    @Override
    public void refresh(@NonNull List<String> data) {
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
        if (maxValue == null)
            maxValue = tempMaxLength;
        else if (maxValue.compareTo(tempMaxLength) < 0)
            maxValue = tempMaxLength;
        if (minValue == null)
            minValue = tempMinLength;
        else if (minValue.compareTo(tempMinLength) > 0)
            minValue = tempMinLength;
        count = count.add(tempCount);
    }

    @Override
    public void getFullStatistic() {
        String header = String.format("Full statistic for '%s'", type);
        String report = String.format("Amount: %s\nMinimum length: %s\nMaximum length: %s",
                count, minValue, maxValue);
        System.out.println(header);
        System.out.println(report);
        System.out.println("----------------------------------------------");
    }
}
