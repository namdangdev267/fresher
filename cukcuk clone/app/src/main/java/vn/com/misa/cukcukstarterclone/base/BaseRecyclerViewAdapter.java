package vn.com.misa.cukcukstarterclone.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @created_by KhanhNQ on 07-Jan-21.
 */
public abstract class BaseRecyclerViewAdapter<T, V extends BaseViewHolder<T>> extends RecyclerView.Adapter<V> {

    protected List<T> items = new ArrayList<>();

    protected OnItemClickListener<T> listener;

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        holder.bindData(items.get(position));
    }

    public void setItemClickListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    public void loadItems(List<T> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public void insertItem(T newItem) {
        items.add(newItem);
        notifyItemInserted(items.indexOf(newItem));
    }

    public void removeItem(T item) {
        int pos = items.indexOf(item);
        items.remove(item);
        notifyItemRemoved(pos);
    }

    public void updateItem(int position, T item) {
        items.set(position, item);
        notifyItemChanged(position);
    }
}
