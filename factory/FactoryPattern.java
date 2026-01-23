public class FactoryPattern {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация Простой Фабрики ===");
        
        // Использование простой фабрики
        SimpleFactory simpleFactory = new SimpleFactory();
        
        Transport truck = simpleFactory.createTransport("truck");
        truck.deliver();
        System.out.println("Стоимость: $" + truck.getCost());
        
        Transport ship = simpleFactory.createTransport("ship");
        ship.deliver();
        
        System.out.println("\n=== Демонстрация Фабричного Метода ===");
        
        // Использование фабричного метода
        Logistics roadLogistics = new RoadLogistics();
        roadLogistics.planDelivery();
        
        Logistics seaLogistics = new SeaLogistics();
        seaLogistics.planDelivery();
        
        Logistics airLogistics = new AirLogistics();
        airLogistics.planDelivery();
        
        System.out.println("\n=== Использование полиморфизма ===");
        
        // Массив логистических компаний
        Logistics[] companies = {
            new RoadLogistics(),
            new SeaLogistics(),
            new AirLogistics()
        };
        
        // Каждая компания планирует доставку своим транспортом
        for (Logistics company : companies) {
            company.planDelivery();
            System.out.println("---");
        }
    }
}
