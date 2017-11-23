package com.illyrix.apps;

import java.util.HashMap;
import java.util.Map;
import com.illyrix.apps.Config;

public class i18n {
    private static String LANGUAGE = "en-us";    // zh-cn; en-us...
    public static void loadLanguage (Config config) {
        i18n.LANGUAGE = config.get("language");
    }
    public static String getLanguage() {
        return i18n.LANGUAGE;
    }

    public static final HashMap<String, HashMap<String, String>> CONVERTION
            = new HashMap<String, HashMap<String, String>>();

    public static String convert (String s) {
        return i18n.CONVERTION.get(s).get(i18n.LANGUAGE);
    }

    public static void init () {
        HashMap save = new HashMap<String, String>();
        save.put("en-us", "Save");
        save.put("zh-cn", "保存");
        i18n.CONVERTION.put("save", save);

        HashMap saveAs = new HashMap<String, String>();
        saveAs.put("en-us", "Save as");
        saveAs.put("zh-cn", "另存为");
        i18n.CONVERTION.put("save as", saveAs);

        HashMap openFile = new HashMap<String, String>();
        openFile.put("en-us", "Open file");
        openFile.put("zh-cn", "打开文件");
        i18n.CONVERTION.put("open file", openFile);

        HashMap openPath = new HashMap<String, String>();
        openPath.put("en-us", "Open directory");
        openPath.put("zh-cn", "打开文件夹");
        i18n.CONVERTION.put("open path", openFile);
    }
}
