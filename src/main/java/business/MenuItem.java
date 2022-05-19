package business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    public abstract double computeRating();

    public abstract int computeCalories();

    public abstract int computeProtein();

    public abstract int computeFat();

    public abstract int computeSodium();

    public abstract double computePrice();

    public abstract String getTitle();
}
