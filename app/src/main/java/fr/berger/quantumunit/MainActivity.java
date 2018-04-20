package fr.berger.quantumunit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
	
	/*
	private Button bt_length;
	private Button bt_time;
	private Button bt_mass;
	private Button bt_temperature;
	private Button bt_amount;
	private Button bt_electricity;
	private Button bt_luminosity;
	private Button bt_plane_angle;
	private Button bt_solid_angle;*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);*/

		/*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});*/
		
		/*
		bt_length = (Button) findViewById(R.id.bt_length);
		bt_time = (Button) findViewById(R.id.bt_time);
		bt_mass = (Button) findViewById(R.id.bt_mass);
		bt_temperature = (Button) findViewById(R.id.bt_temperature);
		bt_amount = (Button) findViewById(R.id.bt_amount);
		bt_electricity = (Button) findViewById(R.id.bt_electricity);
		bt_luminosity = (Button) findViewById(R.id.bt_luminosity);
		bt_plane_angle = (Button) findViewById(R.id.bt_plane_angle);
		bt_solid_angle = (Button) findViewById(R.id.bt_solid_angle);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	
	public void convertLength(View view) {
		Intent intent = new Intent(this, LengthConversionActivity.class);
		startActivity(intent);
	}
	
	public void convertTime(View view) {
		Intent intent = new Intent(this, TimeActivity.class);
		startActivity(intent);
	}
	
	public void convertMass(View view) {
		Intent intent = new Intent(this, MassConversionActivity.class);
		startActivity(intent);
	}
	
	public void convertTemperature(View view) {
		Intent intent = new Intent(this, TemperatureConversionActivity.class);
		startActivity(intent);
	}
	
	public void convertAmount(View view) {
		Intent intent = new Intent(this, AmountConversionActivity.class);
		startActivity(intent);
	}
	
	public void convertElectricity(View view) {
		Intent intent = new Intent(this, ElectricityConversionActivity.class);
		startActivity(intent);
	}
	
	public void convertLuminosity(View view) {
		Intent intent = new Intent(this, LuminosityConversionActivity.class);
		startActivity(intent);
	}
	
	public void convertPlaneAngle(View view) {
		Intent intent = new Intent(this, PlaneAngleConversionActivity.class);
		startActivity(intent);
	}
	
	public void convertSolidAngle(View view) {
		Snackbar.make(view, "Not implemented.", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show();
	}
}
