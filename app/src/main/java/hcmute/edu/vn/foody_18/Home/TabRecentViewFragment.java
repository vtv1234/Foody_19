package hcmute.edu.vn.foody_18.Home;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import hcmute.edu.vn.foody_18.Database.DatabaseHandler;
import hcmute.edu.vn.foody_18.Home.Adapter.TabRecentViewAdapter;
import hcmute.edu.vn.foody_18.Model.FoodLocation;
import hcmute.edu.vn.foody_18.Model.Restaurant;
import hcmute.edu.vn.foody_18.R;
import hcmute.edu.vn.foody_18.ViewHolder.RecentViewHolder;

public class TabRecentViewFragment extends Fragment {

    View view;
    RecyclerView tabRecentViewRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Restaurant> restaurantArrayList;
    TabRecentViewAdapter tabRecentViewAdapter;
    FirestoreRecyclerAdapter<FoodLocation, RecentViewHolder> adapter;
    FirebaseFirestore rootRef;
    Query query;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TabRecentViewFragment() {
        // Required empty public constructor
    }

    public static TabRecentViewFragment newInstance(String param1, String param2) {
        TabRecentViewFragment fragment = new TabRecentViewFragment();
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
        view = inflater.inflate(R.layout.fragment_tab_recent_view, container, false);

//        tabRecentViewRecyclerView = view.findViewById(R.id.rcv_tab_recent_view);
//        layoutManager = new GridLayoutManager(getActivity(), 2);
//        tabRecentViewRecyclerView.setLayoutManager(layoutManager);
//        tabRecentViewRecyclerView.setNestedScrollingEnabled(true);
//
//        rootRef = FirebaseFirestore.getInstance();
//        query = rootRef.collection("FoodLocation");
//        loadData();

        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getActivity());
        restaurantArrayList = new ArrayList<Restaurant>();
        restaurantArrayList = databaseHandler.getAllRestaurants();
        tabRecentViewRecyclerView = view.findViewById(R.id.rcv_tab_recent_view);
        tabRecentViewAdapter = new TabRecentViewAdapter(getActivity().getApplicationContext(), getActivity(), restaurantArrayList);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        tabRecentViewRecyclerView.setLayoutManager(layoutManager);
        tabRecentViewAdapter.setData(restaurantArrayList);
        tabRecentViewRecyclerView.setAdapter(tabRecentViewAdapter);

        return view;
    }


//    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if(result.getResultCode() == Activity.RESULT_OK || result.getResultCode() == Activity.RESULT_CANCELED) {
//
//                    }
//                }
//            }
//    )
}