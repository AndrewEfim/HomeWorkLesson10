import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Set;

public class Registry {

    private boolean bname = false;
    private boolean blastName = false;
    private boolean bbirth = false;
    private boolean bisSick = false;

    private Hospital hospital;
    private String fileName;

    public Registry(Hospital hospital, String fileName) {
        this.hospital = hospital;
        this.fileName = fileName;
    }


    public void write() throws ParserConfigurationException, TransformerException, FileNotFoundException {
        Set<Patient> patients = hospital.getPatient( );

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance( );
        DocumentBuilder builder = factory.newDocumentBuilder( );
        Document document = builder.newDocument( );


        Element root = document.createElement("patients");
        document.appendChild(root);
        for (Patient patient : patients) {
            Element Patient = document.createElement("Patient");
            Element name = document.createElement("name");
            Element surname = document.createElement("lastName");
            Element birth = document.createElement("birth");
            Element isSick = document.createElement("isSick");
            root.appendChild(Patient);
            Patient.appendChild(name);
            name.setTextContent(patient.getName( ));
            Patient.appendChild(surname);
            surname.setTextContent(patient.getLastName( ));
            Patient.appendChild(birth);
            birth.setTextContent(patient.getBirth( ));
            Patient.appendChild(isSick);
            isSick.setTextContent(Boolean.toString(patient.isSick( )));
        }
        Transformer t = TransformerFactory.newInstance( ).newTransformer( );
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(fileName)));
    }

    public void read() {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance( );
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader("fileName"));

            while (eventReader.hasNext( )) {
                XMLEvent event = eventReader.nextEvent( );

                switch (event.getEventType( )) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement( );
                        String qName = startElement.getName( ).getLocalPart( );

                        if (qName.equalsIgnoreCase("patients")) {
                            System.out.println("Start Element : Patient");
                            // Iterator<Attribute> attributes = startElement.getAttributes( );
                        } else if (qName.equalsIgnoreCase("name")) {
                            bname = true;
                        } else if (qName.equalsIgnoreCase("lastName")) {
                            blastName = true;
                        } else if (qName.equalsIgnoreCase("birth")) {
                            bbirth = true;
                        } else if (qName.equalsIgnoreCase("isSick")) {
                            bisSick = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters( );
                        if (bname) {
                            System.out.println("name: " + characters.getData( ));
                            bname = false;
                        }
                        if (blastName) {
                            System.out.println("lastName: " + characters.getData( ));
                            blastName = false;
                        }
                        if (bbirth) {
                            System.out.println("birth: " + characters.getData( ));
                            bbirth = false;
                        }
                        if (bisSick) {
                            System.out.println("is Sick: " + characters.getData( ));
                            bisSick = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement( );

                        if (endElement.getName( ).getLocalPart( ).equalsIgnoreCase("patient")) {
                            System.out.println("End Element : patient");
                            System.out.println( );
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace( );
        } catch (XMLStreamException e) {
            e.printStackTrace( );
        }
    }

    public void uploadRemoteFile() throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/AndrewEfim/ForHomeWork9/master/test.xml");
        URLConnection urlConnection = url.openConnection( );
        InputStreamReader inputStream = new InputStreamReader(urlConnection.getInputStream( ));
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance( );
            XMLEventReader eventReader =
                    factory.createXMLEventReader(inputStream);

            while (eventReader.hasNext( )) {
                XMLEvent event = eventReader.nextEvent( );

                switch (event.getEventType( )) {

                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement( );
                        String qName = startElement.getName( ).getLocalPart( );

                        if (qName.equalsIgnoreCase("patients")) {
                            System.out.println("Start Element : Patient");
                            // Iterator<Attribute> attributes = startElement.getAttributes( );
                        } else if (qName.equalsIgnoreCase("name")) {
                            bname = true;
                        } else if (qName.equalsIgnoreCase("lastName")) {
                            blastName = true;
                        } else if (qName.equalsIgnoreCase("birth")) {
                            bbirth = true;
                        } else if (qName.equalsIgnoreCase("isSick")) {
                            bisSick = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters( );
                        if (bname) {
                            System.out.println("name: " + characters.getData( ));
                            bname = false;
                        }
                        if (blastName) {
                            System.out.println("lastName: " + characters.getData( ));
                            blastName = false;
                        }
                        if (bbirth) {
                            System.out.println("birth: " + characters.getData( ));
                            bbirth = false;
                        }
                        if (bisSick) {
                            System.out.println("is Sick: " + characters.getData( ));
                            bisSick = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement( );

                        if (endElement.getName( ).getLocalPart( ).equalsIgnoreCase("patient")) {
                            System.out.println("End Element : patient");
                            System.out.println( );
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace( );
        }
    }

}


