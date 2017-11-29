package com.illyrix.apps;

import org.eclipse.swt.widgets.Display;

import java.io.IOException;

public class JSEditorTestDrive {
    public static void main(String[] args) {
        JSEditor jsApp = new JSEditor();
        jsApp.setPreference(ResourceManager.getPreferenceStore() );
        ResourceManager.getPreferenceStore().addPropertyChangeListener(jsApp);
        jsApp.setBlockOnOpen(true);
        jsApp.open();
        Display.getCurrent().dispose();
        try {
            ResourceManager.getPreferenceStore().save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}