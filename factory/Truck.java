class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Доставка груза по дороге");
    }
    
    @Override
    public double getCost() {
        return 5000.0;
    }
    
    // Специфичный метод для грузовика
    public void loadCargo() {
        System.out.println("Загрузка груза в кузов");
    }
}
