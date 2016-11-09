package gr.skroutz.gmetal.adapterdelegatessample.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import gr.skroutz.gmetal.adapterdelegatessample.model.Category;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.adapterdelegates.CategoriesAdapterDelegate;
import gr.skroutz.gmetal.adapterdelegatessample.ui.common.BaseAdapterWithDelegates;

public class CategoriesAdapterWithDelegates extends BaseAdapterWithDelegates<Category> {

    public CategoriesAdapterWithDelegates(final Context context, final LayoutInflater layoutInflater,
                                          final View.OnClickListener onClickListener, List<Category> data) {

        super(context, layoutInflater, onClickListener);
        addData(data);
        mAdapterDelegateManager.addDelegate(new CategoriesAdapterDelegate(mContext, mInflater, mClickListener));
    }
}
