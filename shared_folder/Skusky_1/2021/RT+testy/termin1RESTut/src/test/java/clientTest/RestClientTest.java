/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientTest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestClientTest {

    static final String SKRATKA = "VSA";
    static final String TERMIN = "12-02-2021";
    static final String CHYBA = "zly predmet";

    static private RestClient client;

    public RestClientTest() {
    }

    static private Server jettyServer;

    @BeforeClass
    static public void setUp() {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/RestUnitTest/resources");

        jettyServer = new Server(9999);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "rest");

        try {
            jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        client = new RestClient();
    }

    @AfterClass
    static public void tearDown() {
        try {
            jettyServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

////////////////////////////////////////////////////////////////////////////////
    private static Data d1;

    @Test
    public void UT11_GetPoInite_1B() {
        d1 = null;
        try {
            d1 = client.getXml(Data.class, SKRATKA);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadny predmet po inite", d1);
    }

    @Test
    public void UT12_PredmetPoInite_1B() {
        assertNotNull("Ziadny predmet po inite", d1);
        assertEquals(SKRATKA, d1.getPredmet());
    }

    @Test
    public void UT13_CasPoInite_1B() {
        assertNotNull("Ziadny predmet po inite", d1);
        assertEquals(TERMIN, d1.getTermin());
    }

    @Test
    public void UT14_PocetPoInite_1B() {
        long p = -1L;
        try {
            p = client.getPocet(Long.class, SKRATKA);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(0L, p);
    }

    @Test
    public void UT15_GetXXXPoInite_1B() {
        Data d = null;
        try {
            d = client.getXml(Data.class, "XXX");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNull(d);
    }

    @Test
    public void UT21_PostStudentPoInite_1B() {
        String r = "-";
        try {
            r = client.postStudent("Pako", SKRATKA);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(TERMIN, r);
    }

    @Test
    public void UT22_PocetPoPostStudent_1B() {
        long p = -1L;
        try {
            p = client.getPocet(Long.class, SKRATKA);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(1L, p);
    }

    @Test
    public void UT23_PostOpakovanyPoInite_1B() {
        try {
            client.postStudent("Pako", SKRATKA);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        long p = -1L;
        try {
            p = client.getPocet(Long.class, SKRATKA);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(1L, p);
    }

    @Test
    public void UT24_PostDalsiPoInite_1B() {
        try {
            client.postStudent("Pako2", SKRATKA);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        long p = -1L;
        try {
            p = client.getPocet(Long.class, SKRATKA);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(2L, p);
    }

    @Test
    public void UT25_PostPreNeexistujuciPredmet_1B() {
        String r = "-";
        try {
            r = client.postStudent("Pako", "NEEXISTUJE");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertTrue("Chybna odpoved: " + r, r.toLowerCase().contains(CHYBA));
    }

    // PostXML preveri ci predmet uz nenexistuje
    // 2.1   PostXML(PP1) streda, Stano1, Stano2, Stano3
    //      check vrati PP1
    //      getxml(PP1)
    //      check cas = streda
    //      check student.size = 3 
    //      check pocet studentov = 3 
    static private Data pp1;

    @Test
    public void UT31_PostPredmet1_1B() {
        Data d = new Data();
        d.setPredmet("PP1");
        d.setTermin("streda");
        d.getStudent().add("Stano1");
        d.getStudent().add("Stano2");
        d.getStudent().add("Stano3");

        String r = "-";
        try {
            client.postXml(d);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        pp1 = null;
        try {
            pp1 = client.getXml(Data.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull("Ziadne PP1", pp1);
        assertEquals("streda", pp1.getTermin());
    }

    @Test
    public void UT32_Predmet1Size_1B() {
        assertNotNull("Ziadne PP1", pp1);
        assertEquals(3, pp1.getStudent().size());
    }

    @Test
    public void UT33_Predmet1Pocet_1B() {
        long npp1 = -1L;
        try {
            npp1 = client.getPocet(Long.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(3L, npp1);
    }

    // 2.6 poststudent(PP1, Pako)
    //      getxml(PP1)
    //      check pocet studentov = 4   
    @Test
    public void UT34_Predmet1PostStudent_1B() {
        try {
            client.postStudent("Pako", "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        long p = -1L;
        try {
            p = client.getPocet(Long.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(4L, p);
    }

    // 2.7. opakovany poststudent(PP1, Stano1)
    //      getxml(PP1)
    //      check pocet studentov = 4 (bez zmeny)
    @Test
    public void UT35_Predmet1PostStano1Duplicita_1B() {
        String r = "-";
        try {
            r = client.postStudent("Stano1", "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        Data d = null;
        try {
            d = client.getXml(Data.class, "PP1");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertNotNull(d);
        assertEquals("streda", d.getTermin());
        assertEquals(4L, d.getStudent().size());
        assertTrue(d.getStudent().contains("Pako"));
        assertTrue(d.getStudent().contains("Stano1"));
    }

    @Test
    public void UT41_PostPredmetBezDat_1B() {
        Data d = new Data();
        d.setPredmet("PP2");

        try {
            client.postXml(d);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        String r = "-";
        try {
            r = client.postStudent("Stano2", "PP2");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        assertEquals("", r);

        long p = -1L;
        try {
            p = client.getPocet(Long.class, "PP2");
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }
        assertEquals(1L, p);
    }


    // find 
    @Test
    public void UT51_Find1_1B() {
        Data d = new Data();
        d.setPredmet("PP3");
        d.setTermin("streda");
        d.getStudent().add("Stano3");

        try {
            client.postXml(d);
        } catch (Exception e) {
            //neocakavana chyba");
            fail("CHYBA WEB:" + e.getMessage());
        }

        String r = "-";
        try {
            r = client.find("Stano1");
        } catch (Exception e) {
            fail("CHYBA WEB: " + e.getMessage());
        }
        assertTrue("RVal: " + r, r.contains("PP1") && !r.contains("PP2") && !r.contains("PP3"));
    }

    @Test
    public void UT52_Find2_1B() {
        String r = "-";
        try {
            r = client.find("Stano3");
        } catch (Exception e) {
            fail("CHYBA WEB: " + e.getMessage());
        }
        assertTrue("RVal: " + r, r.contains("PP1") && !r.contains("PP2") && r.contains("PP3"));
    }

    @Test
    public void UT53_FindNikto_1B() {
        String r = "-";
        try {
            r = client.find("NIKTO");
        } catch (Exception e) {
            if (e.getMessage().contains("HTTP 50")) {
                fail("EXCEPTION " + e.getMessage());
            }
            // mozno vypustit???
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB???: " + e.getMessage());
            }
        }
        assertTrue("RVal: " + r, (r == null || r.isEmpty()));
    }

    @Test
    public void UT54_FindNull_1B() {
        String r = "-";
        try {
            r = client.find(null);
        } catch (Exception e) {
            if (e.getMessage().contains("HTTP 50")) {
                fail("EXCEPTION " + e.getMessage());
            }
            // mozno vypustit???
            if (!e.getMessage().contains("HTTP 40") && !e.getMessage().contains("HTTP 20")) {
                fail("CHYBA WEB???: " + e.getMessage());
            }
        }
        assertTrue("RVal: " + r, (r == null || r.isEmpty()));
    }

}
