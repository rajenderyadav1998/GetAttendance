package com.example.pattu.getattendence;

/**
 * Created by Pattu on 12-May-17.
 */

class Student {
    private int _id;
    private String _name;
    private String _roll;
    private String _phone;
    private String _email;
    private String _subject;
    private String _class1;
    private int _present;
    private int _absent;
    private int _leave;
    private int _note;
    private String _note_text;


    Student() {
    }

    public Student(int _id, String _name, String _roll, String _phone, String _email, String _subject, String _class1, int _present, int _absent, int _leave, int _note, String _note_text) {
        this._id=_id;
        this._name=_name;
        this._roll=_roll;
        this._phone=_phone;
        this._email=_email;
        this._subject=_subject;
        this._class1=_class1;
        this._present=_present;
        this._absent = _absent;
        this._leave = _leave;
        this._note = _note;
        this._note_text = _note_text;
    }

    public Student(String _name, String _roll, String _phone, String _email, String _subject, String _class1, int _present, int _absent, int _leave, int _note, String _note_text) {
        this._name=_name;
        this._roll=_roll;
        this._phone=_phone;
        this._email=_email;
        this._subject=_subject;
        this._class1=_class1;
        this._present=_present;
        this._absent = _absent;
        this._leave = _leave;
        this._note = _note;
        this._note_text = _note_text;
    }

//Getter

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_roll() {
        return _roll;
    }

    public String get_phone() {
        return _phone;
    }

    public String get_email() {
        return _subject;
    }

    public String get_subject() {
        return _class1;
    }

    String get_class1() {
        return _subject;
    }



//Setter


    void set_present(int _present) {
        this._present = _present;
    }

    void set_absent(int _absent) {
        this._absent = _absent;
    }

    void set_leave(int _leave) {
        this._leave = _leave;
    }

    void set_note(int _note) {
        this._note = _note;
    }

    public int get_present() {
        return _present;
    }

    public int get_absent() {
        return _absent;
    }

    public int get_leave() {
        return _leave;
    }

    public int get_note() {
        return _note;
    }

    public String get_note_text() {
        return _note_text;
    }


    void set_note_text(String _note_text) {
        this._note_text = _note_text;
    }

    void set_id(int _id) {
        this._id = _id;
    }

    void set_name(String _name) {
        this._name = _name;
    }

    void set_roll(String _roll) {
        this._roll = _roll;
    }

    void set_phone(String _phone) {
        this._phone = _phone;
    }

    void set_email(String _email) {
        this._email = _email;
    }

    void set_subject(String _subject) {
        this._subject = _subject;
    }

    void set_class1(String _class1) {
        this._class1 = _class1;
    }
}