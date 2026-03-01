// Пример паттерна «Адаптер» (Adapter) на Java 

// Целевой интерфейс (то, что ожидает клиент)
interface USB {
    void connectViaUSB();
}

// Существующий класс с несовместимым интерфейсом (Adaptee)
class LightningDevice {
    public void connectViaLightning() {
        System.out.println("Устройство с разъёмом Lightning подключено.");
    }
}

// Адаптер: адаптирует LightningDevice к интерфейсу USB
class LightningToUSBAdapter implements USB {
    private LightningDevice lightningDevice;

    public LightningToUSBAdapter(LightningDevice device) {
        this.lightningDevice = device;
    }

    @Override
    public void connectViaUSB() {
        System.out.println("Адаптер преобразует сигнал USB → Lightning");
        lightningDevice.connectViaLightning();
    }
}

// Клиентский код рботает только с интерфейсом USB.
// Не знает о существовании LightningDevice и адаптера — видит лишь объект, реализующий USB.
// Может подключать любые устройства через адаптер, если для них создан соответствующий адаптер.

public class AdapterDemo {
    public static void main(String[] args) {
        // Создаём устройство с разъёмом Lightning
        LightningDevice iphone = new LightningDevice();

        // Создаём адаптер, который позволяет подключить Lightning‑устройство через USB
        USB adapter = new LightningToUSBAdapter(iphone);

        // Клиент работает с адаптером через интерфейс USB
        System.out.println("Попытка подключения устройства через USB‑порт:");
        adapter.connectViaUSB();

        System.out.println();

        // Пример с другим устройством
        LightningDevice airPods = new LightningDevice();
        USB airPodsAdapter = new LightningToUSBAdapter(airPods);

        System.out.println("Подключаем AirPods через USB‑адаптер:");
        airPodsAdapter.connectViaUSB();
    }
}
