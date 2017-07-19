package au.com.jiang.liu.xpath.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.NodeList;

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
		//Given the test instance
		//When the getFirstLevelNodeList method called
		NodeList nodeList = this.testInstance.getFirstLevelNodeList();
		//Then node list should return
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
		//Given the test instance
		//When the getNodeListByTitle method called
		NodeList nodeList = this.testInstance.getNodeListByTitle("XML");
		//Then node list should return
		assertNotNull(nodeList);
		assertThat(nodeList.getLength(), is(1));
	}
	
	@Test
	public void shouldReturnTitleNodeWithNS() {
		//Given the test instance
		DefaultParser parser = new DefaultParser(new File("src/test/resources/dataNSMockUrl.xml"));
		//When the getNodeListByTitle method called
		NodeList nodeList = parser.getAllTutorialsWithNSByTitle("XML");
		//Then node list should return
		assertNotNull(nodeList);
		assertThat(nodeList.getLength(), is(1));
	}
}
