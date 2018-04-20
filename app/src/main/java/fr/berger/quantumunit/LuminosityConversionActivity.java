package fr.berger.quantumunit;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.berger.qube.UnitBuilder;

public class LuminosityConversionActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_luminosity_conversion);
		
		QubeConversionFragment qcf_luminosity = QubeConversionFragment.newInstance(R.color.colorPrimary, R.drawable.ic_luminosity, R.string.luminous_intensity,
				new UnitBuilder().templateCandela());
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fl_luminosity_container, qcf_luminosity);
		ft.commit();
	}
}
