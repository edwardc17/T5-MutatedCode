package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.jsoup.parser.ParseSettings.preserveCase;
import static org.junit.Assert.*;

public class WhiteBoxParseTest {


    @Test
    public void canParseInBodyEndAdoptionFormatters() {
        String html = "<big> test </big>";
        Document doc = Jsoup.parse(html);
        assertEquals("big", doc.selectFirst("big").tagName());
    }

    @Test
    public void canParseFragmentAfterReparent() {
        String html = "<big> test </big><big> test1 </big><big> test2 </big><big> test3 </big>";
        Document doc = Jsoup.parseBodyFragment(html);
        assertEquals(4, doc.select("big").size());
    }


    @Test(expected = NullPointerException.class)
    public void cannotParseInBodyEndAdoptionFormattersInsideTable() {
        String html = "<table> <big> test </big> </table>";
        Document doc = Jsoup.parseBodyFragment(html);
        doc.selectFirst("table").selectFirst("big").text();
    }

    @Test
    public void canParseRowInsideTable() {
        String html = "<table> <tr> test </tr> </table>";
        Document doc = Jsoup.parseBodyFragment(html);
        assertEquals("test", doc.selectFirst("table").selectFirst("tr").text());
    }

    @Test
    public void canParseCellsInsideTables() {
        String html = "<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <th>Firstname</th>\n" +
                "    <th>Lastname</th>\n" +
                "    <th>Age</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Jill</td>\n" +
                "    <td>Smith</td>\n" +
                "    <td>50</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Eve</td>\n" +
                "    <td>Jackson</td>\n" +
                "    <td>94</td>\n" +
                "  </tr>\n" +
                "</table>";
        Document doc = Jsoup.parseBodyFragment(html);
        assertEquals(3, doc.select("tr").size());
        assertEquals(3, doc.selectFirst("tr").select("th").size());
    }

    @Test
    public void canEnableTrackErrorsStatus() {
        HtmlTreeBuilder treeBuilder = new HtmlTreeBuilder();
        Parser parser = new Parser(treeBuilder);
        parser.setTrackErrors(15);
        assertTrue(parser.isTrackErrors());
    }

    @Test
    public void canParseXMLFragment() {
        List<Node> nodes = Parser.parseXmlFragment(
                "<note>\n" +
                        "<to>Tove</to>\n" +
                        "<from>Jani</from>\n" +
                        "<heading>Reminder</heading>\n" +
                        "<body>Don't forget me this weekend!</body>\n" +
                        "</note>", "");

        assertEquals(1, nodes.size());
        assertEquals("note", nodes.get(0).nodeName());
    }

    @Test
    public void canSetBuilderReturnParserForChaining() {
        HtmlTreeBuilder treeBuilder = new HtmlTreeBuilder();
        Parser parser = new Parser(treeBuilder);
        Parser newParser = parser.setTreeBuilder(treeBuilder);
        assertNotNull(newParser);
    }

    @Test
    public void canParserSetBaseURIDecidedByFirstBaseTagWithNonEmptyHref() {
        Document doc = Jsoup.parse("<base href=''> </base><base href='http://hanfa.me'> </base> <base href='http://google.com'> </base>");
        assertEquals("http://hanfa.me", doc.baseUri());
    }

    @Test
    public void canParseCommentInHTMLFile() {
        Document doc = Jsoup.parse("<!-- <a> hello </a> --><a>hello</a><!--Another comment -->");
        assertEquals(1, doc.select("a").size());
        assertEquals("<a>hello</a>", doc.selectFirst("a").toString());
        assertEquals("\n" + "<!-- <a> hello </a> -->", ((Comment) doc.childNodes().get(0)).toString());
        assertEquals("<!--Another comment -->", doc.selectFirst("body").childNodes().get(1).toString());
    }

