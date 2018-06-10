package gr.skroutz.gmetal.adapterdelegatessample.ui.common;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class BaseAdapterWithViewModel extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Pair<BaseViewModel, ViewModelDelegateHandler>> mViewModels = new ArrayList<>();

    // Constructor
    public BaseAdapterWithViewModel(final LayoutInflater layoutInflater) {

        mInflater = layoutInflater;
    }

    public void addBaseViewModel(final BaseViewModel viewModel,
                                 final ViewModelDelegateHandler delegateHandler) {

        mViewModels.add(new Pair<>(viewModel, delegateHandler));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        for (final Pair<BaseViewModel, ViewModelDelegateHandler> viewModelPair : mViewModels) {
            if (viewModelPair.second.handlesViewType(viewType)) {
                return viewModelPair.second.onCreateViewHolder(parent, viewType);
            }
        }

        return new DefaultViewHolder(
            mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Pair<BaseViewModel, ViewModelDelegateHandler> byPosition = getByPosition(position);
        if (byPosition != null) {
            byPosition.second.onBindViewModel(holder, position, byPosition.first);
        }
    }

    @Override
    public int getItemViewType(final int position) {

        final Pair<BaseViewModel, ViewModelDelegateHandler> byPosition = getByPosition(position);
        if (byPosition != null) {
            return byPosition.second.getItemViewType(position, byPosition.first);
        }

        return super.getItemViewType(position);
    }

    private Pair<BaseViewModel, ViewModelDelegateHandler> getByPosition(final int position) {

        for (final Pair<BaseViewModel, ViewModelDelegateHandler> viewModelPair : mViewModels) {
            if (viewModelPair.first.handlesPosition(position)) {
                return viewModelPair;
            }
        }

        return null;
    }

    @Override
    public int getItemCount() {

        int itemCount = 0;
        for (final Pair<BaseViewModel, ViewModelDelegateHandler> viewModelPair : mViewModels) {
            itemCount += viewModelPair.first.getItemCount();
        }
        return itemCount;
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        DefaultViewHolder(final View itemView) {

            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
