package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import com.illyrix.apps.dialog.AboutDialog;
import org.eclipse.jface.action.Action;

public class ExitAction extends Action {
    ZagText zagText;
    public ExitAction (ZagText text) {
        super("Exit");
        zagText = text;
    }
    public void run () {
        zagText.getShell().dispose();
    }
}
