package ru.cft.statictics;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class NumberStatistic extends Statistic<BigDecimal> {

    @ToString.Include
    private BigDecimal sum;

    public NumberStatistic() {
        super(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        this.sum = BigDecimal.ZERO;
    }

    private List<BigDecimal> convert(List<String> data) {
        List<BigDecimal> result = new ArrayList<>();
        for (String s : data) {
            try {
                result.add(new BigDecimal(s.replace(',', '.')));
            } catch (NumberFormatException e) {
                System.err.println(String.format("'%s' is not number.\n%s", s, e.getMessage()));
            }
        }
        return result;
    }

    @Override
    public void refresh(List<String> data) {
        if (data.isEmpty())
            return;
        List<BigDecimal> result = convert(data);
        BigDecimal tempSum = result.stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        BigDecimal tempCount = new BigDecimal(String.valueOf(result.size()));
        BigDecimal tempMaxValue = Collections.max(result);
        BigDecimal tempMinValue = Collections.min(result);
        if (getMaxValue().compareTo(tempMaxValue) < 0)
            maxValue = tempMaxValue;
        if (getMinValue().compareTo(tempMinValue) > 0)
            minValue = tempMinValue;
        count = getCount().add(tempCount);
        sum = getSum().add(tempSum);
    }

    @ToString.Include
    private BigDecimal getAvgValue() {
        if (getCount().compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ZERO;
        return getSum().divide(getCount(), 2, RoundingMode.HALF_UP);
    }
}
