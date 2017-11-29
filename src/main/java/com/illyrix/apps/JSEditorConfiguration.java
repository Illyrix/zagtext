package com.illyrix.apps;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.*;
import org.eclipse.jface.text.presentation.*;
import org.eclipse.jface.text.rules.*;
import org.eclipse.jface.text.source.*;

public class JSEditorConfiguration extends SourceViewerConfiguration {

    private JSEditor editor ;
    public JSEditorConfiguration( JSEditor editor ){
        this.editor=editor;
    }

    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();
        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(new JSCodeScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
        return reconciler;
    }

    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant contentAssistant = new ContentAssistant();
        contentAssistant.setContentAssistProcessor(new ObjectContentAssistant(),IDocument.DEFAULT_CONTENT_TYPE);
        contentAssistant.enableAutoActivation(true);
        contentAssistant.setAutoActivationDelay(500);
        return contentAssistant;
    }
}