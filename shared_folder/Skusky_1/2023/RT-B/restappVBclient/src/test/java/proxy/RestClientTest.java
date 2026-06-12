/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestClientTest {

    static final String IDZMLUVY = "Z123";
    static final String MAJITEL = "Mrkva";        
    static final String ADRESA = "Hlavna ";
    static final String RC = "2001";

    public RestClientTest() {
    }

    static private RestClient client;


    private static int BODY;

    @BeforeClass
    static public void setUp() {
        BODY = 0;

//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
//        context.setContextPath("/RestClientTest/resources");
//
//        jettyServer = new Server(9999);
//        jettyServer.setHandler(context);
//
//        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
//        jerseyServlet.setInitOrder(0);
//
//        // Tells the Jersey Servlet which REST service/class to load.
//        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "rest");
//
//        try {
//            jettyServer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        client = new RestClient();
    }

    @AfterClass
    static public void tearDown() {
        System.out.println();
        System.out.println("BODY = " + BODY);
        System.out.println();

    }

////////////////////////////////////////////////////////////////////////////////
    private static Zmluva z123;
    private static Osoba po;
    static private String resp;
    static private String iPako;

    @Test
    public void UT11_Z123Get_1B() {
        z123 = null;
        try {
            z123 = client.getZmluva(Zmluva.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadna zmluva po inite", z123);
        BODY++;
    }

    @Test
    public void UT12_Z123Pocet0_1B() {
        assertNotNull("Ziadna zmluva po inite", z123);
        System.out.println(""+z123);
        System.out.println(""+z123.getIdZmluvy());
        System.out.println(""+z123.getPocet());
        System.out.println(""+z123.getVlastnik().getMeno());
        assertEquals(IDZMLUVY, z123.getIdZmluvy());
        assertEquals(0, z123.getPocet());
        BODY++;
    }
    
    @Test
    public void UT13_Z123Majitel_2B() {
        assertNotNull("Ziadna zmluva po inite", z123);
        assertNotNull("Ziadny majitel po inite", z123.getVlastnik());
        BODY++;
        assertEquals(MAJITEL, z123.getVlastnik().getMeno());
        assertEquals(RC, z123.getVlastnik().getRocnik());
        assertNotNull("Ziadny adresa po inite", z123.getVlastnik().getAdresa());
        assertTrue("Nespravna adresa po inite",z123.getVlastnik().getAdresa().startsWith(ADRESA));
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
            z123 = client.getZmluva(Zmluva.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadna zmluva", z123);
        assertEquals(1, z123.getPocet());
        BODY++;
    }

    @Test
    public void UT16_Z123String_1B() {
        String zs = null;
        try {
            zs = client.getZmluva(String.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertFalse("XML obsahuje neocakavane elementy: " + zs, zs.contains("soba"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("Pako"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("9999"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("xxxx"));
        BODY++;
    }


// nove poistenie
    static private Zmluva pp1;

    static final String IDPP1 = "PP1";
    static final String MP1 = "Porik";

    @Test
    public void UT21_PostPP1_1B() {
        Zmluva d = new Zmluva();
        d.setIdZmluvy(IDPP1);
        d.setPocet(0);
        d.setVlastnik(new Osoba("Porik","1212","Polna"));

        try {
            client.postZmluva(d);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        pp1 = null;
        try {
            pp1 = client.getZmluva(Zmluva.class, IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadne PP1", pp1);
        BODY++;
    }
    
    @Test
    public void UT22_PP1pocet0_1B() {
        assertNotNull("Ziadne PP1", pp1);
        assertEquals(0, pp1.getPocet());
        BODY++;
    }

    @Test
    public void UT23_PP1Majitel_2B() {
        assertNotNull("Ziadne PP1", pp1);
        assertNotNull("Ziadny majitel", pp1.getVlastnik());
        BODY++;
        assertEquals("Porik", pp1.getVlastnik().getMeno());
        assertEquals("1212", pp1.getVlastnik().getRocnik());
        assertEquals("Polna", pp1.getVlastnik().getAdresa());
        BODY++;
    }

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
            pp1 = client.getZmluva(Zmluva.class, IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadne PP1", pp1);
        assertEquals(1, pp1.getPocet());
        BODY++;
    }
    
    @Test
    public void UT26_PP1GetStano1_1B() {
        po = null;
        try {
            po = client.getOsoba(Osoba.class, IDPP1, resp);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNotNull("Ziadna Osoba", po);
        BODY++;
    }
    
    @Test
    public void UT27_PP1GetStano1_1B() {
        assertNotNull("Ziadna Osoba", po);
        assertEquals("Stano1", po.getMeno());    
        assertEquals("9999", po.getRocnik());    
        assertEquals("xxxx", po.getAdresa());    
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

        assertNotNull("Ziadna Osoba", po);
        assertEquals("Stano2", po.getMeno());    
        BODY++;
    }

    @Test
    public void UT30_PP1GetStano1_1B() {
        assertNotNull("Ziadna Osoba", po);
        assertEquals("222", po.getRocnik());    
        assertEquals("xxx", po.getAdresa());    
        BODY++;
    }
    
    @Test
    public void UT31_GetZmluvaString_1B() {
        String zs = null;
        try {
            zs = client.getZmluva(String.class, IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertFalse("XML obsahuje neocakavane elementy: " + zs, zs.contains("soba"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("Stano"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("xxxx"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("9999"));
        BODY++;
    }

    // delete
    private static int last=0;
            
    @Test
    public void UT41_PP1DeletePosledne_1B() {
        pp1 = null;
        try {
            pp1 = client.getZmluva(Zmluva.class, IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pp1);
        last = pp1.getPocet();
        assertTrue("ziadne osoby", last>0);

        try {
            client.deleteOsoba(IDPP1,""+last);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        try {
            pp1= client.getZmluva(Zmluva.class, IDPP1);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(last-1,pp1.getPocet());        
        BODY++;
        
    }

    @Test
    public void UT42_PP1DeletePosledne_1B() {
        po = null;
        assertTrue("ziadne osoby", last>0);

        try {
            po = client.getOsoba(Osoba.class, IDPP1, ""+last);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNull("Osoba nebola vymazana", po);

        String zs = null;
        try {
            zs = client.getZmluva(String.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertFalse("XML obsahuje neocakavane elementy: " + zs, zs.contains("soba"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("Stano"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("xxxx"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("9999"));
        BODY++;
    }

    @Test
    public void UT43_Z123DeletePosledne_1B() {
        pp1 = null;
        try {
            pp1 = client.getZmluva(Zmluva.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pp1);
        int last = pp1.getPocet();

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

        assertNull("Osoba nebola vymazana", po);
        BODY++;
    }
  
//    @Test
    public void UT44_GetZ123String_1B() {
        String zs = null;
        try {
            zs = client.getZmluva(String.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertFalse("XML obsahuje neocakavane elementy: " + zs, zs.contains("soba"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("Pako"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("xxxx"));
        assertFalse("XML obsahuje neocakavane data: " + zs, zs.contains("9999"));
        BODY++;
    }

    // negativne testy
    // neexistujuca zmluva
    @Test
    public void UT51_GetXXX_1B() {
        Object ret = "chyba";
        try {
            ret = client.getZmluva(Zmluva.class, "XXX");
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