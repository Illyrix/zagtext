package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;
import java.io.IOException;

public class SaveAction extends Action {
    ZagText zagText;
    public SaveAction(ZagText text) {
        super("Save@Ctrl+S");
        zagText = text;
    }
    public void run () {
        if (!zagText.getDocument().isDirty()) return;
        try {
            zagText.getDocument().save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
