package data;

import business.DeliveryService;

import java.io.*;

public class Serializator {
    private static final String FILE_NAME = "deliveryService.ser";

    public static void serialize(DeliveryService deliveryService) {
        try {
            FileOutputStream file = new FileOutputStream(FILE_NAME);
            ObjectOutputStream outputStream = new ObjectOutputStream(file);
            deliveryService.setFirstStarted();
            outputStream.writeObject(deliveryService);
            outputStream.close();
            file.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static DeliveryService deserialize() {
        DeliveryService deliveryService;
        try {
            FileInputStream file = new FileInputStream(FILE_NAME);
            ObjectInputStream inputStream = new ObjectInputStream(file);
            deliveryService = (DeliveryService) inputStream.readObject();
            inputStream.close();
            file.close();
            return deliveryService;
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
