package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.FileDialog;

import java.io.IOException;

public class SaveRemoteAction extends Action {
    ZagText zagText;
    public SaveRemoteAction(ZagText text) {
        super("Save@Ctrl+S");
        zagText = text;
    }
    public void run () {
        if (!zagText.getDocument().isDirty()) return;
        if (zagText.getDocument().getFileName() == null) {
            FileDialog dlg = new FileDialog(zagText.getShell());
            if (zagText.getDocument().getFileName() != null) {
                dlg.setFileName(zagText.getDocument().getFileName());
            }
            String temp = dlg.open();
            if (temp != null) {
                zagText.getDocument().setFileName(temp);
            }
        }
        try {
            zagText.getDocument().save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}