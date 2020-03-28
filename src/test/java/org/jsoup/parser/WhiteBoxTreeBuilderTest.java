package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.jsoup.parser.Parser.parseFragment;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WhiteBoxTreeBuilderTest {

    @Test
    public void contextOwnerDocumentGetCalledOnceIfNotSetupDocToQuirksMode() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String baseURI = "www.google.com/";

        String fragment = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";
        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();
        Element spyContext = spy(context);
        builder.parseFragment(fragment, spyContext, baseURI,
                new Parser(builder));
        verify(spyContext, times(2)).ownerDocument();
    }
    
    
    @Test
    public void canTransitState() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.transition(HtmlTreeBuilderState.InHead);
        assertEquals(HtmlTreeBuilderState.InHead, builder.state());
        builder.markInsertionMode();
        builder.transition(HtmlTreeBuilderState.InBody);
        assertEquals(HtmlTreeBuilderState.InBody, builder.state());
        builder.transition(HtmlTreeBuilderState.InTable);
        assertEquals(HtmlTreeBuilderState.InTable, builder.state());
        assertEquals(HtmlTreeBuilderState.InHead, builder.originalState());
    }


    @Test
    public void canGetBaseURI() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String baseURI = "www.google.com/";
        String fragment = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";
        Document doc = Parser.parse("<div class=child1>", "");
        builder.parseFragment(fragment, doc.select("div.child1").first(), baseURI,
                new Parser(builder));
        assertEquals("www.google.com/", builder.getBaseUri());
    }

    @Test
    public void canAddErrorToTheParser() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String baseURI = "www.google.com/";
        String fragment = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";
        Document doc = Parser.parse("<div class=child1>", "");
        Parser parser = new Parser(builder).setTrackErrors(100);
        assertTrue(parser.isTrackErrors());
        builder.parseFragment(fragment, doc.select("div.child1").first(), baseURI, parser);
        builder.error(HtmlTreeBuilderState.InBody);
        assertEquals("Unexpected token [%s] when in state [%s]", parser.getErrors().get(0).getErrorMessage());
    }

    @Test
    public void canInsertNewStartTag() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""), "", Parser.htmlParser());
        builder.insertStartTag("start");
        assertEquals(1, builder.stack.size());
        assertEquals("start", builder.stack.get(0).tagName());
    }

    @Test
    public void canHandleEmptyUnknownTag() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String fragment = "<unknown/><anotherunknown/>";
        Document doc = Parser.parse("<div class=child1>", "");
        Parser parser = new Parser(builder).setTrackErrors(100);
        List<Node> nodes = builder.parseFragment(fragment, doc.select("div.child1").first(), "http://hanfa.me", parser);
        assertEquals(2, nodes.size());
        assertEquals("<unknown />", nodes.get(0).toString());
        assertEquals("<anotherunknown />", nodes.get(1).toString());
    }

    @Test
    public void tagInListItemScope() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "", Parser.htmlParser());
        assertFalse(builder.inListItemScope("p"));
        builder.insertStartTag("html");
        assertFalse(builder.inListItemScope("p"));
        builder.insert(new Element("ul"));
        assertFalse(builder.inListItemScope("p"));
        builder.insert(new Element("p"));
        assertTrue(builder.inListItemScope("p"));
    }

    @Test
    public void elementInSelectScope() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "", Parser.htmlParser());
        builder.insertStartTag("start");
        builder.insert(new Element("p"));
        builder.insert(new Element("li"));
        assertTrue(builder.inSelectScope("li"));
        assertFalse(builder.inSelectScope("a"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void elementInNotReachableSelectScope() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "", Parser.htmlParser());
        builder.insertStartTag("option");
        builder.inSelectScope("a");
    }

    @Test
    public void canGetHeadElement() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "", Parser.htmlParser());
        builder.insertStartTag("start");
        assertNull(builder.getHeadElement());
        builder.setHeadElement(new Element("head"));
        assertEquals("head", builder.getHeadElement().normalName());
    }

    @Test
    public void canGenerateImpliedEndTags() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "", Parser.htmlParser());
        builder.insertStartTag("start");
        builder.insert(new Element("p"));
        builder.insert(new Element("li"));
        assertEquals(3, builder.stack.size());
        builder.generateImpliedEndTags("p");
        assertEquals(2, builder.stack.size());
        assertEquals("p", builder.stack.get(
                builder.stack.size()-1).normalName());

    }

    @Test
    public void canPushActiveFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "", Parser.htmlParser());
        builder.insertStartTag("start");
        Element el = new Element("p");
        assertNull(builder.getActiveFormattingElement(
                "p"));
        builder.pushActiveFormattingElements(el);
        builder.pushActiveFormattingElements(el);
        builder.pushActiveFormattingElements(el);
        builder.pushActiveFormattingElements(el);
        builder.insertMarkerToFormattingElements();
        builder.pushActiveFormattingElements(el);
        assertEquals("<p></p>",
                builder.removeLastFormattingElement().toString());
        assertNull(builder.removeLastFormattingElement());
        assertEquals("<p></p>",
                builder.removeLastFormattingElement().toString());
        assertEquals("<p></p>",
                builder.removeLastFormattingElement().toString());
        assertEquals("<p></p>",
                builder.removeLastFormattingElement().toString());
        assertNull(builder.removeLastFormattingElement());
    }

    @Test
    public void canReconstructFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "", Parser.htmlParser());
        builder.insertStartTag("start");
        Element elOut = new Element("p");
        Element elIn = new Element("a");
        builder.pushActiveFormattingElements(elOut);
        builder.pushActiveFormattingElements(elIn);
        assertEquals("<p></p>",
                builder.getActiveFormattingElement(
                        "p").toString());
        builder.reconstructFormattingElements();
        assertEquals("<p><a></a></p>",
                builder.getActiveFormattingElement(
                        "p").toString());

    }

    @Test
    public void canReplaceActiveFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "",
                Parser.htmlParser());
        Element elOut = new Element("p");
        Element elIn = new Element("a");
        builder.pushActiveFormattingElements(elOut);
        assertTrue(builder.isInActiveFormattingElements(elOut));
        builder.replaceActiveFormattingElement(elOut, elIn);
        assertTrue(builder.isInActiveFormattingElements(elIn));
        assertFalse(builder.isInActiveFormattingElements(elOut));
    }

    @Test
    public void getActiveFormattingElementsShouldBreakOnNullElementIfHasFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "",
                Parser.htmlParser());
        Element el = new Element("p");
        builder.pushActiveFormattingElements(el);
        builder.insertMarkerToFormattingElements();
        Element formatEl = builder.getActiveFormattingElement(
                "p");
        assertEquals(null, formatEl);
    }

    @Test
    public void getActiveFormattingElementsReturnNextElementIfHasFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "",
                Parser.htmlParser());
        Element el = new Element("p");
        builder.pushActiveFormattingElements(el);
        builder.pushActiveFormattingElements(el);
        Element formatEl = builder.getActiveFormattingElement(
                "p");
        assertEquals("<p></p>", formatEl.toString());
    }

    @Test
    public void getActiveFormattingElementsReturnNullIfNoFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "",
                Parser.htmlParser());
        Element el = builder.getActiveFormattingElement(
                "p");
        assertNull(el);
    }

    @Test
    public void htmlTreeBuilderToStringGivesDesiredFormattedString() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""),
                "", Parser.htmlParser());
        builder.insertStartTag("start");
        assertEquals("TreeBuilder{currentToken=null, " +
                "state=Initial, currentElement=<start></start>}",
                builder.toString());
    }
}
