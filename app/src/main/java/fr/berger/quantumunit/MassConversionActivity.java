package fr.berger.quantumunit;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.berger.qube.UnitBuilder;

public class MassConversionActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mass_conversion);
		
		QubeConversionFragment qcf_mass = QubeConversionFragment.newInstance(R.color.colorPrimary, R.drawable.ic_mass, R.string.mass,
				new UnitBuilder().templateGram(), new UnitBuilder().templateOunce());
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fl_mass_container, qcf_mass);
		ft.commit();
	}
}
