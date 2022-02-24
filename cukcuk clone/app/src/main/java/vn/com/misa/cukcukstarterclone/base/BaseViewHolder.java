package vn.com.misa.cukcukstarterclone.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @created_by KhanhNQ on 07-Jan-21.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    private T item;

    public BaseViewHolder(@NonNull View itemView, final OnItemClickListener<T> listener) {
        super(itemView);

        itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(item, getAdapterPosition());
            }
        });
    }

    public void bindData(T item) {
        this.item = item;
    }
}
