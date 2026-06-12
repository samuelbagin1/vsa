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
    }

////////////////////////////////////////////////////////////////////////////////
    static final String IDZMLUVY = "Z123";
    static final String MAJITEL = "Mrkvicka";
    static final String ZLYARG = "neplatna";

    private static Zmluva z123;
    static private String resp;
    static private String iPako;

    @Test
    public void UT11_GetPoInite_1B() {
        z123 = null;
        try {
            z123 = client.getXml(Zmluva.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadna zmluva po inite", z123);
    }

    @Test
    public void UT12_IdZmluvyPoInite_1B() {
        assertNotNull("Ziadna zmluva po inite", z123);
        assertEquals(IDZMLUVY, z123.getId());
        assertEquals(MAJITEL, z123.getMajitel());
    }

    @Test
    public void UT13_PocetPoInite_1B() {
        resp = null;
        try {
            resp = client.getPocet(IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("0", resp);
    }

    @Test
    public void UT14_GetXXXPoInite_1B() {
        Zmluva d = null;
        try {
            d = client.getXml(Zmluva.class, "XXX");
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
        assertNull(d);
    }

    @Test
    public void UT15_PocetXXXPoInite_1B() {
        resp = null;
        try {
            resp = client.getPocet("XXX");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(resp);
        assertTrue("Chybna odpoved: " + resp, resp.toLowerCase().contains(ZLYARG));
    }

    @Test
    public void UT21_PostPakoPoInite_1B() {
        iPako = null;
        try {
            iPako = client.postOsoba("Pako", IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(iPako);
        assertEquals("0", iPako);
    }

    @Test
    public void UT22_PostDalsiPoInite_1B() {
        resp = null;
        try {
            resp = client.postOsoba("Pako2", IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNotEquals("",resp, iPako);
    }

    @Test
    public void UT23_PocetPoPost_1B() {
        resp = null;
        try {
            resp = client.getPocet(IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        
        assertEquals("2", resp);
    }

    @Test
    public void UT24_OpakovanyPostPoInite_1B() {
        resp = null;
        try {
            resp = client.postOsoba("Pako", IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNotNull(resp);
        assertEquals(iPako, resp);
    }

    @Test
    public void UT25_GetPoPost_1B() {
        z123 = null;
        try {
            z123 = client.getXml(Zmluva.class, IDZMLUVY);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNotNull(z123);
        assertNotNull(z123.getPoistenec());
        assertEquals(2,z123.getPoistenec().size());
    }

    @Test
    public void UT26_PostXXX_1B() {
        resp = null;
        try {
            resp = client.postOsoba("Pako", "XXX");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNotNull(resp);
        assertTrue("Chybna odpoved: " + resp, resp.toLowerCase().contains(ZLYARG));
    }

    static private Zmluva pp1;

    @Test
    public void UT31_PostPP1_1B() {
        Zmluva d = new Zmluva();
        d.setId("PP1");
        d.setMajitel("MP1");
        d.getPoistenec().add("Stano1");
        d.getPoistenec().add("Stano2");
        d.getPoistenec().add("Stano3");

        try {
            client.postXml(d);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        pp1 = null;
        try {
            pp1 = client.getXml(Zmluva.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadne PP1", pp1);
    }

    @Test
    public void UT32_PP1Majitel_1B() {
        assertNotNull("Ziadne PP1", pp1);
        assertEquals("MP1", pp1.getMajitel());
    }

    @Test
    public void UT33_PP1Size_1B() {
        assertNotNull("Ziadne PP1", pp1);
        assertNotNull("Ziadne PP1", pp1.getPoistenec());
        assertEquals(3, pp1.getPoistenec().size());
    }

    // mohlo by sa vynechat
    @Test
    public void UT34_PP1Pocet_1B() {
        resp = null;
        try {
            resp = client.getPocet("PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals("3", resp);
    }

    @Test
    public void UT41_PP1PostStano_1B() {
        resp = null;
        try {
            resp = client.postOsoba("Stano1", "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(resp);

        pp1 = null;
        try {
            pp1 = client.getXml(Zmluva.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pp1);
        assertEquals(3L, pp1.getPoistenec().size());
    }

    @Test
    public void UT51_PP1PostPako_1B() {
        iPako = null;
        try {
            iPako = client.postOsoba("Pako", "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertNotNull(iPako);
        assertTrue(iPako.startsWith("3") || iPako.startsWith("4"));
    }
    
    @Test
    public void UT52_PP1GetPako_1B() {
        resp = null;
        try {
            resp = client.getOsoba("PP1", iPako);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertEquals(resp, "Pako");    
    }
    
    @Test
    public void UT53_PP1DeletePako_1B() {
        pp1 = null;
        try {
            pp1 = client.getXml(Zmluva.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pp1);
        assertEquals(4L, pp1.getPoistenec().size());

        try {
            client.deleteOsoba("PP1",iPako);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        try {
            pp1= client.getXml(Zmluva.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(pp1);
        assertEquals(3L, pp1.getPoistenec().size());
        assertTrue(!pp1.getPoistenec().contains("Pako"));
        assertTrue(pp1.getPoistenec().contains("Stano1"));
        assertTrue(pp1.getPoistenec().contains("Stano2"));
        assertTrue(pp1.getPoistenec().contains("Stano3"));
    }
    
    @Test
    public void UT54_PP1Get99_1B() {
        resp = null;
        try {
            resp = client.getOsoba("PP1", "99");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertTrue("Chybna odpoved: " + resp, resp.toLowerCase().contains(ZLYARG));
    }
}
