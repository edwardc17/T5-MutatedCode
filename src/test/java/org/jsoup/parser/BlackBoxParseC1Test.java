package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BlackBoxParseC1Test {
    @Test
    public void HTMLNullUriNullTest() {
        String html = null;
        String uri = null;
        try {
            Jsoup.parse(html, uri);
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
            Jsoup.parse(html, uri);
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
            Jsoup.parse(html, uri);
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
            Jsoup.parse(html, uri);
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
            Jsoup.parse(html, uri);
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
            Jsoup.parse(html, uri);
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
            Jsoup.parse(html, uri);
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
            Jsoup.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void HTMLEmptyUriEmptyTest() {
        String html = "";
        String uri = "";
        Document doc = Jsoup.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptyWithValidExistedUriTest() {
        String html = "";
        String uri = "https://www.google.com/";
        Document doc = Jsoup.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptyWithValidNonExistedUriEmptyTest() {
        String html = "";
        String uri = "https://www.googleeeeeee.com/";
        Document doc = Jsoup.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptyWithInvalidMissingProtocolUriTest() {
        String html = "";
        String uri = "www.google.com/";
        Document doc = Jsoup.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptylWithInvalidMissingDomainUriEmptyTest() {
        String html = "";
        String uri = "https://www/";
        Document doc = Jsoup.parse(html, uri);
        assertEquals(doc.html(), "<html>\n" +
                " <head></head>\n" +
                " <body></body>\n" +
                "</html>");
    }

    @Test
    public void HTMLEmptyWithInvalidWrongFormatUriTest() {
        String html = "";
        String uri = "www.googlecom/";
        Document doc = Jsoup.parse(html, uri);
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
            Jsoup.parse(html, uri);
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void HTMLIsPlainTextUriEmptyTest() {
        String html = "text";
        String uri = "";
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
            Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
        Document doc = Jsoup.parse(html, uri);
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
}
