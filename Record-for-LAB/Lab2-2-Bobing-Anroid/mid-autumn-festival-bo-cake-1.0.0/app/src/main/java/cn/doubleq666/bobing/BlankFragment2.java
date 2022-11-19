package cn.doubleq666.bobing;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment2 newInstance(String param1, String param2) {
        BlankFragment2 fragment = new BlankFragment2();
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
        View mView = inflater.inflate(R.layout.fragment_blank2, container, false);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/font1.otf");
        TextView textView = mView.findViewById(R.id.textView4);
        textView.setTypeface(typeface);

        TextView textView1 = mView.findViewById(R.id.textView3);
        textView1.setTypeface(typeface);

        TextView textView6 = mView.findViewById(R.id.textView6);
        textView6.setTypeface(typeface);
        textView6.setVisibility(View.INVISIBLE);

        EditText editText = mView.findViewById(R.id.editTextTextPersonName);
        editText.setTypeface(typeface);

        mView.findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).playBtn();
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_blankFragment2_to_blankFragment32);
            }
        });

        mView.findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(editText.getText()).equals("")) {
                    textView6.setVisibility(View.VISIBLE);
                } else {
                    ((MainActivity) getActivity()).setNickname(String.valueOf(editText.getText()));
                    ((MainActivity) getActivity()).playBtn();

                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_blankFragment2_to_blankFragment4);
                }
            }
        });
        return mView;
    }
}