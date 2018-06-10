package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Category;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates.BaseAdapterDelegate;
import java.util.List;

public class CategoriesAdapterDelegate extends BaseAdapterDelegate<List<Category>, CategoriesAdapterDelegate.CategoriesViewHolder> {

    public CategoriesAdapterDelegate(final Context context, final LayoutInflater inflater, final View.OnClickListener clickListener) {

        super(context, inflater, clickListener);
    }

    @Override
    protected boolean isForViewType(@NonNull final List<Category> items, final int position) {

        return !items.get(position).isLeaf;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent) {

        return new CategoriesViewHolder(mInflater.inflate(R.layout.cell_category, parent, false), mClickListener);
    }

    @Override
    protected void onBindViewHolderGeneric(@NonNull final List<Category> items, final int position, @NonNull final CategoriesViewHolder holder, @NonNull List<Object> payloads) {

        Category category = items.get(position);

        holder.categoryText.setText(category.name);
        holder.itemView.setTag(category);
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView categoryText;

        CategoriesViewHolder(final View view,
                             final View.OnClickListener onClickListener) {

            super(view);

            categoryText = view.findViewById(R.id.category_text);
            view.setOnClickListener(onClickListener);
        }
    }
}
