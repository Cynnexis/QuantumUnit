package fr.berger.quantumunit.fragmentinterface;

import android.view.View;

import fr.berger.quantumunit.parcel.UnitParcelable;

public interface Convertible {
	
	void onConversionRequired(View view, UnitParcelable unit, double value);
}
