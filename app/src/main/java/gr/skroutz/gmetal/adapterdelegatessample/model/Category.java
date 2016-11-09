package gr.skroutz.gmetal.adapterdelegatessample.model;

public class Category extends BaseObject {

    public String name;
    public boolean isLeaf;

    public Category(String name, boolean isLeaf) {

        this.name = name;
        this.isLeaf = isLeaf;
    }

}
