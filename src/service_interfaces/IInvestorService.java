package service_interfaces;

import data.investor.Investor;
import data_structures.time_series.TimeSeries;

import java.time.LocalDate;

public interface IInvestorService {
    TimeSeries<LocalDate, Double> getAssetVolumeDevelopment(Investor investor, double estimatedAnnualReturn);

    TimeSeries<LocalDate, Double> getHumanCapitalDevelopment(Investor investor, double discountRate);

    //double calculateAssetVolumeAtRetirement(Investor investor, double estimatedAnnualReturn);

    //double calculateCurrentHumanCapital(Investor investor, double discountRate);

    //double calculateInvestedCapital(Investor investor);
}
