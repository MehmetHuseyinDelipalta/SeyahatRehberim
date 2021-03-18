// Developer => Mehmet Hüseyin Delipalta - coDeveloperman
package com.example.mhd.seyehatrehberim;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> names=new ArrayList<String>();
    static ArrayList<LatLng> location=new ArrayList<LatLng>();
    static ArrayAdapter arrayAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu baglama islemleri
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.add_place,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Menuye tıklanınca ne olacagı ile ilgili islemler
        if (item.getItemId()== R.id.add_place);
        Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id._listView);

        try {

            MapsActivity.database=this.openOrCreateDatabase("Places",MODE_PRIVATE,null);
            Cursor cursor=MapsActivity.database.rawQuery("SELECT*FROM places",null);

            int nameIx=cursor.getColumnIndex("name");
            int latitudeIx =cursor.getColumnIndex("latitude");
            int longitudeIx= cursor.getColumnIndex("longitude");

            while (cursor.moveToNext()){

                String nameFromDatabase=cursor.getString(nameIx);
                String latitudeFromDatabase=cursor.getString(latitudeIx);
                String longitudeFromDatabase=cursor.getString(longitudeIx);

                names.add(nameFromDatabase);

                Double l1 = Double.parseDouble(latitudeFromDatabase);
                Double l2 = Double.parseDouble(longitudeFromDatabase);

                LatLng locationFromDatabase=new LatLng(l1,l2);

                location.add(locationFromDatabase);

                System.out.println( "name: " +nameFromDatabase);



            }
            cursor.close();



        }catch (Exception e){

        }

        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
        listView.setAdapter(arrayAdapter);






    }




}
