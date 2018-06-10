package gr.skroutz.gmetal.adapterdelegatessample.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import gr.skroutz.gmetal.adapterdelegatessample.R;
import gr.skroutz.gmetal.adapterdelegatessample.model.Category;
import gr.skroutz.gmetal.adapterdelegatessample.model.Shop;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels.CategoriesAdapterDelegate;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels.CategoriesViewModel;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels.LeafCategoriesAdapterDelegate;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels.ShopViewModel;
import gr.skroutz.gmetal.adapterdelegatessample.ui.adapters.viewmodels.ShopViewModelAdapterDelegate;
import gr.skroutz.gmetal.adapterdelegatessample.ui.common.BaseAdapterWithViewModel;

import static gr.skroutz.gmetal.adapterdelegatessample.ui.common.ViewModelDelegateHandler.Builder;
import static gr.skroutz.gmetal.adapterdelegatessample.utils.DataUtils.getLeafSampleData;
import static gr.skroutz.gmetal.adapterdelegatessample.utils.DataUtils.getShopData;

public class ComplexListActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_list);

        final RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this, 1));

        final BaseAdapterWithViewModel adapter =
            new BaseAdapterWithViewModel(LayoutInflater.from(this));
        final ShopViewModel shopViewModel = ViewModelProviders.of(this)
                                                              .get(ShopViewModel.class);

        final CategoriesViewModel categoriesViewModel = ViewModelProviders.of(this)
                                                                          .get(
                                                                              CategoriesViewModel.class);
        categoriesViewModel.setStartingPosition(1);

        categoriesViewModel.setData(getLeafSampleData());
        if (shopViewModel.getItemCount() == 0) {
            shopViewModel.addData(getShopData());
        }

        adapter.addBaseViewModel(shopViewModel,
                                 Builder.<Shop>withHandler(this, this).withDelegate(3,
                                                                                    ShopViewModelAdapterDelegate::new)
                                                                      .create());
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

    }
}
