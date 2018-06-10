package gr.skroutz.gmetal.adapterdelegatessample.ui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates.BaseAdapterDelegate;
import java.util.List;

public class ViewModelDelegateHandler<T> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final View.OnClickListener mOnClickListener;
    protected AdapterDelegatesManager<List<T>> mAdapterDelegatesManager =
        new AdapterDelegatesManager<>();

    public ViewModelDelegateHandler(final Context context, final LayoutInflater layoutInflater,
                                    final View.OnClickListener onClickListener) {

        mContext = context;
        mLayoutInflater = layoutInflater;
        mOnClickListener = onClickListener;
    }

    public void addAdapterDelegate(final int viewType,
                                   final BaseAdapterDelegate.AdapterDelegateProvider<List<T>> adapterDelegateProvider) {

        mAdapterDelegatesManager.addDelegate(viewType,
                                             adapterDelegateProvider.provideAdapterDelegate(
                                                 mContext, mLayoutInflater, mOnClickListener));
    }

    public void onBindViewModel(final RecyclerView.ViewHolder holder, final int position,
                                final BaseViewModel<T> viewModel) {

        mAdapterDelegatesManager.onBindViewHolder(viewModel.getData(),
                                                  viewModel.getRealPosition(position), holder);
    }

    public boolean handlesViewType(final int viewType) {

        return mAdapterDelegatesManager.getDelegateForViewType(viewType) != null;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        return mAdapterDelegatesManager.onCreateViewHolder(parent, viewType);
    }

    public int getItemViewType(final int position, final BaseViewModel<T> viewModel) {

        return mAdapterDelegatesManager.getItemViewType(viewModel.getData(),
                                                        viewModel.getRealPosition(position));
    }

    public static class Builder<T> {

        private ViewModelDelegateHandler<T> mInstance;

        protected Builder(final Context context, final LayoutInflater layoutInflater,
                          final View.OnClickListener onClickListener) {

            mInstance = new ViewModelDelegateHandler<>(context, layoutInflater, onClickListener);
        }

        public static <T> Builder<T> withHandler(final Context context,
                                                 final LayoutInflater layoutInflater,
                                                 final View.OnClickListener onClickListener) {

            return new Builder<>(context, layoutInflater, onClickListener);
        }

        public static <T> Builder<T> withHandler(final Context context,
                                                 final View.OnClickListener onClickListener) {

            return new Builder<>(context, LayoutInflater.from(context), onClickListener);
        }

        public Builder<T> withDelegate(final int viewType,
                                       final BaseAdapterDelegate.AdapterDelegateProvider<List<T>> adapterDelegateProvider) {

            mInstance.mAdapterDelegatesManager.addDelegate(viewType,
                                                           adapterDelegateProvider.provideAdapterDelegate(
                                                               mInstance.mContext,
                                                               mInstance.mLayoutInflater,
                                                               mInstance.mOnClickListener));
            return this;
        }

        public ViewModelDelegateHandler<T> create() {

            return mInstance;
        }
    }
}
