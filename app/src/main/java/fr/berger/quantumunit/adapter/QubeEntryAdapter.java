package fr.berger.quantumunit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

import fr.berger.enhancedlist.lexicon.Lexicon;
import fr.berger.quantumunit.R;
import fr.berger.quantumunit.fragmentinterface.Convertible;
import fr.berger.quantumunit.parcel.UnitParcelable;
import fr.berger.qube.Qube;
import fr.berger.qube.exceptions.NoConversionFoundException;
import fr.berger.qube.exceptions.UnitTypeIncompatibleException;

public class QubeEntryAdapter extends BaseAdapter implements Serializable, Cloneable, Convertible, Iterable<UnitParcelable> {
	
	private Context currentContext;
	private LayoutInflater inflater;
	private Lexicon<UnitParcelable> units;
	
	private Lexicon<View> views;
	
	public QubeEntryAdapter(Context context, @NotNull Lexicon<UnitParcelable> units) {
		setCurrentContext(context);
		initInflater();
		setUnits(units);
		initViews();
	}
	public QubeEntryAdapter(Context context, UnitParcelable... units) {
		this(context, new Lexicon<UnitParcelable>(units));
	}
	public QubeEntryAdapter(Context context) {
		setCurrentContext(context);
		initInflater();
		initUnits();
		initViews();
	}
	
	/* BASE ADAPTER OVERRIDES */
	
	@Override
	public int getCount() {
		return getUnits().size();
	}
	
	@Override
	public Object getItem(int position) {
		return getUnits().get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (getViews().size() > position)
			return getViews().get(position);
		
		final View view = getInflater().inflate(R.layout.qube_entry_view, parent, false);
		
		EditText et_unit = (EditText) view.findViewById(R.id.et_unit);
		ImageButton bt_convert = (ImageButton) view.findViewById(R.id.bt_convert);
		
		final UnitParcelable unit = (UnitParcelable) getItem(position);
		
		et_unit.setHint(unit.getName() + " (" + unit.getSymbol() + ")");
		et_unit.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) { }
			
			@Override
			public void afterTextChanged(Editable s) {
				double value;
				try {
					value = Double.valueOf(s.toString());
				} catch (ClassCastException | NumberFormatException ex) {
					ex.printStackTrace();
					value = 0;
				}
				
				onConversionRequired(view, unit, value);
			}
		});
		bt_convert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewGroup group = (ViewGroup) v.getParent();
				AppCompatEditText editText = (AppCompatEditText) group.getChildAt(0);
				
				double value;
				try {
					value = Double.valueOf(editText.getText().toString());
				} catch (ClassCastException | NumberFormatException ex) {
					ex.printStackTrace();
					value = 0;
				}
				
				onConversionRequired(v, unit, value);
			}
		});
		
		// Add the view at the corresponding position in the lexicon
		if (getViews().size() != position)
			while (getViews().size() != position)
				getViews().add(null);
		
		getViews().add(view);
		
		return view;
	}
	
	/* CONVERTIBLE OVERRIDE */
	
	@Override
	public void onConversionRequired(View view, UnitParcelable unit, double value) {
		if (unit == null)
			throw new NullPointerException();
		
		for (UnitParcelable u : getUnits()) {
			if (!unit.equals(u)) {
				try {
					Qube converted = unit.convert(value, u);
					System.out.println("QubeEntryAdapter.onConversionRequired> unit(" + unit.toString() + ") = " + converted.toString());
					
					// Notify all the other views
					// TODO
				} catch (UnitTypeIncompatibleException | NoConversionFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/* ITERABLE OVERRIDES */
	
	@NonNull
	@Override
	public Iterator<UnitParcelable> iterator() {
		return getUnits().iterator();
	}
	
	/* GETTERS & SETTERS */
	
	public Context getCurrentContext() {
		return currentContext;
	}
	
	public void setCurrentContext(Context currentContext) {
		this.currentContext = currentContext;
	}
	
	@NotNull
	public LayoutInflater getInflater() {
		if (inflater == null)
			initInflater();
		
		return inflater;
	}
	
	public void setInflater(LayoutInflater inflater) {
		this.inflater = inflater;
	}
	
	@SuppressWarnings("ConstantConditions")
	public void initInflater(@NotNull Context context) {
		if (context == null)
			throw new NullPointerException();
		
		setInflater((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}
	public void initInflater() {
		initInflater(getCurrentContext());
	}
	
	@SuppressWarnings("ConstantConditions")
	public Lexicon<UnitParcelable> getUnits() {
		if (units == null)
			initUnits();
		
		return units;
	}
	
	@SuppressWarnings("ConstantConditions")
	public void setUnits(@NotNull Lexicon<UnitParcelable> units) {
		if (units == null)
			throw new NullPointerException();
		
		this.units = units;
		configureUnits();
	}
	
	protected void initUnits() {
		setUnits(new Lexicon<UnitParcelable>());
	}
	
	protected void configureUnits() {
		getUnits().setAcceptNullValues(false);
	}
	
	@SuppressWarnings("ConstantConditions")
	public Lexicon<View> getViews() {
		if (views == null)
			initViews();
		
		return views;
	}
	
	@SuppressWarnings("ConstantConditions")
	public void setViews(@NotNull Lexicon<View> views) {
		if (views == null)
			throw new NullPointerException();
		
		this.views = views;
		configureViews();
	}
	
	protected void initViews() {
		setViews(new Lexicon<View>());
	}
	
	protected void configureViews() {
		getViews().setAcceptNullValues(true);
		getViews().setAcceptDuplicates(false);
	}
}
