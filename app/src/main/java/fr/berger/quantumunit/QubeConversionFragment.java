package fr.berger.quantumunit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
	
	private int mainColor;
	private Drawable icon;
	private String name;
	
	private FrameLayout fl_frag;
	private ImageView img_icon;
	private TextView tx_qube;
	
	public QubeConversionFragment() {
		// Required empty public constructor
	}
	
	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param rMainColor Parameter 1.
	 * @param rIcon Parameter 2.
	 * @return A new instance of fragment QubeConversionFragment.
	 */
	public static QubeConversionFragment newInstance(int rMainColor, int rIcon, int rName) {
		QubeConversionFragment fragment = new QubeConversionFragment();
		Bundle args = new Bundle();
		args.putInt(COLOR, rMainColor);
		args.putInt(ICON, rIcon);
		args.putInt(NAME, rName);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			int rMainColor = getArguments().getInt(COLOR);
			int rIcon = getArguments().getInt(ICON);
			int rName = getArguments().getInt(NAME);
			
			mainColor = ContextCompat.getColor(getContext(), rMainColor);
			icon = ContextCompat.getDrawable(getContext(), rIcon);
			name = getResources().getString(rName);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_qube_conversion, container, false);
		
		fl_frag = view.findViewById(R.id.fl_qube_conversion);
		fl_frag.setBackgroundColor(mainColor);
		
		img_icon = view.findViewById(R.id.qube_icon);
		img_icon.setImageDrawable(icon);
		
		tx_qube = view.findViewById(R.id.tx_qube);
		tx_qube.setText(name);
		
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
