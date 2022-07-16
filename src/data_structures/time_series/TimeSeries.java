package data_structures.time_series;

import java.util.Iterator;
import java.util.List;

public class TimeSeries<T, D> implements Iterable {
    private int size;
    private List<TimeSeriesDataPoint<T, D>> dataPoints;

    public void addDataPoint(TimeSeriesDataPoint<T, D> dataPoint){
        this.dataPoints.add(dataPoint);
        this.size++;
    }

    public TimeSeriesDataPoint<T, D> getLastDataPoint(){
        return this.dataPoints.get(this.dataPoints.size() - 1);
    }


    @Override
    public Iterator iterator() {
        return dataPoints.iterator();
    }
}
