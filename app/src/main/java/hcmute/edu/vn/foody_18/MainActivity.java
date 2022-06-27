package hcmute.edu.vn.foody_18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hcmute.edu.vn.foody_18.Fragment.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewPager2 = findViewById(R.id.view_pager);
        this.bottomNavigationView =findViewById(R.id.bottom_navigation);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.menu_home) {
                viewPager2.setCurrentItem(0);
            } else if(id == R.id.menu_saved) {
                viewPager2.setCurrentItem(1);
            } else if(id == R.id.menu_order_management) {
                viewPager2.setCurrentItem(2);
            } else if(id == R.id.menu_notification) {
                viewPager2.setCurrentItem(3);
            } else if(id == R.id.menu_account) {
                viewPager2.setCurrentItem(4);
            }
            return true;
        });
        viewPager2.setUserInputEnabled(false);
    }
}