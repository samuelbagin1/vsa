/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbapp;

import dbapp.Application.*;
import dbapp.Kniha.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author edu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTest {

    private static int BODY;

    public ApplicationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        BODY = 0;
//        prepareTables();
    }

    @AfterClass
    public static void vysledok() {
        System.out.println("BODY = " + BODY);
    }

    @Test
    // vytvorenie tabulky a jedneho zaznamu
    // kontrola navratovej hodnoty 
    public void test_01() {
        dropTable();

        Long r1 = Application.novaKniha("P0");
        assertNotNull("null returned", r1);
        BODY += 1;
    }

    @Test
    // kontrola struktury vytvorenej tabulky: nazov=P0 a dostupnost=NULL
    // mozno spojit s test_01 prip. vynechat
    public void test_02() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM KNIHA");
            assertTrue("empty result set", rs.next());
            assertEquals("P0", rs.getString("TITUL"));
            assertNull(rs.getObject("DOSTUPNOST"));
            assertFalse("result set to big", rs.next());
        } catch (SQLException ex) {
            fail("SQLException: " + ex.getMessage());
        }
        BODY += 1;
    }

    @Test
    // kontrola struktury vytvorenej tabulky: cena=NULL
    public void test_03() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM KNIHA");
            assertTrue("empty result set", rs.next());
            assertNull(rs.getObject("CENA"));
            assertFalse("result set to big", rs.next());
        } catch (SQLException ex) {
            fail("SQLException: " + ex.getMessage());
        }
        BODY += 1;
    }

    @Test
    // dostupnost varchar
    public void test_11() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement s = con.createStatement();
            s.executeUpdate("INSERT INTO KNIHA (ID, TITUL, DOSTUPNOST, CENA) VALUES (1111, 'XX', 'ZAJTRA', 3.5)");
        } catch (SQLException ex) {
            if (ex.getMessage().contains("not-null") == false) {
                fail("SQLException: " + ex.getMessage());
            }
        }
        BODY += 1;
    }

    @Test
    // nazov not-null 
    public void test_12() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement s = con.createStatement();
            s.executeUpdate("INSERT INTO KNIHA (ID, TITUL, DOSTUPNOST, CENA) VALUES (1112, NULL, NULL, 3.5)");
        } catch (SQLException ex) {
            if (ex.getMessage().contains("not-null")) {
                BODY += 1;
                return;
            } else {
                fail("SQLException: " + ex.getMessage());
            }
        }
        fail("TITUL nie je not-null");
    }

    @Test
    // nazov unique 
    public void test_13() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement s = con.createStatement();
            s.executeUpdate("INSERT INTO KNIHA (ID, TITUL, DOSTUPNOST, CENA) VALUES (1113, 'P0', NULL, 3.5)");
        } catch (SQLException ex) {
            if (ex.getMessage().contains("unique")) {
                BODY += 1;
                return;
            } else {
                fail("SQLException: " + ex.getMessage());
            }
        }
        fail("TITUL nie je unique");
    }

    @Test
    public void test_21() {
        Long r = 0L;
        try {
            r = Application.novaKniha("P0");
        } catch (Exception ex) {
            fail("Exception: " + ex.getMessage());
        }
        assertNull(r);

        BODY += 1;
    }

    @Test
    public void test_22() {
        dropTable();
        createTable();
        Dostupnost d = null;
        try {
            d = Application.zistiDostupnost("P2");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Neocakavana vynimka: " + ex.getMessage());
        }
        assertEquals(Dostupnost.IHNED, d);
        BODY++;
    }

    @Test
    public void test_23() {
        dropTable();
        createTable();
        Dostupnost d = null;
        try {
            d = Application.zistiDostupnost("XY");
        } catch (Exception ex) {
            if (ex.getMessage().toLowerCase().startsWith("neznam")) {
                BODY++;
                return;
            }
            ex.printStackTrace();
            fail("Neocakavana vynimka: " + ex.getMessage());
        }
        fail("Neprisla ocakavana vynimka");
    }

    @Test
    public void test_24() {
        dropTable();
        createTable();
        Dostupnost d = null;
        try {
            Application.upravCeny();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Neocakavana vynimka: " + ex.getMessage());
        }

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM KNIHA ORDER BY TITUL");
            assertTrue("empty result set", rs.next());
            assertNull(rs.getObject("CENA"));
            assertTrue("empty result set", rs.next());
            assertEquals(16.0, rs.getDouble("CENA"), 0.001);
            assertTrue("empty result set", rs.next());
            assertEquals(24.0, rs.getDouble("CENA"), 0.001);
            assertFalse("result set to big", rs.next());
        } catch (SQLException ex) {
            fail("SQLException: " + ex.getMessage());
        }
        BODY++;
    }

////////////////////////////////////////////////////////////////////////////////    
    static private void dropTable() {

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {

            Statement st = con.createStatement();
            try {
                st.executeUpdate("DROP TABLE KNIHA");
            } catch (Exception ex) {
            }

            st.executeUpdate("UPDATE SEQUENCE SET SEQ_COUNT = 1 WHERE SEQ_NAME = 'SEQ_GEN'");
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationTest.class.getName()).log(Level.INFO, ex.getMessage());
        }
    }

    static private void createTable() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE KNIHA "
                    + "(ID BIGINT NOT NULL, TITUL VARCHAR(255) UNIQUE NOT NULL, "
                    + "DOSTUPNOST VARCHAR(255), CENA float8, PRIMARY KEY (ID)) ");
            st.executeUpdate("INSERT INTO KNIHA (ID, TITUL, DOSTUPNOST, CENA) "
                    + "VALUES (111, 'P1', 'DO5DNI', NULL)");
            st.executeUpdate("INSERT INTO KNIHA (ID, TITUL, DOSTUPNOST, CENA) "
                    + "VALUES (222, 'P2', 'IHNED', 20.0)");
            st.executeUpdate("INSERT INTO KNIHA (ID, TITUL, DOSTUPNOST, CENA) "
                    + "VALUES (333, 'P3', 'NASKLADE', 30.0)");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
