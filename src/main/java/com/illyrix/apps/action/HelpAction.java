package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import com.illyrix.apps.dialog.AboutDialog;
import org.eclipse.jface.action.Action;

public class HelpAction extends Action {
    ZagText zagText;
    public HelpAction (ZagText text) {
        super("Help@F1");
        zagText = text;
    }
    public void run () {
        AboutDialog dialog = new AboutDialog(zagText.getShell());
        dialog.open();
    }
}
