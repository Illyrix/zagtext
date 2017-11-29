package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;

public class RedoAction extends Action {
    ZagText zagText;
    public RedoAction(ZagText text) {
        super("Redo@Ctrl+Y");
        zagText = text;
    }
    public void run () {
        zagText.getUndoManager().redo();
    }
}
