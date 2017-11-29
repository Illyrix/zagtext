package com.illyrix.apps.dialog;

import org.eclipse.jface.preference.*;

public class RemotePreference extends FieldEditorPreferencePage {
    private StringFieldEditor host;
    private IntegerFieldEditor port;
    private StringFieldEditor name;
    private StringFieldEditor pass;

    public RemotePreference() {
        super(GRID);
    }

    protected void createFieldEditors() {
        host = new StringFieldEditor("Host", "Remote Host:", getFieldEditorParent());
        addField(host);
        port = new IntegerFieldEditor("Port", "Remote Port:", getFieldEditorParent());
        addField(port);
        name = new StringFieldEditor("Name", "User Name:", getFieldEditorParent());
        addField(name);
        pass = new StringFieldEditor("Pass", "Password:", getFieldEditorParent());
        addField(pass);
    }
}