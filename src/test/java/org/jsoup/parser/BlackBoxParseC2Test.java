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
    public void FragmentHTMLWithInvalidMismatchedTagsAndNullBaseURI() {
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
    public void HTMLWithInvalidMismatchedTagsAndEmptyBaseURI() {
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
    public void HTMLWithInvalidMismatchedTagsAndValidExistingBaseURI() {
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
    public void HTMLWithInvalidMismatchedTagsAndValidNonExistingBaseURI() {
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
    public void HTMLWithInvalidMismatchedTagsAndMissingProtocolBaseURI() {
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
    public void HTMLWithInvalidMismatchedTagsAndMissingDomainBaseURI() {
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
    public void HTMLWithInvalidMismatchedTagsAndWrongFormatBaseURI() {
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
    public void HTMLWithValidMatchedTagsAndNullBaseURI() {
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
    public void HTMLWithValidMatchedTagsAndEmptyBaseURI() {
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
    public void HTMLWithValidMatchedTagsAndValidExistingBaseURI() {
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
    public void HTMLWithValidMatchedTagsAndValidNonExistingBaseURI() {
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
    public void HTMLWithValidMatchedTagsAndMissingProtocolBaseURI() {
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
    public void HTMLWithValidMatchedTagsAndMissingDomainBaseURI() {
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
    public void HTMLWithValidMatchedTagsAndWrongFormatBaseURI() {
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
    public void HTMLWithValidMismatchedTagsAndNullBaseURI() {
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
    public void HTMLWithValidMismatchedTagsAndEmptyBaseURI() {
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
    public void HTMLWithValidMismatchedTagsAndValidExistingBaseURI() {
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
    public void HTMLWithValidMismatchedTagsAndValidNonExistingBaseURI() {
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
    public void HTMLWithValidMismatchedTagsAndMissingProtocolBaseURI() {
        String baseURI = "www.google.com/";
        String fragmentHtml = "<p>text<br>text3</p>";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<p>text<br>text3</p>");
    }

    @Test
    public void HTMLWithValidMismatchedTagsAndMissingDomainBaseURI() {
        String baseURI = "https://www/";
        String fragmentHtml = "<p>text<br>text3</p>";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<p>text<br>text3</p>");
    }

    @Test
    public void HTMLWithValidMismatchedTagsAndWrongFormatBaseURI() {
        String baseURI = "https://www.googlecom/";
        String fragmentHtml = "<p>text<br>text3</p>";

        Document doc = Parser.parse("<div class=\"child1\">", "");
        Element context = doc.select("div.child1").first();

        List<Node> result = Parser.parseFragment(fragmentHtml, context, baseURI);

        assertEquals(result.get(0).toString(), "<p>text<br>text3</p>");
    }

}
