/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author edu
 */
public class NewMain {

  public static void main(String[] args) {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
     EntityManager em = emf.createEntityManager();
     
     Predmet predmet = new Predmet();
     Profesor profesor = new Profesor();
     Student student = new Student();
     
     profesor.setMeno("Kossaczky");
     profesor.setUstav("UIM");
     student.setMeno("Adam");
     student.setRocnik("treti");
     
     List<String> literatura = new ArrayList();
     literatura.add("ucebnica");
     
     List<Student> studenti = new ArrayList();
     studenti.add(student);
     
     predmet.setLiteratura(literatura);
     predmet.setPrednasajuci_id(profesor);
     predmet.setStudenti(studenti);
  } 
}
