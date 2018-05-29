package com.nullpointers.toutmate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nullpointers.toutmate.Nearby.Result;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceDetailsFragment extends Fragment {

    private ImageView placeImageView;
    private TextView placeNameTV;
    private RatingBar placeRatingBar;
    private TextView placeAddressTV;


    public PlaceDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_place_details, container, false);

        placeImageView = view.findViewById(R.id.placeImageView);
        placeNameTV = view.findViewById(R.id.placeNameTextView);
        placeRatingBar = view.findViewById(R.id.placeRatingBar);
        placeAddressTV = view.findViewById(R.id.placeAddressTextView);

        Bundle bundle = getArguments();

        if (bundle != null) {
            Result currentPlace = (Result) bundle.getSerializable("result");
            placeImageView.setImageResource(R.mipmap.ic_launcher);
            placeNameTV.setText(currentPlace.getName());
            placeRatingBar.setRating(currentPlace.getRating().floatValue());
            placeAddressTV.setText(currentPlace.getVicinity());
        }

        // Inflate the layout for this fragment
        return view;
    }

}
