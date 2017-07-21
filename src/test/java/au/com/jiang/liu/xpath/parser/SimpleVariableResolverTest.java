package au.com.jiang.liu.xpath.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.xml.namespace.QName;

import org.junit.Test;

public class SimpleVariableResolverTest {

	private static final String TEST_VALUE = "abc123";
	private static final QName TEST_QNAME_ID = new QName("id");

	@Test
	public void ShouldReturnObject() {
		//Given the SimpleVariableResolver instance
		SimpleVariableResolver resolver = new SimpleVariableResolver();
		//When the addVariable method called
		resolver.addVariable(TEST_QNAME_ID, TEST_VALUE);
		//Then the object should return
		Object variable = resolver.resolveVariable(TEST_QNAME_ID);
		assertNotNull(variable);
		assertThat(variable, is(TEST_VALUE));
	}
}
