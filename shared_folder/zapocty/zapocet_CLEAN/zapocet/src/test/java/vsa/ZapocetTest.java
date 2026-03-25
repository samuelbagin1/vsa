/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package vsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author igor
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ZapocetTest {

    public static int BODY = 0;

    public ZapocetTest() {
    }

    @BeforeAll
    public static void setUpClass() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement st = con.createStatement();
            try {
                st.executeUpdate("DROP TABLE KNIHA");
            } catch (SQLException ex) {
//                Logger.getLogger(ZapocetTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            st.executeUpdate("CREATE TABLE KNIHA (ISBN VARCHAR(255) NOT NULL, CENA FLOAT, NAZOV VARCHAR(255), PRIMARY KEY (ISBN))");
        }        
        BODY = 0;
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("");
        System.out.println("BODY = " + BODY);
        System.out.println("");
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    static private void prepareTables() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM KNIHA");
            st.executeUpdate("INSERT INTO KNIHA (ISBN, CENA, NAZOV) VALUES ('K00', 10.0, 'stara' )");
//            con.commit();
//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ZapocetTest.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    static private void prepareTables2() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM KNIHA");
            st.executeUpdate("INSERT INTO KNIHA (ISBN, CENA, NAZOV) VALUES ('K10', 10.0, 'k0' )");
            st.executeUpdate("INSERT INTO KNIHA (ISBN, CENA, NAZOV) VALUES ('K11', 10.0, 'k1' )");
            st.executeUpdate("INSERT INTO KNIHA (ISBN, CENA, NAZOV) VALUES ('K12', 10.0, 'k2' )");
//            con.commit();
//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ZapocetTest.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
    private void knihaCheck(String key, String n, double c) {
        ResultSet rs = null;
        String nazov = null;
        double cena = Double.NaN;
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT NAZOV, CENA FROM KNIHA WHERE ISBN='"+key+"'");
            rs.next();
            nazov = rs.getString("NAZOV");
            cena = rs.getDouble("CENA");
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }
        assertEquals(n, nazov);
        assertEquals(c, cena, 0.001);
        
    }
    private void countCheck(long n) {
        ResultSet rs = null;
        long cnt = -1;
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/edudb", "edu", "edu4edu")) {
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM KNIHA");
            rs.next();
            cnt = rs.getLong(1);
        } catch (SQLException ex) {
            fail("CHYBA SQL: " + ex.getMessage());
        }

        assertEquals(n, cnt);        
    }
    // pridanie novej knihy - pozitivny test
    @Test
    public void UT00_NovaKniha() {
        prepareTables();

        try {
            Zapocet proj = new Zapocet();
            proj.aktualizujKnihu("N01", "nova", 20.0);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        
        countCheck(2);
        BODY++;
    }
    
    // kontrola novej knihy v db 
    @Test
    public void UT01_NovaKnihaCheck() {
        knihaCheck("N01", "nova", 20.0);
        BODY++;
    }
    
    // zmena nazvu aj ceny a nasledna kontrola novych hodnot v db
    @Test
    public void UT02_NovyNazovCena() {
        prepareTables();

        try {
            Zapocet proj = new Zapocet();
            proj.aktualizujKnihu("K00", "nova2", 30.0);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        
        countCheck(1);
        knihaCheck("K00", "nova2", 30.0);
        BODY++;
    }
    
    // len zmena nazvu 
    @Test
    public void UT03_NovyNazov() {
        prepareTables();

        try {
            Zapocet proj = new Zapocet();
            proj.aktualizujKnihu("K00","nova3", null);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        
        countCheck(1);
        knihaCheck("K00", "nova3", 10.0);
        BODY++;
    }

    // len zmena nazvu 
    @Test
    public void UT04_NovaCena() {
        prepareTables();

        try {
            Zapocet proj = new Zapocet();
//            proj.aktualizujKnihu("K00", "", 30.0);
            proj.aktualizujKnihu("K00", null, 33.0);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        
        countCheck(1);
        knihaCheck("K00", "stara", 33.0);
        BODY++;
    }

    // nezadany kluc - mal by vyhodit vinimkus textom chyba
    @Test
    public void UT05_ZiadnyKluc() {
        prepareTables();

        try {
            Zapocet proj = new Zapocet();
//            proj.aktualizujKnihu(null, "OK", null);
            proj.aktualizujKnihu("", "OK", null);
        } catch (Exception e) {
            assertEquals("chyba", e.getMessage());  
        }
        countCheck(1);
        BODY++;
    }
    
    // zmena cien dvoch knih
    @Test
    public void UT06_Cennik() {
        prepareTables2();

        int r = -1;
        try {
            Zapocet proj = new Zapocet();
            Map<String, Double> cennik = new HashMap<>();
            cennik.put("K11", 20.0);
            cennik.put("K12", 30.0);
            r = proj.aktualizujCeny(cennik);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(2, r);  
        BODY++;        
    }
    
    // kontrola zmenenych cien
    @Test
    public void UT07_CennikCheck() {
        countCheck(3);
        knihaCheck("K11", "k1", 20.0);
        knihaCheck("K12", "k2", 30.0);
        BODY++;        
    }
    
    // pokus o zmenu ceny neexistujucej knihy
    @Test
    public void UT08_Cennik() {
        prepareTables2();

        int r = -1;
        try {
            Zapocet proj = new Zapocet();
            Map<String, Double> cennik = new HashMap<>();
            cennik.put("xxx", 40.0);
            cennik.put("K10", 40.0);
            r = proj.aktualizujCeny(cennik);
        } catch (Exception e) {
            fail("CHYBA EX: " + e.getMessage());
        }
        assertEquals(1, r);  
        BODY++;        
    }
    // kontrola zmenenych cien
    @Test
    public void UT09_Cennik2Check() {
        countCheck(3);
        knihaCheck("K10", "k0", 40.0);
        BODY++;        
    }
    
}
