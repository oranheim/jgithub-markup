package ruby;

import org.junit.Test;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static org.junit.Assert.assertNotNull;

/**
 * Created by oranheim on 05/02/2017.
 */
public class RubyTest {

    @Test
    public void testRuby() throws Exception {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine rubyEngine = scriptEngineManager.getEngineByName("jruby");
        assertNotNull(rubyEngine);
        ScriptContext context = rubyEngine.getContext();

//        File file = new File("/Users/oranheim/IdeaProjects/jgithub-markup/README.md");
        String file = "/Users/oranheim/IdeaProjects/jgithub-markup/README.md";
        context.setAttribute("file", file, ScriptContext.ENGINE_SCOPE);

        rubyEngine.eval("require 'rubygems'");
        rubyEngine.eval("require 'github/markup'");
        rubyEngine.eval("GitHub::Markup.render($file, File.read($file))", context);
    }

}
