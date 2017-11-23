package com.illyrix.apps.component;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Shell;

public class ControlOperate implements Operate{
    Shell shell;
    Action exit = new Action("Exit") {
        public void run() {
            shell.dispose();
        }
    };
    public ControlOperate(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void add(MenuManager toolBar) {
        toolBar.add(exit);
    }
}
