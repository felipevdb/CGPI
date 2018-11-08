package draw_tools;

import java.util.LinkedList;

import org.w3c.dom.NodeList;

import draw_xml.XML_Document;
import javafx.geometry.Point2D;

public class Draw_Import {
	public String xmlFilePath = "C:\\Users\\martinig\\Desktop\\vectors.xml";
	private static final String root_tag = "Figura";
	XML_Document xml = new XML_Document();

	public void importXML() {
		xml.xml_document = xml.loadDocument(xmlFilePath);
	}

	public LinkedList<Point2D> getPoints() {
		LinkedList<Point2D> pontos = new LinkedList<Point2D>();
		NodeList nodes = xml.xml_document.getFirstChild().getChildNodes();
		int vectors_size = nodes.getLength();
		for (int i = 0; i < vectors_size; i++) {
			if (nodes.item(i).getNodeName().equals("Ponto")) {
				Point2D ponto = new Point2D(
						xml.xml_util.transformVP(Double.valueOf(nodes.item(i).getChildNodes().item(1).getTextContent()),
								720), // X
						xml.xml_util.transformVP(Double.valueOf(nodes.item(i).getChildNodes().item(3).getTextContent()),
								512)); // Y
				pontos.add(ponto);
			}
		}
		return pontos;
	}

	public LinkedList<LinkedList<Point2D>> getLines() {
		LinkedList<LinkedList<Point2D>> lines = new LinkedList<LinkedList<Point2D>>();
		NodeList nodes = xml.xml_document.getFirstChild().getChildNodes();
		int vectors_size = nodes.getLength();
		for (int i = 0; i < vectors_size; i++) {
			if (nodes.item(i).getNodeName().equals("Reta")) {
				LinkedList<Point2D> line_points = new LinkedList<Point2D>();
				NodeList nodes_lines = nodes.item(i).getChildNodes();
				int lines_size = nodes.item(i).getChildNodes().getLength();
				for (int j = 0; j < lines_size; j++) {
					if (nodes_lines.item(j).getNodeName().equals("Ponto")) {
						Point2D ponto = new Point2D(
								xml.xml_util.transformVP(Double.valueOf(nodes_lines.item(j).getChildNodes().item(1).getTextContent()), 720), // X
								xml.xml_util.transformVP(Double.valueOf(nodes_lines.item(j).getChildNodes().item(3).getTextContent()), 512)); // Y
						line_points.add(ponto);
					}
				}
				lines.add(line_points);
			}
		}
		return lines;
	}

}
