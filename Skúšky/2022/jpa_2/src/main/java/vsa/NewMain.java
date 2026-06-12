/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa;

import java.time.LocalDate;
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

  public static void main(String[] args) throws Exception {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
      EntityManager em = emf.createEntityManager();
      updateSalary(em, "Jano", 20.5);
      
      double s = salaryOn(em, "Jano", LocalDate.now());
      System.out.println(s);
  }
  
  public static void updateSalary (EntityManager em, String name, double salary) {
      LocalDate ld = LocalDate.now();
      
      Query q = em.createQuery("SELECT COUNT(z) FROM Zamestnanec z WHERE z.id.meno = :meno AND z.id.datumOd = :datumOd");
      q.setParameter("meno", name);
      q.setParameter("datumOd", ld);
      if ((long) q.getSingleResult() > 0) {
          return;
      }
      Zamestnanec z = new Zamestnanec();
      ZamestnanecPK zpk = new ZamestnanecPK();
      
      zpk.setMeno(name);
      zpk.setDatumOd(ld);
      z.setId(zpk);
      z.setPlat(salary);
      em.getTransaction().begin();
      try {
          em.persist(z);
          em.getTransaction().commit();
      }
      catch (Exception e) {
          em.getTransaction().rollback();
          e.printStackTrace();
      }
  }
  
  public static double salaryOn(EntityManager em, String name, LocalDate date) throws Exception {
      Query q = em.createQuery("SELECT z FROM Zamestnanec z WHERE z.id.meno = :meno AND z.id.datumOd <= :datumOd ORDER BY z.id.datumOd DESC");
      q.setParameter("meno", name);
      q.setParameter("datumOd", date);
      q.setMaxResults(1);
      Zamestnanec z = (Zamestnanec) q.getSingleResult();
      return z.getPlat();
  }
   
}
