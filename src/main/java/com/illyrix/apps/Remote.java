package com.illyrix.apps;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;

class Message {
    public boolean status;
    public String msg;
}

public class Remote {
    static String host;
    static Integer port;
    static String name;
    static String pass;

    static private Message parseJson (String s) {
        Gson gson = new Gson();
        Message result = gson.fromJson(s, Message.class);
        return result;
    }

    static public void set (String h, Integer po, String n, String pa) {
        host = h;
        port = po;
        name = n;
        pass = pa;
    }

    static public void setHost(String s){ host = s; }
    static public void setPort(Integer i){ port = i; }
    static public void setName(String s){ name = s; }
    static public void setPass(String s){ pass = s; }

    static public void upload (String name, String file) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", Remote.name);
        map.put("password", Remote.pass);
        map.put("filename", name);
        map.put("content", file);
        String str = "";
        try {
            str = HttpUtil.httpPost("http://"+host+":"+port.toString(), map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Message m = parseJson(str);
        if (!m.status) {
            throw new Exception(m.msg);
        }
    }

    static public String download (String name) throws Exception {
        String str = "";
        try {
            str = HttpUtil.httpGet("http://"+host+":"+port.toString()+"?username="+Remote.name+"&password="+Remote.pass+"&filename="+name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Message m = parseJson(str);
        if (!m.status) {
            throw new Exception(m.msg);
        }
        return m.msg;
    }
}
