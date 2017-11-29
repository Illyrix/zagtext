package com.illyrix.apps;

import com.illyrix.apps.action.*;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
//import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.*;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

public class ZagText extends ApplicationWindow {
    private PersistentDocument document;
    private SourceViewer viewer;
    private IUndoManager undoManager;

    public static void main (String[] args) {
        ZagText app = new ZagText();
        app.setBlockOnOpen(true);
        app.open();
        Display.getCurrent().dispose();
    }

    public ZagText() {
        super(null);
        this.addMenuBar();
        this.addToolBar( SWT.FLAT );
    }
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("ZagText Editor");
        shell.setSize(1000, 600);
    }
    protected Control createContents(Composite parent) {
        Composite top = new Composite(parent,SWT.NONE);
        top.setLayout( new FillLayout());

        document = new PersistentDocument();
        viewer = new SourceViewer(top, new VerticalRuler(10), SWT.V_SCROLL | SWT.H_SCROLL);
//        configuration = new JSEditorConfiguration(this);
//        viewer.configure(configuration );
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
        MenuManager fileMenu = new MenuManager("文件(&F)");
        MenuManager editMenu = new MenuManager("编辑(&E)");
        MenuManager helpMenu = new MenuManager("帮助(&H)");

        fileMenu.add( new OpenAction(this) );
        fileMenu.add( new SaveAction(this) );
        fileMenu.add( new Separator());
        fileMenu.add( new PrintAction(this) );

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
    //初始化工具栏
    protected ToolBarManager createToolBarManager(int style) {
        ToolBarManager toolBar = new ToolBarManager(style);
        toolBar.add( new OpenAction(this) );
        toolBar.add( new SaveAction(this) );
        toolBar.add( new Separator() );
        toolBar.add( new PrintAction(this) );

        toolBar.add( new UndoAction(this) );
        toolBar.add( new RedoAction(this) );
        toolBar.add( new Separator() );
        toolBar.add( new SearchAction(this) );
        toolBar.add( new Separator() );
        toolBar.add( new PreferenceAction(this) );
        toolBar.add( new HelpAction(this) );
        return toolBar;
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

    public void propertyChange(PropertyChangeEvent event) {
//        if (event.getNewValue()==null)
//            return;
//        if (event.getProperty().equals(Constants.CODE_FONT))
//            eventManager.setCodeFont( (FontData[]) event.getNewValue());
    }
}
