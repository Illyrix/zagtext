package com.illyrix.apps;

import org.eclipse.jface.text.rules.IWordDetector;
public class KeywordDetector implements IWordDetector {

    public boolean isWordStart(char c) {

        for (int i = 0, n = Constants.JS_SYNTAX_KEYWORD.length; i < n; i++)
            if (c == ((String) Constants.JS_SYNTAX_KEYWORD[i]).charAt(0))
                return true;
        return false;
    }

    public boolean isWordPart(char c) {
        for (int i = 0, n = Constants.JS_SYNTAX_KEYWORD.length; i < n; i++)
            if (((String) Constants.JS_SYNTAX_KEYWORD[i]).indexOf(c) != -1)
                return true;
        return false;
    }
}