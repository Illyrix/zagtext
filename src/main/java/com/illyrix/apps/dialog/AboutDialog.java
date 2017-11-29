package com.illyrix.apps.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class AboutDialog extends Dialog {
    public AboutDialog (Shell parent) {
        super(parent);
    }

    protected Control createContents (Composite parent) {
        this.getShell().setSize(200, 150);
        this.getShell().setText("About Us");
        parent.setLayout(new GridLayout());
        return parent;
    }
}
