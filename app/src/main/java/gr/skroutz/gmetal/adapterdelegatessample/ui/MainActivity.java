package gr.skroutz.gmetal.adapterdelegatessample.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Category;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels.CategoriesAdapterDelegate;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels.CategoriesViewModel;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels.LeafCategoriesAdapterDelegate;
import gr.skroutz.gmetal.adapterdelegatessample.ui.common.BaseAdapterWithViewModel;
import gr.skroutz.gmetal.adapterdelegatessample.ui.common.ViewModelDelegateHandler.Builder;
import java.util.List;

import static gr.skroutz.gmetal.adapterdelegatessample.utils.DataUtils.getLeafSampleData;
import static gr.skroutz.gmetal.adapterdelegatessample.utils.DataUtils.getNonLeafSampleData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PARAM_LEAF = "is.leaf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final boolean isLeaf = getIntent().getBooleanExtra(PARAM_LEAF, false);

        final RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this, 1));

        final List<Category> data = isLeaf ? getLeafSampleData() : getNonLeafSampleData();
        final BaseAdapterWithViewModel adapter =
            new BaseAdapterWithViewModel(LayoutInflater.from(this));
        final CategoriesViewModel categoriesViewModel = ViewModelProviders.of(this)
                                                                          .get(
                                                                              CategoriesViewModel.class);
        categoriesViewModel.setData(data);
        adapter.addBaseViewModel(categoriesViewModel,
                                 Builder.<Category>withHandler(this, this).withDelegate(1,
                                                                                        CategoriesAdapterDelegate::new)
                                                                          .withDelegate(2,
                                                                                        LeafCategoriesAdapterDelegate::new)
                                                                          .create());
        list.setAdapter(adapter);
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
