package com.illyrix.apps.dialog;

import com.illyrix.apps.ZagText;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IRegion;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import java.util.regex.PatternSyntaxException;

public class FindAndReplace extends Dialog{
    private ZagText zagText;
    private Button btnFind;
    private Button btnReplace;
    private Button btnReplaceAll;

    private FindReplaceDocumentAdapter findAdapter;

    public FindAndReplace (ZagText z, Shell sh) {
        super(sh);
        zagText = z;
        findAdapter = new FindReplaceDocumentAdapter(z.getDocument());
    }

    protected void configureShell (Shell sh) {
        super.configureShell(sh);
        sh.setText("Find/Replace");
        sh.setSize(220, 280);
    }

    protected Control createContents(Composite parent) {
        parent.setLayout(new GridLayout(2, false));

        new Label(parent, SWT.LEFT).setText("Find:");
        final Text findText = new Text(parent, SWT.BORDER);
        findText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        new Label(parent, SWT.LEFT).setText("Replace:");
        final Text replaceText = new Text(parent, SWT.BORDER);
        replaceText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Group group = new Group(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 2;
        group.setLayoutData(data);
        group.setText("Direction");
        group.setLayout(new GridLayout(2,true));

        final Button forward = new Button(group, SWT.RADIO);
        forward.setText("Forward");
        final Button back = new Button(group, SWT.RADIO);
        back.setText("Back");

        group = new Group(parent, SWT.NONE);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 2;
        group.setLayoutData(data);
        group.setText("Options");
        group.setLayout(new GridLayout(2,true));

        final Button matchCase = new Button(group, SWT.CHECK);
        matchCase.setText("Case Sensitive");

        final Button regexp = new Button(group, SWT.CHECK);
        regexp.setText("RegExp");

        Composite composite = new Composite(parent, SWT.NONE);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = 2;
        composite.setLayoutData(data);
        composite.setLayout(new GridLayout(2, true));

        btnFind = new Button(composite, SWT.PUSH);
        btnFind.setText("Find");
        btnFind.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        btnReplace = new Button(composite, SWT.PUSH);
        btnReplace.setText("Replace");
        btnReplace.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        btnReplaceAll = new Button(composite, SWT.PUSH);
        btnReplaceAll.setText("Replace All");
        btnReplaceAll.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        btnFind.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                isFind(findAdapter, findText.getText(), forward.getSelection(), matchCase.getSelection(), regexp.getSelection());
            }
        });

        btnReplace.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                replace(findAdapter, findText.getText(), forward.getSelection(), matchCase.getSelection(), regexp.getSelection(), replaceText.getText());
            }
        });

        btnReplaceAll.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                while (replace(findAdapter, findText.getText(), forward.getSelection(), matchCase.getSelection(), regexp.getSelection(), replaceText.getText())) {}
            }
        });

        forward.setSelection(true);
        findText.setFocus();
        return parent;
    }

    public boolean isFind (FindReplaceDocumentAdapter adapter,
                           String find,
                           boolean forward,
                           boolean matchCase,
                           boolean regexp) {
        boolean bFind = false;
        IRegion region = null;
        try {
            int offset = zagText.getViewer().getTextWidget().getCaretOffset();

            if (!forward) {
                Point pt = zagText.getViewer().getSelectedRange();
                if (pt.x != pt.y)  offset = pt.x - 1;
            }
            if (offset >= adapter.length()) offset = adapter.length() - 1;
            if (offset < 0) offset = 0;

            region = adapter.find(offset, find, forward, matchCase, false, regexp);
            if (region != null) {
                zagText.getViewer().setSelectedRange(region.getOffset(), region.getLength());
                bFind = true;
            }
        } catch (BadLocationException e) {
            return false;
        } catch (PatternSyntaxException e) {
            return false;
        }
        return bFind;
    }

    public boolean replace(FindReplaceDocumentAdapter adapter,
                        String find,
                        boolean forward,
                        boolean matchCase,
                        boolean regexp,
                        String replaced) {
        boolean b = isFind(adapter, find, forward, matchCase, regexp);
        if (b) {
            try {
                adapter.replace(replaced, false);
            } catch (BadLocationException e) {
                return false;
            }
        }
        return b;
    }
}
