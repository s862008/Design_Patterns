// Пример паттерна «Заместитель» (Proxy) на Java

interface Image {
    void display();
}

// Реальный объект: загружает и отображает изображение
class RealImage implements Image {
    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Загружаем изображение: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Отображаем изображение: " + filename);
    }
}

// Заместитель (Proxy): контролирует доступ к реальному объекту
class ImageProxy implements Image {
    private final String filename;
    private RealImage realImage; // Отложенная инициализация

    public ImageProxy(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// Клиентский код:
// Работает только с интерфейсом Image — не знает, кто перед ним: реальный объект или заместитель.
// Создаёт ImageProxy, думая, что работает с обычным изображением.
// Вызывает display() — заместитель берёт на себя заботу о загрузке, если нужно.

public class ProxyDemo {
    public static void main(String[] args) {
        // Создаём заместители — изображения пока не загружены
        Image image1 = new ImageProxy("photo1.jpg");
        Image image2 = new ImageProxy("photo2.jpg");

        System.out.println("Заместители созданы. Изображения ещё не загружены.\n");

        // Первая операция с image1 — происходит загрузка и отображение
        System.out.println("Первая операция с image1:");
        image1.display();

        System.out.println();

        // Вторая операция с image1 — загрузка не повторяется
        System.out.println("Вторая операция с image1:");
        image1.display();

        System.out.println();

        // Первая операция с image2 — происходит загрузка и отображение
        System.out.println("Первая операция с image2:");
        image2.display();

        System.out.println();

        // Демонстрация ленивой загрузки: image2 не загружался до первого вызова display()
        System.out.println("Демонстрация ленивой загрузки завершена.");
    }
}
