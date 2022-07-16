package data_structures.time_series;

import java.util.Objects;

public class TimeSeriesDataPoint<T, D>{
    private final T time;
    private final D data;

    public TimeSeriesDataPoint(T time, D data) {
        this.time = time;
        this.data = data;
    }

    public T getTime() {
        return time;
    }

    public D getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSeriesDataPoint<?, ?> that = (TimeSeriesDataPoint<?, ?>) o;
        return time.equals(that.time) && data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, data);
    }
}
