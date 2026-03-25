package vsa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Map;

public class Zapocet {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("vsaPU");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // placeholder pre NetBeans Run Project
    }

    public void aktualizujKnihu(String isbn, String nazov, Double cena) throws Exception {
        // TODO
    }

    public int aktualizujCeny(Map<String, Double> cennik) throws Exception {
        // TODO
    }
}
