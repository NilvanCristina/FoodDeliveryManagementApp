package business;

import bill.Bill;
import utils.UsedFunctions;
import data.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @invariant wellFormed()
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private static final long serialVersionUID = -1766454925999239578L;
    private boolean firstStart;
    private List<MenuItem> menu;
    private Map<Order, ArrayList<MenuItem>> orders;
    private List<User> users;
    private ArrayList<MenuItem> shoppingCartList;
    private ArrayList<MenuItem> compositeMenuItem;
    private List<Order> ordersDetails;

    public DeliveryService() {
        firstStart = true;
        menu = new ArrayList<>();
        orders = new TreeMap<>();
        users = new ArrayList<>();
        shoppingCartList = new ArrayList<>();
        compositeMenuItem = new ArrayList<>();
    }

    public void setFirstStarted() {
        this.firstStart = false;
    }

    public boolean checkIfUsernameExists(String username) {
        if (users.size() == 0)
            return false;

        for (User user : users) {
            if (user.getUsername().equals(username))
                return true;
        }

        return false;
    }

    public void addUser(String username, String password, String role) {
        assert !checkIfUsernameExists(username) : "Username taken!";

        if (role.equals("administrator")) {
            users.add(new Administrator(username, password));
        }

        if (role.equals("employee")) {
            users.add(new Employee(username, password));
        }

        if (role.equals("client")) {
            users.add(new Client(username, password));
        }
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }

        return null;
    }

    public SignInFeedback getSignInFeedback(String username, String password, String role) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.getRole().equals(role))
                return SignInFeedback.ALL_GOOD;

            if (user.getUsername().equals(username) && !user.getPassword().equals(password) && user.getRole().equals(role))
                return SignInFeedback.WRONG_PASSWORD;

            if (user.getUsername().equals(username) && user.getPassword().equals(password) && !user.getRole().equals(role))
                return SignInFeedback.WRONG_ROLE;
        }

        return SignInFeedback.WRONG_USERNAME;
    }

    public String getSearchUserMessage(String username, String password, String role) {
        SignInFeedback signInFeedback = getSignInFeedback(username, password, role);
        String message = "";

        if (signInFeedback == SignInFeedback.ALL_GOOD) {
            message = message.concat("ALL_GOOD");
        }

        if (signInFeedback == SignInFeedback.WRONG_PASSWORD) {
            message = message.concat("WRONG_PASSWORD");
        }

        if (signInFeedback == SignInFeedback.WRONG_ROLE) {
            message = message.concat("WRONG_ROLE");
        }

        if (signInFeedback == SignInFeedback.WRONG_USERNAME) {
            message = message.concat("WRONG_USERNAME");
        }

        return message;
    }

    public String getStringMenu() {
        String finalMenu = "";
        int menuItemNumber = 1;

        finalMenu = finalMenu.concat("The menu: \n");

        for (MenuItem menuItem : menu) {
            finalMenu = finalMenu.concat(menuItemNumber + ". ");
            finalMenu = finalMenu.concat(menuItem.toString());
            menuItemNumber += 1;
        }

        return finalMenu;
    }

    public String[] getMenuTitles() {
        String[] menuItemsTitles = new String[menu.size()];

        for (int i = 0; i < menu.size(); i++) {
            menuItemsTitles[i] = menu.get(i).getTitle();
        }

        return menuItemsTitles;
    }

    public void addToList(String title, String listName) {
        for (MenuItem menuItem : menu) {
            if (menuItem.getTitle().equals(title)) {
                if (listName.equals("cart")) {
                    shoppingCartList.add(menuItem);
                } else if (listName.equals("composite")) {
                    compositeMenuItem.add(menuItem);
                }
            }
        }
    }

    public String getListContent(String listName) {
        if (shoppingCartList.size() == 0 && listName.equals("cart")) {
            return "Nothing in the shopping cart yet!";
        } else if (compositeMenuItem.size() == 0 && listName.equals("composite")) {
            return "No item chose yet!";
        }

        String cart = "";
        int menuItemNumber = 1;

        cart = cart.concat("Now having: \n");

        if (listName.equals("cart")) {
            for (MenuItem menuItem : shoppingCartList) {
                cart = cart.concat(menuItemNumber + ". ");
                cart = cart.concat(menuItem.toString());
                menuItemNumber += 1;
            }
        } else if (listName.equals("composite")) {
            for (MenuItem menuItem : compositeMenuItem) {
                cart = cart.concat(menuItemNumber + ". ");
                cart = cart.concat(menuItem.toString());
                menuItemNumber += 1;
            }
        }

        return cart;
    }

    public void emptyList(String listName) {
        if (listName.equals("cart")) {
            shoppingCartList.clear();
        } else if (listName.equals("composite")) {
            compositeMenuItem.clear();
        }
    }

    @Override
    public void importProducts() {
        List<MenuItem> menuItems;
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader("products.csv"));

            menuItems = bufferedReader.lines()
                    .skip(1)
                    .distinct()
                    .map(line -> Arrays.asList(line.split(",")))
                    .map(list ->
                    new BaseProduct(list.get(0),
                            Double.parseDouble(list.get(1)),
                            Integer.parseInt(list.get(2)),
                            Integer.parseInt(list.get(3)),
                            Integer.parseInt(list.get(4)),
                            Integer.parseInt(list.get(5)),
                            Double.parseDouble(list.get(6))))
                    .collect(Collectors.toList());

            List<MenuItem> auxMenu = menuItems.stream().distinct().collect(Collectors.toList());

            menu.addAll(auxMenu);

            bufferedReader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    /** Aceasta metoda permite administratorului sa adauge un produs nou.
     * @pre wellFormed(), title != "", rating >= 0, calories >= 0, protein >= 0, fat >= 0, sodium >= 0, price > 0
     * @param title titlul produsului
     * @param rating raiting-ul produsului
     * @param calories caloriile produsului
     * @param protein proteinele produsului
     * @param fat grasimile produsului
     * @param sodium cantitatea de sodiu a produsului
     * @param price pretul produsului
     * @post wellFormed(), meniul trebuie sa contina produsul format din datele de intrare
     */
    @Override
    public void addMenuItem(String title, double rating, int calories, int protein, int fat, int sodium, double price) {
        assert wellFormed();
        assert !title.equals("") && rating >= 0 && calories >= 0 && protein >= 0 &&
                fat >= 0 && sodium >= 0 && price > 0 : "Invalid data for adding a new menu item!";

        BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        menu.add(baseProduct);

        assert menu.contains(baseProduct);
        assert wellFormed();
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        assert wellFormed();
        assert menuItem != null : "Invalid menu item!";

        menu.add(menuItem);

        assert menu.contains(menuItem);
        assert wellFormed();
    }


    /** Aceasta metoda permite administratorului sa creeze un produs nou format din produse deja existente.
     * @pre wellFormed(), name != "", compositeMenuItem.size() != 0
     * @param name numele produsului
     * @post wellFormed(), meniul trebuie sa contina noul produs
     */
    @Override
    public void createMenuItem(String name) {
        assert wellFormed();
        assert !name.equals("") && compositeMenuItem.size() != 0 : "Invalid data for creating a new composite menu item!";

        CompositeProduct compositeProduct = new CompositeProduct(name, compositeMenuItem);
        menu.add(compositeProduct);

        assert menu.contains(compositeProduct);
        assert wellFormed();
    }


    /** Aceasta metoda permite administratorului sa stearga un produs.
     * @pre wellFormed(), title != ""
     * @param title titlul produsului
     * @post wellFormed(), meniul nu trebuie sa contina elementul care trebuia sters
     */
    @Override
    public void deleteMenuItem(String title) {
        assert wellFormed();
        assert !title.equals("") : "Invalid data for a deleting a menu item!";

        MenuItem item = null;
        for (MenuItem menuItem : menu) {
            if (menuItem.getTitle().equals(title)) {
                item = menuItem;
            }
        }

        assert item != null : "No item with title " + title + " was found!";
        menu.remove(item);
        assert !menu.contains(item);
        assert wellFormed();
    }

    /** Aceasta metoda permite administratorului sa editeze un produs.
     * @pre wellFormed(), toSetField != "", toSetValue != "", conditionField != "", conditionValue != ""
     * @param toSetField campul care va fi modificat
     * @param toSetValue noua valoare a campului
     * @param conditionField campul dupa care se face cautarea
     * @param conditionValue valoarea campului dupa care se face cautarea
     * @post wellFormed()
     */
    @Override
    public void editMenuItem(String toSetField, String toSetValue, String conditionField, String conditionValue) {
        assert wellFormed();
        assert !toSetField.equals("") && !toSetValue.equals("") &&
                !conditionField.equals("") && !conditionValue.equals("") : "Invalid data for editing a menu item!";

        for (MenuItem menuItem : menu) {
            if (menuItem instanceof BaseProduct) {
                if (((BaseProduct) menuItem).getField(conditionField).equals(conditionValue)) {
                    ((BaseProduct) menuItem).setField(toSetField, toSetValue);
                }
            }
        }

        assert wellFormed();
    }

    public void setOrdersDetails() {
        ordersDetails = new ArrayList<>(orders.keySet());
    }

    public void printOrdersDetails() {
        setOrdersDetails();

        for (Order order : ordersDetails) {
            System.out.println(order.getOrderId() + " " + order);
        }
    }

    /** @pre startHour >= 0, startHour < 24, endHour >= 0, endHour < 24, startHour < endHour
     * @param startHour ora de inceput
     * @param endHour ora de sfarsit
     * @return text ce contine raportul
     */
    @Override
    public String generateTimeIntervalReport(int startHour, int endHour) {
        assert startHour >= 0 && startHour < 24;
        assert endHour >= 0 && endHour < 24;
        assert startHour < endHour;

        setOrdersDetails();

        List<Order> selectedOrders = ordersDetails.stream()
                .filter(o -> o.getHour() >= startHour && o.getHour() < endHour)
                .collect(Collectors.toList());

        String content = "";
        content = content.concat("Time Interval Report\n");

        for (Order order : selectedOrders) {
            content = content.concat(order.toString());
        }

        Write writer = new Write("Report1.txt", content);

        return content;
    }

    /** @pre number >= 0
     * @param number numarul minim de cate ori a fost comandat un produs
     * @return text ce contine raportul
     */
    @Override
    public String generateOrderedMoreThanReport(int number) {
        assert number >= 0;

        String content = "";
        content = content.concat("Ordered More Than Report\n");

        for (MenuItem item : menu) {
            long count = orders.entrySet()
                    .stream()
                    .filter(o -> o.getValue().contains(item))
                    .count();

            if (count >= number)
                content = content.concat(item.toString());
        }

        Write writer = new Write("Report2.txt", content);

        return content;
    }

    public double computeMultipleOrdersPrice(List<Order> selectedOrders) {
        double price = 0;

        for (Order order : selectedOrders) {
            List<MenuItem> menuItems = orders.get(order);

            for (MenuItem menuItem : menuItems) {
                price += menuItem.computePrice();
            }
        }

        return price;
    }

    /** @pre times >= 0, amount >= 0
     * @param times numarul minim de ori de care clientul a comandat
     * @param amount suma minima a comenzilor unui client
     * @return text ce contine raportul
     */
    @Override
    public String generateClientsOrderedMoreThanReport(int times, int amount) {
        assert times >= 0 && amount >= 0;

        setOrdersDetails();

        String content = "";
        content = content.concat("Clients Ordered More Than Report\n");

        for (User user : users) {
            if (user instanceof Client) {
                long nbOfTimes = orders.entrySet()
                        .stream()
                        .distinct()
                        .filter(o -> o.getKey().getClientUsername().equals(user.getUsername()))
                        .count();

                if (nbOfTimes >= times) {
                    List<Order> selectedOrders = ordersDetails.stream()
                            .filter(o -> o.getClientUsername().equals(user.getUsername()))
                            .collect(Collectors.toList());

                    if (computeMultipleOrdersPrice(selectedOrders) >= amount) {
                        content = content.concat(user.toString());
                    }
                }
            }
        }

        Write writer = new Write("Report3.txt", content);

        return content;
    }

    /** @param day ziua comenzii
     * @param month luna comenzii
     * @param year anul comenzii
     * @return text ce contine raportul
     */
    @Override
    public String generateOrderedInDayReport(int day, int month, int year) {
        assert day >= 1 && day <= 31;
        assert month >= 1 && month <= 12;

        HashSet<MenuItem> menuItems = (HashSet<MenuItem>) orders.entrySet()
                .stream()
                .filter(p -> p.getKey().getDayOfMonth() == day
                        && p.getKey().getMonth() == (month - 1)
                        && p.getKey().getYear() == year)
                .flatMap(p -> p.getValue().stream())
                .collect(Collectors.toSet());

        String content = "";
        content = content.concat("Ordered In Day Report\n");

        for (MenuItem item : menuItems) {
            long nbOfTimes = orders.entrySet()
                    .stream()
                    .filter(p -> p.getValue().contains(item))
                    .count();

            content = content.concat(item.getTitle() + " " + nbOfTimes + "\n");
        }

        Write writer = new Write("Report4.txt", content);

        return content;
    }

    public String generateReport(String reportName, String reportCriteria) {
        if (reportName.equals("Time Interval Report")) {
            String[] hours = UsedFunctions.splitText(reportCriteria);
            return generateTimeIntervalReport(Integer.parseInt(hours[0]), Integer.parseInt(hours[1]));
        }

        if (reportName.equals("Ordered More Than Report")) {
            return generateOrderedMoreThanReport(Integer.parseInt(reportCriteria));
        }

        if (reportName.equals("Clients Ordered More Than Report")) {
            String[] data = UsedFunctions.splitText(reportCriteria);
            return generateClientsOrderedMoreThanReport(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        }

        if (reportName.equals("Ordered In Day Report")) {
            String[] data = UsedFunctions.splitText(reportCriteria);
            return generateOrderedInDayReport(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]));
        }

        return "";
    }

    @NotNull
    private String getMessageForObservers(ArrayList<MenuItem> orderedItems) {
        String message = "";

        message = message.concat("New order!\n");
        message = message.concat(UsedFunctions.getItemsWithTotal(orderedItems));

        return message;
    }

    public int getOrdersDetailsSize() {
        ordersDetails = new ArrayList<>(orders.keySet());
        return ordersDetails.size();
    }

    /** Aceasta metoda permite clientului sa plaseze o comanda.
     * @pre clientUsername != "", shoppingCartList.size() != 0
     * @param clientUsername username-ul clientului care face comanda
     */
    @Override
    public void createOrder(String clientUsername) {
        assert !clientUsername.equals("") && shoppingCartList.size() != 0;

        int orderId = getOrdersDetailsSize() + 1;

        Order newOrder = new Order(orderId, clientUsername);
        ArrayList<MenuItem> menuItems = new ArrayList<>(shoppingCartList);
        orders.put(newOrder, menuItems);

        ArrayList<MenuItem> orderedItems = orders.get(newOrder);

        String message = getMessageForObservers(orderedItems);

        setChanged();
        notifyObservers(message);

        Bill bill = new Bill(newOrder, menuItems);
    }

    private String getContent(List<MenuItem> menuItems) {
        String content = "";

        for (MenuItem menuItem : menuItems) {
            content = content.concat(menuItem.toString());
        }

        return content;
    }

    @Override
    public String searchByTitle(String title) {
        assert !title.equals("") : "Invalid data for searching!";

        List<MenuItem> menuItems = menu.stream()
                .filter(p -> p.getTitle().contains(title))
                .collect(Collectors.toList());

        return getContent(menuItems);
    }

    @Override
    public String searchByRating(double rating) {
        assert rating >= 0 : "Invalid data for searching!";

        List<MenuItem> menuItems = menu.stream()
                .filter(p -> p.computeRating() == rating)
                .collect(Collectors.toList());

        return getContent(menuItems);
    }

    @Override
    public String searchByCalories(int calories) {
        assert calories >= 0 : "Invalid data for searching!";

        List<MenuItem> menuItems = menu.stream()
                .filter(p -> p.computeCalories() == calories)
                .collect(Collectors.toList());

        return getContent(menuItems);
    }

    @Override
    public String searchByProtein(int protein) {
        assert protein >= 0 : "Invalid data for searching!";

        List<MenuItem> menuItems = menu.stream()
                .filter(p -> p.computeProtein() == protein)
                .collect(Collectors.toList());

        return getContent(menuItems);
    }

    @Override
    public String searchByFat(int fat) {
        assert fat >= 0 : "Invalid data for searching!";

        List<MenuItem> menuItems = menu.stream()
                .filter(p -> p.computeFat() == fat)
                .collect(Collectors.toList());

        return getContent(menuItems);
    }

    @Override
    public String searchBySodium(int sodium) {
        assert sodium >= 0 : "Invalid data for searching!";

        List<MenuItem> menuItems = menu.stream()
                .filter(p -> p.computeSodium() == sodium)
                .collect(Collectors.toList());

        return getContent(menuItems);
    }

    @Override
    public String searchByPrice(double price) {
        assert price >= 0 : "Invalid data for searching!";

        List<MenuItem> menuItems = menu.stream()
                .filter(p -> p.computePrice() == price)
                .collect(Collectors.toList());

        return getContent(menuItems);
    }

    public String searchByCriteria(String field, String value) {
        String result = "";

        switch (field) {
            case "title" -> result = searchByTitle(value);
            case "rating" -> result = searchByRating(Double.parseDouble(value));
            case "calories" -> result = searchByCalories(Integer.parseInt(value));
            case "protein" -> result = searchByProtein(Integer.parseInt(value));
            case "fat" -> result = searchByFat(Integer.parseInt(value));
            case "sodium" -> result = searchBySodium(Integer.parseInt(value));
            case "price" -> result = searchByPrice(Double.parseDouble(value));
            default -> {
            }
        }

        return result;
    }

    public boolean checkEmptyOrders() {
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
            if (entry.getValue() == null)
                return true;
        }

        return false;
    }

    /** Aceasta metoda reprezinta invariant-ul clasei. In orice moment aceste conditii trebuie sa fie indeplinite:
     * - daca aplicatia nu este la prima pornire, meniul si lista de useri trebuie sa aiba macar un element
     * - daca exista comenzi, acestea nu au voie sa fie goale
     */
    public boolean wellFormed() {
        if (!firstStart) {
            if (menu.size() == 0)
                return false;

            if (users.size() == 0)
                return false;
        }

        if (orders.size() > 0) {
            return !checkEmptyOrders();
        }

        return true;
    }

    public void showOrders() {
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
