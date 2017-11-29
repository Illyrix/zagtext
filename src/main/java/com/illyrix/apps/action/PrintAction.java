package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;

public class PrintAction extends Action {
    ZagText zagText;
    public PrintAction(ZagText text) {
        super("Print@Ctrl+P");
        zagText = text;
    }
    public void run () {
        zagText.getViewer().getTextWidget().print();
    }
}
