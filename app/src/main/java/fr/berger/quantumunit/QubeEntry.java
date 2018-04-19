package fr.berger.quantumunit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.plus.PlusOneButton;

import fr.berger.qube.Unit;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link QubeEntry.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QubeEntry#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QubeEntry extends Fragment {
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String UNIT = "fr.berger.quantumunit.QubeEntry.UNIT";
	
	private Unit currentUnit;
	
	private EditText et_unit;
	private ImageButton bt_convert;
	
	private OnFragmentInteractionListener mListener;
	
	public QubeEntry() {
		// Required empty public constructor
	}
	
	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param unit Parameter 1.
	 * @return A new instance of fragment QubeEntry.
	 */
	// TODO: Rename and change types and number of parameters
	public static QubeEntry newInstance(Unit unit) {
		QubeEntry fragment = new QubeEntry();
		Bundle args = new Bundle();
		args.putSerializable(UNIT, unit);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			currentUnit = (Unit) getArguments().getSerializable(UNIT);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_qube_entry, container, false);
		
		//Find the +1 button
		//mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);
		
		et_unit = (EditText) view.findViewById(R.id.et_unit);
		bt_convert = (ImageButton) view.findViewById(R.id.bt_convert);
		
		if (currentUnit != null) {
			et_unit.setHint(currentUnit.getName() + " (" + currentUnit.getSymbol() + ")");
			bt_convert.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO: Notify the other fragments
				}
			});
		}
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// Refresh the state of the +1 button each time the activity receives focus.
		//mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
	}
	
	public void onConversionRequired(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}
	
	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
	
}
