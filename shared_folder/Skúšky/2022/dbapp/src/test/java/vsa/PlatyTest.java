package vsa;

import java.sql.*;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.*;
import static org.junit.Assert.*;

public class PlatyTest {

    private static EntityManagerFactory emf;
    private static int BODY = 0;

    private static Connection getCon() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/edudb",
                "edu",
                "edu4edu"
        );
    }

    private static void dropTable(Statement st, String table) {
        try {
            st.executeUpdate("DROP TABLE IF EXISTS " + table + " CASCADE");
        } catch (SQLException ex) {
        }
    }

    private static void createTables() throws SQLException {
        try (Connection con = getCon()) {
            Statement st = con.createStatement();

            dropTable(st, "ZAMESTNANEC");

            st.executeUpdate(
                    "CREATE TABLE ZAMESTNANEC ("
                    + "MENO VARCHAR(255) NOT NULL, "
                    + "DATUM_OD DATE NOT NULL, "
                    + "PLAT DOUBLE PRECISION, "
                    + "PRIMARY KEY (MENO, DATUM_OD)"
                    + ")"
            );
        }
    }

    private static void prepareTables() throws SQLException {
        if (emf != null) {
            emf.getCache().evictAll();
        }

        try (Connection con = getCon()) {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM ZAMESTNANEC");
        }
    }

    private static int countRows() throws SQLException {
        try (Connection con = getCon()) {
            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT COUNT(*) FROM ZAMESTNANEC");
            rs.next();
            return rs.getInt(1);
        }
    }

    private static void insertSalary(String meno, LocalDate datum, double plat) throws SQLException {
        if (emf != null) {
            emf.getCache().evictAll();
        }

        try (Connection con = getCon()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ZAMESTNANEC (MENO, DATUM_OD, PLAT) VALUES (?, ?, ?)"
            );
            ps.setString(1, meno);
            ps.setDate(2, java.sql.Date.valueOf(datum));
            ps.setDouble(3, plat);
            ps.executeUpdate();
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        BODY = 0;
        createTables();
        emf = Persistence.createEntityManagerFactory("vsaPU");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println();
        System.out.println("BODY = " + BODY);
        System.out.println();

        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void UT01_updateSalaryVytvoriZaznam_3b() throws Exception {
        prepareTables();

        EntityManager em = emf.createEntityManager();
        Platy.updateSalary(em, "Fero", 1000.0);
        em.clear();
        em.close();

        assertEquals(1, countRows());
        BODY += 3;
    }

    @Test
    public void UT02_updateSalaryDvakratTenIstyDen_3b() throws Exception {
        prepareTables();

        EntityManager em = emf.createEntityManager();
        Platy.updateSalary(em, "Fero", 1000.0);
        Platy.updateSalary(em, "Fero", 2000.0);
        em.clear();
        em.close();

        assertEquals(1, countRows());
        BODY += 3;
    }

    @Test
    public void UT03_salaryOnPresnyDatum_3b() throws Exception {
        prepareTables();

        LocalDate d = LocalDate.of(2022, 5, 10);
        insertSalary("Jano", d, 1500.0);

        EntityManager em = emf.createEntityManager();
        double plat = Platy.salaryOn(em, "Jano", d);
        em.clear();
        em.close();

        assertEquals(1500.0, plat, 0.001);
        BODY += 3;
    }

    @Test
    public void UT04_salaryOnNajblizsiStarsiDatum_4b() throws Exception {
        prepareTables();

        insertSalary("Jano", LocalDate.of(2022, 1, 1), 1000.0);
        insertSalary("Jano", LocalDate.of(2022, 3, 1), 1300.0);
        insertSalary("Jano", LocalDate.of(2022, 6, 1), 1600.0);

        EntityManager em = emf.createEntityManager();
        double plat = Platy.salaryOn(em, "Jano", LocalDate.of(2022, 4, 10));
        em.clear();
        em.close();

        assertEquals(1300.0, plat, 0.001);
        BODY += 4;
    }

    @Test
    public void UT05_salaryOnIgnorujeInehoZamestnanca_3b() throws Exception {
        prepareTables();

        insertSalary("Jano", LocalDate.of(2022, 1, 1), 1000.0);
        insertSalary("Fero", LocalDate.of(2022, 1, 1), 5000.0);

        EntityManager em = emf.createEntityManager();
        double plat = Platy.salaryOn(em, "Jano", LocalDate.of(2022, 2, 1));
        em.clear();
        em.close();

        assertEquals(1000.0, plat, 0.001);
        BODY += 3;
    }

    @Test
    public void UT06_salaryOnNeexistuje_2b() throws Exception {
        prepareTables();

        EntityManager em = emf.createEntityManager();

        try {
            Platy.salaryOn(em, "Nikto", LocalDate.of(2022, 1, 1));
            fail("Neprisla vynimka");
        } catch (Exception e) {
            BODY += 2;
        } finally {
            em.clear();
            em.close();
        }
    }
}