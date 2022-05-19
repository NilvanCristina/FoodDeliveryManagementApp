package utils;

import business.MenuItem;

import java.util.List;

public class UsedFunctions {
    public static String[] splitText(String text) {
        return text.split("\\s+");
    }

    public static String getReportInfo(String reportName) {
        String info = "";

        if (reportName.equals("Time Interval Report")) {
            info = info.concat("Please enter two hours!\n");
        }

        if (reportName.equals("Ordered More Than Report")) {
            info = info.concat("Please enter a number!\n");
        }

        if (reportName.equals("Clients Ordered More Than Report")) {
            info = info.concat("Please enter a number of times and an amount!\n");
        }

        if (reportName.equals("Ordered In Day Report")) {
            info = info.concat("Please enter a date with this format: dd mm yyyy!\n");
        }

        return info;
    }

    public static String getItemsWithTotal(List<MenuItem> products) {
        String content = "";
        int menuItemNumber = 1;
        double price = 0;

        for (MenuItem menuItem : products) {
            content = content.concat(menuItemNumber + ". ");
            content = content.concat(menuItem.toString());
            price += menuItem.computePrice();
            menuItemNumber += 1;
        }

        content = content.concat("Total: " + price);

        return content;
    }
}
