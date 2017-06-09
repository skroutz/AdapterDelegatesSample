package gr.skroutz.gmetal.adapterdelegatessample.ui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterWithDelegates<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Attributes
    protected List<T> mData;
    protected final LayoutInflater mInflater;
    protected final Context mContext;
    protected final AdapterDelegatesManager<List<T>> mAdapterDelegateManager;

    // UI
    protected final View.OnClickListener mClickListener;

    // Constructor
    public BaseAdapterWithDelegates(final Context context, final LayoutInflater layoutInflater, final View.OnClickListener onClickListener) {

        mData = new ArrayList<>();
        mInflater = layoutInflater;
        mContext = context;
        mClickListener = onClickListener;
        mAdapterDelegateManager = new AdapterDelegatesManager<>();
    }

    @Override
    public long getItemId(final int position) {

        return mData.get(position).hashCode();
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    // Properties
    @SuppressWarnings("unused")
    public void setData(final List<T> list) {

        mData = list;
    }

    public void addData(final List<T> list) {

        if (list == null) {
            return;
        }

        mData.addAll(list);
    }

    @Override
    public int getItemViewType(final int position) {

        return mAdapterDelegateManager.getItemViewType(mData, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        return mAdapterDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        mAdapterDelegateManager.onBindViewHolder(mData, position, holder);
    }
}
