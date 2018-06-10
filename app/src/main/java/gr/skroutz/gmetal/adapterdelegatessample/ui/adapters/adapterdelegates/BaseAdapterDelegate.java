package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import java.util.List;

public abstract class BaseAdapterDelegate<T, K extends RecyclerView.ViewHolder>
    extends AdapterDelegate<T> {

    protected final Context mContext;
    protected final LayoutInflater mInflater;
    protected final View.OnClickListener mClickListener;

    public BaseAdapterDelegate(final Context context, final LayoutInflater inflater,
                               final View.OnClickListener clickListener) {

        mContext = context;
        mInflater = inflater;
        mClickListener = clickListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull final T items, final int position,
                                    @NonNull final RecyclerView.ViewHolder holder,
                                    @NonNull final List<Object> payloads) {

        onBindViewHolderGeneric(items, position, (K) holder, payloads);
    }

    protected abstract void onBindViewHolderGeneric(@NonNull final T items, final int position,
                                                    @NonNull final K holder,
                                                    @NonNull final List<Object> payloads);

    @FunctionalInterface
    public interface AdapterDelegateProvider<T> {

        AdapterDelegate<T> provideAdapterDelegate(final Context context,
                                                  final LayoutInflater inflater,
                                                  final View.OnClickListener clickListener);
    }
}
