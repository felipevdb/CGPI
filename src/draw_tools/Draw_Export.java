package draw_tools;

import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import draw_xml.XML_Document;
import javafx.geometry.Point2D;

public class Draw_Export {
	public String xmlFilePath = "C:\\Users\\martinig\\Desktop\\vectors.xml";
	private static final String root_tag = "Figura";
	XML_Document xml = new XML_Document();

	public void exportXML(LinkedList<Point2D> points, LinkedList<LinkedList<Point2D>> lines)
			throws TransformerException {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			xml.xml_document = document;
			Element root = xml.xml_document.createElement(root_tag);
			xml.xml_document.appendChild(root);
			putPoints(points, root);
			putLines(lines, root);
			xml.saveDocument(xml.xml_document, xmlFilePath);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}

	private void putPoints(LinkedList<Point2D> points, Element root) {
		points.forEach((p) -> {
			xml.savePoint(p, xml.xml_document, root);
		});
	}

	private void putLines(LinkedList<LinkedList<Point2D>> lines, Element root) {
		lines.forEach((l) -> {
			xml.saveLine(l, xml.xml_document, root);
		});
	}
}
