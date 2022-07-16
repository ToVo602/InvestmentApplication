package data_structures.time_series;

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

}
