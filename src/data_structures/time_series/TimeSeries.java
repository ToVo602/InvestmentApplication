package data_structures.time_series;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TimeSeries<T, D> implements Iterable {
    private int size;
    private List<TimeSeriesDataPoint<T, D>> dataPoints;

    public TimeSeries(){
        this.dataPoints = new LinkedList<>();
    }

    public int size(){
        return this.size;
    }

    public TimeSeriesDataPoint<T, D> getDataPointAtIndex(int index){
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }
        return this.dataPoints.get(index);
    }

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
