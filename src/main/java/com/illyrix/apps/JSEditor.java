package com.illyrix.apps;

import org.eclipse.jface.action.*;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.*;
import org.eclipse.jface.text.source.*;
import org.eclipse.jface.util.*;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import com.illyrix.apps.action.*;
import com.illyrix.apps.dialog.*;

public class JSEditor extends ApplicationWindow implements IPropertyChangeListener {

    private PersistentDocument document;
    private SourceViewer viewer;
    private EventManager eventManager;
    private PreferenceStore preference;
    private IUndoManager undoManager;
    private JSEditorConfiguration configuration;
    public JSEditor() {
        super(null);
        eventManager = new EventManager(this);
        this.addMenuBar();
        this.addToolBar( SWT.FLAT );
    }
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("JavaScript 编辑器");
        shell.setSize(600, 400);
    }
    protected Control createContents(Composite parent) {
        Composite top = new Composite(parent,SWT.NONE);
        top.setLayout( new FillLayout());
        document = new PersistentDocument();
        viewer = new SourceViewer(top, new VerticalRuler(10), SWT.V_SCROLL | SWT.H_SCROLL);
        configuration = new JSEditorConfiguration(this);
        viewer.configure(configuration);
        viewer.setDocument(document);
        undoManager = new TextViewerUndoManager(100);
        undoManager.connect(viewer);
        initCodeFont();
        return parent;
    }

    private void initCodeFont() {
        FontData defaultFont = new FontData("Courier New", 10, SWT.NORMAL);
        FontData data = StringConverter.asFontData(ResourceManager.getPreferenceStore().getString(Constants.CODE_FONT),
                defaultFont);
        Font font = new Font(this.getShell().getDisplay(), data);
        viewer.getTextWidget().setFont(font);
    }
    //初始化菜单项
    protected MenuManager createMenuManager() {
        MenuManager top = new MenuManager();
        MenuManager fileMenu = new MenuManager("文件(&F)");
        MenuManager editMenu = new MenuManager("编辑(&E)");
        MenuManager helpMenu = new MenuManager("帮助(&H)");

        fileMenu.add( new OpenAction(this) );
        fileMenu.add( new SaveAction(this) );
        fileMenu.add( new Separator());
        fileMenu.add( new PrintAction(this) );

        editMenu.add( new UndoAction(this));
        editMenu.add( new RedoAction(this));
        editMenu.add( new Separator());
        editMenu.add( new SearchAction(this));
        editMenu.add( new Separator());
        editMenu.add( new PreferenceAction(this));

        helpMenu.add( new HelpAction(this));
        top.add( fileMenu );
        top.add( editMenu ) ;
        top.add(helpMenu);

        return top;
    }

    protected ToolBarManager createToolBarManager(int style) {
        ToolBarManager toolBar = new ToolBarManager(style);
        toolBar.add(new OpenAction(this));
        toolBar.add( new SaveAction(this) );
        toolBar.add( new Separator());
        toolBar.add( new PrintAction(this) );

        toolBar.add( new UndoAction(this));
        toolBar.add( new RedoAction(this));
        toolBar.add( new Separator());
        toolBar.add( new SearchAction(this));
        toolBar.add( new Separator());
        toolBar.add( new PreferenceAction(this));
        toolBar.add( new HelpAction(this));
        return toolBar;
    }

    public PersistentDocument getDocument() {
        return document;
    }

    public SourceViewer getViewer() {
        return viewer;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public PreferenceStore getPreference() {
        return preference;
    }

    public void setPreference(PreferenceStore preference) {
        this.preference = preference;
    }

    public IUndoManager getUndoManager() {
        return undoManager;
    }

    public JSEditorConfiguration getConfiguration() {
        return configuration;
    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getNewValue()==null)
            return;
        if (event.getProperty().equals(Constants.CODE_FONT))
            eventManager.setCodeFont( (FontData[]) event.getNewValue());
    }
}