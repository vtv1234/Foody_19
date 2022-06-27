package hcmute.edu.vn.foody_18.Fragment;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hcmute.edu.vn.foody_18.R;
import hcmute.edu.vn.foody_18.SignInActivity;

public class AccountFragment extends Fragment {

    private static final String USER_NAME = "NameUser";
    private String nameUser = "";
    TextView accountName;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        View view =  inflater.inflate(R.layout.fragment_account, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("currentUser", Context.MODE_PRIVATE);

        accountName = view.findViewById(R.id.accountFragment_textview_accountName);

        accountName.setText(sharedPreferences.getString("email", "Đăng nhập tài khoản"));

        accountName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SignInActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}