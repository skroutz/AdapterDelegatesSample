package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Category;
import gr.skroutz.gmetal.adapterdelegatessample.model.Shop;

public class CategoriesAndShopAdapter extends CategoriesAdapter {

    private static final int LEAF_CATEGORY_VIEW_TYPE = BASE_VIEW_ID_START + 2;
    private static final int SHOP_VIEW_TYPE = BASE_VIEW_ID_START + 3;

    private static final int DEFAULT_SHOP_POSITION = 0;

    private Shop mShop;
    private Drawable mPlayDrawable;
    private Drawable mSaveDrawable;

    public CategoriesAndShopAdapter(final Context context, final LayoutInflater layoutInflater, final View.OnClickListener onClickListener, final List<Category> data, Shop shop) {

        super(context, layoutInflater, onClickListener, data);
        mShop = shop;
        mPlayDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_play);
        mSaveDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_save);
    }

    @Override
    public int getItemViewType(final int position) {

        if (position == DEFAULT_SHOP_POSITION) {
            return SHOP_VIEW_TYPE;
        } else if (mData.get(position).isLeaf) {
            return LEAF_CATEGORY_VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            final ViewGroup parent, final int viewType) {

        if (viewType == LEAF_CATEGORY_VIEW_TYPE) {
            return new LeafCategoryViewHolder(
                    mInflater.inflate(R.layout.cell_leaf_category,
                                      parent, false), mClickListener);
        } else if (viewType == SHOP_VIEW_TYPE) {
            return new ShopViewHolder(
                    mInflater.inflate(R.layout.cell_shop,
                                      parent, false), mClickListener);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(
            final RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == LEAF_CATEGORY_VIEW_TYPE) {
            LeafCategoryViewHolder viewHolder =
                    (LeafCategoryViewHolder) holder;
            viewHolder.categoryText.setText(mData.get(position).name);
            if (position % 2 == 0) {
                viewHolder.categoryIcon.setImageDrawable(mPlayDrawable);
            } else {
                viewHolder.categoryIcon.setImageDrawable(mSaveDrawable);
            }
        } else if (holder.getItemViewType() == SHOP_VIEW_TYPE) {
            ShopViewHolder viewHolder = (ShopViewHolder) holder;
            viewHolder.shopText.setText(mShop.name);
            viewHolder.itemView.setTag(mShop);
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {

        TextView shopText;

        ShopViewHolder(final View view, View.OnClickListener listener) {

            super(view);

            shopText = (TextView) view.findViewById(R.id.shop_text);
            view.setOnClickListener(listener);
        }
    }

    static class LeafCategoryViewHolder extends CategoriesViewHolder {

        ImageView categoryIcon;

        LeafCategoryViewHolder(final View view, View.OnClickListener listener) {

            super(view, listener);
            categoryIcon = (ImageView) view.findViewById(R.id.category_icon);
        }
    }
}
