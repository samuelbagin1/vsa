package skola;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbApp {

    /**
     * @param args the command line arguments
     *
     * Len pre vase otestovanie. Mozete si upravit.
     */
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("skolaPU");
        EntityManager em = emf.createEntityManager();

        novyPredmet(em,"VSA");
        zapisatStudenta(em, "Mrkva", "VSA");
        System.out.println("Pocet predmetov: " + pocetPredmetov(em, "Mrkva"));    // vypise 1  
    }

    /* Vrati pocet predmetov, ktore ma zapisane student so zadanym menom. 
     * Ak meno nie je zadane alebo student s danym menom neexistuje vrati 0.
     * Pozn. Metoda sa moze spolahnut na to, ze v DB je meno studenta jedinecne
     */
    public static int pocetPredmetov(EntityManager em, String meno) throws Exception {
        if (meno == null)
            return 0;
        Student student = em.createQuery("SELECT s from Student s where s.meno = :meno",Student.class)
                .setParameter("meno",meno)
                .getResultStream()
                .findFirst()
                .orElse(null);
        if (student == null)
            return 0;
        return student.getPredmety().size();
    }

    /* Vytvori novy predmet.
     *
     * @param em            entity manager
     * @param kodPredmetu   kod predmetu
     *
     * Metoda naprv zisti ci predmet s danym kodom uz neexistuje v databaze.
     * Ak kod predmetu nie je zadany (alebo je prazdny), vyhodi vynimku so spravou "chybne udaje".
     * Ak predmet s danym kodom existuje, vyhodi vynimku so spravou "predmet existuje".
     * Ak predmet s danym kodom neexistuje, vytvori novy predmet a vlozi ho do DB
     *    Poznamka: odbor predmetu bude null a na predmet nebudu zapisani ziadni studenti
     *
     * Navratova hodnota:   
     *   Novovytvoreny objekt predmetu 
     */
    public static Predmet novyPredmet(EntityManager em, String kodPredmetu ) throws Exception {
        if (kodPredmetu == null || kodPredmetu.equals(""))
            throw new Exception("chybne udaje");
        Predmet predmet = em.find(Predmet.class,kodPredmetu);
        if (predmet != null)
            throw new Exception("predmet existuje");
        Predmet newPredmet = new Predmet();
        newPredmet.setKod(kodPredmetu);
        em.getTransaction().begin();
        em.persist(newPredmet);
        em.getTransaction().commit();
        return newPredmet;
    }

    /* Zapise studenta na predmet:
     *
     * @param em            entity manager
     * @param meno          meno studenta
     * @param kodPredmetu   kod predmetu
     *
     * Metoda vyhlada studenta podla mena a predmet podla kodu.
     *   Pozn. Metoda sa moze spolahnut na to, ze v DB je meno studenta jedinecne
     * Ak student so zadanym menom nenexituje, vytvori ho.
     * Ak student este nie je zapisany na predmet, prida ho medzi studentov predmetu.
     * Ak student s danym menom uz bol zapisany na predmet, neprida ho znovu,
     *   ale vyhodi vynimku so spravou "duplicitny zapis"
     * Ak meno studenta alebo kod predmetu nie je zadane, vyhodi vynimku so spravou "chybne udaje"
     * Ak predmet neexituje, vyhodi vynimku so spravou "predmet neexistuje".
     */
    public static void zapisatStudenta(EntityManager em, String meno, String kodPredmetu) throws Exception {
        if (meno == null || kodPredmetu == null)
            throw new Exception("chybne udaje");
        Predmet predmet = em.find(Predmet.class,kodPredmetu);
        if (predmet == null)
            throw new Exception("predmet neexistuje");
        for (Student student: predmet.getStudenti()) {
            if (student.getMeno().equals(meno))
                throw new Exception("duplicitny zapis");
        }
        Student student = em.createQuery("SELECT s FROM Student s WHERE s.meno = :meno", Student.class)
                .setParameter("meno",meno)
                .getResultStream()
                .findFirst()
                .orElse(null);
        boolean persistStudent = student == null;
        if (student == null)
            student = new Student(meno);

        em.getTransaction().begin();
        if (persistStudent)
            em.persist(student);
        student.getPredmety().add(predmet);
        predmet.getStudenti().add(student);
        em.getTransaction().commit();
    }

}
