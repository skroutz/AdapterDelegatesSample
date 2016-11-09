package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Category;
import gr.skroutz.gmetal.adapterdelegatessample.ui.common.BaseAdapter;

public class CategoriesAdapter extends BaseAdapter<Category> {

    public static final int CATEGORY_VIEW_TYPE = BASE_VIEW_ID_START + 1;

    public CategoriesAdapter(final Context context, final LayoutInflater layoutInflater, final View.OnClickListener onClickListener, List<Category> data) {

        super(context, layoutInflater, onClickListener);
        addData(data);
    }

    @Override
    public int getItemViewType(final int position) {

        int baseItemViewType = super.getItemViewType(position);

        if (baseItemViewType == INVALID_VIEW) {
            return CATEGORY_VIEW_TYPE;
        }

        return baseItemViewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            final ViewGroup parent, final int viewType) {

        if (viewType == CATEGORY_VIEW_TYPE) {
            final View newView = mInflater.inflate(
                    R.layout.cell_category, parent, false);
            return new CategoriesViewHolder(newView,
                                            mClickListener);
        }

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(
            final RecyclerView.ViewHolder holder, final int position) {

        if (holder.getItemViewType() == CATEGORY_VIEW_TYPE) {
            CategoriesViewHolder viewHolder = (CategoriesViewHolder) holder;
            Category category = mData.get(position);

            viewHolder.categoryText.setText(category.name);
            viewHolder.itemView.setTag(category);
        } else {
            super.onBindViewHolder(holder, position);
        }
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
