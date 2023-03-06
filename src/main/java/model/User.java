package model;

import java.util.Objects;

public class User {
    public String firstName;
    public String lastName;
    public String email;
    public int age;
    public int salary;
    public String department;

    public User(){
    }

    public User(String firstName,String lastName,String email,int age,int salary,String department){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString(){
        return "{\n" + firstName + "\n" + lastName + "\n" + email +
                "\n" + age + "\n" + salary + "\n" + department + "\n}\n";
    }

    @Override
    public boolean equals(Object o){
        User u = (User)o;
        return (
                Objects.equals(this.firstName,u.firstName) &&
                Objects.equals(this.lastName,u.lastName) &&
                Objects.equals(this.email,u.email) &&
                this.age==u.age &&
                this.salary == u.salary &&
                Objects.equals(this.department,u.department)
                );
    }
}
