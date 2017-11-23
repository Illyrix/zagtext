package com.illyrix.apps.gui;

import com.illyrix.apps.component.*;
import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.jface.action.*;
import org.eclipse.swt.widgets.Menu;

import java.awt.*;

public class Toolbar {
    Window window;
    MenuManager barMenuManager;

    Toolbar (final Window window) {
        this.window = window;
    }

    public void init () {
        barMenuManager = new MenuManager();
        MenuManager fileMenuManager = new MenuManager("File");
        MenuManager editMenuManager = new MenuManager("Edit");
        MenuManager formatMenuManager = new MenuManager("Format");

        barMenuManager.add(fileMenuManager);
        barMenuManager.add(editMenuManager);
        barMenuManager.add(formatMenuManager);

        window.fileOperate.add(fileMenuManager);
        fileMenuManager.add(new Separator());
        window.controlOperate.add(fileMenuManager);
        fileMenuManager.add(new Separator());
        window.textOperate.add(editMenuManager);
        fileMenuManager.add(new Separator());
        window.formatOperate.add(formatMenuManager);

        barMenuManager.updateAll(true);
        window.mainShell.setMenuBar(barMenuManager.createMenuBar((Decorations)window.mainShell));
    }
}
