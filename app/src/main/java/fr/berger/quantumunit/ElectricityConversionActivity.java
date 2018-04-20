package fr.berger.quantumunit;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.berger.qube.UnitBuilder;

public class ElectricityConversionActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_electricity_conversion);
		
		QubeConversionFragment qcf_electricity = QubeConversionFragment.newInstance(R.color.colorPrimary, R.drawable.ic_electricity, R.string.electricity,
				new UnitBuilder().templateAmpere());
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fl_electricity_container, qcf_electricity);
		ft.commit();
	}
}
