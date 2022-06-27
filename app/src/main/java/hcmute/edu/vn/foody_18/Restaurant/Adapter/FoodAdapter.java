package hcmute.edu.vn.foody_18.Restaurant.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_18.Event.ItemClickListener;
import hcmute.edu.vn.foody_18.Model.Food;
import hcmute.edu.vn.foody_18.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private ArrayList<Food> foodList;

    public FoodAdapter(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food =foodList.get(position);
        Picasso.get().load(food.getThumbImage()).into(holder.foodThumbImage);
        holder.foodName.setText(food.getName());
        holder.foodDescription.setText(food.getDescription());
        double price = Double.parseDouble(food.getPrice());
        DecimalFormat formatter = new DecimalFormat("#,###");
        holder.foodPrice.setText(formatter.format(price) + "đ");

    }

    @Override
    public int getItemCount() {
        if(foodList != null) {
            return foodList.size();
        }
        return 0;
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView foodThumbImage;
        private TextView foodName;
        private TextView foodDescription;
        private TextView foodPrice;

        private ItemClickListener itemClickListener;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodThumbImage = itemView.findViewById(R.id.image_food);
            foodName = itemView.findViewById(R.id.txv_food_name);
            foodDescription = itemView.findViewById(R.id.txv_food_description);
            foodPrice = itemView.findViewById(R.id.txv_food_price);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAbsoluteAdapterPosition(), false);
        }
    }
}
