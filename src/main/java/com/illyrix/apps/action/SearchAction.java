package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import com.illyrix.apps.dialog.FindAndReplace;
import org.eclipse.jface.action.Action;

public class SearchAction extends Action {
    ZagText zagText;
    public SearchAction (ZagText text) {
        super("Search@Ctrl+F");
        zagText = text;
    }
    public void run () {
        new FindAndReplace(zagText, zagText.getShell()).open();
    }
}