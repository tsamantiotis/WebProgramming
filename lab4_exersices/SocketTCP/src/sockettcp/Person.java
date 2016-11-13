/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettcp;

import java.io.Serializable;

/**
 *
 * @author Maira
 */
public class Person implements Serializable{
    private String Name;
    private String Surname;
    Person(){
        Name="";
        Surname="";
    }
    Person(String Name,String Surname){
        this.Name=Name;
        this.Surname=Surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }
    
    
}
