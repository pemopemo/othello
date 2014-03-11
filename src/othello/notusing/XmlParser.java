package othello.notusing;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XmlParser {

	public XmlParser() {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document doc = builder.parse(new File("list.xml"));
			Element root = doc.getDocumentElement();

			NodeList itemlist = root.getElementsByTagName("item");
			for (int i = 0; i < itemlist.getLength(); i++) {
				Node node = itemlist.item(i);
				NodeList children = node.getChildNodes();
				String name = null;
				String price = null;
				for (int j = 0; j < children.getLength(); j++) {
					Node child = children.item(j);
					if (child.getNodeName().equals("name")) {
						name = child.getFirstChild().getNodeValue().trim();
					} else if (child.getNodeName().equals("price")) {
						price = child.getFirstChild().getNodeValue().trim();
					}
				}
				System.out.println(name + "F" + price + "‰~");
			}

			// String uri =
			// "http://cloud.github.com/downloads/Prototik/Eclipse-Juno-Dark/theme-9645.xml";
			// Node root = builder.parse(uri);
			//
			// if (root.getNodeType() == Node.DOCUMENT_NODE) {
			// System.out.println("Root is Document!");
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
