package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gr.skroutz.gmetal.adapterdelegatessample.model.Category;
import gr.skroutz.gmetal.adapterdelegatessample.model.Shop;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates.CategoriesAdapterDelegate;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates.LeafCategoriesAdapterDelegate;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates.ShopAdapterDelegate;
import gr.skroutz.gmetal.adapterdelegatessample.ui.common.BaseAdapterWithDelegates;

public class CategoriesAndShopAdapterWithDelegates extends BaseAdapterWithDelegates<Category> {

    private static final int SHOP_VIEW_TYPE = 1001;

    public CategoriesAndShopAdapterWithDelegates(final Context context, final LayoutInflater layoutInflater,
                                                 final View.OnClickListener onClickListener,
                                                 final List<Category> data, final Shop shop) {

        super(context, layoutInflater, onClickListener);
        addData(data);
        mAdapterDelegateManager.addDelegate(new LeafCategoriesAdapterDelegate(mContext, mInflater, mClickListener));
        mAdapterDelegateManager.addDelegate(SHOP_VIEW_TYPE, new ShopAdapterDelegate<List<Category>>(mContext, mInflater, mClickListener, shop));
        mAdapterDelegateManager.setFallbackDelegate(new CategoriesAdapterDelegate(mContext, mInflater, mClickListener));
    }

    @Override
    public int getItemViewType(final int position) {

        if (position == 0) {
            return SHOP_VIEW_TYPE;
        }
        return super.getItemViewType(position - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        return mAdapterDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        int realDataPosition = (holder.getItemViewType() != SHOP_VIEW_TYPE) ? position - 1 : position;
        mAdapterDelegateManager.onBindViewHolder(mData, realDataPosition, holder);
    }

    @Override
    public int getItemCount() {

        return super.getItemCount() + 1;
    }
}
