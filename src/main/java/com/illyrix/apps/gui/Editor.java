package com.illyrix.apps.gui;

import com.illyrix.apps.Config;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;

import java.io.File;

public class Editor {
    Window window;
    public StyledText text;
    boolean hasUnsavedChanges;
    private Color StringToColor(String s) {
        String [] nums = s.split(",");
        int R = Integer.parseInt(nums[0]);
        int G = Integer.parseInt(nums[1]);
        int B = Integer.parseInt(nums[2]);
        if (nums.length == 4) {
            int A = Integer.parseInt(nums[3]);
            return new Color(window.display, new RGBA(R, G, B, A));
        } else
            return new Color(window.display, new RGB(R, G, B));
    }

    public Editor (Window window) {
        this.window = window;
        window.mainShell.setLayout(new GridLayout());

        text = new StyledText(window.mainShell, SWT.MULTI
//                | SWT.WRAP
//                | SWT.BORDER
                | SWT.H_SCROLL
                | SWT.V_SCROLL
        );

        String backgroundColor = Config.Conf.get("editor.background");
        if (backgroundColor.equals("")) backgroundColor = "50,50,50";
        text.setBackground(StringToColor(backgroundColor));

        text.setLayoutData(new GridData(GridData.FILL_BOTH));

        text.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                hasUnsavedChanges = true;
                text.redraw();
            }
        });

        text.addLineStyleListener(new LineStyleListener() {
            @Override
            public void lineGetStyle(LineStyleEvent event) {
                StyleRange styleRange = new StyleRange();
                styleRange.foreground = new Color(window.display, new RGBA(198, 198, 198, 200));
                int maxLine = text.getLineCount();
                int bulletLength = Integer.toString(maxLine).length();
                int bulletWidth = (bulletLength + 1) * text.getLineHeight() / 2;
                styleRange.metrics = new GlyphMetrics(0, 0, bulletWidth);
                event.bullet = new Bullet(ST.BULLET_TEXT, styleRange);
                int bulletLine = text.getLineAtOffset(event.lineOffset) + 1;
                event.bullet.text = String.format("%" + bulletLength + "s", bulletLine);
            }
        });

    }
}
