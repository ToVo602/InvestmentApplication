package investment_calculator;

public class Main {
    public static void main(String[] args) {
        Investor tovo = new Investor("ToVo", 2000, 600, (65 - 23)*12);
        double investedCapital = tovo.calculateInvestedCapital();
        double lifeTimeAssetVolume = tovo.calculateLifetimeAssetVolume(0.06);

        System.out.printf("ToVo has invested %.2f€\nand will have about %.2f€\nin %d years.",
                investedCapital, lifeTimeAssetVolume, (int) tovo.getDurationInMonths() / 12);

        double currentIncome = 52000;
        double estimatedAnnualIncomeGrowth = 0.01;
        double discountingRate = 0.02;
        int amountOfYears = 40;
        double accIncome = 0;
        double accDiscountedAnnualIncomes = 0;
        for(int i = 0; i < amountOfYears; i++){
            currentIncome = currentIncome * (1 + estimatedAnnualIncomeGrowth);
            accIncome = accIncome + currentIncome;
            accDiscountedAnnualIncomes = accDiscountedAnnualIncomes  + currentIncome / Math.pow(1 + discountingRate, i);
        }
        double estimatedHumanCapital = accDiscountedAnnualIncomes;

    }
}
