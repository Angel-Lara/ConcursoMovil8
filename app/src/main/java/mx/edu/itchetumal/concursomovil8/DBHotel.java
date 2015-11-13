package mx.edu.itchetumal.concursomovil8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Javier on 13/11/2015.
 */
public class DBHotel extends SQLiteOpenHelper {

    public DBHotel(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table restaurante(numeroCliente int primary key,numeroPersonas int, fecha text, hora text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
