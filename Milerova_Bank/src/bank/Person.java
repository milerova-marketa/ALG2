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
public class Person extends Client {
    
    private String name;

    public Person(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String callName() {
        String ending = name.substring(name.length()-3);
        if("ova".equals(ending)){
            return "pani " + name;
        } else{
            return "pan " + name;
        }
    }
}
