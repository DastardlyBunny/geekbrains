package ru.geekbrains.lesson5;

public class Person {

    private String fullName;
    private String position;
    private String email;
    private String telephone;
    private double salary;
    private int age;

    public Person(String fullName, String position, String email, String telephone, double salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.telephone = telephone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
