package data.investor;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class InvestorTest {
    @Test
    public void creatingValidInvestor(){
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
    }

    @Test
    public void correctAge(){
        //Given: a curtain investor
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

        //When: the getAge() is called
        int investorAge = investor.getAge();

        //Then: calculation of correct age
        Assert.assertEquals(23, investorAge);
    }

    @Test(expected = IllegalArgumentException.class)
    public void investorWithNegativNumbers(){
        Investor investor = Investor.builder()
                .firstname("Tobias")
                .lastname("Vogler")
                .birthday(LocalDate.of(1998, 11, 20))
                .currentAnnualNetIncome(29000.0)
                .annualIncomeIncreaseRate(0.015)
                .currentInvestableCapital(5000.0)
                .monthlySavingRate(-0.2)
                .plannedRetirementAge(68)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void investorWithPlannedRetirementAgeInThePast(){
        Investor investor = Investor.builder()
                .firstname("Tobias")
                .lastname("Vogler")
                .birthday(LocalDate.of(1998, 11, 20))
                .currentAnnualNetIncome(29000.0)
                .annualIncomeIncreaseRate(0.015)
                .currentInvestableCapital(5000.0)
                .monthlySavingRate(0.2)
                .plannedRetirementAge(10)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void investorBornInTheFuture(){
        Investor investor = Investor.builder()
                .firstname("Tobias")
                .lastname("Vogler")
                .birthday(LocalDate.of(2050, 11, 20))
                .currentAnnualNetIncome(29000.0)
                .annualIncomeIncreaseRate(0.015)
                .currentInvestableCapital(5000.0)
                .monthlySavingRate(0.2)
                .plannedRetirementAge(68)
                .build();
    }

}
