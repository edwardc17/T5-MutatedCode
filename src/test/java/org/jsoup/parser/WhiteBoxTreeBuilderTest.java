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
        verify(spyContext, times(1)).ownerDocument();
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
        assertEquals("www.google.com", builder.getBaseUri());
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
    public void notCovered() {
//        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        // line 99, 101, 103, 105, 107, 122-123 uncovered
        //builder.parseFragment();
        
        //line 150
        //builder.state(); ok

        //line 174
        //builder.getBaseUri(); ok

        //line 196
        //builder.error(builder.state()); ok

        //line 211-215
        //builder.insert(startTag); ok

        //line 239-244
        //builder.insertEmpty();

        //line 250-256
        //builder.insertForm(); OK

        //line 271, 273
        //builder.insert(charToken);

        //line 291
        //builder.insert(node);

        //line 300
        //builder.push();

        //line 319
        //builder.isElementInQueue();

        //line 322 Mutant 4
        //builder.getFromStack();

        //line 329
        //builder.getFromStack();

        //line 333-340 Mutant8
        // originally return false *********************
        //builder.removeFromStack();

        //line 363-371
        //builder.popStackToBefore();

        //line 389, 391
        //builder.clearStackToTableContext();

        //line 396-403
        //builder.aboveOnStack();

        //line 407-409 Mutant 9 *********************
        //builder.insertOnStackAfter();

        //line 411
        //builder.replaceOnStack();

        //line 416-419 private replaceInQueue(),
        //used at line 706-707
        //builder.replaceActiveFormattingElement();

        //line 430-462, except 454-456
        //builder.resetInsertionMode();

        //line 491, 494 private

        //line 511
        //builder.inListItemScope();

        //line 524-533
        //builder.inSelectScope();

        //line 540
//        builder.getHeadElement();

        //line 552
//        builder.getFormElement();

        //line 556
        //builder.setFormElement();

        //line 579-580
        //builder.generateImpliedEndTags(excludeTag);

        //line 603
//        builder.removeLastFormattingElement();

        //line 610-619 will cover 628-630 isSameFormattingElement
        //builder.pushActiveFormattingElements(in);

        //line 639-670
        //builder.reconstructFormattingElements();

        //line 677
//        builder.clearFormattingElementsToLastMarker();

        //line 690
        //builder.isInActiveFormattingElements(element);

        //line 698 Mutant 10 *********************
        //builder.getActiveFormattingElement(nodeName);

        //line 706-707 Mentioned above

        //line 722, 724, 732
        //builder.insertInFosterParent(nodeIn);
        // ISSUE, NO SIDE EFFECT

        //line 737, 740
//        builder.toString();
    }

    @Test
    public void htmlTreeBuilderToStringGivesDesiredFormattedString() {
//        HtmlTreeBuilder builder = new HtmlTreeBuilder();
//        System.out.println(builder.stack.size());
//        String fragmentHTML = "<p id=\"myP\"></p>";
//        String baseURI = "www.google.com/";
////        Parser parser = new Parser(builder);
////        Document doc = Parser.parse(fragmentHTML, baseURI);
////        Reader inputHtml = new StringReader(fragmentHTML);
////        builder.initialiseParse(inputHtml, baseURI, parser);
//        List<Node> nodes = Parser.parseFragment(
//                fragmentHTML, null, baseURI);
//        Element el = nodes.get(0).ownerDocument().getElementById("myP");
//        //builder.push(el);
//        System.out.println(el);
//        System.out.println(builder.stack == null);
    }

    @Test
    public void getActiveFormattingElementsReturnNullIfNoFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String fragmentHTML = "<p id=\"myP\"></p>";
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        parser.parseInput(fragmentHTML, baseURI);
        Element el = builder.getActiveFormattingElement("p");
        assertNull(el);
    }

    @Test
    public void canGetActiveFormattingElementsReturnNextElementIfHasFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String fragmentHTML = "<p id=\"myP\"></p>";
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        Document doc = parser.parseInput(fragmentHTML, baseURI);
        Element el = doc.getElementById("myP");
        builder.pushActiveFormattingElements(el);
        builder.pushActiveFormattingElements(el);
        Element formatEl = builder.getActiveFormattingElement("p");
        assertEquals("<p id=\"myP\"></p>", formatEl.toString());
    }

    @Test
    public void canGetActiveFormattingElementsShouldBreakOnNullElementIfHasFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String fragmentHTML = "<p id=\"myP\"></p>";
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        Document doc = parser.parseInput(fragmentHTML, baseURI);
        Element el = doc.getElementById("myP");
        builder.pushActiveFormattingElements(el);
        builder.insertMarkerToFormattingElements();
        Element formatEl = builder.getActiveFormattingElement("p");
        assertEquals(null, formatEl.toString());
    }

    @Test
    public void canReplaceActiveFormattingElementsShouldBreakOnNullElementIfHasFormattingElements() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String fragmentHTML = "<p id=\"myP\"></p>\n" +
                "<a id=\"anA\"></a>";
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        Document doc = parser.parseInput(fragmentHTML, baseURI);
        Element elOut = doc.getElementById("myP");
        Element elIn = doc.getElementById("anA");
        builder.pushActiveFormattingElements(elOut);
        assertTrue(builder.isInActiveFormattingElements(elOut));
        builder.replaceActiveFormattingElement(elOut, elIn);
        assertTrue(builder.isInActiveFormattingElements(elIn));
    }

}
