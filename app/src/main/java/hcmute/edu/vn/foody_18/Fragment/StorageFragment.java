package hcmute.edu.vn.foody_18.Fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import hcmute.edu.vn.foody_18.R;
import hcmute.edu.vn.foody_18.Storage.StorageViewPagerAdapter;

public class StorageFragment extends Fragment {

    private TabLayout storageTabLayout;
    private ViewPager2 viewPager2;
    private View view;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public StorageFragment() {
        // Required empty public constructor
    }

    public static StorageFragment newInstance(String param1, String param2) {
        StorageFragment fragment = new StorageFragment();
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
        view = inflater.inflate(R.layout.fragment_storage, container, false);
        storageTabLayout = view.findViewById(R.id.storage_tab_layout);
        viewPager2 = view.findViewById(R.id.storage_view_pager);
        StorageViewPagerAdapter adapter = new StorageViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        String[] tabTitles = {"Tất cả", "Địa điểm", "Hình ảnh", "Bài viết"};

        new TabLayoutMediator(storageTabLayout, viewPager2, (storageTabLayout, position) ->
                storageTabLayout.setText(tabTitles[position])
                ).attach();

        return view;
    }
}