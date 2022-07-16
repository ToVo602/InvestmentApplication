package main;

import data.investor.Investor;
import data_structures.time_series.TimeSeries;
import services.InvestorService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Investor investor = Investor.builder()
                .firstname("Tobi")
                .lastname("Vogler")
                .birthday(LocalDate.of(1998, 11, 20))
                .currentAnnualNetIncome(40000.0)
                .annualIncomeIncreaseRate(0.015)
                .currentInvestableCapital(5000.0)
                .monthlySavingRate(0.15)
                .plannedRetirementAge(68)
                .build();

        InvestorService investorService = new InvestorService();
        TimeSeries<LocalDate, Double> assetVolumeDevelopment = investorService.getAssetVolumeDevelopment(investor, 0.07);
        TimeSeries<LocalDate, Double> humanCapitalDevelopment = investorService.getHumanCapitalDevelopment(investor, 0.015);
    }
}
