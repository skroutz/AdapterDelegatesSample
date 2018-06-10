package gr.skroutz.gmetal.adapterdelegatessample.ui.common;

import android.arch.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewModel<T> extends ViewModel {

    protected int mStartingPosition;

    protected List<T> mData = new ArrayList<>();

    public void setData(final List<T> data) {

        mData = data;
    }

    public void addData(final T dataItem) {

        mData.add(dataItem);
    }

    public List<T> getData() {

        return mData;
    }

    public void setStartingPosition(final int startingPosition) {

        mStartingPosition = startingPosition;
    }

    public int getStartingPosition() {

        return mStartingPosition;
    }

    public boolean handlesPosition(final int position) {

        if ((position >= mStartingPosition) && (position < (mStartingPosition + getItemCount()))) {
            return true;
        }

        return false;
    }

    protected int getRealPosition(final int position) {

        return position - mStartingPosition;
    }

    public int getItemCount() {

        return mData.size();
    }
}
