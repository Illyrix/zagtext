package com.illyrix.apps.component;

import com.illyrix.apps.gui.Editor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;

public class FormatOperate implements Operate {
    StyledText text;

    public FormatOperate(Editor e) {
        text = e.text;
        wrapText.setAccelerator(SWT.CTRL + 'W');
    }

    // Action: set wrap property.
    Action wrapText = new Action("Wrap", IAction.AS_CHECK_BOX) {
        public void run() {
//            emit("wrap", isChecked());
            text.setWordWrap(isChecked());
        }
    };

    @Override
    public void add(MenuManager toolBar) {
        toolBar.add(wrapText);
    }
}
