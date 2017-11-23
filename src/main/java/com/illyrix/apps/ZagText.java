package com.illyrix.apps;

import com.illyrix.apps.gui.*;

public class ZagText {
    public static void main(String[] args) {
        Config.Conf = new Config();
        i18n.init();
        i18n.loadLanguage(Config.Conf);

        Window _window = new Window();
        _window.init();
    }
}
