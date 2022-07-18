package main;

import data.investor.Investor;
import data_structures.time_series.TimeSeries;
import services.InvestorService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Investor investor = Investor.builder()
                .firstname("Tobias")
                .lastname("Vogler")
                .birthday(LocalDate.of(1998, 11, 20))
                .currentAnnualNetIncome(29000.0)
                .annualIncomeIncreaseRate(0.015)
                .currentInvestableCapital(5000.0)
                .monthlySavingRate(0.2)
                .plannedRetirementAge(68)
                .build();

        System.out.println(investor.getFullName((String firstname, String lastname) -> {return firstname + " " + lastname;}));

        InvestorService investorService = new InvestorService();
        TimeSeries<LocalDate, Double> assetVolumeDevelopment = investorService.getAssetVolumeDevelopment(investor, 0.07);
        double assetVolumeAtRetirement = investorService.getAssetVolumeAtRetirement(investor, 0.07);
        TimeSeries<LocalDate, Double> humanCapitalDevelopment = investorService.getHumanCapitalDevelopment(investor, 0.025);
        double currentHumanCapital = investorService.getCurrentHumanCapital(investor, 0.025);

        for (int i = 0; i < assetVolumeDevelopment.size(); i++) {
            if((assetVolumeDevelopment.getDataPointAtIndex(i).getData() / humanCapitalDevelopment.getDataPointAtIndex(i).getData()) > 1){
                System.out.println("AssetVolume: " + assetVolumeDevelopment.getDataPointAtIndex(i).getData());
                System.out.println("HumanCapital: " + humanCapitalDevelopment.getDataPointAtIndex(i).getData());
                System.out.println("AssetVolume > HumanCapital after: " + i + " years");
                break;
            }
        }

        System.out.println("\nAssetVolume at Retirement: " + assetVolumeAtRetirement);
        System.out.println("CurrentHumanCapital: " + currentHumanCapital);

    }
}
