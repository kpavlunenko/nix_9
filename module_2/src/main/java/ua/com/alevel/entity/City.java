package ua.com.alevel.entity;

public class City {

    private String name;
    private int index;
    private boolean isInTree;

    public City(String name, int index) {
        this.name = name;
        this.isInTree = false;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInTree() {
        return isInTree;
    }

    public void setInTree(boolean inTree) {
        isInTree = inTree;
    }
}
