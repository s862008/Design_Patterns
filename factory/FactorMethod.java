/**
 * Абстрактный класс Logistics  - определяет фабричный метод,
 * который должны реализовать конкретные создатели
 */
abstract class Logistics {
    
    /**
     * Фабричный метод - будет переопределен в подклассах
     */
    public abstract Transport createTransport();
    
    /**
     * Бизнес-логика, которая использует фабричный метод
     */
    public void planDelivery() {
        Transport transport = createTransport();
        System.out.println("Стоимость доставки: $" + transport.getCost());
        transport.deliver();
    }
}

/**
 * Конкретный создатель A - создает грузовики
 */
class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}

/**
 * Конкретный создатель B - создает корабли
 */
class SeaLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}

/**
 * Конкретный создатель C - создает самолеты
 */
class AirLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Airplane();
    }
}
