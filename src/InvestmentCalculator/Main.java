package InvestmentCalculator;

public class Main {
    public static void main(String[] args) {
        Investor heike = new Investor("Heike Vogler", 0, 200, 13*12);

        heike.calculateInvestedCapital();
        heike.calculateLifetimeAssetVolume(0.06);
    }
}
