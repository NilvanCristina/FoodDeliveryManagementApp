package business;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
    private String title;
    private ArrayList<MenuItem> menuItems;

    public CompositeProduct(String title) {
        this.title = title;
        this.menuItems = new ArrayList<>();
    }

    public CompositeProduct(String title, ArrayList<MenuItem> menuItems) {
        this.title = title;
        this.menuItems = new ArrayList<>();
        this.menuItems.addAll(menuItems);
    }

    public void addItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    @Override
    public double computeRating() {
        double rating = 0;

        for (MenuItem item: menuItems) {
            rating += item.computeRating();
        }

        return rating / menuItems.size();
    }

    @Override
    public int computeCalories() {
        int calories = 0;

        for (MenuItem item: menuItems) {
            calories += item.computeCalories();
        }

        return calories;
    }

    @Override
    public int computeProtein() {
        int protein = 0;

        for (MenuItem item: menuItems) {
            protein += item.computeProtein();
        }

        return protein;
    }

    @Override
    public int computeFat() {
        int fat = 0;

        for (MenuItem item: menuItems) {
            fat += item.computeFat();
        }

        return fat;
    }

    @Override
    public int computeSodium() {
        int sodium = 0;

        for (MenuItem item: menuItems) {
            sodium += item.computeSodium();
        }

        return sodium;
    }

    @Override
    public double computePrice() {
        int price = 0;

        for (MenuItem item: menuItems) {
            price += item.computePrice();
        }

        return price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "title='" + title + '\'' +
                ", menuItems=" + menuItems +
                "}" + "\n";
    }
}
