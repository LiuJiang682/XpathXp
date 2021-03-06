package au.com.jiang.liu.xpath.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathVariableResolver;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DefaultParser {

	private File file;

	public DefaultParser(File file) {
		this.file = file;
	}

	public NodeList getFirstLevelNodeList() {
		NodeList nodeList = null;
		try {
			FileInputStream fileIS = new FileInputStream(this.getFile());
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(fileIS);

			XPath xPath = XPathFactory.newInstance().newXPath();

			String expression = "/Tutorials/Tutorial";

			nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		return nodeList;
	}

	public Node getNodeById(String id) {
		Node node = null;
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			XPath xPath = XPathFactory.newInstance().newXPath();

			String expression = "/tutorials/tutorial[@tutId=" + "'" + id + "'" + "]";

			node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);

		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		return node;
	}

	public NodeList getNodeListByTitle(String name) {
		NodeList nodeList = null;
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			this.clean(xmlDocument);

			XPath xPath = XPathFactory.newInstance().newXPath();

			String expression = "//Tutorial[descendant::title[text()=" + "'" + name + "'" + "]]";

			nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		return nodeList;
	}

	public NodeList getElementsByDate(String date) {
		NodeList nodeList = null;

		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			this.clean(xmlDocument);

			XPath xPath = XPathFactory.newInstance().newXPath();

			String expression = "//tutorial[number(translate(date, '/', '')) > " + date + "]";

			nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		return nodeList;
	}

	public NodeList getAllTutorials() {
		NodeList nodeList = null;
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			this.clean(xmlDocument);

			XPath xPath = XPathFactory.newInstance().newXPath();

			xPath.setNamespaceContext(new NamespaceContext() {

				@Override
				public Iterator getPrefixes(String arg0) {
					return null;
				}

				@Override
				public String getPrefix(String arg0) {
					return null;
				}

				@Override
				public String getNamespaceURI(String arg0) {
					if ("bdn".equals(arg0)) {
						// return "http://www.baeldung.com/full_archive";
						return "http://www.fpml.org/FpML-5/confirmation";
					}
					return null;
				}
			});

			String expression = "/bdn:tutorials/bdn:tutorial";

			nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		return nodeList;
	}

	public NodeList getAllTutorialsWithNSByTitle(final String title) {
		NodeList nodeList = null;
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			this.clean(xmlDocument);

			XPath xPath = XPathFactory.newInstance().newXPath();

			xPath.setNamespaceContext(new NamespaceContext() {

				@Override
				public Iterator getPrefixes(String arg0) {
					return null;
				}

				@Override
				public String getPrefix(String arg0) {
					return null;
				}

				@Override
				public String getNamespaceURI(String arg0) {
					if ("bdn".equals(arg0)) {
						return "http://localhost:8080";
						// return "http://www.baeldung.com/full_archive";
						// return "http://www.fpml.org/FpML-5/confirmation";
					}
					return null;
				}
			});

			// String expression = "/bdn:tutorials/bdn:tutorial";
			// String expression = "//bdn:Tutorial[descendant::title[text()=" + "'" + title
			// + "'" + "]]";
			String expression = "/bdn:tutorials/bdn:tutorial/bdn:title[text()=" + "'" + title + "'" + "]";
			// String expression = "//bdn:tutorial/bdn:title[text()=" + "'" + title + "'" +
			// "]";

			nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		return nodeList;
	}

	public NodeList getAllPaths() {
		NodeList nodeList = null;
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			this.clean(xmlDocument);

			XPath xPath = XPathFactory.newInstance().newXPath();

			xPath.setNamespaceContext(new NamespaceContext() {

				@Override
				public Iterator getPrefixes(String arg0) {
					return null;
				}

				@Override
				public String getPrefix(String arg0) {
					return null;
				}

				@Override
				public String getNamespaceURI(String arg0) {
					if ("lp".equals(arg0)) {
						return "http://localhost:8080";
					}
					return null;
				}
			});

			String expression = "//lp:path";
			nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			e.printStackTrace();
		}
		return nodeList;
	}

	private void clean(Node node) {

		NodeList childs = node.getChildNodes();

		for (int n = childs.getLength() - 1; n >= 0; n--) {
			Node child = childs.item(n);
			short nodeType = child.getNodeType();

			if (nodeType == Node.ELEMENT_NODE)
				clean(child);
			else if (nodeType == Node.TEXT_NODE) {
				String trimmedNodeVal = child.getNodeValue().trim();
				if (trimmedNodeVal.length() == 0)
					node.removeChild(child);
				else
					child.setNodeValue(trimmedNodeVal);
			} else if (nodeType == Node.COMMENT_NODE)
				node.removeChild(child);
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Node ParserForObjectTypes(final String dataType)
			throws SAXException, IOException, ParserConfigurationException {
		Node node = null;

		try {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			XPath xPath = XPathFactory.newInstance().newXPath();
			final Map<String, Object> vars = new HashMap<String, Object>();
			xPath.setXPathVariableResolver(new XPathVariableResolver() {
				public Object resolveVariable(QName name) {
					return vars.get(name.getLocalPart());
				}
			});

			XPathExpression expr = xPath.compile("/type/OBJECT_TYPE[. = $type]/following-sibling::prop[1]");
			vars.put("type", dataType);
			node = (Node) expr.evaluate(xmlDocument, XPathConstants.NODE);
			// NodeList nodeList = (NodeList) xPath
			// .compile("//OBJECT_TYPE[text() = *]/following-sibling::prop[1]/*")
			// .evaluate(xmlDocument, XPathConstants.NODESET);
			//
			// for (int i = 0; i < nodeList.getLength(); i++) {
			// System.out.println(nodeList.item(i).getNodeName() + " = " +
			// nodeList.item(i).getTextContent());
			// }

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return node;
	}
	
	public Node ParserForObjectTypesWithVariableResolver(final String dataType)
			throws SAXException, IOException, ParserConfigurationException {
		Node node = null;

		try {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			XPath xPath = XPathFactory.newInstance().newXPath();
			SimpleVariableResolver resolver = new SimpleVariableResolver();
			resolver.addVariable(new QName("type"), dataType);
			xPath.setXPathVariableResolver(resolver);
			
			XPathExpression expr = xPath.compile("/type/OBJECT_TYPE[. = $type]/following-sibling::prop[1]");
			
			node = (Node) expr.evaluate(xmlDocument, XPathConstants.NODE);
			// NodeList nodeList = (NodeList) xPath
			// .compile("//OBJECT_TYPE[text() = *]/following-sibling::prop[1]/*")
			// .evaluate(xmlDocument, XPathConstants.NODESET);
			//
			// for (int i = 0; i < nodeList.getLength(); i++) {
			// System.out.println(nodeList.item(i).getNodeName() + " = " +
			// nodeList.item(i).getTextContent());
			// }

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return node;
	}

	public NodeList getAllObjectTypes()
			throws SAXException, IOException, ParserConfigurationException {
		NodeList nodeList = null;

		try {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			XPath xPath = XPathFactory.newInstance().newXPath();

			nodeList = (NodeList) xPath.compile("//OBJECT_TYPE/following-sibling::prop[1]/*")
					.evaluate(xmlDocument, XPathConstants.NODESET);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return nodeList;
	}
	
	public NodeList getAllDataSuffixObjectTypes()
			throws SAXException, IOException, ParserConfigurationException {
		NodeList nodeList = null;

		try {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			Document xmlDocument = builder.parse(this.getFile());

			XPath xPath = XPathFactory.newInstance().newXPath();

//			nodeList = (NodeList) xPath.compile("//OBJECT_TYPE[text() = 'Data']/following-sibling::param[1]/*")
//					.evaluate(xmlDocument, XPathConstants.NODESET);
			
			nodeList = (NodeList) xPath.compile("//OBJECT_TYPE[contains(text(), \"Data\")]/following-sibling::param[1]/*")
					.evaluate(xmlDocument, XPathConstants.NODESET);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return nodeList;
	}

	public NodeList filterEmptyNodes(Node node) throws Exception {

		XPathFactory xpathFactory = XPathFactory.newInstance();
		// XPath to find empty text nodes.
		XPathExpression xpathExp = xpathFactory.newXPath().compile("//text()[normalize-space(.) = '']");
		NodeList emptyTextNodes = (NodeList) xpathExp.evaluate(node, XPathConstants.NODESET);
		// Remove each empty text node from document.
		for (int i = 0; i < emptyTextNodes.getLength(); i++) {
			Node emptyTextNode = emptyTextNodes.item(i);
			emptyTextNode.getParentNode().removeChild(emptyTextNode);
		}

		return node.getChildNodes();
	}

	public String getMultiNSId() throws Exception {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		DocumentBuilder builder = builderFactory.newDocumentBuilder();

		Document xmlDocument = builder.parse(this.getFile());
		
		XPathFactory xpathFactory = XPathFactory.newInstance();
		// XPath to find empty text nodes.
		XPath xPath = xpathFactory.newXPath();
//		NamespaceContext context = new NamespaceContextMap(
//		        "am", "http://localhost:8080/a", 
//		        "s", "http://localhost:8080/s");
//		xPath.setNamespaceContext(context);
//		XPathExpression xpathExp = xPath.compile("/am:entry/am:content/s:series-poc/s:c_series.c_id/text()");
//		return (String) xpathExp.evaluate(xmlDocument, XPathConstants.STRING);
		xPath.setNamespaceContext(new NamespaceContext() {

			@Override
			public Iterator getPrefixes(String arg0) {
				return null;
			}

			@Override
			public String getPrefix(String arg0) {
				return null;
			}

			@Override
			public String getNamespaceURI(String arg0) {
				if ("am".equals(arg0)) {
					return "http://localhost:8080/a";
				}
				else if ("s".equals(arg0)) {
					return "http://localhost:8080/s";
				}
				return null;
			}
		});

		String expression = "/am:entry/am:content/s:series-poc";
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
		System.err.println("NodeList Length: " + nodeList.getLength());
		return "abc";
	}
}
