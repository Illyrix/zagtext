package com.illyrix.apps.action;

import com.illyrix.apps.ZagText;
import com.illyrix.apps.dialog.EditorPreference;
import com.illyrix.apps.dialog.RemotePreference;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.*;

public class PreferenceAction extends Action {
    ZagText zagText;
    public PreferenceAction (ZagText text) {
        super("Preference@Ctrl+P");
        zagText = text;
    }
    public void run () {
        PreferenceManager mgr = new PreferenceManager();
        mgr.addToRoot(new PreferenceNode("editor", "editor", null, EditorPreference.class.getName()));
        mgr.addToRoot(new PreferenceNode("remote", "remote", null, RemotePreference.class.getName()));
        PreferenceDialog dialog = new PreferenceDialog(zagText.getShell(), mgr);
        dialog.setPreferenceStore(zagText.getPreference());
        dialog.open();
    }
}