package data.investor;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.BinaryOperator;

public class Person {
    private final String firstname;
    private final String lastname;
    private final LocalDate birthday;

    public Person(String firstname, String lastname, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getAge(){
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }

    public String getFullName(BinaryOperator<String> func){
        return func.apply(this.firstname, this.lastname);
    }
}