    @Test
    public void canAutoInsertHeadAndBodyForEmptyHTML() {
        Document doc = Jsoup.parse("");
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canAutoInsertHeadAndBodyForBodyOnlyHTML() {
        Document doc = Jsoup.parse("<body> bb </body>");
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canAutoInsertHeadAndBodyForHeadOnlyHTML() {
        Document doc = Jsoup.parse("<head> bb </head>");
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canAutoInsertHeadAndBodyForHTMLOnlyHTML() {
        Document doc = Jsoup.parse("<html>  </html>");
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canParseTagAttribute() {
        Document doc = Jsoup.parse("<div class=\"container\"> </div> <a href=\"baidu.com\"> </a>");
        assertEquals("container", doc.selectFirst("div").attr("class"));
        assertEquals("", doc.selectFirst("div").attr("nonclass"));
        assertEquals("baidu.com", doc.selectFirst("a").attr("href"));
    }

    @Test
    public void canParseTagAttributeCaseSensitive() {
        Document doc = Jsoup.parse("<div foo=Foo bar=Bar Zoo=zoo yay=\"Yay\"> </div>");
        assertEquals("Foo", doc.selectFirst("div").attr("foo"));
        assertEquals("Bar", doc.selectFirst("div").attr("bar"));
        assertEquals("zoo", doc.selectFirst("div").attr("Zoo"));
        assertEquals("Yay", doc.selectFirst("div").attr("yay"));
    }

    @Test
    public void canParseDuplicationAttributes() {
        Document doc = Jsoup.parse("<div foo=Foo Foo=foo foo=foo/>");
        assertEquals("Foo", doc.selectFirst("div").attr("foo"));
        assertEquals("Foo", doc.selectFirst("div").attr("Foo"));
    }

    @Test
    public void canIgnoreIncompleteTag() {
        Document doc = Jsoup.parse("<div class=\"container\"");
        assertEquals("", doc.text());
    }

    @Test
    public void canParseIncompleteTabWithComment() {
        Document doc = Jsoup.parse("<div>test<!-- yooo </div> -->");
        assertEquals("test", doc.text());
        assertEquals(1, doc.select("div").size());
    }

    @Test
    public void canParseIncompleteAttribute() {
        Document doc = Jsoup.parse("<div href=\"baidu.com >");
        assertEquals("", doc.text());
    }

    @Test
    public void canParseUnterminatedTag() {
        Document doc = Jsoup.parse("<p>a<textarea>b<p>c");
        assertEquals(2, doc.select("p").size());
        assertEquals("ab", doc.select("p").get(0).text());
        assertEquals("c", doc.select("p").get(1).text());
        assertEquals(1, doc.select("textarea").size());
        assertEquals("b", doc.select("textarea").text());
    }

    @Test
    public void canParseATagWithRootHref() {
        Document doc = Jsoup.parse("<a href=/>Test</a>");
        assertEquals(1, doc.select("a").size());
        assertEquals("/", doc.selectFirst("a").attr("href"));
        assertEquals("Test", doc.selectFirst("a").text());
    }

    @Test
    public void canAutoInsertHeadAndHtmlForBodyOnlyHTMLWhenParseInput() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        Document doc = parser.parseInput("<body>  </body>", baseURI);
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canAutoInsertHtmlAndBodyForHeadOnlyHTMLWhenParseInput() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        Document doc = parser.parseInput("<head>  </head>", baseURI);
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canAutoInsertHeadAndBodyForHTMLOnlyHTMLWhenParseInput() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        Document doc = parser.parseInput("<html>  </html>", baseURI);
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canAutoInsertHtmlAndBodyForHeadOnlyHTMLWhenParseInputWithReader() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        String html = "<head>  </head>";
        Reader inputHtml = new StringReader(html);
        Document doc = parser.parseInput(inputHtml, baseURI);
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canAutoInsertHeadAndHtmlForBodyOnlyHTMLWhenParseInputWithReader() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        String html = "<body>  </body>";
        Reader inputHtml = new StringReader(html);
        Document doc = parser.parseInput(inputHtml, baseURI);
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canAutoInsertHeadAndBodyForHTMLOnlyHTMLWhenParseInputWithReader() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        String html = "<html>  </html>";
        Reader inputHtml = new StringReader(html);
        Document doc = parser.parseInput(inputHtml, baseURI);
        assertEquals(1, doc.select("html").size());
        assertEquals(1, doc.select("head").size());
        assertEquals(1, doc.select("body").size());
    }

    @Test
    public void canParseFragmentInputWithoutContextProvided() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String fragmentHTML = "<p id=\"myP\"></p>";
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        List<Node> nodes = parser.parseFragmentInput(
                fragmentHTML, null, baseURI);
        assertEquals(2, nodes.get(0).childNodeSize());
        assertEquals("head", nodes.get(0).childNode(0).nodeName());
        assertEquals("body", nodes.get(0).childNode(1).nodeName());
    }

    @Test
    public void canParseFragmentInputWithContextProvided() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        String fragmentHTML = "<p id=\"myP\"></p>";
        String baseURI = "www.google.com/";
        Parser parser = new Parser(builder);
        Document doc = Parser.parse(fragmentHTML, baseURI);
        Element context = doc.getElementById("myP");
        List<Node> nodes = parser.parseFragmentInput(
                fragmentHTML, context, baseURI);
        assertEquals(0, nodes.get(0).childNodeSize());
        assertEquals("p", nodes.get(0).nodeName());
    }

    @Test
    public void canGetTreeBuilder() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        Parser parser = new Parser(builder);
        assertEquals(builder, parser.getTreeBuilder());
    }
}
