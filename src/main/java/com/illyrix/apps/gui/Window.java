package com.illyrix.apps.gui;

import com.illyrix.apps.component.ControlOperate;
import com.illyrix.apps.component.FileOperate;
import com.illyrix.apps.component.FormatOperate;
import com.illyrix.apps.component.TextOperate;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


import com.illyrix.apps.gui.Toolbar;
//import org.eclipse.swt.widgets.ToolBar;
//import org.eclipse.swt.SWT;

public class Window {

    Display display;
    Shell mainShell;

    Editor editor;
    Toolbar toolbar;

    ControlOperate controlOperate;
    FileOperate fileOperate;
    TextOperate textOperate;
    FormatOperate formatOperate;

    final static String ICON_PATH = "target/classes/icon.png";
    final static String APP_NAME = "ZagText";

    public Window() {
        display = new Display();
        mainShell = new Shell(display);

        editor = new Editor(this);
        toolbar = new Toolbar(this);

        controlOperate = new ControlOperate(mainShell);
        fileOperate = new FileOperate();
        textOperate = new TextOperate(editor);
        formatOperate = new FormatOperate(editor);
    }

    public void init () {
        toolbar.init();
        Image image = new Image(mainShell.getDisplay(), ICON_PATH);
        mainShell.setImage(image);
        mainShell.setText(APP_NAME);
//        this.mainShell.pack();
        mainShell.open();

        while (!mainShell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}
