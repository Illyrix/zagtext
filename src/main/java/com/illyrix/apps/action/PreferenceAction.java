package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.action.Action;

public class PreferenceAction extends Action {
    ZagText zagText;
    public PreferenceAction (ZagText text) {
        super("Preference@Ctrl+P");
        zagText = text;
    }
    public void run () {
    }
}