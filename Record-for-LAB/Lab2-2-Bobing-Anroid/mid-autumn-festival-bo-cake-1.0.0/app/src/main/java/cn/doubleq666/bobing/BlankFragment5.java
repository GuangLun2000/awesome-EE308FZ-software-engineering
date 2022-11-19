package cn.doubleq666.bobing;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment5 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment5.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment5 newInstance(String param1, String param2) {
        BlankFragment5 fragment = new BlankFragment5();
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
        View mView = inflater.inflate(R.layout.fragment_blank5, container, false);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/font1.otf");
        TextView textView = mView.findViewById(R.id.textView4);
        textView.setTypeface(typeface);

        View view = mView.findViewById(R.id.imageView9);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).playBtn();
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_blankFragment5_to_blankFragment2);
            }
        });
//
        WebView webView = mView.findViewById(R.id.webView2);
        webView.loadUrl("https://www.doubleq666.cn/bobing/getRank");
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebChromeClient(new WebChromeClient());
        webView.setBackgroundColor(0);
//        webView.getBackground().setAlpha(0);



//        webView.getSettings().setStandardFontFamily(Typeface.createFromAsset(getActivity().getAssets(), "font/font1.otf"););
        return mView;
    }
}