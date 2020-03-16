package org.jsoup.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.jsoup.parser.Parser.parseFragment;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
}
