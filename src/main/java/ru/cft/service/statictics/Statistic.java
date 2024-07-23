package ru.cft.service.statictics;

import lombok.Getter;
import lombok.ToString;
import ru.cft.entity.StringType;

import java.util.List;

@Getter
@ToString
public abstract class Statistic<T extends Number> {

    protected T maxValue;
    protected T minValue;
    protected T count;
    protected StringType type;

    public Statistic(StringType type, T count) {
        this.type = type;
        this.count = count;
    }

    public abstract void refresh(List<String> data);

    public void getShortStatistic() {
        String header = String.format("Short statistic for '%s'", type);
        System.out.println(header);
        System.out.println(String.format("Amount: %s", count));
        System.out.println("----------------------------------------------");
    }

    public abstract void getFullStatistic();
}
