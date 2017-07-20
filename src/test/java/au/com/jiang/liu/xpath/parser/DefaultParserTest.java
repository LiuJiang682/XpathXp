package au.com.jiang.liu.xpath.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DefaultParserTest {

	private static final Logger LOGGER = Logger.getLogger(DefaultParserTest.class);

	private DefaultParser testInstance;

	@Before
	public void setUp() {
		File dataFile = new File("src/main/resources/data.xml");
		if (!dataFile.exists())
			fail("data file does not exist!");
		this.testInstance = new DefaultParser(dataFile);
	}

	@After
	public void teamDown() {
		this.testInstance = null;
	}

	@Test
	public void shouldReturnFirstLevelNodeList() {
		// Given the test instance
		// When the getFirstLevelNodeList method called
		NodeList nodeList = this.testInstance.getFirstLevelNodeList();
		// Then node list should return
		assertNotNull(nodeList);
		assertThat(nodeList.getLength(), is(2));
	}

	@Test
	public void shouldReturnAllTutorialWithNameSpace() {
		DefaultParser parser = new DefaultParser(new File("src/test/resources/dataNS.xml"));
		NodeList list = parser.getAllTutorials();
		assertNotNull(list);
		assertThat(list.getLength(), is(4));
	}

	@Test
	public void shouldReturnTitleNode() {
		// Given the test instance
		// When the getNodeListByTitle method called
		NodeList nodeList = this.testInstance.getNodeListByTitle("XML");
		// Then node list should return
		assertNotNull(nodeList);
		assertThat(nodeList.getLength(), is(1));
	}

	@Test
	public void shouldReturnTitleNodeWithNS() {
		// Given the test instance
		DefaultParser parser = new DefaultParser(new File("src/test/resources/dataNSMockUrl.xml"));
		// When the getNodeListByTitle method called
		NodeList nodeList = parser.getAllTutorialsWithNSByTitle("XML");
		// Then node list should return
		assertNotNull(nodeList);
		assertThat(nodeList.getLength(), is(1));
	}

	@Test
	public void shouldReturnPaths() {
		// Given the test instance
		DefaultParser parser = new DefaultParser(new File("src/test/resources/dataMulti-NS.xml"));
		// When the getNodeListByTitle method called
		NodeList nodeList = parser.getAllPaths();
		// Then node list should return
		assertNotNull(nodeList);
		assertThat(nodeList.getLength(), is(2));
		for (int i = 0; i < nodeList.getLength(); i++) {
			LOGGER.debug(nodeList.item(i).getTextContent());
		}
	}

	@Test
	public void shouldReturnTypes() throws Exception {
		// Given the test instance
		DefaultParser parser = new DefaultParser(new File("src/test/resources/type.xml"));
		// When the getNodeListByTitle method called
		Node node = parser.ParserForObjectTypes("Data");
		// Then node list should return
		assertNotNull(node);
		LOGGER.debug(node);
		NodeList children = node.getChildNodes();
		LOGGER.debug("Contains: " + children.getLength());
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (Node.ELEMENT_NODE == child.getNodeType()) {
				LOGGER.debug(child.getNodeName() + "=" + child.getTextContent());
			}
		}

		NodeList elements = parser.filterEmptyNodes(node);
		assertNotNull(elements);
		LOGGER.debug("elements contains: " + elements.getLength());
		for (int j = 0; j < elements.getLength(); j++) {
			Node e = elements.item(j);
			LOGGER.debug(e.getNodeName() + "=" + e.getTextContent());
		}
	}

	@Test
	public void shouldReturnAllObjectType() throws SAXException, IOException, ParserConfigurationException {
		// Given the test instance
		DefaultParser parser = new DefaultParser(new File("src/test/resources/type.xml"));
		// When the getNodeListByTitle method called
		NodeList nodeList = parser.getAllObjectTypes();
		assertNotNull(nodeList);
		for (int i = 0; i < nodeList.getLength(); i++) {
			LOGGER.debug("Object Type: " + nodeList.item(i).getNodeName() + " = " + nodeList.item(i).getTextContent());
		}
	}
	
	@Test
	public void shouldReturnAllDataSuffixObjectType() throws SAXException, IOException, ParserConfigurationException {
		// Given the test instance
		DefaultParser parser = new DefaultParser(new File("src/test/resources/type.xml"));
		// When the getNodeListByTitle method called
		NodeList nodeList = parser.getAllDataSuffixObjectTypes();
		assertNotNull(nodeList);
		for (int i = 0; i < nodeList.getLength(); i++) {
			LOGGER.debug("Data suffix Object Type: " + nodeList.item(i).getNodeName() + " = " + nodeList.item(i).getTextContent());
		}
	}
}
