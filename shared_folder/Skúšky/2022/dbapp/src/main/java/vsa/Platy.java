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
  
  static void updateSalary(EntityManager em, String name, double salary) {
      if (name == null || name.isEmpty() || salary < 0) {
          return;
      }
      
      LocalDate dnes = LocalDate.now();
      
      ZamestnanecPK pk = new ZamestnanecPK();
      pk.setMeno(name);
      pk.setDatum_od(dnes);
      
      em.getTransaction().begin();
      Zamestnanec existujuci = em.find(Zamestnanec.class, pk);
      
      if (existujuci == null) {
          Zamestnanec zam = new Zamestnanec();
          zam.setPk(pk);
          zam.setPlat(salary);
          em.persist(zam);
      }
      
      em.getTransaction().commit();
  }
  
  
  static double salaryOn(EntityManager em, String name, LocalDate date) throws Exception {
      List<Zamestnanec> zamestnanci = em.createQuery("SELECT z FROM Zamestnanec z WHERE z.id.meno = :meno AND z.id.datum_od <= :datum ORDER BY z.id.datum_od DESC", Zamestnanec.class).setParameter("meno", name).setParameter("datum", date).getResultList();
      Zamestnanec zam = zamestnanci.isEmpty() ? null : zamestnanci.get(0);
      
      if (zam == null) {
          throw new Exception("nenajdene");
      }
      
      return zam.getPlat();
  }
}
