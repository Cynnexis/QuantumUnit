package fr.berger.quantumunit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import fr.berger.enhancedlist.lexicon.Lexicon;
import fr.berger.quantumunit.adapter.QubeEntryAdapter;
import fr.berger.quantumunit.parcel.UnitParcelable;
import fr.berger.qube.Unit;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QubeConversionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QubeConversionFragment extends Fragment {
	
	public static final String COLOR = "fr.berger.quantumunit.QubeConversionFragment.COLOR";
	public static final String ICON = "fr.berger.quantumunit.QubeConversionFragment.ICON";
	public static final String NAME  = "fr.berger.quantumunit.QubeConversionFragment.NAME";
	public static final String UNITS  = "fr.berger.quantumunit.QubeConversionFragment.UNITS";
	
	private int mainColor;
	private Drawable icon;
	private String name;
	@NotNull
	private Lexicon<UnitParcelable> units;
	
	private FrameLayout fl_frag;
	private ImageView img_icon;
	private TextView tx_qube;
	private ListView lv_fragments;
	
	public QubeConversionFragment() {
		units = new Lexicon<>();
	}
	
	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param rMainColor Parameter 1.
	 * @param rIcon Parameter 2.
	 * @return A new instance of fragment QubeConversionFragment.
	 */
	@SuppressWarnings("ConstantConditions")
	public static QubeConversionFragment newInstance(int rMainColor, int rIcon, int rName, @NotNull Lexicon<Unit> units) {
		if (units == null)
			throw new NullPointerException();
		
		// Building parcelable array
		ArrayList<UnitParcelable> list = new ArrayList<>(units.size());
		for (Unit unit : units) {
			if (unit != null)
				list.add(new UnitParcelable(unit));
		}
		
		QubeConversionFragment fragment = new QubeConversionFragment();
		
		Bundle args = new Bundle();
		args.putInt(COLOR, rMainColor);
		args.putInt(ICON, rIcon);
		args.putInt(NAME, rName);
		args.putParcelableArrayList(UNITS, list);
		
		fragment.setArguments(args);
		return fragment;
	}
	public static QubeConversionFragment newInstance(int rMainColor, int rIcon, int rName, @NotNull ArrayList<Unit> units) {
		return newInstance(rMainColor, rIcon, rName, new Lexicon<>(units));
	}
	public static QubeConversionFragment newInstance(int rMainColor, int rIcon, int rName, @NotNull Unit... units) {
		return newInstance(rMainColor, rIcon, rName, new Lexicon<Unit>(units));
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			int rMainColor = getArguments().getInt(COLOR);
			int rIcon = getArguments().getInt(ICON);
			int rName = getArguments().getInt(NAME);
			ArrayList<UnitParcelable> list = getArguments().getParcelableArrayList(UNITS);
			
			mainColor = ContextCompat.getColor(getContext(), rMainColor);
			icon = ContextCompat.getDrawable(getContext(), rIcon);
			name = getResources().getString(rName);
			if (list != null)
				units.addAll(list);
			else
				units = new Lexicon<>(UnitParcelable.class);
		}
	}
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_qube_conversion, container, false);
		
		fl_frag = view.findViewById(R.id.fl_qube_conversion);
		fl_frag.setBackgroundColor(mainColor);
		
		img_icon = view.findViewById(R.id.qube_icon);
		img_icon.setImageDrawable(icon);
		
		tx_qube = view.findViewById(R.id.tx_qube);
		tx_qube.setText(name);
		
		// Building QubeEntry fragment from "units" list
		lv_fragments = view.findViewById(R.id.lv_fragments);
		QubeEntryAdapter adapter = new QubeEntryAdapter(this.getContext(), units);
		lv_fragments.setAdapter(adapter);
		
		return view;
	}
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
	}
}
