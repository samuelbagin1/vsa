/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientTest;


import jakarta.ws.rs.core.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RestClientTest {

    private static int n1 = 1;
    private static int n2 = 2;
    private static int n3 = 3;

    private static int BODY = 0;

    private static HttpServer server;
    private static RestClient client; 

    public RestClientTest() {
    }

    @BeforeAll
    public static void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        client = new RestClient();
        BODY=0;
    }

    @AfterAll
    public static void tearDown() throws Exception {
        System.out.println("\nUspesne UnitTesty = " + BODY);
        System.out.println("\nBODY = " + ((BODY<10)?BODY:2*BODY-10));
        System.out.println("");
        server.stop();
    }

////////////////////////////////////////////////////////////////////////////////
    @Test
    public void UT11_Get_PrazdnySlovnik_1B() {
        int r = -1;
        try {
            r = client.getPocet(Integer.class);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(0, r);
        BODY++;
    }

    @Test
    public void UT12_Put_SkOne_1B() {
        String r = "";
        try {
            client.setPreklad("jeden", "one", "sk");
            r = client.getPocet(String.class);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertTrue(r.contains("1"));
        BODY++;
    }

    @Test
    public void UT13_Get_SkOne_1B() {
        String r = null;
        try {
            r = client.getPreklad("one", "sk");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r, "Ziadna odpoved");
        assertTrue(r.contains("jeden"), "Nespravna odpoved " + r);
        BODY++;
    }

    @Test
    public void UT14_Get_OneXXX_1B() {
        String r = null;
        try {
            r = client.getPreklad("one", "XXX");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r, "Ziadna odpoved");
        assertTrue(r.toLowerCase().contains("exist"), "Nespravna odpoved" + r);
        BODY++;
    }

    @Test
    public void UT15_Get_XXXsk_1B() {
        String r = null;
        try {
            r = client.getPreklad("XXX", "sk");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r, "Ziadna odpoved");
        assertTrue(r.toLowerCase().contains("neznam"), "Nespravna odpoved" + r);
        BODY++;
    }

    @Test
    public void UT16_Put_OneDe_1B() {
        String r = "";
        try {
            client.setPreklad("eins", "one", "de");
            // pocet jazykov sa nezmeni
            r = client.getPocet(String.class);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertTrue(r.contains("1"));
        BODY++;

    }

    @Test
    public void UT17_Jazyky_One_1B() {
        String r = client.getZoznam("one",String.class);
        assertNotNull(r, "Ziadna odpoved");
        assertTrue(r.contains("sk"), "Neobsahuje sk" + r);
        assertTrue(r.contains("de"), "Neobsahuje de" + r);
        BODY++;
    }

    @Test
    public void UT18_Get_OneDe_1B() {
       String r = null;
        try {
            r = client.getPreklad("one", "de");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r, "Ziadna odpoved");
         assertTrue(r.contains("eins"), "Nespravna odpoved" + r);
        BODY++;
    }
    
    // moze sa vypustit
    @Test
    public void UT19_Get_OneSk_1B() {
       String r = null;
        try {
            r = client.getPreklad("one", "sk");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r, "Ziadna odpoved");
         assertTrue(r.contains("jeden"), "Nespravna odpoved" + r);
        BODY++;
    }
    
    @Test
    public void UT20_Put_TwoDe_1B() {
       String r = null;
       String r2 = null;
        try {
            client.setPreklad("zwei", "two", "de");
            r = client.getPreklad("one","de");
            r2 = client.getPreklad("two","de");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r, "Ziadna odpoved");
        assertTrue(r.contains("eins"), "Nespravna odpoved" + r);
        assertNotNull(r2, "Ziadna odpoved");
        assertTrue(r2.contains("zwei"), "Nespravna odpoved" + r2);
        BODY++;
    }
    
    // TODO pocet slov
    // TODO jazyky pre two

    @Test
    public void UT21_Put_OneSk_1B() {
       String r = null;
        try {
            client.setPreklad("raz", "one", "sk");
            r = client.getPreklad("one", "sk");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r, "Ziadna odpoved");
        assertTrue(r.contains("raz"), "Nespravna odpoved" + r);
        BODY++;
    }
    // TODO pocet slov sa nezmenil 2

    @Test
    public void UT22_Del_OneDe_1B() {
       String r = null;
       String r2 = null;
        try {
            client.delPreklad("one", "de");
            r = client.getPreklad("one","de");
            r2 = client.getPreklad("two", "de");
            //TODO  kontrola Get one sk
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r, "Ziadna odpoved");
        assertTrue(r.toLowerCase().contains("exist"), "Nespravna odpoved" + r);
        assertNotNull(r2, "Ziadna odpoved");
        assertTrue(r2.contains("zwei"), "Nespravna odpoved" + r2);
        BODY++;
    }
    // TODO jazyky pre one
    
    @Test
    public void UT23_Del_One_1B() {
       String r = null;
       String r2 = null;
        try {
            client.delSlovo("one");
            r = client.getPreklad("one","sk");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(r, "Ziadna odpoved");
        assertTrue(r.toLowerCase().contains("neznam"), "Nespravna odpoved" + r);
        BODY++;
    }
    
    // jazyky pre one: "Nezname slovo" 
    @Test
    public void UT24_Zoznam_One_1B() {
        Response r = client.getZoznam("one",Response.class);
        assertNotNull(r, "Ziadna odpoved");
        assertEquals(404, r.getStatus());
        BODY++;
    }

    // TODO pocet slov: 1;
    @Test
    public void UT25_Get_Pocet_1B() {
        String r = "";
        try {
            r = client.getPocet(String.class);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertTrue(r.contains("1"));
        BODY++;
    }
    
}
