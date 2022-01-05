package InvestmentCalculator;

public class Investor {
    // private final int MONTHS_PER_YEAR = 12;

    private String name;
    private double startingCapital;
    private double savingsPerMonth;
    private int durationInMonths;

    public Investor(String name, double startingCapital, double savingsPerMonth, int durationInMonths) {
        this.name = name;
        this.startingCapital = startingCapital;
        this.savingsPerMonth = savingsPerMonth;
        this.durationInMonths = durationInMonths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartingCapital() {
        return startingCapital;
    }

    public void setStartingCapital(double startingCapital) {
        this.startingCapital = startingCapital;
    }

    public double getSavingsPerMonth() {
        return savingsPerMonth;
    }

    public void setSavingsPerMonth(double savingsPerMonth) {
        this.savingsPerMonth = savingsPerMonth;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    private static double revertAnnualIntoMonthlyReturn(double estimatedAnnualReturn){
        return Math.pow(1 + estimatedAnnualReturn, (double) 1/12) - 1;
    }

    public double calculateInvestedCapital(){
        double investedCapital = this.startingCapital + this.savingsPerMonth * this.durationInMonths;
        System.out.printf("%s will invest %.2f€\n", this.name, investedCapital);
        return investedCapital;
    }

    public double calculateLifetimeAssetVolume(double estimatedAnnualReturn){
        double monthlyReturn = revertAnnualIntoMonthlyReturn(estimatedAnnualReturn);
        double currentAssetVolume = this.startingCapital;
        for (int i = 0; i < this.durationInMonths; i++) {
            double monthlyGains = (this.savingsPerMonth + currentAssetVolume) * monthlyReturn + this.savingsPerMonth;
            currentAssetVolume += monthlyGains;
        }
        System.out.printf("%s will have %.2f€\n", this.name, currentAssetVolume);
        return currentAssetVolume;
    }
}
