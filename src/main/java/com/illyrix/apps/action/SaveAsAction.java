package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;

import java.io.IOException;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.FileDialog;

import java.io.IOException;

public class SaveAsAction extends Action {
    ZagText zagText;
    public SaveAsAction(ZagText text) {
        super("Save As@Ctrl+Alt+S");
        zagText = text;
    }
    public void run () {
        try {
            FileDialog dlg = new FileDialog(zagText.getShell());
            if (zagText.getDocument().getFileName() != null) {
                dlg.setFileName(zagText.getDocument().getFileName());
            }
            String temp = dlg.open();
            if (temp != null) {
                zagText.getDocument().setFileName(temp);
                zagText.getDocument().save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}