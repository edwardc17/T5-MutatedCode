package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;

import java.util.List;

import static org.jsoup.parser.Parser.parseFragment;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BlackBoxParseC2Test {

    @Test
    public void fragmentHTMLWithNullAndNullContextAndNullBaseURI() {
        String fragmentHtml = null;
        Element context = null;
        String baseURI = null;

        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test
    public void fragmentHTMLWithNullAndNullContextAndEmptyBaseURI() {
        String fragmentHtml = null;
        Element context = null;
        String baseURI = "";

        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test
    public void fragmentHTMLWithNullAndNullContextAndValidExistingBaseURI() {
        String fragmentHtml = null;
        Element context = null;
        String baseURI = "https://www.google.com/";

        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test
    public void fragmentHTMLWithNullAndNullContextAndValidNonExistingBaseURI() {
        String fragmentHtml = null;
        Element context = null;
        String baseURI = "https://www.googleeeeeee.com/";

        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test
    public void fragmentHTMLWithNullAndContextAndMissingProtocolBaseURI() {
        String fragmentHtml = null;
        String baseURI = "www.google.com/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test
    public void fragmentHTMLWithNullAndContextAndMissingDomainBaseURI() {
        String fragmentHtml = null;
        String baseURI = "https://www/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test
    public void fragmentHTMLWithNullAndContextAndWrongFormatBaseURI() {
        String fragmentHtml = null;
        String baseURI = "https://www/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }

    @Test
    public void fragmentHTMLWithEmptyAndContextAndNullBaseURI() {
        String fragmentHtml = "";
        String baseURI = null;

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void fragmentHTMLWithEmptyAndContextAndEmptyBaseURI() {
        String fragmentHtml = "";
        String baseURI = "";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertTrue(doc_Frag.isEmpty());
    }

    @Test
    public void fragmentHTMLWithEmptyAndContextAndValidExistingBaseURI() {
        String fragmentHtml = "";
        String baseURI = "https://www.google.com/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertTrue(doc_Frag.isEmpty());
    }

    @Test
    public void fragmentHTMLWithEmptyAndContextAndValidNonExistingBaseURI() {
        String fragmentHtml = "";
        String baseURI = "https://www.googleeeeeee.com/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertTrue(doc_Frag.isEmpty());
    }

    @Test
    public void fragmentHTMLWithEmptyAndNullContextAndMissingProtocolBaseURI() {
        String fragmentHtml = "";
        String baseURI = "www.google.com/";
        Element context = null;

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithEmptyAndNullContextAndMissingDomainBaseURI() {
        String fragmentHtml = "";
        String baseURI = "https://www/";
        Element context = null;

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithEmptyAndNullContextAndWrongFormatBaseURI() {
        String fragmentHtml = "";
        String baseURI = "https://www.googlecom/";
        Element context = null;

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);


        assertEquals(doc_Frag.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithPlainTextAndNullContextAndNullBaseURI() {
        String fragmentHtml = "text";
        String baseURI = null;
        Element context = null;

        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void fragmentHTMLWithPlainTextAndNullContextAndEmptyBaseURI() {
        String fragmentHtml = "text";
        String baseURI = "";
        Element context = null;

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  text\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithPlainTextAndNullContextAndValidExistingBaseURI() {
        String fragmentHtml = "text";
        String baseURI = "https://www.google.com/";
        Element context = null;

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  text\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithPlainTextAndNullContextAndValidNonExistingBaseURI() {
        String fragmentHtml = "text";
        String baseURI = "https://www.googleeeeeee.com/ ";
        Element context = null;

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  text\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithPlainTextAndContextAndMissingProtocolBaseURI() {
        String fragmentHtml = "text";
        String baseURI = "https://www.googleeeeeee.com/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "\n" +
                "text");
    }

    @Test
    public void fragmentHTMLWithPlainTextAndContextAndMissingDomainBaseURI() {
        String fragmentHtml = "text";
        String baseURI = "https://www/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "\n" +
                "text");
    }

    @Test
    public void fragmentHTMLWithPlainTextAndContextAndWrongFormatBaseURI() {
        String fragmentHtml = "text";
        String baseURI = "https://www.googlecom/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "\n" +
                "text");
    }

    @Test
    public void fragmentHTMLWithOpenTagsAndContextAndNullBaseURI() {
        String fragmentHtml = "<h1 page1</h1>";
        String baseURI = null;

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        try {
            List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void fragmentHTMLWithOpenTagsAndContextAndEmptyBaseURI() {
        String fragmentHtml = "<h1 page</h1>";
        String baseURI = "";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertTrue(doc_Frag.get(0).hasAttr("h1"));
    }

    @Test
    public void fragmentHTMLWithOpenTagsAndContextAndValidExistingBaseURI() {
        String fragmentHtml = "<h1 page</h1>";
        String baseURI = "https://www.google.com/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertTrue(doc_Frag.get(0).hasAttr("h1"));
    }

    @Test
    public void fragmentHTMLWithOpenTagsAndContextAndValidNonExistingBaseURI() {
        String fragmentHtml = "<h1 page</h1>";
        String baseURI = "https://www.googleeeeeee.com/";

        Document doc = Parser.parse("<div class=child1>", "");
        Element context = doc.select("div.child1").first();

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertTrue(doc_Frag.get(0).hasAttr("h1"));
    }

    @Test
    public void fragmentHTMLWithOpenTagsAndNullContextAndValidMissingProtocolBaseURI() {
        String fragmentHtml = "<h1 page</h1>";
        String baseURI = "www.google.com/";
        Element context = null;

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <h1 page< h1></h1>\n" +
                " </body>\n" +
                "</html>");

        assertTrue(doc_Frag.get(0).childNode(1).childNode(0).hasAttr("h1"));
    }

    @Test
    public void fragmentHTMLWithOpenTagsAndNullContextAndValidMissingDomainBaseURI() {
        String fragmentHtml = "<h1 page</h1>";
        String baseURI = "https://www/";
        Element context = null;

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <h1 page< h1></h1>\n" +
                " </body>\n" +
                "</html>");

        assertTrue(doc_Frag.get(0).childNode(1).childNode(0).hasAttr("h1"));
    }

    @Test
    public void fragmentHTMLWithOpenTagsAndNullContextAndValidWrongFormatBaseURI() {
        String fragmentHtml = "<h1 page</h1>";
        String baseURI = "https://www.googlecom/";
        Element context = null;

        List<Node> doc_Frag = parseFragment(fragmentHtml, context, baseURI);

        assertEquals(doc_Frag.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <h1 page< h1></h1>\n" +
                " </body>\n" +
                "</html>");

        assertTrue(doc_Frag.get(0).childNode(1).childNode(0).hasAttr("h1"));
    }

    @Test
    public void fragmentHTMLWithInvalidMismatchedTagsAndNullBaseURI() {
        String fragmentHtml = "<dir><text>hi guys</dir>";
        Element context = null;
        String baseURI = null;
        try {
            parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void fragmentHTMLWithInvalidMismatchedTagsAndEmptyBaseURI() {
        String fragmentHtml = "<dir><text>hi guys</dir>";
        Element context = null;
        String baseURI = "";
        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithInvalidMismatchedTagsAndValidExistingBaseURI() {
        String baseURI = "https://www.google.com/";
        String fragmentHtml = "<dir><text>hi guys</dir>";
        Element context = null;
        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithInvalidMismatchedTagsAndValidNonExistingBaseURI() {
        String baseURI = "https://www.googleeeeeee.com/";
        String fragmentHtml = "<dir><text>hi guys</dir>";
        Element context = null;
        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithInvalidMismatchedTagsAndMissingProtocolBaseURI() {
        String baseURI = "www.google.com/";
        String fragmentHtml = "<dir><text>hi guys</dir>";

        Document doc = Parser.parse("<div class=\"child1\">", baseURI);
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<dir>\n" +
                " <text>\n" +
                "  hi guys\n" +
                " </text>\n" +
                "</dir>");
    }

    @Test
    public void fragmentHTMLWithInvalidMismatchedTagsAndMissingDomainBaseURI() {
        String baseURI = "https://www/";
        String fragmentHtml = "<dir><text>hi guys</dir>";

        Document doc = Parser.parse("<div class=\"child1\">", baseURI);
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<dir>\n" +
                " <text>\n" +
                "  hi guys\n" +
                " </text>\n" +
                "</dir>");
    }

    @Test
    public void fragmentHTMLWithInvalidMismatchedTagsAndWrongFormatBaseURI() {
        String baseURI = "https://www.googlecom/";
        String fragmentHtml = "<dir><text>hi guys</dir>";

        Document doc = Parser.parse("<div class=\"child1\">", baseURI);
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<dir>\n" +
                " <text>\n" +
                "  hi guys\n" +
                " </text>\n" +
                "</dir>");

    }

    @Test
    public void fragmentHTMLWithValidMatchedTagsAndNullBaseURI() {
        String baseURI = null;
        String fragmentHtml = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        try {
            Parser.parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void fragmentHTMLWithValidMatchedTagsAndEmptyBaseURI() {
        String baseURI = "";
        String fragmentHtml = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<dir> \n" +
                " <text>\n" +
                "  hi guys\n" +
                " </text> \n" +
                "</dir>");

    }

    @Test
    public void fragmentHTMLWithValidMatchedTagsAndValidExistingBaseURI() {
        String baseURI = "https://www.google.com/";
        String fragmentHtml = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<dir> \n" +
                " <text>\n" +
                "  hi guys\n" +
                " </text> \n" +
                "</dir>");
    }

    @Test
    public void fragmentHTMLWithValidMatchedTagsAndValidNonExistingBaseURI() {
        String baseURI = "https://www.googleeeeeee.com/ ";
        String fragmentHtml = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<dir> \n" +
                " <text>\n" +
                "  hi guys\n" +
                " </text> \n" +
                "</dir>");
    }

    @Test
    public void fragmentHTMLWithValidMatchedTagsAndMissingProtocolBaseURI() {
        String baseURI = "www.google.com/";
        String fragmentHtml = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";
        Element context = null;

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <dir> \n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text> \n" +
                "  </dir> \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithValidMatchedTagsAndMissingDomainBaseURI() {
        String baseURI = "https://www/";
        String fragmentHtml = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";
        Element context = null;

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <dir> \n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text> \n" +
                "  </dir> \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithValidMatchedTagsAndWrongFormatBaseURI() {
        String baseURI = "https://www.googlecom/";
        String fragmentHtml = "<dir>\n" +
                "<text>hi guys</text>\n" +
                "</dir>\n";
        Element context = null;

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <dir> \n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text> \n" +
                "  </dir> \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithValidMismatchedTagsAndNullBaseURI() {
        String fragmentHtml = "<p>text<br>text3</p>";
        Element context = null;
        String baseURI = null;

        try {
            Parser.parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void fragmentHTMLWithValidMismatchedTagsAndEmptyBaseURI() {
        String baseURI = "";
        String fragmentHtml = "<p>text<br>text3</p>";
        Element context = null;

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <p>text<br>text3</p>\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithValidMismatchedTagsAndValidExistingBaseURI() {
        String baseURI = "https://www.google.com/";
        String fragmentHtml = "<p>text<br>text3</p>";
        Element context = null;

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <p>text<br>text3</p>\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithValidMismatchedTagsAndValidNonExistingBaseURI() {
        String baseURI = "https://www.googleeeeeee.com/";
        String fragmentHtml = "<p>text<br>text3</p>";
        Element context = null;

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <p>text<br>text3</p>\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithValidMismatchedTagsAndMissingProtocolBaseURI() {
        String baseURI = "www.google.com/";
        String fragmentHtml = "<p>text<br>text3</p>";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<p>text<br>text3</p>");
    }

    @Test
    public void fragmentHTMLWithValidMismatchedTagsAndMissingDomainBaseURI() {
        String baseURI = "https://www/";
        String fragmentHtml = "<p>text<br>text3</p>";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<p>text<br>text3</p>");
    }

    @Test
    public void fragmentHTMLWithValidMismatchedTagsAndWrongFormatBaseURI() {
        String baseURI = "https://www.googlecom/";
        String fragmentHtml = "<p>text<br>text3</p>";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<p>text<br>text3</p>");
    }

    @Test
    public void fragmentHTMLWithInValidTagNamesAndNullBaseURI() {
        String fragmentHtml = "<p>text<br>text3</p>";
        String baseURI = null;

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        try {
            Parser.parseFragment(fragmentHtml, context, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void fragmentHTMLWithInValidTagNamesAndEmptyBaseURI() {
        String baseURI = "";
        String fragmentHtml = "<pipi>text</pipi>";


        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<pipi>\n" +
                " text\n" +
                "</pipi>");
    }

    @Test
    public void fragmentHTMLWithInValidTagNamesAndValidExistingBaseURI() {
        String baseURI = "https://www.google.com/";
        String fragmentHtml = "<pipi>text</pipi>";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<pipi>\n" +
                " text\n" +
                "</pipi>");
    }

    @Test
    public void fragmentHTMLWithInValidTagNamesAndValidNonExistingBaseURI() {
        String baseURI = "https://www.googleeeeeee.com/";
        String fragmentHtml = "<pipi>text</pipi>";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<pipi>\n" +
                " text\n" +
                "</pipi>");
    }

    @Test
    public void fragmentHTMLWithInValidTagNamesAndMissingProtocolBaseURI() {
        String baseURI = "www.google.com/";
        String fragmentHtml = "<pipi>text</pipi>";
        Element context = null;

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <pipi>\n" +
                "   text\n" +
                "  </pipi>\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithInValidTagNamesAndMissingDomainBaseURI() {
        String baseURI = "https://www/";
        String fragmentHtml = "<pipi>text</pipi>";
        Element context = null;


        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <pipi>\n" +
                "   text\n" +
                "  </pipi>\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void fragmentHTMLWithInValidTagNamesAndWrongFormatBaseURI() {
        String baseURI = "https://www.googlecom/";
        String fragmentHtml = "<pipi>text</pipi>";
        Element context = null;


        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <pipi>\n" +
                "   text\n" +
                "  </pipi>\n" +
                " </body>\n" +
                "</html>");
    }

}
