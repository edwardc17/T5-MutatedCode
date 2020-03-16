package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BlackBoxParseC1Test {
    @Test
    public void HTMLNullUriNullTest() {
        String html = null;
        String uri = null;
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void HTMLNullUriEmptyTest() {
        String html = null;
        String uri = "";
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void HTMLNullWithValidExistedUriTest() {
        String html = null;
        String uri = "https://www.google.com/";
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void HTMLNulllWithValidNonExistedUriEmptyTest() {
        String html = null;
        String uri = "https://www.googleeeeeee.com/";
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void HTMLNullWithInvalidMissingProtocolUriTest() {
        String html = null;
        String uri = "www.google.com/";
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void HTMLNulllWithInvalidMissingDomainUriEmptyTest() {
        String html = null;
        String uri = "https://www/";
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void HTMLNullWithInvalidWrongFormatUriTest() {
        String html = null;
        String uri = "www.googlecom/";
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void HTMLEmptyUriNullTest() {
        String html = "";
        String uri = null;
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void HTMLEmptyUriEmptyTest() {
        String html = "";
        String uri = "";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptyWithValidExistedUriTest() {
        String html = "";
        String uri = "https://www.google.com/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptyWithValidNonExistedUriEmptyTest() {
        String html = "";
        String uri = "https://www.googleeeeeee.com/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptyWithInvalidMissingProtocolUriTest() {
        String html = "";
        String uri = "www.google.com/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptylWithInvalidMissingDomainUriEmptyTest() {
        String html = "";
        String uri = "https://www/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptyWithInvalidWrongFormatUriTest() {
        String html = "";
        String uri = "www.googlecom/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLIsPlainTextUriNullTest() {
        String html = "text";
        String uri = null;
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void HTMLIsPlainTextUriEmptyTest() {
        String html = "text";
        String uri = "";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  text\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLIsPlainTextWithValidExistedUriTest() {
        String html = "text";
        String uri = "https://www.google.com/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  text\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLIsPlainTextWithValidNonExistedUriEmptyTest() {
        String html = "text";
        String uri = "https://www.googleeeeeee.com/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  text\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLIsPlainTextWithInvalidMissingProtocolUriTest() {
        String html = "text";
        String uri = "www.google.com/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  text\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLIsPlainTextlWithInvalidMissingDomainUriEmptyTest() {
        String html = "text";
        String uri = "https://www/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  text\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLIsPlainTextWithInvalidWrongFormatUriTest() {
        String html = "text";
        String uri = "www.googlecom/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  text\n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithOpenTagsUriNullTest() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</head>\n" +
                "</html>\n";
        String uri = null;
        try {
            Parser.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void HTMLWithOpenTagsUriEmptyTest() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</head>\n" +
                "</html>\n";
        String uri = "";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\">\n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>    \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithOpentagsAndValidExistedUriTest() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</head>\n" +
                "</html>\n";
        String uri = "https://www.google.com/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\">\n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>    \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithOpenTagsAndValidNonExistedUriEmptyTest() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</head>\n" +
                "</html>\n";
        String uri = "https://www.googleeeeeee.com/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\">\n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>    \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithOpenTagsAndInvalidMissingProtocolUriTest() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</head>\n" +
                "</html>\n";
        String uri = "www.google.com/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\">\n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>    \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithOpenTagsAndInvalidMissingDomainUriEmptyTest() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</head>\n" +
                "</html>\n";
        String uri = "https://www/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\">\n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>    \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithOpenTagsAndInvalidWrongFormatUriTest() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</head>\n" +
                "</html>\n";
        String uri = "www.googlecom/";
        Document doc = Parser.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\">\n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>    \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithInvalidMismatchedTagsAndNullBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = null;

        try {
            Parser.parse(html, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void HTMLWithInvalidMismatchedTagsAndEmptyBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "";
        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithInvalidMismatchedTagsAndValidExistingBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.google.com/";
        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithInvalidMismatchedTagsAndValidNonExistingBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.googleeeeeee.com/";
        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithInvalidMismatchedTagsAndMissingProtocolBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "www.google.com/";
        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithInvalidMismatchedTagsAndMissingDomainBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www/";
        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithInvalidMismatchedTagsAndWrongFormatBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.googlecom/";
        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head>\n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMatchedTagsAndNullBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = null;

        try {
            Parser.parse(html, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void HTMLWithValidMatchedTagsAndEmptyBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMatchedTagsAndValidExistingBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.google.com/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMatchedTagsAndValidNonExistingBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.googleeeeeee.com/ ";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMatchedTagsAndMissingProtocolBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "www.google.com/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMatchedTagsAndMissingDomainBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMatchedTagsAndWrongFormatBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>page1</h1>\n" +
                "<dir><text>hi guys</text></dir>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.googlecom/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <h1>page1</h1> <dir>\n" +
                "   <text>\n" +
                "    hi guys\n" +
                "   </text>\n" +
                "  </dir>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMismatchedTagsAndNullBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>text<br>text3</p>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = null;

        try {
            Parser.parse(html, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void HTMLWithValidMismatchedTagsAndEmptyBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>text<br>text3</p>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <p>text<br>text3</p>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMismatchedTagsAndValidExistingBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>text<br>text3</p>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.google.com/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <p>text<br>text3</p>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMismatchedTagsAndValidNonExistingBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>text<br>text3</p>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.googleeeeeee.com/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <p>text<br>text3</p>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMismatchedTagsAndMissingProtocolBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>text<br>text3</p>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "www.google.com/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <p>text<br>text3</p>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMismatchedTagsAndMissingDomainBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>text<br>text3</p>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <p>text<br>text3</p>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithValidMismatchedTagsAndWrongFormatBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<p>text<br>text3</p>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.googlecom/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <p>text<br>text3</p>   \n" +
                " </body>\n" +
                "</html>");
    }

    @Test
    public void HTMLWithInValidTagNamesAndNullBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<pipi>text</pipi>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = null;

        try {
            Parser.parse(html, baseURI);
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }

    @Test
    public void HTMLWithInValidTagNamesAndEmptyBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<pipi>text</pipi>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> <pipi>\n" +
                "   text\n" +
                "  </pipi>   \n" +
                " </body>\n" +
                "</html>");
        Element pipi = doc.body().select("pipi").first();
        assertNotNull(pipi);
    }

    @Test
    public void HTMLWithInValidTagNamesAndValidExistingBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<pipi>text</pipi>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.google.com/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> <pipi>\n" +
                "   text\n" +
                "  </pipi>   \n" +
                " </body>\n" +
                "</html>");
        Element pipi = doc.body().select("pipi").first();
        assertNotNull(pipi);
    }

    @Test
    public void HTMLWithInValidTagNamesAndValidNonExistingBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<pipi>text</pipi>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.googleeeeeee.com/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> <pipi>\n" +
                "   text\n" +
                "  </pipi>   \n" +
                " </body>\n" +
                "</html>");
        Element pipi = doc.body().select("pipi").first();
        assertNotNull(pipi);
    }

    @Test
    public void HTMLWithInValidTagNamesAndMissingProtocolBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<pipi>text</pipi>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "www.google.com/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> <pipi>\n" +
                "   text\n" +
                "  </pipi>   \n" +
                " </body>\n" +
                "</html>");
        Element pipi = doc.body().select("pipi").first();
        assertNotNull(pipi);
    }

    @Test
    public void HTMLWithInValidTagNamesAndMissingDomainBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<pipi>text</pipi>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> <pipi>\n" +
                "   text\n" +
                "  </pipi>   \n" +
                " </body>\n" +
                "</html>");
        Element pipi = doc.body().select("pipi").first();
        assertNotNull(pipi);
    }

    @Test
    public void HTMLWithInValidTagNamesAndWrongFormatBaseURI() {
        String html = "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<pipi>text</pipi>\n" +
                "</body>\n" +
                "</html>\n";

        String baseURI = "https://www.googlecom/";

        Document doc = Parser.parse(html, baseURI);

        assertEquals(doc.html(), "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\"> \n" +
                " </head> \n" +
                " <body> <pipi>\n" +
                "   text\n" +
                "  </pipi>   \n" +
                " </body>\n" +
                "</html>");
        Element pipi = doc.body().select("pipi").first();
        assertNotNull(pipi);
    }
}
