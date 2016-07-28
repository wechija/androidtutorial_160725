package com.example.a.a24_fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountFragment extends Fragment {


    public CountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_count, container, false);
        final TextView countTextView = (TextView) view.findViewById(R.id.countTextView);
        Button btn = (Button) view.findViewById(R.id.btnIncrease);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = countTextView.getText().toString();
                int number = Integer.valueOf(str);
                number++;
                countTextView.setText(""+number);
            }
        });
        return view;
    }



}
