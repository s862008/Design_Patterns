/**
 * Простая фабрика - создает объекты на основе входного параметра
 */
class SimpleFactory {
    
    /**
     * Метод создания транспортного средства
     * @param type тип транспорта
     * @return экземпляр Transport
     * @throws IllegalArgumentException если тип не поддерживается
     */
    public Transport createTransport(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Тип транспорта не может быть пустым");
        }
        
        switch (type.toLowerCase()) {
            case "truck":
                return new Truck();
            case "ship":
                return new Ship();
            case "airplane":
                return new Airplane();
            default:
                throw new IllegalArgumentException("Неизвестный тип транспорта: " + type);
        }
    }
}
