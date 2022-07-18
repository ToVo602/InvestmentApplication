package services;

import data.investor.Investor;
import data_structures.time_series.TimeSeries;
import data_structures.time_series.TimeSeriesDataPoint;
import service_interfaces.IInvestorService;
import utils.InvestorServices;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class InvestorService implements IInvestorService {

    @Override
    public TimeSeries<LocalDate, Double> getAssetVolumeDevelopment(Investor investor, double estimatedAnnualReturn) {
        if(investor == null){
            throw new NullPointerException("investor must not be null");
        }
        if(estimatedAnnualReturn > 1 || estimatedAnnualReturn < 0){
            throw new IllegalArgumentException("estimatedAnnualReturn must be between 0 and 1 (inclusive)");
        }

        double monthlyReturn = InvestorServices.convertAnnualIntoMonthlyReturnRate(estimatedAnnualReturn);
        int yearsUntilRetirement = investor.getPlannedRetirementAge() - investor.getAge();
        double annualIncomeIncreaseRate = investor.getAnnualIncomeIncreaseRate();
        double monthlySavingRate = investor.getMonthlySavingRate();

        LocalDate currentDate = LocalDate.now();
        double assetVolume = investor.getCurrentInvestableCapital();
        double monthlyNetIncome = investor.getCurrentAnnualNetIncome() / 12;
        double monthlySavings = monthlyNetIncome * monthlySavingRate;
        TimeSeries<LocalDate, Double> returnSeries = new TimeSeries<>();

        for (int i = 0; i < yearsUntilRetirement; i++) {
            for (int j = 0; j < 12; j++) {
                double monthlyGains = (assetVolume + monthlySavings) * monthlyReturn + monthlySavings;
                assetVolume += monthlyGains;
            }
            returnSeries.addDataPoint(new TimeSeriesDataPoint<>(currentDate, assetVolume));
            currentDate = currentDate.plusYears(1L);
            monthlyNetIncome += monthlyNetIncome * annualIncomeIncreaseRate;
            monthlySavings = monthlyNetIncome * monthlySavingRate;
        }
        return returnSeries;
    }

    @Override
    public TimeSeries<LocalDate, Double> getHumanCapitalDevelopment(Investor investor, double discountRate) {
        List<Double> discountedAnnualIncomes = getDiscountedAnnualNetIncomes(investor, discountRate);

        LocalDate currentDate = LocalDate.now();
        TimeSeries<LocalDate, Double> returnSeries = new TimeSeries<>();

        while(!discountedAnnualIncomes.isEmpty()){
            double accDiscountedIncomes = discountedAnnualIncomes
                    .stream()
                    .mapToDouble((d) -> d)
                    .sum();

            returnSeries.addDataPoint(new TimeSeriesDataPoint<>(currentDate, accDiscountedIncomes));

            currentDate = currentDate.plusYears(1L);
            discountedAnnualIncomes = discountedAnnualIncomes
                    .stream()
                    .map((d) -> d * (1 + discountRate))
                    .collect(Collectors.toList());
            discountedAnnualIncomes.remove(0);
        }
        return returnSeries;
    }

    private List<Double> getDiscountedAnnualNetIncomes(Investor investor, double discountRate){
        int yearsUntilRetirement = investor.getPlannedRetirementAge() - investor.getAge();
        double[] discountedAnnualNetIncomes = new double[yearsUntilRetirement];
        double annualIncomeIncreaseRate = investor.getAnnualIncomeIncreaseRate();

        double annualIncome = investor.getCurrentAnnualNetIncome();
        for (int i = 0; i < yearsUntilRetirement; i++) {
            discountedAnnualNetIncomes[i] = annualIncome / Math.pow((1 + discountRate), i);
            annualIncome = annualIncome * (1 + annualIncomeIncreaseRate);
        }

        List<Double> discountedAnnualIncomes = new LinkedList<Double>();
        for(double number : discountedAnnualNetIncomes){
            discountedAnnualIncomes.add(number);
        }
        return discountedAnnualIncomes;
    }
}
