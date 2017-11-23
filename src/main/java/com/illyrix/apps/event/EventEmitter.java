//package com.illyrix.apps.event;
//
//import java.util.HashMap;
//import java.util.function.Consumer;
////import java.util.function.Function;
//
//public class EventEmitter {
//    private  static HashMap<String, Consumer<?>> events = new HashMap<>();
//
//    public static void emit(String event, Object param) {
//        events.get(event).apply(param);
//    }
//
//    public static void emit(String event) {
//        events.get(event).apply(null);
//    }
//
//    public static void emit(String event, boolean b) {
//        events.get(event).apply(b);
//    }
//
//    public static void emit(String event, String s) {
//        events.get(event).apply(s);
//    }
//
//    public static void emit(String event, Integer i) {
//        events.get(event).apply(i);
//    }
//
//    public static void emit(String event, Double d) {
//        events.get(event).apply(d);
//    }
//
//    public static void listen(String event, Consumer<?> callback) {
//        if (!events.containsKey(event)) {
//            events.put(event, callback);
//        } else {
//            Consumer<?> origin = events.get(event);
////            events.put(event, origin.andThen(callback));
//        }
//    }
//}
