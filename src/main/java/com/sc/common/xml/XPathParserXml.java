package com.sc.common.xml;

import org.apache.ibatis.builder.BuilderException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;

/**
 * xml解析例子代码
 */
public class XPathParserXml {
    private final Document document;
    private boolean validation;
    private EntityResolver entityResolver;  //校验文件加载
    private XPath xpath;

    public XPathParserXml(String xmlContent, boolean validation, EntityResolver entityResolver) {
        init(validation, entityResolver);
        this.document = createDocument(new InputSource(new StringReader(xmlContent)));
    }
    public XPathParserXml(InputStream xmlIn, boolean validation, EntityResolver entityResolver) {
        init(validation, entityResolver);
        this.document = createDocument(new InputSource(xmlIn));
    }
    private void init(boolean validation, EntityResolver entityResolver) {
        this.validation = validation;
        this.entityResolver = entityResolver;
        XPathFactory factory = XPathFactory.newInstance();
        this.xpath = factory.newXPath();
    }

    private Document createDocument(InputSource inputSource) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setValidating(validation);

            factory.setIgnoringComments(true);
            factory.setExpandEntityReferences(true);
            factory.setNamespaceAware(false);
            factory.setCoalescing(false);
            factory.setIgnoringElementContentWhitespace(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(entityResolver);
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    throw exception;
                }
            });
            return builder.parse(inputSource);
        } catch (Exception e) {
            throw new RuntimeException("Error creating document instance.  Cause: " + e, e);
        }
    }

    //XPath解析节点
    private Object evaluate(String expression, Object root, QName returnType) {
        try {
            return xpath.evaluate(expression, root, returnType);
        } catch (Exception e) {
            throw new BuilderException("Error evaluating XPath.  Cause: " + e, e);
        }
    }

    public String evalString(String exp) {
        String result = (String) evaluate(exp, document, XPathConstants.STRING);
        return result;
    }

    public boolean evalBoolean(String exp) {
        boolean result = (boolean) evaluate(exp, document, XPathConstants.BOOLEAN);
        return result;
    }

    public Integer evalInteger(String exp) {
        return Integer.valueOf(evalString(exp));
    }

    public Double evalDouble(String exp) {
        return (Double) evaluate(exp, document, XPathConstants.NUMBER);
    }

    public Node evalNode(String exp) {
        Node node = (Node) evaluate(exp, document, XPathConstants.NODE);
        return node;
    }

    public NodeList evalNodes(String exp) {
        NodeList nodes = (NodeList) evaluate(exp, document, XPathConstants.NODESET);
        return nodes;
    }

}
