package com.illyrix.apps.component;

import com.illyrix.apps.gui.Editor;
import org.eclipse.jface.action.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;

//import com.illyrix.apps.event.EventEmitter;
//
//import static com.illyrix.apps.event.EventEmitter.emit;
//import static com.illyrix.apps.event.EventEmitter.listen;

public class TextOperate implements Operate{

    // The control used for text displaying and editing.
    StyledText text;

    public TextOperate (Editor e) {
//        listen("copy", (Object o) -> { this.text.copy(); return null; });
//        listen("cut", (Object o) -> { this.text.cut(); return null; });
//        listen("paste", (Object o) -> { this.text.paste(); return null; });
//        listen("copy", (o) -> { this.text.setWordWrap(o); return null; });

        text = e.text;
        copyText.setAccelerator(SWT.CTRL + 'C');
        pasteText.setAccelerator(SWT.CTRL + 'V');
        cutText.setAccelerator(SWT.CTRL + 'X');
    }

    Action copyText = new Action("Copy") {
        public void run() {
//            emit("copy");
            text.copy();
        }
    };

    // Action: cut the selected text.
    Action cutText = new Action("Cut") {
        public void run() {
//            emit("cut");
            text.cut();
        }
    };

    // Action: paste the text on clipboard.
    Action pasteText =new Action("Paste") {
        public void run() {
//            emit("paste");
            text.paste();
        }
    };

    @Override
    public void add(MenuManager toolBar) {
        toolBar.add(copyText);
        toolBar.add(cutText);
        toolBar.add(pasteText);
    }
}
