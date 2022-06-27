package hcmute.edu.vn.foody_18.Restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_18.Event.ItemClickListener;
import hcmute.edu.vn.foody_18.Model.Menu;
import hcmute.edu.vn.foody_18.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    Context context;

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    ArrayList<Menu> menuList;

    public MenuAdapter(ArrayList<Menu> menuList) {
        this.menuList = menuList;
    }

    public MenuAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Menu> menuList) {
        this.menuList = menuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_foods_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        holder.txtMenuTitle.setText(menu.getTitle() + " (" + String.valueOf(menu.getQuantity()) + ")");

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.rcvListFoods.getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setInitialPrefetchItemCount(menu.getFoods().size());
        FoodAdapter foodAdapter = new FoodAdapter(menu.getFoods());

        holder.rcvListFoods.setLayoutManager(layoutManager);
        holder.rcvListFoods.setAdapter(foodAdapter);
        holder.rcvListFoods.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        if(menuList != null) {
            return menuList.size();
        }
        return 0;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMenuTitle;
        private RecyclerView rcvListFoods;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMenuTitle = itemView.findViewById(R.id.txv_menu_title);
            rcvListFoods = itemView.findViewById(R.id.rcv_list_foods);
        }


    }
}
