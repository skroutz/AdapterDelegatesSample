package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Category;

public class LeafCategoriesAdapterDelegate extends CategoriesAdapterDelegate {

    private final Drawable mPlayDrawable;
    private final Drawable mSaveDrawable;

    public LeafCategoriesAdapterDelegate(final Context context, final LayoutInflater inflater, final View.OnClickListener clickListener) {

        super(context, inflater, clickListener);
        mPlayDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_play);
        mSaveDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_save);
    }

    @Override
    protected boolean isForViewType(@NonNull final List<Category> items, final int position) {

        return items.get(position).isLeaf;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent) {

        return new LeafCategoryViewHolder(
                mInflater.inflate(R.layout.cell_leaf_category,
                                  parent, false), mClickListener);
    }

    @Override
    protected void onBindViewHolderGeneric(@NonNull final List<Category> items, final int position, @NonNull final CategoriesViewHolder holder, @NonNull List<Object> payloads) {

        super.onBindViewHolderGeneric(items, position, holder, payloads);
        LeafCategoryViewHolder viewHolder = (LeafCategoryViewHolder) holder;
        if (position % 2 == 0) {
            viewHolder.categoryIcon.setImageDrawable(mPlayDrawable);
        } else {
            viewHolder.categoryIcon.setImageDrawable(mSaveDrawable);
        }
    }

    static class LeafCategoryViewHolder extends CategoriesAdapterDelegate.CategoriesViewHolder {

        ImageView categoryIcon;

        LeafCategoryViewHolder(final View view, View.OnClickListener listener) {

            super(view, listener);
            categoryIcon = (ImageView) view.findViewById(R.id.category_icon);
        }
    }
}
