package hcmute.edu.vn.foody_18.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import hcmute.edu.vn.foody_18.Event.ItemClickListener;
import hcmute.edu.vn.foody_18.R;

public class RecentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtLocationFoodName;
    public ShapeableImageView imageView;
//    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public RecentViewHolder(@NonNull View itemView) {
        super(itemView);

        txtLocationFoodName = (TextView) itemView.findViewById(R.id.txv_name_food_location);
        imageView = itemView.findViewById(R.id.thumb_img_food_location);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAbsoluteAdapterPosition(), false);
    }
}
