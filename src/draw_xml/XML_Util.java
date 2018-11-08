package draw_xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class XML_Util {
	public static String convertXmltoString(DOMSource xmlDocument) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		String xmlString = "";
		try {
			transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			// transform document to string
			transformer.transform(xmlDocument, new StreamResult(writer));
			xmlString = writer.getBuffer().toString();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return xmlString;
	}

	public static String format(String xml, Boolean ommitXmlDeclaration)
			throws IOException, SAXException, ParserConfigurationException {

		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = db.parse(new InputSource(new StringReader(xml)));

		OutputFormat format = new OutputFormat(doc);
		format.setIndenting(true);
		format.setIndent(2);
		format.setOmitXMLDeclaration(ommitXmlDeclaration);
		format.setLineWidth(Integer.MAX_VALUE);
		Writer outxml = new StringWriter();
		XMLSerializer serializer = new XMLSerializer(outxml, format);
		serializer.serialize(doc);

		return outxml.toString();
	}

	public static Document convertStringtoXML(String xmlString) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
			Document xmlDocument = builder.parse(new InputSource(new StringReader(xmlString)));
			return xmlDocument;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public double normalizePoint(double n, double canvassizeN) {
		double normaln = n / canvassizeN;
		return normaln;
	}
	
	public double transformVP(double n,double canvassizeN) {
		double vppoint = n * canvassizeN;
		return vppoint;
	}
}
