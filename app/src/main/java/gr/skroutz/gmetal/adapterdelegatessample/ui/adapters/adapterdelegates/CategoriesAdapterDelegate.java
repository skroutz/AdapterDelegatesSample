package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;

import java.util.List;

import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Category;

public class CategoriesAdapterDelegate implements AdapterDelegate<List<Category>> {

    protected final Context mContext;
    protected final LayoutInflater mInflater;
    protected final View.OnClickListener mClickListener;

    public CategoriesAdapterDelegate(final Context context, final LayoutInflater inflater, final View.OnClickListener clickListener) {

        mContext = context;
        mInflater = inflater;
        mClickListener = clickListener;
    }

    @Override
    public boolean isForViewType(@NonNull final List<Category> items, final int position) {

        return true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent) {

        return new CategoriesViewHolder(mInflater.inflate(R.layout.cell_category, parent, false), mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final List<Category> items, final int position, @NonNull final RecyclerView.ViewHolder holder) {

        CategoriesViewHolder viewHolder = (CategoriesViewHolder) holder;
        Category category = items.get(position);

        viewHolder.categoryText.setText(category.name);
        viewHolder.itemView.setTag(category);
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView categoryText;

        CategoriesViewHolder(final View view,
                             final View.OnClickListener onClickListener) {

            super(view);

            categoryText = (TextView) view.findViewById(R.id.category_text);
            view.setOnClickListener(onClickListener);
        }
    }
}
