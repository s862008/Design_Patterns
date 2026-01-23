class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Доставка груза по морю");
    }
    
    @Override
    public double getCost() {
        return 15000.0;
    }
    
    // Специфичный метод для корабля
    public void sail() {
        System.out.println("Корабль отправляется в плавание");
    }
}
