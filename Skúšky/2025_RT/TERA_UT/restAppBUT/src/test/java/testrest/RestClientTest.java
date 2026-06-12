/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RestClientTest {
    private static int BODY;
    static final String IDZMLUVY = "Z123";
    static final String MAJITEL = "Hrasko";         //Mrkva
    static final String ADRESA = "Hlavna ";
    static final String RC = "2001";
    static double SUMA = 199.0;

    private static HttpServer server;
    private static RestClient client; 


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
        System.out.println();
        System.out.println("BODY = " + BODY);
        System.out.println();
        server.stop();
    }

    public RestClientTest() {
    }

////////////////////////////////////////////////////////////////////////////////
    private static Poistenie z123;
    private static Osoba po;
    static private String resp;
    static private String iPako;

    @Test
    public void UT11_Z123Get_1B() {
        z123 = null;
        try {
            z123 = client.getZmluva(Poistenie.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(z123,"Ziadna zmluva po inite");
        BODY++;
    }

    @Test
    public void UT12_Z123Pocet0_1B() {
        assertNotNull(z123,"Ziadna zmluva po inite");
        assertEquals(IDZMLUVY, z123.getIdZmluvy());
        assertEquals(SUMA, z123.getPoistnaSuma());
        assertEquals(0, z123.getPocetPoistencov());
        BODY++;
    }
    
    @Test
    public void UT13_Z123Majitel_2B() {
        assertNotNull(z123, "Ziadna zmluva po inite");
        assertNotNull(z123.getMajitel(), "Ziadny majitel po inite");
//        BODY++;
        assertEquals(MAJITEL, z123.getMajitel().getMeno());
        assertEquals(RC, z123.getMajitel().getRc());
//        assertNotNull(z123.getMajitel().getBydlisko(), "Ziadna adresa po inite" );
//        assertTrue(z123.getMajitel().getBydlisko().startsWith(ADRESA), "Nespravna adresa po inite");
        BODY++;
    }


    @Test
    public void UT14_Z123Post_1B() {
        iPako = null;
        try {
            iPako = client.postOsoba(new Osoba("Pako","9999","xxxx"), IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(iPako);
        assertEquals("1", iPako);
        BODY++;
    }

    @Test
    public void UT15_Z123Pocet1_1B() {
        z123 = null;
        try {
            z123 = client.getZmluva(Poistenie.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(z123, "Ziadna zmluva");
        assertEquals(1, z123.getPocetPoistencov());
        BODY++;
    }

    // test ci zoznam poistenych osob je xmltransient
    @Test
    public void UT16_Z123String_1B() {
        String zs = null;
        try {
            zs = client.getZmluva(String.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertFalse(zs.contains("soba"), "XML obsahuje neocakavane elementy: " + zs);
        assertFalse(zs.contains("Pako"), "XML obsahuje neocakavane data: " + zs);
        assertFalse(zs.contains("9999"), "XML obsahuje neocakavane data: " + zs);
        assertFalse(zs.contains("xxxx"), "XML obsahuje neocakavane data: " + zs);
        BODY++;
    }


// nove poistenie
    static private Poistenie pp1;

    static final String IDPP1 = "PP1";
    static final String MP1 = "Porik";

    @Test
    public void UT21_PostPP1_1B() {
        Poistenie d = new Poistenie();
        d.setIdZmluvy(IDPP1);
        d.setPocetPoistencov(2);
        d.setMajitel(new Osoba("Porik","1212","Polna"));

        try {
            client.postZmluva(d);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        pp1 = null;
        try {
            pp1 = client.getZmluva(Poistenie.class, IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pp1, "Ziadne PP1");
        BODY++;
    }
    
    @Test
    public void UT22_PP1pocet0_1B() {
        assertNotNull(pp1, "Ziadne PP1");
        assertEquals(0, pp1.getPocetPoistencov());
        BODY++;
    }

    @Test
    public void UT23_PP1Majitel_2B() {
        assertNotNull(pp1, "Ziadne PP1");
        assertNotNull(pp1.getMajitel(), "Ziadny majitel");
//        BODY++;
        assertEquals(pp1.getMajitel().getMeno(), "Porik");
        assertEquals(pp1.getMajitel().getRc(), "1212");
        assertEquals(pp1.getMajitel().getBydlisko(), "Polna");
        BODY++;
    }

    // POST 1. osoby do noveho poistenia
    @Test
    public void UT24_PP1PostStano1_1B() {
        resp = null;
        try {
            resp = client.postOsoba(new Osoba("Stano1","9999","xxxx"), IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("1",resp);
        BODY++;
    }

    @Test
    public void UT25_PP1pocet1_1B() {
        pp1 = null;
        try {
            pp1 = client.getZmluva(Poistenie.class, IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pp1, "Ziadne PP1");
        assertEquals(1, pp1.getPocetPoistencov());
        BODY++;
    }
    
    // GET pridanej osoby
    @Test
    public void UT26_PP1GetStano1_1B() {
        po = null;
        try {
            po = client.getOsoba(Osoba.class, IDPP1, resp);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNotNull(po, "Ziadna Osoba");
        BODY++;
    }
    
    @Test
    public void UT27_PP1GetStano1_1B() {
        assertNotNull(po, "Ziadna Osoba");
        assertEquals(po.getMeno(), "Stano1");    
        assertEquals(po.getRc(), "9999");    
        assertEquals(po.getBydlisko(), "xxxx");    
        BODY++;
    }
    
    @Test
    public void UT28_PP1PostStano2_1B() {
        resp = null;
        try {
            resp = client.postOsoba(new Osoba("Stano2","222","xxx"), IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("2",resp);
        BODY++;
    }

    @Test
    public void UT29_PP1GetStano2_1B() {
        po = null;
        try {
            po = client.getOsoba(Osoba.class, IDPP1, resp);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNotNull(po, "Ziadna Osoba");
        assertEquals(po.getMeno(), "Stano2");    
        BODY++;
    }


    // DELETE
    private static int last=0;
            
    @Test
    public void UT41_PP1DeletePosledne_1B() {
        pp1 = null;
        try {
            pp1 = client.getZmluva(Poistenie.class, IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pp1);
        last = pp1.getPocetPoistencov();
        assertTrue(last>0, "ziadne osoby");

        try {
            client.deleteOsoba(IDPP1,""+last);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        try {
            pp1= client.getZmluva(Poistenie.class, IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(last-1,pp1.getPocetPoistencov());        
        BODY++;
        
    }

    @Test
    public void UT42_PP1DeletePosledne_1B() {
        po = null;
        assertTrue(last>0, "ziadne osoby");

        try {
            po = client.getOsoba(Osoba.class, IDPP1, ""+last);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNull(po, "Osoba nebola vymazana");

        String zs = null;
        try {
            zs = client.getZmluva(String.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertFalse(zs.contains("soba"), "XML obsahuje neocakavane elementy: " + zs);
        assertFalse(zs.contains("Stano"), "XML obsahuje neocakavane data: " + zs);
        assertFalse(zs.contains("9999"), "XML obsahuje neocakavane data: " + zs);
        assertFalse(zs.contains("xxxx"), "XML obsahuje neocakavane data: " + zs);
        BODY++;
    }

    @Test
    public void UT43_Z123DeletePosledne_1B() {
        pp1 = null;

        try {
            client.deleteOsoba(IDZMLUVY,"1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
        po = null;
        try {
            po = client.getOsoba(Osoba.class, IDZMLUVY, "1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNull(po, "Osoba nebola vymazana");
        BODY++;
    }
  
    // NEGATIVNE testy
    // GET neexistujucej zmluvy - nesmie spadnut s HTTP 500
    @Test
    public void UT51_GetXXX_1B() {
        Object ret = "chyba";
        try {
            ret = client.getZmluva(Poistenie.class, "XXX");
        } catch (Exception e) {
            //neocakavana chyba");
            if (e.getMessage().contains("HTTP 50")) {
                fail("EXCEPTION " + e.getMessage());
            }
            // mozno vypustit???
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB???: " + e.getMessage());
            }
        }
        assertNull(ret);
        BODY++;
    }

    // POST Osoba do neexistujucej zmluvy - nesmie spadnut
    @Test
    public void UT52_PostXXX_1B() {
        String resp = null;
        try {
            resp = client.postOsoba(new Osoba("Stano2","9999","xxxx"), "XXX");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertTrue((resp==null) || resp.isEmpty());
        BODY++;
    }
    
    // GET osoba mimo rozsahu - nesmie spadnut
    @Test
    public void UT53_PP1Get99_1B() {
        Object ret = "chyba";
        try {
            ret = client.getOsoba(Osoba.class, IDPP1, "99");
        } catch (Exception e) {
            //neocakavana chyba");
            if (e.getMessage().contains("HTTP 50")) {
                fail("EXCEPTION " + e.getMessage());
            }
            // mozno vypustit???
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB???: " + e.getMessage());
            }
        }
        assertNull(ret);
        BODY++;
    }
    
}