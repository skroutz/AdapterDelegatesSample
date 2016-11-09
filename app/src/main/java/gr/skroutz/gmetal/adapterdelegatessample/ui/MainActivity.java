package gr.skroutz.gmetal.adapterdelegatessample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Category;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.CategoriesAdapter;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.CategoriesAdapterWithDelegates;
import gr.skroutz.gmetal.adapterdelegatessample.utils.DataUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PARAM_LEAF = "is.leaf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isLeaf = getIntent().getBooleanExtra(PARAM_LEAF, false);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this, 1));
        //list.setAdapter(new CategoriesAdapter(this, getLayoutInflater(), this, isLeaf ? DataUtils.getLeafSampleData() : DataUtils.getNonLeafSampleData()));
        list.setAdapter(new CategoriesAdapterWithDelegates(this, getLayoutInflater(), this, isLeaf ? DataUtils.getLeafSampleData() : DataUtils.getNonLeafSampleData()));
    }

    @Override
    public void onClick(final View view) {

        Category category = (Category) view.getTag();

        if (category.isLeaf) {
            startActivity(new Intent(this, ComplexListActivity.class));
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(PARAM_LEAF, true);
            startActivity(intent);
        }
    }
}
