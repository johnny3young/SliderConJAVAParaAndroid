package com.example.slider;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderFragment extends Fragment {
    View view;
    ImageView image;
    TextView title, content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_slider,container,false); // inflamos la vista para poder obtener los elementos que se encuentran dentro de los xml
        image = view.findViewById(R.id.image);
        content = view.findViewById(R.id.txtContent);
        title = view.findViewById(R.id.txtTitle);
        RelativeLayout background  = view.findViewById(R.id.background); // agregamos el RelativeLayount para poder cambiarle el color de fondo


        if (getArguments() != null){
            title.setText(getArguments().getString("title"));
            content.setText(getArguments().getString("content"));
            image.setImageResource(getArguments().getInt("image"));
            background.setBackgroundColor(getArguments().getInt("color"));
        }

        return view;
    }
}
