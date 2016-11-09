package gr.skroutz.gmetal.adapterdelegatessample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.CategoriesAndShopAdapter;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.CategoriesAndShopAdapterWithDelegates;
import gr.skroutz.gmetal.adapterdelegatessample.utils.DataUtils;

public class ComplexListActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_list);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this, 1));
        list.setAdapter(new CategoriesAndShopAdapterWithDelegates(this, getLayoutInflater(), this, DataUtils.getLeafSampleData(), DataUtils.getShopData()));
    }

    @Override
    public void onClick(final View view) {
        
    }
}
