package com.juanpablo.crud_bbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonInsertar, botonActualizar, botonBorrar, botonBuscar;
    EditText textoId,textoNombre,textoApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonInsertar=(Button)findViewById(R.id.insertar);
        botonActualizar=(Button)findViewById(R.id.actualizar);
        botonBorrar=(Button)findViewById(R.id.borrar);
        botonBuscar=(Button)findViewById(R.id.buscar);

        textoId=findViewById(R.id.id);
        textoNombre=findViewById(R.id.nombre);
        textoApellido=findViewById(R.id.apellido);

        final BBDD_Helper helper=new BBDD_Helper(this);

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=helper.getWritableDatabase();

                ContentValues values=new ContentValues();
                values.put(Estructura_BBDD.NOMBRE_COLUMNA1,Integer.parseInt(textoId.getText().toString()));
                values.put(Estructura_BBDD.NOMBRE_COLUMNA2,textoNombre.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA3,textoApellido.getText().toString());

                long newRowId=db.insertOrThrow(Estructura_BBDD.TABLE_NAME,null,values);

                Toast.makeText(getApplicationContext(), "Se guardo el registro con clave: "+
                        newRowId, Toast.LENGTH_LONG).show();


            }
        });
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=helper.getReadableDatabase();
                String[] projection = {
                        //Estructura_BBDD.NOMBRE_COLUMNA1,
                        Estructura_BBDD.NOMBRE_COLUMNA2,
                        Estructura_BBDD.NOMBRE_COLUMNA3
                };
                String selection = Estructura_BBDD.NOMBRE_COLUMNA1+ " = ?";
                String[] selectionArgs = { textoId.getText().toString() };

                /*String sortOrder=
                        Estructura_BBDD.NOMBRE_COLUMNA3+ " DESC";*/

                try {
                    Cursor c = db.query(
                            Estructura_BBDD.TABLE_NAME,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            null  // sortOrder
                    );


                    c.moveToFirst();
                    textoNombre.setText(c.getString(0));
                    textoApellido.setText(c.getString(1));
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"No se encontro registro ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}