package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Shop;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates.BaseAdapterDelegate;
import java.util.List;

public class ShopViewModelAdapterDelegate
    extends BaseAdapterDelegate<List<Shop>, ShopViewModelAdapterDelegate.ShopViewHolder> {

    public ShopViewModelAdapterDelegate(final Context context, final LayoutInflater inflater,
                                        final View.OnClickListener clickListener) {

        super(context, inflater, clickListener);
    }

    @Override
    protected boolean isForViewType(@NonNull final List<Shop> items, final int position) {

        return true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent) {

        return new ShopViewHolder(mInflater.inflate(R.layout.cell_shop, parent, false),
                                  mClickListener);
    }

    @Override
    protected void onBindViewHolderGeneric(@NonNull final List<Shop> items, final int position,
                                           @NonNull final ShopViewHolder holder,
                                           @NonNull List<Object> payloads) {

        final Shop shop = items.get(position);
        holder.shopText.setText(shop.name);
        holder.itemView.setTag(shop);
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {

        TextView shopText;

        ShopViewHolder(final View view, View.OnClickListener listener) {

            super(view);

            shopText = view.findViewById(R.id.shop_text);
            view.setOnClickListener(listener);
        }
    }
}
