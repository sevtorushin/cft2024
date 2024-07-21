package ru.cft.statictics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public abstract class Statistic<T extends Number> {

    protected T maxValue;
    protected T minValue;
    protected T count;

    public abstract void refresh(List<String> data);
}
