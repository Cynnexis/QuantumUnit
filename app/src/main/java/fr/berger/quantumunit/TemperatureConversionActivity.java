package fr.berger.quantumunit;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.berger.qube.UnitBuilder;

public class TemperatureConversionActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temperature_conversion);
		
		QubeConversionFragment qcf_temperature = QubeConversionFragment.newInstance(R.color.colorPrimary, R.drawable.ic_temperature, R.string.temperature,
				new UnitBuilder().templateKelvin(), new UnitBuilder().templateCelsius(), new UnitBuilder().templateFahrenheit());
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fl_temperature_container, qcf_temperature);
		ft.commit();
	}
}
