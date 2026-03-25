/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package vsa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author igor
 */
public class Zapocet {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
    public static final EntityManager em = emf.createEntityManager();
    
    /*  Main si môžete upraviť. Je len pre vase otestovanie, nehodnotí sa. 
     */
    public static void main(String[] args) throws Exception {
        Zapocet z = new Zapocet();
        z.aktualizujKnihu("111", "jes", 22.0);
    }

    /*  Metóda vyhľadá v databáze knihu podľa zadaného isbn (klúč) a aktualizuje údaje o nej, pričom:
    Ak nie je zadané isbn (t.j. je null alebo prázdne), vyhodí výnimku s textom "zle isbn".
    Ak kniha s daným isbn v databáze neexistuje, vytvorí novú knihu so zadanými údajmi.
    Ak v databáze už existuje, aktualizuje údaje o knihe takto: 
    - ak argument metódy nazov je zadaný (nenulový), aktualizuje názov v databáze.
    - ak je argument metódy cena zadaný, aktualizuje cenu v databáze. 
     */
    public void aktualizujKnihu(String isbn, String nazov, Double cena) throws Exception {
        
        if (isbn == null || isbn.isEmpty()) {
            throw new Exception("chyba");
        }
        
        List<Kniha> books = em.createQuery("SELECT k FROM Kniha k WHERE k.isbn = :isbn", Kniha.class).setParameter("isbn", isbn).getResultList();
        Kniha book = books.isEmpty() ? null : books.get(0);
        
        if (book == null) {
            em.getTransaction().begin();
            
            book = new Kniha();
            book.setIsbn(isbn);
            book.setCena(cena);
            book.setNazov(nazov);
            
            em.persist(book);
            
            em.getTransaction().commit();
        } else {
            em.getTransaction().begin();
            if (nazov != null && !nazov.isEmpty()) {
                book.setNazov(nazov);
            }
            
            if (cena != null) {
                book.setCena(cena);
            }
            
            em.getTransaction().commit();
        }
        
    }

    /*  Metóda dostane ako argument cennik - mapu, v ktorej klúč je isbn knihy a hodnota jej nová cena.
    Pre každú položku z cenníka vyhľadá knihu v databáze a aktualizuje jej cenu.
    Ak kniha s daným isbn v databáze neexistuje, položku ignoruje. 
    Metóda vráti počet kníh s aktualizovanými cenami.
     */
    public int aktualizujCeny(Map<String, Double> cennik) {
        int count = 0;
        
        em.getTransaction().begin();
        
        for (Map.Entry<String, Double> entry : cennik.entrySet()) {
            String isbn = entry.getKey();
            Double novaCena = entry.getValue();
            
            List<Kniha> books = em.createQuery("SELECT k FROM Kniha k WHERE k.isbn = :isbn").setParameter("isbn", isbn).getResultList();
            
            if (!books.isEmpty()) {
                Kniha book = books.get(0);
                book.setCena(novaCena);
                count++;
            }
        }
        
        
        em.getTransaction().commit();
        
        return count;
    }
}
