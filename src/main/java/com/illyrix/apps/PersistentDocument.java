package com.illyrix.apps;

import java.io.*;
import org.eclipse.jface.text.*;

public class PersistentDocument extends Document implements IDocumentListener {
    private String fileName;
    private boolean dirty;
    public PersistentDocument() {
        this.addDocumentListener(this);
    }

    public void save() throws IOException {
        if (fileName == null)
            return;
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(fileName));
            out.write(get());
            dirty = false;
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }

    public String getFileNameWithSuffix(String pathandname) {
        int start = pathandname.lastIndexOf("\\");
        if (start != -1 ) {
            return pathandname.substring(start + 1);
        } else {
            return null;
        }
    }

    public void saveToRemote() throws Exception {
        String filename = this.getFileName();
        if (filename == null) return;
        filename = getFileNameWithSuffix(filename);
        Remote.upload(filename, get());
    }

    public void loadFromRemote() throws Exception {
        String filename = this.getFileName();
        if (filename == null) return;
        filename = getFileNameWithSuffix(filename);
        set(Remote.download(filename));
    }

    public void open() throws IOException {
        if (fileName == null)
            return;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            StringBuffer buf = new StringBuffer();
            int n;
            while ((n = in.read()) != -1) {
                buf.append((char) n);
            }
            set(buf.toString());
            dirty = false;
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }
    public boolean isDirty() {
        return dirty;
    }
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void documentAboutToBeChanged(DocumentEvent arg0) {}
    public void documentChanged(DocumentEvent arg0) {
        dirty = true;
    }
}