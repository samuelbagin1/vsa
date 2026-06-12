/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author edu
 */
public class Platy {

  public static void main(String[] args) throws Exception {
  }
  
  public static void updateSalary (EntityManager em, String name, double salary) {
      LocalDate dnes = LocalDate.now();
      ZamestnanecPK id = new ZamestnanecPK(name, dnes);
      
      if (em.find(Zamestnanec.class, id) != null) {
          return;
      }
      
      em.getTransaction().begin();
      
      Zamestnanec z = new Zamestnanec(name, dnes, salary);
      em.persist(z);
      em.getTransaction().commit();
  }
  
  public static double salaryOn(EntityManager em, String name, LocalDate date) throws Exception {
      List<Zamestnanec> vysledok = em.createQuery("SELECT z FROM Zamestnanec z WHERE z.id.meno = :meno AND z.id.datum_od <= :datum ORDER BY z.id.datum_od DESC", Zamestnanec.class)
              .setParameter("meno", name).setParameter("datum", date).getResultList();
      
      Zamestnanec zamestnanec = vysledok.isEmpty() ? null : vysledok.get(0);
      
      if (zamestnanec == null) {
          throw new Exception("nenajdene");
      }
      return zamestnanec.getPlat();
  }
   
}
