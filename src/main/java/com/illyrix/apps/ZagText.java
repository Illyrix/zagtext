package com.illyrix.apps;

import com.illyrix.apps.action.*;

import org.eclipse.jface.action.*;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.text.*;
import org.eclipse.jface.text.source.*;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

import java.io.IOException;

public class ZagText extends ApplicationWindow implements IPropertyChangeListener {
    private PersistentDocument document;
    private SourceViewer viewer;
    private IUndoManager undoManager;
    private PreferenceStore preference;

    public static void main (String[] args) {
        ZagText app = new ZagText();
        app.setBlockOnOpen(true);
        app.open();
        Display.getCurrent().dispose();
        try {
            app.getPreference().save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ZagText() {
        super(null);
        addMenuBar();
        preference = new PreferenceStore("editor.preference");
        try {
            preference.load();
        } catch (IOException e) {
            try {
                preference.save();
            } catch (IOException ev) {
                ev.printStackTrace();
            }
        }
        preference.addPropertyChangeListener(this);
    }
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("ZagText Editor");
        shell.setSize(960, 600);
    }
    protected Control createContents(Composite parent) {
        Composite top = new Composite(parent,SWT.NONE);
        top.setLayout( new FillLayout());

        document = new PersistentDocument();
        viewer = new SourceViewer(top, addLineNumber(), SWT.V_SCROLL | SWT.H_SCROLL);
        viewer.setDocument(document);
        undoManager = new TextViewerUndoManager(100);
        undoManager.connect(viewer);

        initCodeFont();
        return parent;
    }

    private void initCodeFont() {
        FontData defaultFont = new FontData("Courier New", 10, SWT.NORMAL);
        Font font = new Font(this.getShell().getDisplay(), defaultFont);
        viewer.getTextWidget().setFont(font);
    }

    protected MenuManager createMenuManager() {
        MenuManager top = new MenuManager();
        MenuManager fileMenu = new MenuManager("File(&F)");
        MenuManager editMenu = new MenuManager("Edit(&E)");
        MenuManager helpMenu = new MenuManager("Help(&H)");

        fileMenu.add( new OpenAction(this) );
        fileMenu.add( new SaveAction(this) );
        fileMenu.add( new SaveAsAction(this) );
        fileMenu.add( new Separator());
        fileMenu.add( new PrintAction(this) );;
        fileMenu.add( new Separator());
        fileMenu.add( new ExitAction(this) );

        editMenu.add( new UndoAction(this) );
        editMenu.add( new RedoAction(this) );
        editMenu.add( new Separator() );
        editMenu.add( new SearchAction(this) );
        editMenu.add( new Separator() );
        editMenu.add( new PreferenceAction(this) );

        helpMenu.add( new HelpAction(this) );
        top.add( fileMenu );
        top.add( editMenu ) ;
        top.add( helpMenu );

        return top;
    }

    private CompositeRuler addLineNumber () {
        CompositeRuler ruler = new CompositeRuler();

        LineNumberRulerColumn lineCol = new LineNumberRulerColumn();
        Color bColor = new Color(this.getShell().getDisplay(), 240, 240, 240);
        lineCol.setBackground(bColor);
        Color fColor = new Color(this.getShell().getDisplay(), 150, 150, 150);
        lineCol.setForeground(fColor);

        ruler.addDecorator(0, lineCol);
        return ruler;
    }

    public PersistentDocument getDocument() {
        return document;
    }

    public SourceViewer getViewer() {
        return viewer;
    }

    public IUndoManager getUndoManager() {
        return undoManager;
    }

    public PreferenceStore getPreference() { return preference; }

    public void setPreference(PreferenceStore preference) { this.preference = preference; }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getNewValue()==null)
            return;
        if (event.getProperty().equals("Font")) {
            Font font = new Font(this.getShell().getDisplay(), (FontData[]) event.getNewValue());
            this.getViewer().getTextWidget().setFont(font);
        }
        if (event.getProperty().equals("Background")) {
            RGB color = (RGB)event.getNewValue();
            this.getViewer().getTextWidget().setBackground(new Color(this.getShell().getDisplay(), color));
        }
    }
}
