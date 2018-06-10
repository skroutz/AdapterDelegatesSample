package gr.skroutz.gmetal.adapterdelegatessample.utils;

import gr.skroutz.gmetal.adapterdelegatessample.model.Category;
import gr.skroutz.gmetal.adapterdelegatessample.model.Shop;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static List<Category> getNonLeafSampleData() {

        List<Category> sampleData = new ArrayList<Category>();

        sampleData.add(new Category("Cat1", false));
        sampleData.add(new Category("Cat2", false));
        sampleData.add(new Category("Cat3", false));
        sampleData.add(new Category("Cat4", false));

        return sampleData;
    }

    public static List<Category> getLeafSampleData() {

        List<Category> sampleData = new ArrayList<Category>();

        sampleData.add(new Category("Cat1-leaf", true));
        sampleData.add(new Category("Cat2-leaf", true));
        sampleData.add(new Category("Cat3-non-leaf", false));
        sampleData.add(new Category("Cat4-leaf", true));

        return sampleData;
    }

    public static Shop getShopData() {

        return new Shop("Shop", "Some fancy Rd, London LA14YZ, UK");
    }
}
