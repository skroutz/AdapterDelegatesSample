package gr.skroutz.gmetal.adapterdelegatessample.ui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gr.skroutz.gmetal.adapterdelegatessample.model.BaseObject;

public abstract class BaseAdapter<T extends BaseObject> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Constants
    protected static final int BASE_VIEW_ID_START = 100;
    protected static final int INVALID_VIEW = -3;

    // Attributes
    protected List<T> mData;
    protected final LayoutInflater mInflater;
    protected final Context mContext;

    // UI
    protected final View.OnClickListener mClickListener;

    // Constructor
    public BaseAdapter(final Context context, final LayoutInflater layoutInflater, final View.OnClickListener onClickListener) {

        mData = new ArrayList<>();
        mInflater = layoutInflater;
        mContext = context;
        mClickListener = onClickListener;
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

        return INVALID_VIEW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        throw new IllegalArgumentException("Invalid view type specified: " + viewType);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

    }

}
