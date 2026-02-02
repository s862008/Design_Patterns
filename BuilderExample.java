public class Laptop {
    private final String brand;
    private final String processor;
    private final int ram;
    private final int storage;
    private final boolean hasSSD;
    private final String graphicsCard;

    // Частный конструктор: доступен только через Builder
    private Laptop(Builder builder) {
        this.brand = builder.brand;
        this.processor = builder.processor;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.hasSSD = builder.hasSSD;
        this.graphicsCard = builder.graphicsCard;
    }

    // Геттеры (для краткости опущены)
    public String getBrand() { return brand; }
    public String getProcessor() { return processor; }
    public int getRam() { return ram; }
    public int getStorage() { return storage; }
    public boolean isHasSSD() { return hasSSD; }
    public String getGraphicsCard() { return graphicsCard; }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", processor='" + processor + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", hasSSD=" + hasSSD +
                ", graphicsCard='" + graphicsCard + '\'' +
                '}';
    }

    // Вложенный статический класс Builder
    public static class Builder {
        private final String brand;  // обязательный параметр
        private final String processor;  // обязательный параметр


        private int ram = 8;           // значение по умолчанию
        private int storage = 256;    // значение по умолчанию
        private boolean hasSSD = true;  // значение по умолчанию
        private String graphicsCard = "Integrated";  // значение по умолчанию


        // Конструктор с обязательными полями
        public Builder(String brand, String processor) {
            this.brand = brand;
            this.processor = processor;
        }

        // Сеттеры для необязательных полей (возвращают this для цепочки вызовов)
        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder setHasSSD(boolean hasSSD) {
            this.hasSSD = hasSSD;
            return this;
        }

        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        // Метод build() создаёт финальный объект Laptop
        public Laptop build() {
            return new Laptop(this);
        }
    }

    // Пример использования
    public static void BuilderExample(String[] args) {
        // Создаём ноутбук с минимальными настройками (используем значения по умолчанию)
        Laptop laptop1 = new Laptop.Builder("Dell", "Intel i5")
                .build();
        System.out.println(laptop1);

        // Создаём ноутбук с полной настройкой
        Laptop laptop2 = new Laptop.Builder("ASUS", "AMD Ryzen 7")
                .setRam(16)
                .setStorage(512)
                .setHasSSD(false)
                .setGraphicsCard("NVIDIA RTX 3060")
                .build();
        System.out.println(laptop2);
    }
}
