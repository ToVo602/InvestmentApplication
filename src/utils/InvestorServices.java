package utils;

public class InvestorServices {
    private InvestorServices(){}

    public static double convertAnnualIntoMonthlyReturnRate(double annualReturn){
        return Math.pow(1 + annualReturn, (double) 1/12) - 1;
    }
}
