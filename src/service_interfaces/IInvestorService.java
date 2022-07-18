package service_interfaces;

import data.investor.Investor;
import data_structures.time_series.TimeSeries;

import java.time.LocalDate;

public interface IInvestorService {
    /**
     * calculates an annual time series from now to the retirement age of a given investor of the investor's asset volume under the assumption that
     * the investor invests the current investable capital and a fixed share of the investors income per month. The income gets increased by a fixed rate once a year.
     *
     * @param investor must not be {@code null}
     * @param estimatedAnnualReturn must be between 0 and 1
     * @return a special list of LocalDate (starting with the current date (LocalDate.now())) and Double (calculated asset volume at the given LocalDate) pairs for every year from the current age of the investor to it's retirement age
     * @throws NullPointerException if investor == {@code null}
     * @throws IllegalArgumentException if estimatedAnnualReturn is not between 0 and 1 (inclusive)
     */
    TimeSeries<LocalDate, Double> getAssetVolumeDevelopment(Investor investor, double estimatedAnnualReturn);

    TimeSeries<LocalDate, Double> getHumanCapitalDevelopment(Investor investor, double discountRate);

    default double getAssetVolumeAtRetirement(Investor investor, double estimatedAnnualReturn){
        return getAssetVolumeDevelopment(investor, estimatedAnnualReturn).getLastDataPoint().getData();
    }

    default double getCurrentHumanCapital(Investor investor, double discountRate){
        return getHumanCapitalDevelopment(investor, discountRate).getDataPointAtIndex(0).getData();
    }

    //double calculateInvestedCapital(Investor investor);
}
