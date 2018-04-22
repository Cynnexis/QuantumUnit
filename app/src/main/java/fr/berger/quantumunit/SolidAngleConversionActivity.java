package fr.berger.quantumunit;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.berger.qube.UnitBuilder;

public class SolidAngleConversionActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solid_angle);
		
		QubeConversionFragment qcf_solidAngle = QubeConversionFragment.newInstance(R.color.colorPrimary, R.drawable.ic_solid_angle, R.string.solid_angle
				);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fl_solid_angle_container, qcf_solidAngle);
		ft.commit();
	}
}
