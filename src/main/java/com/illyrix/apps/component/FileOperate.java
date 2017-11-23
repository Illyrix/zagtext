package com.illyrix.apps.component;

import org.eclipse.jface.action.*;

//import com.illyrix.apps.event.EventEmitter;
import org.eclipse.swt.SWT;

import java.io.File;

public class FileOperate implements Operate {
    // The file associated with current text content.
    File file;

    // Is there any changes since last saving action?
    boolean hasUnsavedChanges = false;

    // The recent directory
    String lastOpenDirectory;

    Action newFile = new Action("New") {
        public void run() {
//            EventEmitter.emit("new file");
        }
    };

    Action openFile = new Action("Open") {
        public void run() {
//            EventEmitter.emit("open file");
        }
    };

    Action openPath = new Action("Open path") {
        public void run() {
//            EventEmitter.emit("open path");
        }
    };

    Action saveFile = new Action("Save") {
        public void run() {
//            EventEmitter.emit("save file");
        }
    };

    Action saveAsFile = new Action("Save as") {
        public void run() {
//            EventEmitter.emit("save as file");
        }
    };

    public FileOperate () {
        this.newFile.setAccelerator(SWT.CTRL + 'N');
        this.openFile.setAccelerator(SWT.CTRL + 'O');
        this.openPath.setAccelerator(SWT.CTRL + SWT.SHIFT + 'O');
        this.saveFile.setAccelerator(SWT.CTRL + 'S');
        this.saveAsFile.setAccelerator(SWT.CTRL + SWT.SHIFT + 'S');
    }

    @Override
    public void add(MenuManager toolBar) {
        toolBar.add(newFile);
        toolBar.add(openFile);
        toolBar.add(openPath);
        toolBar.add(new Separator());
        toolBar.add(saveFile);
        toolBar.add(saveAsFile);
    }
}
