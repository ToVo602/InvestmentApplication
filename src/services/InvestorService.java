package services;

import data.investor.Investor;
import data_structures.time_series.TimeSeries;
import data_structures.time_series.TimeSeriesDataPoint;
import service_interfaces.IInvestorService;
import utils.InvestorServices;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class InvestorService implements IInvestorService {

    @Override
    public TimeSeries<LocalDate, Double> getAssetVolumeDevelopment(Investor investor, double estimatedAnnualReturn) {
        TimeSeries<LocalDate, Double> returnSeries = new TimeSeries<>();
        double monthlyReturn = InvestorServices.convertAnnualIntoMonthlyReturnRate(estimatedAnnualReturn);
        int yearsUntilRetirement = investor.getPlannedRetirementAge() - investor.getAge();

        LocalDate currentDate = LocalDate.now();
        double assetVolume = investor.getCurrentInvestableCapital();
        double monthlyNetIncome = investor.getCurrentAnnualNetIncome() / 12;
        double monthlySavings = monthlyNetIncome * investor.getMonthlySavingRate();

        for (int i = 0; i < yearsUntilRetirement; i++) {
            for (int j = 0; j < 12; j++) {
                double monthlyGains = (assetVolume + monthlySavings) * monthlyReturn + monthlySavings;
                assetVolume += monthlyGains;
                returnSeries.addDataPoint(new TimeSeriesDataPoint<>(currentDate, assetVolume));
                currentDate = currentDate.plusMonths(1L);
            }
            monthlyNetIncome += monthlyNetIncome * investor.getAnnualIncomeIncreaseRate();
            monthlySavings = monthlyNetIncome * investor.getMonthlySavingRate();
        }
        return returnSeries;
    }

    @Override
    public TimeSeries<LocalDate, Double> getHumanCapitalDevelopment(Investor investor, double discountRate) {
        double[] discountedAnnualNetIncomesArray = getDiscountedAnnualNetIncomes(investor, discountRate);
        List<Double> discountedAnnualIncomes = new LinkedList<Double>();
        for(double number : discountedAnnualNetIncomesArray){
            discountedAnnualIncomes.add(number);
        }

        TimeSeries<LocalDate, Double> returnSeries = new TimeSeries<>();
        LocalDate currentDate = LocalDate.now();

        for(double number : discountedAnnualIncomes){
            double accDiscountedIncomes = discountedAnnualIncomes
                    .stream()
                    .reduce(0.0, (subtotal, d) -> subtotal + d);

            returnSeries.addDataPoint(new TimeSeriesDataPoint<>(currentDate, accDiscountedIncomes));

            currentDate = currentDate.plusYears(1L);
            discountedAnnualIncomes.remove(number);
        }
        return returnSeries;
    }

    private double[] getDiscountedAnnualNetIncomes(Investor investor, double discountRate){
        int yearsUntilRetirement = investor.getPlannedRetirementAge() - investor.getAge();
        double[] discountedAnnualNetIncomes = new double[yearsUntilRetirement];
        double annualIncomeIncreaseRate = investor.getAnnualIncomeIncreaseRate();

        double annualIncome = investor.getCurrentAnnualNetIncome();

        for (int i = 0; i < yearsUntilRetirement; i++) {
            discountedAnnualNetIncomes[i] = annualIncome / Math.pow((1 + discountRate), i);
            annualIncome = annualIncome * (1 + annualIncomeIncreaseRate);
        }
        return discountedAnnualNetIncomes;
    }
}
