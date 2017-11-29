package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.MessageBox;

public class LoadRemoteAction extends Action {
    ZagText zagText;
    public LoadRemoteAction(ZagText text) {
        super("Load Remote");
        zagText = text;
    }
    public void run () {
        try {
            zagText.getDocument().loadFromRemote();
            MessageBox msg = new MessageBox(zagText.getShell());
            msg.setText("Success");
            msg.setMessage("Text file has been load.");
            msg.open();
        } catch (Exception e) {
            MessageBox msg = new MessageBox(zagText.getShell());
            msg.setText("Error");
            msg.setMessage(e.toString());
            msg.open();
        }
    }
}