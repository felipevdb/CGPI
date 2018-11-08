package draw_xml;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javafx.geometry.Point2D;

public class XML_Document {
	public Document xml_document;
	public XML_Util xml_util = new XML_Util();

	public void savePoint(Point2D p, Document document, Element root) {
		Element point_tag = document.createElement("Ponto");
		root.appendChild(point_tag);

		Element x = document.createElement("x");
		x.appendChild(document.createTextNode(String.valueOf(xml_util.normalizePoint(p.getX(), 720))));
		point_tag.appendChild(x);

		Element y = document.createElement("y");
		y.appendChild(document.createTextNode(String.valueOf(xml_util.normalizePoint(p.getY(), 512))));
		point_tag.appendChild(y);
	}

	public void saveLine(LinkedList<Point2D> l, Document document, Element root) {
		Element line_tag = document.createElement("Reta");
		root.appendChild(line_tag);

		savePoint(l.getFirst(), document, line_tag);
		savePoint(l.getLast(), document, line_tag);

		saveRGB(document, line_tag);
	}

	public void saveRGB(Document document, Element root) {
		Element rgb_tag = document.createElement("Cor");
		root.appendChild(rgb_tag);

		Element r = document.createElement("R");
		r.appendChild(document.createTextNode("0"));
		rgb_tag.appendChild(r);

		Element g = document.createElement("G");
		g.appendChild(document.createTextNode("255"));
		rgb_tag.appendChild(g);

		Element b = document.createElement("B");
		b.appendChild(document.createTextNode("0"));
		rgb_tag.appendChild(b);
	}

	public Document loadDocument(String xmlfilepath) {
		Document load_document = null;
		try {
			File file = new File(xmlfilepath);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			DOMSource domSource = new DOMSource(document);
			String xmlString = xml_util.convertXmltoString(domSource);
			try {
				xmlString = xml_util.format(xmlString, true);
			} catch (IOException | SAXException | ParserConfigurationException e) {
				e.printStackTrace();
			}
			Document xml_formatted = xml_util.convertStringtoXML(xmlString);
			domSource = new DOMSource(xml_formatted);
			load_document = xml_formatted;
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return load_document;
	}

	public void saveDocument(Document document, String xmlFilePath) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));
			String xmlString = xml_util.convertXmltoString(domSource);
			try {
				xmlString = xml_util.format(xmlString, true);
			} catch (IOException | SAXException | ParserConfigurationException e) {
				e.printStackTrace();
			}
			Document xml_formatted = xml_util.convertStringtoXML(xmlString);
			domSource = new DOMSource(xml_formatted);
			transformer.transform(domSource, streamResult);
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}
