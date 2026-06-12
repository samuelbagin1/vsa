package saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {
    
    private String name;

    public SaxHandler(String name) {
        this.name = name;
    }
    
    String text = null;
    
    boolean inZameranie = false;
    boolean inPrednaska = false;
    boolean inVyucujuci = false;
    
    String nazovZamerania = null;
    String semester = null;
    String nazovPrednasky = null;
    String menoVyucujuceho = null;
    
    String nazvyPrednasok = "";
    

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        if (localName.equals("nazov")) {
            if (inPrednaska) {
                nazovPrednasky = text;
            }
        }
        
        if (localName.equals("meno")) {
            if (inPrednaska && inVyucujuci) {
                menoVyucujuceho = text;
            }
        }
        
        if (localName.equals("zameranie")) {
            inZameranie = false;
        }
        
        if (localName.equals("prednaska")) {
            inPrednaska = false;
            
            if (name.equals(menoVyucujuceho) && "zimny".equals(semester)) {
                nazvyPrednasok += nazovPrednasky + " ";
            }
            
            nazovZamerania = null;
            semester = null;
            nazovPrednasky = null;
            menoVyucujuceho = null;
        }
        
        if (localName.equals("vyucujuci")) {
            inVyucujuci = false;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("zameranie")) {
            inZameranie = true;
        }
        
        if (localName.equals("prednaska")) {
            inPrednaska = true;
            semester = attributes.getValue("semester");
        }
        
        if (localName.equals("vyucujuci")) {
            inVyucujuci = true;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(nazvyPrednasok);
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
