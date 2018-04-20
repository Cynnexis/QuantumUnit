package fr.berger.quantumunit;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.berger.qube.UnitBuilder;

public class AmountConversionActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amount_conversion);
		
		QubeConversionFragment qcf_amount = QubeConversionFragment.newInstance(R.color.colorPrimary, R.drawable.ic_amount, R.string.amount,
				new UnitBuilder().templateMole());
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fl_amount_container, qcf_amount);
		ft.commit();
	}
}
