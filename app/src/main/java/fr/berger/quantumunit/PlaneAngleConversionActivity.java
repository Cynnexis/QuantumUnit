package fr.berger.quantumunit;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.berger.qube.UnitBuilder;

public class PlaneAngleConversionActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plane_angle_conversion);
		
		QubeConversionFragment qcf_planeAngle = QubeConversionFragment.newInstance(R.color.colorPrimary, R.drawable.ic_plane_angle, R.string.plane_angle
				);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fl_plane_angle_container, qcf_planeAngle);
		ft.commit();
	}
}
