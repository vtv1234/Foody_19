package hcmute.edu.vn.foody_18.Restaurant;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hcmute.edu.vn.foody_18.Database.DatabaseHandler;
import hcmute.edu.vn.foody_18.Model.Menu;
import hcmute.edu.vn.foody_18.R;
import hcmute.edu.vn.foody_18.Restaurant.Adapter.MenuAdapter;

public class TabOrderFragment extends Fragment {
    private static final String ARG_RESTAURANT_ID = "restaurantId";
    private int restaurantId;

    View view;
    RecyclerView menuRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Menu> menuArrayList;
    MenuAdapter menuAdapter;

    public TabOrderFragment() {
        // Required empty public constructor
    }

    public static TabOrderFragment newInstance(int restaurantId) {
        TabOrderFragment fragment = new TabOrderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_RESTAURANT_ID, restaurantId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurantId = getArguments().getInt(ARG_RESTAURANT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_order, container, false);
        if(restaurantId != 0) {
            DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getActivity());
            menuArrayList = new ArrayList<Menu>();
            menuArrayList = databaseHandler.getAllMenuFoodsByIdRestaurant(restaurantId);
            menuRecyclerView = view.findViewById(R.id.rcv_foods_menu);
            menuAdapter = new MenuAdapter(getActivity().getApplicationContext());
            layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            menuRecyclerView.setLayoutManager(layoutManager);
            menuAdapter.setData(menuArrayList);
            menuRecyclerView.setAdapter(menuAdapter);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TabOrderFragment", "Restaurant Id = " + restaurantId);

    }
}