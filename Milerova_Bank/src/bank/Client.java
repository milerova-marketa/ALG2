/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.ArrayList;

/**
 *
 * @author Marketa.Milerova
 */
public abstract class Client {

    private String name;
    private ArrayList<Account> accounts = new ArrayList();
    
    public Client(String name){
        this.name = name;
    }

    public void openNewAccount(double sum) {
        this.accounts.add(new Account(sum));
    }
    public double balanceOnAll(){
        double sum = 0;
        for (Account account : accounts) {
            sum += account.getBalance();
        }
        return sum;
    }
    public abstract String callName();
}
