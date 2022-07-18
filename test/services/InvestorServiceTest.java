package services;

import data.investor.Investor;
import org.junit.Assert;
import org.junit.Test;
import service_interfaces.IInvestorService;

import java.time.LocalDate;

public class InvestorServiceTest {
    public static final double DOUBLE_DELTA = 0.000001;

    @Test
    public void calculateCorrectHumanCapital(){
        //Given a valid investor and a InvestorService
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

        IInvestorService investorService = new InvestorService();

        //When: calculating the HumanCapital of an investor where annualIncomeIncreaseRate == discountRate
        double currentHumanCapital = investorService.getCurrentHumanCapital(investor, 0.015);

        //Then: getting correct HumanCapital
        Assert.assertEquals((investor.getPlannedRetirementAge() - investor.getAge()) * investor.getCurrentAnnualNetIncome(),
                currentHumanCapital, DOUBLE_DELTA);
    }
}
