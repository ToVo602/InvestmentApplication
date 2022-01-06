package InvestmentCalculator;

public class Main {
    public static void main(String[] args) {
        Investor tovo = new Investor("ToVo", 2000, 600, (65 - 23)*12);
        double investedCapital = tovo.calculateInvestedCapital();
        double lifeTimeAssetVolume = tovo.calculateLifetimeAssetVolume(0.06);

        System.out.printf("ToVo has invested %.2f€\nand will have about %.2f€\nin %d years.", investedCapital, lifeTimeAssetVolume, (int) tovo.getDurationInMonths() / 12);

    }
}
