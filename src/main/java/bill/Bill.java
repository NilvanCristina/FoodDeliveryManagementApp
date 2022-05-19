package bill;

import utils.UsedFunctions;
import business.MenuItem;
import business.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private int billId;
    private Order order;
    private List<MenuItem> products;

    public Bill(Order order, List<MenuItem> products) {
        this.billId = order.getOrderId();
        this.order = order;
        this.products = new ArrayList<>(products);

        generateBill();
    }

    private String generateContentBill() {
        String content = "";

        content = content.concat("------ ORDER ------\n");
        content = content.concat("Client's username: " + order.getClientUsername() + "\n");
        content = content.concat("Products:\n");
        content = content.concat(UsedFunctions.getItemsWithTotal(products));

        return content;
    }

    private void generateBill() {
        String fileName = "Order" + billId + ".txt";
        File file = new File(fileName);

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(generateContentBill());
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
