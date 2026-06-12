package vsa;

import java.sql.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.*;
import static org.junit.Assert.*;

public class OrmTest {

    private static EntityManagerFactory emf;
    private static int BODY = 0;

    private static Connection getCon() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu"
        );
    }

    private boolean tableExists(String table) throws SQLException {
        Connection con = getCon();
        ResultSet rs = con.getMetaData().getTables(null, null, table.toLowerCase(), null);
        boolean ok = rs.next();
        con.close();
        return ok;
    }

    private boolean columnExists(String table, String column) throws SQLException {
        Connection con = getCon();
        ResultSet rs = con.getMetaData().getColumns(null, null, table.toLowerCase(), column.toLowerCase());
        boolean ok = rs.next();
        con.close();
        return ok;
    }

    private boolean columnNotNullable(String table, String column) throws SQLException {
        Connection con = getCon();
        ResultSet rs = con.getMetaData().getColumns(null, null, table.toLowerCase(), column.toLowerCase());
        boolean ok = false;
        if (rs.next()) {
            ok = rs.getInt("NULLABLE") == DatabaseMetaData.columnNoNulls;
        }
        con.close();
        return ok;
    }

    @BeforeClass
    public static void setUpClass() {
        BODY = 0;
        emf = Persistence.createEntityManagerFactory("vsa_zadanie2t_dbanot_jar_1.0-SNAPSHOTPU");
        assertNotNull(emf);
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
    public void UT01_tabulkyExistuju_3b() throws Exception {
        assertTrue(tableExists("osoba"));
        assertTrue(tableExists("profesor"));
        assertTrue(tableExists("student"));
        assertTrue(tableExists("predmet"));
        assertTrue(tableExists("student_predmet"));
        assertTrue(tableExists("predmet_literatura"));
        BODY += 3;
    }

    @Test
    public void UT02_predmetStlpce_2b() throws Exception {
        assertTrue(columnExists("predmet", "id"));
        assertTrue(columnExists("predmet", "nazov"));
        assertTrue(columnExists("predmet", "prednasajuci_id"));
        BODY += 2;
    }

    @Test
    public void UT03_osobaProfesorStudentStlpce_2b() throws Exception {
        assertTrue(columnExists("osoba", "id"));
        assertTrue(columnExists("osoba", "meno"));

        assertTrue(columnExists("profesor", "id"));
        assertTrue(columnExists("profesor", "ustav"));

        assertTrue(columnExists("student", "id"));
        assertTrue(columnExists("student", "rocnik"));

        BODY += 2;
    }

    @Test
    public void UT04_joinTabulkyStlpce_2b() throws Exception {
        assertTrue(columnExists("student_predmet", "student_fk"));
        assertTrue(columnExists("student_predmet", "predmet_fk"));

        assertTrue(columnExists("predmet_literatura", "predmet_id"));
        assertTrue(columnExists("predmet_literatura", "literatura"));

        BODY += 2;
    }

    @Test
    public void UT05_rocnikNotNull_1b() throws Exception {
        assertTrue(columnNotNullable("student", "rocnik"));
        BODY++;
    }

    @Test
    public void UT06_persistenceNabehne_1b() {
        assertNotNull(emf);
        BODY++;
    }
}