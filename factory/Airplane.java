class Airplane implements Transport {
    @Override
    public void deliver() {
        System.out.println("Доставка груза по воздуху");
    }
    
    @Override
    public double getCost() {
        return 25000.0;
    }
    
    // Специфичный метод для самолета
    public void takeOff() {
        System.out.println("Самолет взлетает");
    }
}
