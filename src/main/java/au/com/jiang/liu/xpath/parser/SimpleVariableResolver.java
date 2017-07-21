package au.com.jiang.liu.xpath.parser;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathFunction;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;

public class SimpleVariableResolver implements XPathVariableResolver, XPathFunctionResolver {

	private final Map<QName, Object> vars = new HashMap<>();
	
	/**
	 * External methods to add parameter
	 * 
	 * @param name Parameter name
	 * @param value Parameter value
	 */
	public void addVariable(QName name, Object value) {
		vars.put(name, value);
	}
	
	@Override
	public XPathFunction resolveFunction(QName functionName, int arity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object resolveVariable(QName variableName) {
		return vars.get(variableName);
	}

}
