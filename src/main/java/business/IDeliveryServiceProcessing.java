package business;

public interface IDeliveryServiceProcessing {
    // Administrator

    void importProducts();

    void addMenuItem(String title, double rating, int calories, int protein, int fat, int sodium, double price);

    void addMenuItem(MenuItem menuItem);

    void createMenuItem(String name);

    void deleteMenuItem(String title);

    void editMenuItem(String toSetField, String toSetValue, String conditionField, String conditionValue);

    String generateTimeIntervalReport(int startHour, int endHour);

    String generateOrderedMoreThanReport(int number);

    String generateClientsOrderedMoreThanReport(int times, int amount);

    String generateOrderedInDayReport(int day, int month, int year);

    // Client

    void createOrder(String clientUsername);

    String searchByTitle(String title);

    String searchByRating(double rating);

    String searchByCalories(int calories);

    String searchByProtein(int protein);

    String searchByFat(int fat);

    String searchBySodium(int sodium);

    String searchByPrice(double price);
}
