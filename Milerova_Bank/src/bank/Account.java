/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author Marketa.Milerova
 */
public class Account {

    private double balance = 0;

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public void insert(double sum) {
        if (sum > 0) {
            this.balance += sum;
        } else {
            throw new IllegalArgumentException("Zadejte kladnou sumu.");
        }
    }

    public void withdraw(double sum) {
        if (balance < sum) {
            throw new IllegalArgumentException("Nelze vybrat více peněz než je na účtě.");
        } else {
            this.balance -= sum;
        }
    }

    public double getBalance() {
        return balance;
    }

//    public static void main(String[] args) {
//        Account a1 = new Account();
//        a1.insert(40);
//        System.out.println(a1.getBalance());
//        Account a2 = new Account(300);
//        System.out.println(a2.getBalance());
//        a1.withdraw(30);
//        System.out.println(a1.getBalance());
//    }

}
