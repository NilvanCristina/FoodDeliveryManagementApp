package business;

public class BaseProduct extends MenuItem {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private double price;

    public BaseProduct() {

    }

    public BaseProduct(String title) {
        this.title = title;
    }

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, double price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getField(String field) {
        String result = "";

        switch (field) {
            case "title" -> result = title;
            case "rating" -> result = String.valueOf(rating);
            case "calories" -> result = String.valueOf(calories);
            case "protein" -> result = String.valueOf(protein);
            case "fat" -> result = String.valueOf(fat);
            case "sodium" -> result = String.valueOf(sodium);
            case "price" -> result = String.valueOf(price);
            default -> {
            }
        }

        return result;
    }

    public void setField(String field, String value) {
        switch (field) {
            case "title" -> setTitle(value);
            case "rating" -> setRating(Double.parseDouble(value));
            case "calories" -> setCalories(Integer.parseInt(value));
            case "protein" -> setProtein(Integer.parseInt(value));
            case "fat" -> setFat(Integer.parseInt(value));
            case "sodium" -> setSodium(Integer.parseInt(value));
            case "price" -> setPrice(Double.parseDouble(value));
            default -> {
            }
        }
    }

    @Override
    public double computeRating() {
        return rating;
    }

    @Override
    public int computeCalories() {
        return calories;
    }

    @Override
    public int computeProtein() {
        return protein;
    }

    @Override
    public int computeFat() {
        return fat;
    }

    @Override
    public int computeSodium() {
        return sodium;
    }

    @Override
    public double computePrice() {
        return price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "title='" + title + '\'' +
                ", price=" + price +
                "}" + "\n";
    }
}
