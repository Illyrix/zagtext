package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;

public class UndoAction extends Action {
    ZagText zagText;
    public UndoAction(ZagText text) {
        super("Undo@Ctrl+Z");
        zagText = text;
    }
    public void run () {
        zagText.getUndoManager().undo();
    }
}
