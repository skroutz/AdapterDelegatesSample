package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Shop;

public class ShopAdapterDelegate<T> extends BaseAdapterDelegate<T, ShopAdapterDelegate.ShopViewHolder> {

    private Shop mShop;

    public ShopAdapterDelegate(final Context context, final LayoutInflater inflater, final View.OnClickListener clickListener, final Shop shop) {

        super(context, inflater, clickListener);
        mShop = shop;
    }

    @Override
    protected boolean isForViewType(@NonNull final T items, final int position) {

        return (position == 0);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent) {

        return new ShopViewHolder(mInflater.inflate(R.layout.cell_shop,
                                                    parent, false), mClickListener);
    }

    @Override
    protected void onBindViewHolderGeneric(@NonNull final T items, final int position, @NonNull final ShopViewHolder holder, @NonNull List<Object> payloads) {

        holder.shopText.setText(mShop.name);
        holder.itemView.setTag(mShop);
    }

    public void setShop(Shop shop) {

        mShop = shop;
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {

        TextView shopText;

        ShopViewHolder(final View view, View.OnClickListener listener) {

            super(view);

            shopText = (TextView) view.findViewById(R.id.shop_text);
            view.setOnClickListener(listener);
        }
    }
}
