/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banka;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Banka {
    
    public Banka(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbappPU");
        EntityManager em = emf.createEntityManager();
        
        
        
    }
    
    

    /* 
    Metóda slúži na vytvorenie nového prevodu
    Dostane sumu, ktorú treba previesť, a čísla účtov, z ktorého a na ktorý sa suma prevádza. 
    Najprv preverí či zadané účty existujú a suma je kladná.
    - Ak niektorý z účtov neexistuje alebo suma nie je kladná, vyhodí výnimku so správou: "Chybne udaje"
    - Inak vytvorí nový záznam v tabuľke prevodov so zadanými údajmi, pričom hodnota 
      v stĺpci REALIZOVANY nebude zadaná (NULL) a vráti ID vytvoreného prevodu.
    Poznamka. V tabuľke účtov táto metóda nerobí žiadne zmeny.    
    */
    public static long vytvorPrevod(EntityManager em, double suma, long zUctu, long naUcet) throws Exception {
     
        Ucet z = em.find(Ucet.class,zUctu);
        Ucet na = em.find(Ucet.class,naUcet);
        if(z == null || na == null || suma < 0){
            throw new Exception("Chybne udaje");
        }
        Prevod p = new Prevod();
        p.setSuma(suma);
        p.setUcetNa(na);
        p.setUcetZ(z);

        
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        
        return p.getId();
    }
    
    /* 
    Metóda vyhľadá v databáze všetky nezrealizované prevody a ak je možné, zrealizuje ich.  
    Nezrealizovaný prevod je prevod, ktorý nemá hodnotu v stĺpci REALIZOVANY.
    Prevod je možné zrealizovať, ak stav účtu, z ktorého sa SUMA prevádza (t.j. ZUCTU_ID) 
      nie je nižší ako prevádzaná SUMA.
    Realizácia prevodu sa vykoná tak, že prevádzaná SUMA sa odčíta od stavu účtu,
       z ktorého sa prevádza a pripočíta sa k stavu na druhom účte (t.j NAUCET_ID). 
    Zároveň v prevode sa do stĺpca REALIZOVANY zadá aktuálny čas ako čas realizácie. 
    Všetky tieto zmeny funkcia zapíše do databázy naraz v rámci jednej transakcie.  
    Návratová hodnota metódy je počet zrealizovaných prevodov.
    */
    public static int realizujPrevody(EntityManager em) {
        int pocetPrevodov = 0;
        
        em.getTransaction().begin();
        TypedQuery<Prevod> query = em.createNamedQuery("Prevod.findAll", Prevod.class);
        List<Prevod> prevody = query.getResultList();
        for (Prevod p : prevody) {
            if (p.getRealizovany() != null){
                continue;
            }
            if (p.getUcetZ().getStav() < p.getSuma()) {
                continue;
            }
            Double novyZostatokZ = p.getUcetZ().getStav() - p.getSuma();
            Double novyZostatokNa = p.getUcetNa().getStav() + p.getSuma();
            
            p.getUcetZ().setStav(novyZostatokZ);
            p.getUcetNa().setStav(novyZostatokNa);
            p.setRealizovany(new Date());
            pocetPrevodov += 1;
            
        }
        em.getTransaction().commit();
        
        return pocetPrevodov;
    }
}
