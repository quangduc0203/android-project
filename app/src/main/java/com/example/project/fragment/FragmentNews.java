package com.example.project.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.project.R;
import com.example.project.ui.NewLaptop;
import com.example.project.ui.NewPhone;
import com.example.project.ui.NewTablet;
import com.example.project.ui.NewWatch;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNews#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNews extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnPhone;
    private Button btnLaptop;
    private Button btnWatch;
    private Button btnTablet;

    public FragmentNews() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNews.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNews newInstance(String param1, String param2) {
        FragmentNews fragment = new FragmentNews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnPhone = getActivity().findViewById(R.id.btnPhone);
        btnLaptop = getActivity().findViewById(R.id.btnLaptop);
        btnWatch = getActivity().findViewById(R.id.btnWatch);
        btnTablet = getActivity().findViewById(R.id.btnTablet);

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewPhone.class);
                getActivity().startActivity(intent);
            }
        });
        btnLaptop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewLaptop.class);
                getActivity().startActivity(intent);
            }
        });
        btnTablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewTablet.class);
                getActivity().startActivity(intent);
            }
        });
        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewWatch.class);
                getActivity().startActivity(intent);
            }
        });
    }
}