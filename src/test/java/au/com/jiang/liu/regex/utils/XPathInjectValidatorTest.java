package au.com.jiang.liu.regex.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class XPathInjectValidatorTest {

	@Test
	public void shouldFindTheInjectString() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc(");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringCloseBacket() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc)d(");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringForwardSlash() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc/");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringDoubleForwardSlash() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc//");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringSingQuate() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc'");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringSpace() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc ");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringEqualSign() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc=");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringOpenSquareBacket() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc[");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringCloseSquareBacket() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc]");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringColon() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("ab:c");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringComma() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc,");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringStartSign() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("a*bc");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringStartPosition() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("(abc)");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldFindTheInjectStringCombine() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("fff(a[b*]c) /ooo");
		//Then the flag is true
		assertThat(contain, is(true));
	}
	
	@Test
	public void shouldNotFindTheInjectString() throws UnsupportedEncodingException {
		//Given the XPathInjectValidator
		//When the isContainsXPathInjectionCharater method called
		boolean contain = XPathInjectionValidator.isContainsXPathInjectionCharacter("abc12");
		//Then the flag is true
		assertThat(contain, is(false));
	}
}
