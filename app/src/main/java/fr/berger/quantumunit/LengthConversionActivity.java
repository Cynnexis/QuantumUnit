package fr.berger.quantumunit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import fr.berger.qube.UnitBuilder;

public class LengthConversionActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_length_conversion);
		
		QubeConversionFragment qcf_length = QubeConversionFragment.newInstance(R.color.colorLength, R.drawable.ic_length, R.string.length,
				new UnitBuilder().templateMeter(), new UnitBuilder().templateMile());
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fl_length_container, qcf_length);
		ft.commit();
	}
}
