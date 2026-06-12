package testrest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RestClientTest {
    private static int BODY;

    static final String VSA = "VSA";
    static final String DEN_VSA = "utorok";
    static final String PREDMET_DB = "DBS";
    static final String DEN_DB = "streda";
    static final String STUDENT_1 = "Jozef Mrkvicka";
    static final String STUDENT_2 = "Janko Hrasko";

    private static HttpServer server;
    private static RestClient client;
    private static Skuska vsa;
    private static Skuska dbs;
    private static String resp;

    @BeforeAll
    public static void setUp() throws Exception {
        server = Main.startServer();
        client = new RestClient();
        BODY = 0;
    }

    @AfterAll
    public static void tearDown() throws Exception {
        System.out.println();
        System.out.println("BODY = " + BODY);
        System.out.println();
        client.close();
        server.stop();
    }

    @Test
    public void UT11_InitVSAGetXml_1B() {
        vsa = null;
        try {
            vsa = client.getSkuska(Skuska.class, VSA);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(vsa, "Po inite neexistuje skuska VSA");
        BODY++;
    }

    @Test
    public void UT12_InitVSAObsah_2B() {
        assertNotNull(vsa, "Po inite neexistuje skuska VSA");
        assertEquals(VSA, vsa.getPredmet());
        assertEquals(DEN_VSA, vsa.getDen());
        assertNotNull(vsa.getStudent(), "Zoznam studentov nesmie byt null");
        assertEquals(0, vsa.getStudent().size(), "Po inite nema byt prihlaseny ziaden student");
        BODY += 2;
    }

    @Test
    public void UT13_InitVSAPocetText_1B() {
        resp = null;
        try {
            resp = client.getPocetStudentov(VSA);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("0", resp.trim());
        BODY++;
    }

    @Test
    public void UT14_GetPredmetyObsahujeVSA_1B() {
        resp = null;
        try {
            resp = client.getPredmety();
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(resp);
        assertTrue(resp.contains(VSA), "Zoznam predmetov neobsahuje VSA: " + resp);
        BODY++;
    }

    @Test
    public void UT21_PostDBS_1B() {
        Skuska s = new Skuska(PREDMET_DB, DEN_DB);
        try {
            resp = client.postSkuska(s);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(PREDMET_DB, resp.trim());
        BODY++;
    }

    @Test
    public void UT22_GetDBSXml_2B() {
        dbs = null;
        try {
            dbs = client.getSkuska(Skuska.class, PREDMET_DB);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(dbs, "DBS nebola vytvorena");
        assertEquals(PREDMET_DB, dbs.getPredmet());
        assertEquals(DEN_DB, dbs.getDen());
        assertEquals(0, dbs.getStudent().size());
        BODY += 2;
    }

    @Test
    public void UT23_PostDuplicitaSkuska_1B() {
        Skuska s = new Skuska(PREDMET_DB, "piatok");
        try {
            resp = client.postSkuska(s);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("duplicita", resp.trim());
        BODY++;
    }

    @Test
    public void UT24_DuplicitaNezmeniDen_1B() {
        dbs = null;
        try {
            dbs = client.getSkuska(Skuska.class, PREDMET_DB);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(dbs, "DBS neexistuje");
        assertEquals(DEN_DB, dbs.getDen(), "Duplicitny POST nesmie zmenit povodnu skusku");
        BODY++;
    }

    @Test
    public void UT31_PostStudent1_1B() {
        try {
            resp = client.postStudent(PREDMET_DB, STUDENT_1);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(DEN_DB, resp.trim());
        BODY++;
    }

    @Test
    public void UT32_PocetStudentov1_1B() {
        try {
            resp = client.getPocetStudentov(PREDMET_DB);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("1", resp.trim());
        BODY++;
    }

    @Test
    public void UT33_GetXmlObsahujeStudenta_1B() {
        dbs = null;
        try {
            dbs = client.getSkuska(Skuska.class, PREDMET_DB);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(dbs, "DBS neexistuje");
        assertTrue(dbs.getStudent().contains(STUDENT_1), "XML neobsahuje prihlaseneho studenta");
        BODY++;
    }

    @Test
    public void UT34_PostStudentDuplicita_1B() {
        try {
            resp = client.postStudent(PREDMET_DB, STUDENT_1);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertTrue(resp.contains(DEN_DB), "Odpoved ma obsahovat den skusky: " + resp);
        assertTrue(resp.contains("duplicita"), "Odpoved ma obsahovat duplicita: " + resp);
        BODY++;
    }

    @Test
    public void UT35_DuplicitaNezvysiPocet_1B() {
        try {
            resp = client.getPocetStudentov(PREDMET_DB);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("1", resp.trim());
        BODY++;
    }

    @Test
    public void UT36_PostStudent2_1B() {
        try {
            resp = client.postStudent(PREDMET_DB, STUDENT_2);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(DEN_DB, resp.trim());
        BODY++;
    }

    @Test
    public void UT37_GetPredmetyStudenta_1B() {
        try {
            resp = client.getPredmetyStudenta(STUDENT_1);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(resp);
        assertTrue(resp.contains(PREDMET_DB), "Student nie je najdeny v DBS: " + resp);
        assertFalse(resp.contains(VSA), "Student nema byt prihlaseny na VSA: " + resp);
        BODY++;
    }

    @Test
    public void UT38_GetPredmetyStudentaZiadne_1B() {
        try {
            resp = client.getPredmetyStudenta("Nikto Neexistuje");
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("ziadne predmety", resp.trim());
        BODY++;
    }

    @Test
    public void UT41_GetNeexistujuciPredmetText_1B() {
        Object ret = "chyba";
        try {
            ret = client.getPocetStudentov("XXX");
        } catch (Exception e) {
            if (e.getMessage().contains("HTTP 50")) {
                fail("EXCEPTION " + e.getMessage());
            }
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB???: " + e.getMessage());
            }
            ret = null;
        }
        if (ret != null) {
            assertEquals("0", ret.toString().trim());
        }
        BODY++;
    }

    @Test
    public void UT42_GetNeexistujuciPredmetXml_1B() {
        Object ret = "chyba";
        try {
            ret = client.getSkuska(Skuska.class, "XXX");
        } catch (Exception e) {
            if (e.getMessage().contains("HTTP 50")) {
                fail("EXCEPTION " + e.getMessage());
            }
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB???: " + e.getMessage());
            }
            ret = null;
        }
        assertNull(ret);
        BODY++;
    }

    @Test
    public void UT43_PostStudentNeexistujuciPredmet_1B() {
        try {
            resp = client.postStudent("XXX", STUDENT_1);
        } catch (Exception e) {
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("predmet neexistuje", resp.trim());
        BODY++;
    }
}
