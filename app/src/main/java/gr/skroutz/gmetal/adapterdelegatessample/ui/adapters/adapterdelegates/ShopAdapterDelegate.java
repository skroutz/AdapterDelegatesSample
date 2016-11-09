package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;

import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Shop;

public class ShopAdapterDelegate implements AdapterDelegate<Shop> {

    private final Context mContext;
    private final LayoutInflater mInflater;
    private final View.OnClickListener mClickListener;

    public ShopAdapterDelegate(final Context context, final LayoutInflater inflater, final View.OnClickListener clickListener) {

        mContext = context;
        mInflater = inflater;
        mClickListener = clickListener;
    }

    @Override
    public boolean isForViewType(@NonNull final Shop items, final int position) {

        return position == 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent) {

        return new ShopViewHolder(mInflater.inflate(R.layout.cell_shop,
                                                    parent, false), mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final Shop items, final int position, @NonNull final RecyclerView.ViewHolder holder) {

        ShopViewHolder viewHolder = (ShopViewHolder) holder;
        viewHolder.shopText.setText(items.name);
        viewHolder.itemView.setTag(items);
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
