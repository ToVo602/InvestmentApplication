package data.investor;

import java.time.LocalDate;

public class Investor extends Person{
    private final double currentAnnualNetIncome;
    private final double annualIncomeIncreaseRate;
    private final double monthlySavingRate;
    private final double currentInvestableCapital;
    private final int plannedRetirementAge;

    private Investor(String firstname, String lastname, LocalDate birthday, double currentAnnualNetIncome,
                     double annualIncomeIncreaseRate, double monthlySavingRate, double currentInvestableCapital,
                     int plannedRetirementAge){
        super(firstname, lastname, birthday);
        this.currentAnnualNetIncome = currentAnnualNetIncome;
        this.annualIncomeIncreaseRate = annualIncomeIncreaseRate;
        this.monthlySavingRate = monthlySavingRate;
        this.currentInvestableCapital = currentInvestableCapital;
        this.plannedRetirementAge = plannedRetirementAge;
    }

    public double getCurrentAnnualNetIncome(){
        return this.currentAnnualNetIncome;
    }

    public double getAnnualIncomeIncreaseRate(){
        return this.annualIncomeIncreaseRate;
    }

    public double getMonthlySavingRate() {
        return monthlySavingRate;
    }

    public double getCurrentInvestableCapital() {
        return currentInvestableCapital;
    }

    public int getPlannedRetirementAge() {
        return plannedRetirementAge;
    }

    public static InvestorBuilder builder(){
        return new InvestorBuilder();
    }

    public static class InvestorBuilder{
        private String firstname;
        private String lastname;
        private LocalDate birthday;
        private double currentAnnualNetIncome;
        private double annualIncomeIncreaseRate;
        private double monthlySavingRate;
        private double currentInvestableCapital;
        private int plannedRetirementAge;

        private InvestorBuilder() {}

        public InvestorBuilder firstname(String firstname){
            this.firstname = firstname;
            return this;
        }

        public InvestorBuilder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public InvestorBuilder birthday(LocalDate birthday){
            this.birthday = birthday;
            return this;
        }

        public InvestorBuilder currentAnnualNetIncome(double currentAnnualNetIncome){
            this.currentAnnualNetIncome = currentAnnualNetIncome;
            return this;
        }

        public InvestorBuilder annualIncomeIncreaseRate(double annualIncomeIncreaseRate){
            this.annualIncomeIncreaseRate = annualIncomeIncreaseRate;
            return this;
        }

        public InvestorBuilder monthlySavingRate(double monthlySavingRate){
            this.monthlySavingRate = monthlySavingRate;
            return this;
        }

        public InvestorBuilder currentInvestableCapital(double currentInvestableCapital){
            this.currentInvestableCapital = currentInvestableCapital;
            return this;
        }

        public InvestorBuilder plannedRetirementAge(int plannedRetirementAge){
            this.plannedRetirementAge = plannedRetirementAge;
            return this;
        }

        public Investor build(){
            return new Investor(this.firstname, this.lastname, this.birthday, this.currentAnnualNetIncome, this.annualIncomeIncreaseRate,
                    this.monthlySavingRate, this.currentInvestableCapital, this.plannedRetirementAge);
        }
    }
}
