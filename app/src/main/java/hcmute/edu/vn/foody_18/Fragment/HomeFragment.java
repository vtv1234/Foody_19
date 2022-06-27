package hcmute.edu.vn.foody_18.Fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import hcmute.edu.vn.foody_18.R;
import hcmute.edu.vn.foody_18.Home.Adapter.HomeViewPagerAdapter;

public class HomeFragment extends Fragment {

    private TabLayout homeTabLayout;
    private ViewPager2 viewPager2;
    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        homeTabLayout = view.findViewById(R.id.home_tab_layout);
        viewPager2 = view.findViewById(R.id.home_view_pager);
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        String[] tabTitles = {"Quán ăn", "Gần tôi"};

        new TabLayoutMediator(homeTabLayout, viewPager2, true, false, (tab, position) -> {
            tab.setText(tabTitles[position]);
            if(tab != null) {
                TextView tabTextView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.layout_tab_item, homeTabLayout, false);
                tabTextView.setText(tab.getText());
                tabTextView.setTextAppearance(getContext(), position == 0 ? R.style.TextAppearance_Tabs_style_1_Selected : R.style.TextAppearance_Tabs_style_1);
                tab.setCustomView(tabTextView);
            }
        }).attach();


        homeTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab selectedTab) {
                int tabCount = homeTabLayout.getTabCount();
                for(int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tab = homeTabLayout.getTabAt(i);
                    View tabView = tab != null ? tab.getCustomView() : null;

                    if(tabView instanceof TextView) {
                        ((TextView) tabView).setTextAppearance(getContext(), selectedTab.equals(tab)
                                ? R.style.TextAppearance_Tabs_style_1_Selected
                                : R.style.TextAppearance_Tabs_style_1
                        );
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
        return view;
    }
}