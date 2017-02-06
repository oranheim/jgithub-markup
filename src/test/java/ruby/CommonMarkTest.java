package ruby;

import com.github.kevinsawicki.http.HttpRequest;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oranheim on 06/02/2017.
 */
public class CommonMarkTest {

    // Use highlight.js to emphasize code: https://highlightjs.org/
    @Test
    public void testMe() throws Exception {
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder() .extensions(extensions).build();
        HttpRequest req = HttpRequest.get("https://raw.githubusercontent.com/descoped/descoped-server/master/README.md");
        String body = req.body();
        Node document = parser.parse(body);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String r = renderer.render(document);
//        r = StringEscapeUtils.unescapeHtml(r);
        StringBuffer buf = new StringBuffer();
        buf.append("<html>")
                .append("<head>")
                .append("<style>")
                .append("pre code {display: block; width: 500px; background-color: silver;}\n")
                .append(".language-xml {background-color: silver;\n")
                .append("</style>")
//                .append("<link crossorigin=\"anonymous\" href=\"https://assets-cdn.github.com/assets/frameworks-298818692f75de57d67115ca5a0c1f983d1d5ad302774216c297495f46f0a3da.css\" integrity=\"sha256-KYgYaS913lfWcRXKWgwfmD0dWtMCd0IWwpdJX0bwo9o=\" media=\"all\" rel=\"stylesheet\" />")
//                .append("<link crossorigin=\"anonymous\" href=\"https://assets-cdn.github.com/assets/github-d74d8d2476aa9341da3e77c2f74c15777c246ede555edebd433a1d0a68935dd6.css\" integrity=\"sha256-102NJHaqk0HaPnfC90wVd3wkbt5VXt69QzodCmiTXdY=\" media=\"all\" rel=\"stylesheet\" />")
                .append("</head>")
                .append("<body>").append(r).append("</body></html>");
        FileOutputStream fos = new FileOutputStream("/tmp/foo.html");
        fos.write(buf.toString().getBytes());
    }
}
