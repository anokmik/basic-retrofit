package sample.retrofit.client.models;

import com.google.gson.annotations.Expose;

public final class ResponseUser {
    @Expose
    private final String name;
    @Expose
    private final String surname;
    @Expose
    private final int age;
    @Expose
    private final double balance;

    public ResponseUser(String name, String surname, int age, double balance) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return ResponseUser.class.getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                '}';
    }

}