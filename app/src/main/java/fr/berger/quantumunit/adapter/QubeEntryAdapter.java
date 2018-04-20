package fr.berger.quantumunit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import fr.berger.enhancedlist.lexicon.Lexicon;
import fr.berger.quantumunit.R;
import fr.berger.quantumunit.fragmentinterface.Convertible;
import fr.berger.quantumunit.parcel.UnitParcelable;
import fr.berger.qube.Qube;
import fr.berger.qube.Unit;
import fr.berger.qube.exceptions.NoConversionFoundException;
import fr.berger.qube.exceptions.UnitTypeIncompatibleException;

public class QubeEntryAdapter extends BaseAdapter implements Serializable, Cloneable, Convertible, Iterable<UnitParcelable> {
	
	private Context currentContext;
	private LayoutInflater inflater;
	private Lexicon<UnitParcelable> units;
	
	/**
	 * When converting == true, it means that there is conversion in progress, and that
	 * EditText.setText will be called. Hence, the TextWatcher must be disabled during the
	 * operation.
	 */
	private boolean converting = false;
	
	/**
	 * Save all the views created so far. "views" and "units" are sync (views[i] associated to the
	 * unit units[i])
	 */
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
				if (!converting) {
					if (s.toString().equals(""))
						clearAllEditText();
					else {
						double value;
						try {
							value = Double.valueOf(s.toString());
						} catch (ClassCastException | NumberFormatException ex) {
							ex.printStackTrace();
							value = 0;
						}
						
						onConversionRequired(view, unit, value);
					}
				}
			}
		});
		bt_convert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!converting) {
					ViewGroup group = (ViewGroup) v.getParent();
					AppCompatEditText editText = (AppCompatEditText) group.getChildAt(0);
					String message = editText.getText().toString();
					
					if (message.equals(""))
						clearAllEditText();
					else {
						double value;
						try {
							value = Double.valueOf(message);
						} catch (ClassCastException | NumberFormatException ex) {
							ex.printStackTrace();
							value = 0;
						}
						
						onConversionRequired(v, unit, value);
					}
				}
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
		if (unit == null) {
			converting = false;
			throw new NullPointerException();
		}
		
		// Search unit in "units"
		ArrayList<Integer> positions = units.search(unit);
		if (positions.isEmpty()) {
			converting = false;
			throw new IllegalArgumentException("unit \"" + unit.toString() + "\" is not in the list \"units\".");
		}
		if (positions.size() > 1)
			Log.w(this.getClass().getSimpleName(), "The unit \"" + unit.toString() + "\" appears " + positions.size() + " times in \"units\". The first element will be taken");
		
		int position = positions.get(0);
		
		// Notify all the other views
		for (int i = 0, maxi = getViews().size(); i < maxi; i++) {
			if (i != position) {
				// Convert
				Unit destination = getUnits().get(i);
				if (destination != null) {
					try {
						Qube converted = unit.convert(value, destination);
						changeEditText(i, Double.toString(converted.getValue()));
					} catch (UnitTypeIncompatibleException | NoConversionFoundException e) {
						converting = false;
						e.printStackTrace();
					}
				}
			}
		}
		converting = false;
	}
	
	/**
	 * Change the content of the edittext contained in view "i" in "views"
	 * @param position
	 */
	public void changeEditText(int position, @NotNull String message) {
		View currentView = getViews().get(position);
		if (currentView != null) {
			ViewGroup group = (ViewGroup) currentView;
			View maybeEditText = group.getChildAt(0);
			
			// Search for the EditText in "group"
			int j;
			int maxj;
			for (j = 0, maxj = group.getChildCount(); j < maxj && (maybeEditText == null || !(maybeEditText instanceof EditText)); j++)
				maybeEditText = group.getChildAt(j);
			
			// If not found
			if (j == maxj) {
				converting = false;
				throw new IllegalArgumentException("No EditText in the view n°" + position + ": " + currentView.toString());
			}
			
			converting = true;
			EditText et = (EditText) maybeEditText;
			et.setText(message);
			converting = false;
		}
	}
	
	public void clearAllEditText() {
		converting = true;
		for (int i = 0, maxi = getViews().size(); i < maxi; i++)
			changeEditText(i, "");
		converting = false;
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
