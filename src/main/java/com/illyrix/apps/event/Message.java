package com.illyrix.apps.event;

import static com.illyrix.apps.event.Message.TYPES.*;

public class Message {
    private long _long;
    private double _double;
    private String _string;
    private Object _object;
    private boolean _boolean;

    private TYPES _type;
    public enum TYPES {
        LONG, DOUBLE, STRING, OBJECT, BOOLEAN
    }

    public Message(int value) {
        this.set(value);
    }

    public Message(double value) {
        this.set(value);
    }

    public Message(String value) {
        this.set(value);
    }

    public Message(Object value) {
        this.set(value);
    }

    public Message(boolean value) {
        this.set(value);
    }

    public void set(int value) {
        this._type = LONG;
        this._long = value;
    }

    public void set(double value) {
        this._type = DOUBLE;
        this._double = value;
    }

    public void set(String value) {
        this._type = STRING;
        this._string = value;
    }

    public void set(Object value) {
        this._type = OBJECT;
        this._object = value;
    }

    public void set(boolean value) {
        this._type = BOOLEAN;
        this._boolean = value;
    }

    public long get() {
        return this._long;
    }
}
