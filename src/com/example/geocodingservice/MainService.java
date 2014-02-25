package com.example.geocodingservice;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

public class MainService extends Activity{

	GPSTracker gps;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		gps = new GPSTracker(MainService.this);

        // check if GPS enabled     
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            String Text = "My current location is:\n" +"Latitude =" +latitude+"\nLongitude ="+ longitude;
            Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
            try{
            List<Address> addresses=geo.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {       
    		      String message ="\n\nAddress:\n"+addresses.get(0).getFeatureName() + 
    		        		"," + addresses.get(0).getLocality() +
    		        		"," + addresses.get(0).getAdminArea() + 
    		        		"," + addresses.get(0).getCountryName();

    		      	File file = new File(Environment.getExternalStorageDirectory().getPath()+ "/GPS.txt");
    				if(!file.exists())
    			    {
    			    	file.createNewFile();
    			    }
    				FileWriter out = new FileWriter(file);
    				out.write(Text);
    				out.write(message);
    				out.close();	
            	}
            }catch(Exception e){}

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }
	}



}
