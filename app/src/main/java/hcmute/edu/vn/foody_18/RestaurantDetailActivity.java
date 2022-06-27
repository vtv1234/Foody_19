package hcmute.edu.vn.foody_18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import hcmute.edu.vn.foody_18.Database.DatabaseHandler;
import hcmute.edu.vn.foody_18.Model.Restaurant;
import hcmute.edu.vn.foody_18.Restaurant.Adapter.RestaurantViewPagerAdapter;

public class RestaurantDetailActivity extends AppCompatActivity {
    private static final String ARG_RESTAURANT_ID = "restaurantId";
    private DatabaseHandler databaseHandler;
    private Restaurant currentRestaurant;
    private int restaurantId = 0;

    private ImageView coverImage;
    private TextView txtRestaurantName;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private EditText editText;
    private MaterialButton searchBtn;
    private MaterialButton backBtn;
    private MaterialButton shareBtn;

    private void mapping() {
        this.coverImage = findViewById(R.id.restaurantA_cover_image);
        this.txtRestaurantName = findViewById(R.id.restaurantA_txv_restaurant_name);
        this.toolbar = findViewById(R.id.restaurantA_toolbar);
        this.appBarLayout = findViewById(R.id.restaurantA_appbar);
        this.backBtn = findViewById(R.id.restaurantA_btn_back);
        this.editText = findViewById(R.id.restaurantA_edt_search);
        this.searchBtn = findViewById(R.id.restaurantA_btn_search);
        this.shareBtn = findViewById(R.id.restaurantA_btn_share);

        this.tabLayout = findViewById(R.id.restaurantA_tab_layout);
        this.viewPager2 = findViewById(R.id.restaurantA_view_pager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        mapping();

        if(getIntent() != null) {
            this.restaurantId = getIntent().getIntExtra(ARG_RESTAURANT_ID, 0);
            Log.d("RestaurantDetails", "Restaurant Id: " + restaurantId);
            if(restaurantId != 0) {
                databaseHandler = DatabaseHandler.getInstance(getApplicationContext());
                currentRestaurant = databaseHandler.getRestaurant(restaurantId);
                Picasso.get().load(currentRestaurant.getCoverImage()).into(this.coverImage);
                txtRestaurantName.setText(txtRestaurantName.getText() + currentRestaurant.getName());
            }
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        });


        this.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = -1;
            @SuppressLint("ResourceType")
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange + verticalOffset == 0) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    backBtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));
                    backBtn.setIconTintResource(R.color.primary_color);
                    editText.setVisibility(View.VISIBLE);
                    searchBtn.setVisibility(View.INVISIBLE);
                    shareBtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));
                    shareBtn.setIconTintResource(R.color.primary_color);
                }
                else {
                    toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));
                    backBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.crystal_clear_button_bg));
                    backBtn.setIconTintResource(R.color.white);
                    editText.setVisibility(View.INVISIBLE);
                    searchBtn.setVisibility(View.VISIBLE);
                    shareBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.crystal_clear_button_bg));
                    shareBtn.setIconTintResource(R.color.white);
                }
            }
        });


        RestaurantViewPagerAdapter restaurantViewPagerAdapter = new RestaurantViewPagerAdapter(this, restaurantId);
        viewPager2.setAdapter(restaurantViewPagerAdapter);

        String[] tabTitles = {"Đặt đơn", "Bình luận", "Thông tin"};

        new TabLayoutMediator(tabLayout, viewPager2, true, false, (tab, position) -> {
            tab.setText(tabTitles[position]);
            if(tab != null) {
                TextView tabTextView = (TextView) LayoutInflater.from(this.getApplicationContext()).inflate(R.layout.layout_tab_item, tabLayout, false);
                tabTextView.setText(tab.getText());
                tabTextView.setTextAppearance(getApplicationContext(), position == 0 ? R.style.TextAppearance_Tabs_style_2_Selected : R.style.TextAppearance_Tabs_style_2);
                tab.setCustomView(tabTextView);
            }
        }).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab selectedTab) {
                int tabCount = tabLayout.getTabCount();
                for(int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tab = tabLayout.getTabAt(i);
                    View tabView = tab != null ? tab.getCustomView() : null;

                    if(tabView instanceof TextView) {
                        ((TextView) tabView).setTextAppearance(getApplicationContext(), selectedTab.equals(tab)
                                ? R.style.TextAppearance_Tabs_style_2_Selected
                                : R.style.TextAppearance_Tabs_style_2
                        );
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}