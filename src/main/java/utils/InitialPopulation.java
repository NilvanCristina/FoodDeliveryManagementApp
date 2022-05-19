package utils;

import business.DeliveryService;

public class InitialPopulation {
    private DeliveryService deliveryService;

    public InitialPopulation(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        populateApp();
    }

    private void populateUsers() {
        deliveryService.addUser("admin", "admin", "administrator");
        deliveryService.addUser("employee", "emp", "employee");
        deliveryService.addUser("cristina", "cristina", "client");
        deliveryService.addUser("oli", "oli", "client");
    }

    private void populateMenu() {
        deliveryService.addMenuItem("Cartofi prajiti", 3.9, 200, 45, 56, 15, 10);
        deliveryService.addMenuItem("Snitel", 4.7, 300, 45, 36, 10, 5);
        deliveryService.addMenuItem("Supa de pui", 2.7, 190, 36, 23, 18, 15);
        deliveryService.addMenuItem("Coca-Cola", 5, 30, 15, 6, 12, 5);
    }

    private void populateApp() {
        populateUsers();
        populateMenu();
    }
}
