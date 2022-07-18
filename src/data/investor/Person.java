package data.investor;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.BinaryOperator;

class Person {
    private final String firstname;
    private final String lastname;
    private final LocalDate birthday;

    public Person(String firstname, String lastname, LocalDate birthday) {
        if(firstname == null || lastname == null || birthday == null){
            throw new NullPointerException();
        }
        if(firstname.strip().length() == 0 || lastname.strip().length() == 0){
            throw new IllegalArgumentException("firstname and lastname have to be real names");
        }
        if(Period.between(birthday, LocalDate.now()).getYears() < 0){
            throw new IllegalArgumentException("Birthday must not be in the future");
        }
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
