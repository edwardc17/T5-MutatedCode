package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.Test;

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
        assertEquals(1, 1);
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


}
