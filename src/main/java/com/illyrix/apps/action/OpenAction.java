package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import java.io.IOException;

public class OpenAction extends Action {
    ZagText zagText;
    public OpenAction(ZagText text) {
        super("Open@Ctrl+O");
        zagText = text;
    }
    public void run () {
        FileDialog dialog = new FileDialog(zagText.getShell(), SWT.OPEN);
//        dialog.setFilterExtensions("");
        String name = dialog.open();
        if (name == null || name.length() == 0) {
            return;
        }
        try {
            zagText.getDocument().setFileName(name);
            zagText.getDocument().open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
