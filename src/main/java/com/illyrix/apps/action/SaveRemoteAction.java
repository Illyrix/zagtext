package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.MessageBox;

import java.io.IOException;

public class SaveRemoteAction extends Action {
    ZagText zagText;
    public SaveRemoteAction(ZagText text) {
        super("Save Remote");
        zagText = text;
    }
    public void run () {
        try {
            zagText.getDocument().saveToRemote();
            MessageBox msg = new MessageBox(zagText.getShell());
            msg.setText("Success");
            msg.setMessage("Text file has been saved.");
            msg.open();
        } catch (Exception e) {
            MessageBox msg = new MessageBox(zagText.getShell());
            msg.setText("Error");
            msg.setMessage(e.toString());
            msg.open();
        }
    }
}