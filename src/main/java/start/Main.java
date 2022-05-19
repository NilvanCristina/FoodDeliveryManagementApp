package start;

import business.*;
import data.Serializator;
import presentation.MainGUI;
import utils.InitialPopulation;

public class Main {
    public static final boolean ALREADY_STARTED = true;

    public static void startApp() {
        if (ALREADY_STARTED) {
            DeliveryService deliveryService = Serializator.deserialize();
            MainGUI mainGUI = new MainGUI(deliveryService);
        } else {
            DeliveryService deliveryService = new DeliveryService();
            InitialPopulation database = new InitialPopulation(deliveryService);
            Serializator.serialize(deliveryService);
        }
    }

    public static void main(String[] args) {
        startApp();
    }
}
