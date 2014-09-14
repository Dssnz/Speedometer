package soutar.dave.speedometer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity implements LocationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 2, this);
        this.onLocationChanged(null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        //get textview to work with
        TextView txt = (TextView) this.findViewById(R.id.textView1);
        //check for location data
        if (location == null) {
            txt.setText("-.- km/ph");
        } else {
            //convert m/s to km/ph
            float KPH = location.getSpeed() / 1000 * 3600;
            //alter decimal places
            String speed = (String.format(" %.0f ",KPH));
            //change text color depending on speed
            if (KPH > 49) {
                txt.setTextColor(Color.rgb(255,153,51));
            }
            if (KPH > 95) {
                txt.setTextColor(Color.rgb(255,0,0));
            }
            else {
                txt.setTextColor(Color.rgb(51,255,51));
            }
            //display speed
            txt.setText(speed);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
