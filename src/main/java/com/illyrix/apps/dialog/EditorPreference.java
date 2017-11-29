package com.illyrix.apps.dialog;

import org.eclipse.jface.preference.*;

public class EditorPreference extends FieldEditorPreferencePage {
    private ColorFieldEditor bgColor;
    private FontFieldEditor codeFont;

    public EditorPreference() {
        super(GRID);
    }

    protected void createFieldEditors() {
        bgColor = new ColorFieldEditor("Background", "Background Color:", getFieldEditorParent());
        addField(bgColor);
        codeFont = new FontFieldEditor("Font", "Font:", getFieldEditorParent());
        addField(codeFont);
    }
}
