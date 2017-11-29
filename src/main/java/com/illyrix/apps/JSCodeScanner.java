package com.illyrix.apps;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.*;
import org.eclipse.swt.SWT;

public class JSCodeScanner extends RuleBasedScanner {

    private TextAttribute keywords ;
    private TextAttribute string ;
    private TextAttribute object ;
    private TextAttribute comment ;
    public JSCodeScanner(){
        keywords = new TextAttribute (ResourceManager.getColor(Constants.COLOR_KEYWORD),null,SWT.BOLD);
        string = new TextAttribute (ResourceManager.getColor(Constants.COLOR_STRING));
        object = new TextAttribute (ResourceManager.getColor(Constants.COLOR_OBJECT));
        comment = new TextAttribute (ResourceManager.getColor(Constants.COLOR_COMMENT),null,SWT.ITALIC);
        setupRules();
    }
    private void setupRules() {
        List rules = new ArrayList();
        rules.add(new SingleLineRule("\"", "\"",new Token( string), '\\'));
        rules.add(new SingleLineRule("'", "'", new Token( string), '\\'));
        rules.add(new SingleLineRule("/*", "*/", new Token( comment), '\\'));
        rules.add(new EndOfLineRule("//", new Token( comment),'\\'));
                rules.add(new WhitespaceRule(new IWhitespaceDetector() {
                    public boolean isWhitespace(char c) {
                        return Character.isWhitespace(c);
                    }
                }));
        WordRule keywordRule = new WordRule(new KeywordDetector());
        for (int i = 0, n = Constants.JS_SYNTAX_KEYWORD.length; i < n; i++)
            keywordRule.addWord(Constants.JS_SYNTAX_KEYWORD[i], new Token( keywords ));
        rules.add(keywordRule);
        WordRule objectRule = new WordRule(new ObjectDetector());
        for (int i = 0, n = Constants.JS_SYNTAX_BUILDIB_OBJECT.length; i < n; i++)
            objectRule.addWord(Constants.JS_SYNTAX_BUILDIB_OBJECT[i], new Token( object ));
        rules.add(objectRule);
        IRule[] result = new IRule[rules.size()];
        rules.toArray(result);
        setRules(result);
    }
}