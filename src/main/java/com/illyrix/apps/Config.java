package com.illyrix.apps;

import com.google.gson.*;

import java.io.*;

public class Config {
    static final String DEFAULT_CONFIG_PATH = "config.json";

    private JsonObject _configJson;

    public static Config Conf;

    Config (String path) {
        this._load(path);
    }

    Config () {
        this._load(Config.DEFAULT_CONFIG_PATH);
    }

    public String get (String key) {
        try {
            return this._configJson.get(key).getAsString();
        } catch (Exception e) {
            return "";
        }
    }

    public void set (String key, String value) {
        this._configJson.addProperty(key, value);
    }

    private void _load (String path) {
        JsonParser parse = new JsonParser();
        try {
            this._configJson = (JsonObject) parse.parse(new FileReader(path));
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            File file = new File(path);
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file, false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("{}");
                bw.flush();
                bw.close();
                fw.close();
            } catch (IOException io) {
                e.printStackTrace();
            }
        }
    }
}
