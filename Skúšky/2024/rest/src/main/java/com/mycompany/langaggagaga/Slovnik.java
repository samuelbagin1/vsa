/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.langaggagaga;

import java.util.HashMap;

/**
 *
 * @author edu
 */
public class Slovnik {
    private HashMap<String, HashMap<String, String>> preklady = new HashMap<>();

    public HashMap<String, HashMap<String, String>> getPreklady() {
        return preklady;
    }

    public void setPreklady(String jazyk,HashMap<String, String> preklady) {
        this.preklady.put(jazyk, preklady);
    }
   
    
}
