package ru.cft.statictics;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.cft.StringType;

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

    private static final Logger log = LogManager.getLogger(NumberStatistic.class.getSimpleName());

    public NumberStatistic(StringType type) {
        super(type, BigDecimal.ZERO);
        this.sum = BigDecimal.ZERO;
    }

    private List<BigDecimal> convert(@NonNull List<String> data) {
        List<BigDecimal> result = new ArrayList<>();
        for (String s : data) {
            try {
                result.add(new BigDecimal(s.replace(',', '.')));
            } catch (NumberFormatException e) {
                log.warn(String.format("'%s' is not number.\n%s", s, e.getMessage()));
            }
        }
        return result;
    }

    @Override
    public void refresh(@NonNull List<String> data) {
        if (data.isEmpty())
            return;
        List<BigDecimal> result = convert(data);
        BigDecimal tempSum = result.stream()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        BigDecimal tempCount = new BigDecimal(String.valueOf(result.size()));
        BigDecimal tempMaxValue = Collections.max(result);
        BigDecimal tempMinValue = Collections.min(result);
        if (maxValue == null)
            maxValue = tempMaxValue;
        else if (maxValue.compareTo(tempMaxValue) < 0)
            maxValue = tempMaxValue;
        if (minValue == null)
            minValue = tempMinValue;
        else if (minValue.compareTo(tempMinValue) > 0)
            minValue = tempMinValue;
        count = count.add(tempCount);
        sum = sum.add(tempSum);
    }

    @Override
    public void getFullStatistic() {
        String header = String.format("Full statistic for '%s'", type);
        String report = String.format("Amount: %s\nMinimum value: %s\nMaximum value: %s\nAverage value: %s\nSum: %s\n",
                count, minValue, maxValue, getAvgValue(), sum);
        System.out.println(header);
        System.out.println(report);
        System.out.println("----------------------------------------------");
    }

    @ToString.Include
    private BigDecimal getAvgValue() {
        if (getCount().compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ZERO;
        return getSum().divide(getCount(), 2, RoundingMode.HALF_UP);
    }
}
