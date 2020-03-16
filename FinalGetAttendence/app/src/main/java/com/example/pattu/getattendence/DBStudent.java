package com.example.pattu.getattendence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;


class DBStudent extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "rajj";
    private static final String TABLE_TEACHER = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CLASS = "class1";
    private static final String KEY_ROLL = "roll";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";

    private SQLiteDatabase db;

    DBStudent(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        String CREATE_TEACHER_TABLE = "CREATE TABLE " + TABLE_TEACHER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_CLASS + " TEXT,"
                + KEY_ROLL + " TEXT,"
                + KEY_SUBJECT + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_TEACHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
        onCreate(db);
    }

    void addStudent(String name, String class1, String roll, String subject, String phone, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_CLASS, class1);
        values.put(KEY_ROLL, roll);
        values.put(KEY_SUBJECT, subject);
        values.put(KEY_PHONE, phone);
        values.put(KEY_EMAIL, email);

        db.insert(TABLE_TEACHER, null, values);
        db.close();
    }

    List<Student> allStudent() {
        List<Student> studentList = new ArrayList<Student>();
        String selectQuery = "SELECT * FROM " + TABLE_TEACHER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.set_id(Integer.parseInt(cursor.getString(0)));
                student.set_name(cursor.getString(1));
                student.set_roll(cursor.getString(2));
                student.set_phone(cursor.getString(3));
                student.set_email(cursor.getString(4));
                student.set_subject(cursor.getString(5));
                student.set_class1(cursor.getString(6));


                studentList.add(student);
            } while (cursor.moveToNext());
        }
        return studentList;

    }

    Student student(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Student student = new Student();

        Cursor cursor = db.query(TABLE_TEACHER, new String[]{
                        KEY_ID,
                        KEY_NAME,
                        KEY_ROLL,
                        KEY_PHONE,
                        KEY_EMAIL,
                        KEY_SUBJECT,
                        KEY_CLASS,
                },
                KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.getCount() >= 1) {
            student.set_id(Integer.parseInt(cursor.getString(0)));
            student.set_name(cursor.getString(1));
            student.set_class1(cursor.getString(2));
            student.set_roll(cursor.getString(3));
            student.set_subject(cursor.getString(4));
            student.set_phone(cursor.getString(5));
            student.set_email(cursor.getString(6));

        }
        return student;
    }

    List<Student> sudentCat(String branch, String sem) {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TEACHER + " WHERE " + KEY_CLASS + "='" + branch + "' AND " + KEY_SUBJECT + "='" + sem + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.set_id(Integer.parseInt(cursor.getString(0)));
                student.set_name(cursor.getString(1));
                student.set_class1(cursor.getString(2));
                student.set_roll(cursor.getString(3));
                student.set_subject(cursor.getString(4));
                student.set_phone(cursor.getString(5));
                student.set_email(cursor.getString(6));


                studentList.add(student);
            } while (cursor.moveToNext());
        }
        return studentList;
    }

    public List<Student> allStudentAttendance(String date, String branch, String sem) {

        SQLiteDatabase db = this.getReadableDatabase();

        StringBuilder newDate = new StringBuilder(date);
        newDate.setCharAt(2, '_');
        newDate.setCharAt(5, '_');

        List<Student> studentList = new ArrayList<>();

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + "a" + newDate + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_CLASS + " TEXT,"
                + KEY_ROLL + " TEXT,"
                + KEY_SUBJECT + " TEXT,"
                + "present" + " INTEGER,"
                + "absent" + " INTEGER,"
                + "leave" + " INTEGER,"
                + "note" + " INTEGER,"
                + "note_text" + " TEXT" + ")";

        Log.d("test", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);

        String selectQuery = "SELECT * FROM " + "a" + newDate
                + " WHERE " + KEY_CLASS + "='" + branch + "'"
                + " AND " + KEY_SUBJECT + "='" + sem + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.set_id(Integer.parseInt(cursor.getString(0)));
                student.set_name(cursor.getString(1));
                student.set_class1(cursor.getString(2));
                student.set_roll(cursor.getString(3));
                student.set_subject(cursor.getString(4));
                student.set_present(Integer.parseInt(cursor.getString(5)));
                student.set_absent(Integer.parseInt(cursor.getString(6)));
                student.set_leave(Integer.parseInt(cursor.getString(7)));
                student.set_note(Integer.parseInt(cursor.getString(8)));
                student.set_note_text(cursor.getString(9));

                studentList.add(student);
            } while (cursor.moveToNext());
        }
        return studentList;
    }

    Student studentAttend(String date, String branch, String sem, String name, String roll) {
        SQLiteDatabase db = this.getReadableDatabase();
        Student student = new Student();

        StringBuilder newDate = new StringBuilder(date);
        newDate.setCharAt(2, '_');
        newDate.setCharAt(5, '_');

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + "a" + newDate + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_CLASS + " TEXT,"
                + KEY_ROLL + " TEXT,"
                + KEY_SUBJECT + " TEXT,"
                + "present" + " INTEGER,"
                + "absent" + " INTEGER,"
                + "leave" + " INTEGER,"
                + "note" + " INTEGER,"
                + "note_text" + " TEXT" + ")";

        Log.d("test", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);

        String selectQuery = "SELECT * FROM " + "a" + newDate
                + " WHERE " + KEY_CLASS + "='" + branch + "'"
                + " AND " + KEY_SUBJECT + "='" + sem + "'"
                + " AND " + KEY_NAME + "='" + name + "'"
                + " AND " + KEY_ROLL + "='" + roll + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.getCount() >= 1) {
            student.set_id(Integer.parseInt(cursor.getString(0)));
            student.set_name(cursor.getString(1));
            student.set_class1(cursor.getString(2));
            student.set_roll(cursor.getString(3));
            student.set_subject(cursor.getString(4));
            student.set_present(Integer.parseInt(cursor.getString(5)));
            student.set_absent(Integer.parseInt(cursor.getString(6)));
            student.set_leave(Integer.parseInt(cursor.getString(7)));
            student.set_note(Integer.parseInt(cursor.getString(8)));
            student.set_note_text(cursor.getString(9));
        }

        return student;
    }

    void takeAttendence(String date, String name, String class1, String roll, String subject, int p, int a, int l, int n, String note) {
        SQLiteDatabase db = this.getWritableDatabase();

        StringBuilder newDate = new StringBuilder(date);
        newDate.setCharAt(2, '_');
        newDate.setCharAt(5, '_');


        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_CLASS, class1);
        values.put(KEY_ROLL, roll);
        values.put(KEY_SUBJECT, subject);
        values.put("present", p);
        values.put("absent", a);
        values.put("leave", l);
        values.put("note", n);
        values.put("note_text", note);

        db.insert("a" + newDate.toString(), null, values);
        db.close();
    }
}
