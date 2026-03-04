// Пример паттерна «Состояние» (State) на Java 
// Интерфейс состояния: определяет поведение для каждого состояния
interface DeviceState {
    void pressButton(Device device);
    String getStatus();
}

// Конкретное состояние 1: Выключено
class OffState implements DeviceState {
    @Override
    public void pressButton(Device device) {
        System.out.println("Включаем устройство...");
        device.setState(new OnState());
    }

    @Override
    String getStatus() {
        return "Выключено";
    }
}

// Конкретное состояние 2: Включено
class OnState implements DeviceState {
    @Override
    public void pressButton(Device device) {
        System.out.println("Переходим в режим ожидания...");
        device.setState(new StandbyState());
    }

    @Override
    String getStatus() {
        return "Включено";
    }
}

// Конкретное состояние 3: Ожидание
class StandbyState implements DeviceState {
    @Override
    public void pressButton(Device device) {
        System.out.println("Выключаем устройство...");
        device.setState(new OffState());
    }

    @Override
    String getStatus() {
        return "Ожидание";
    }
}

// Контекст: устройство, поведение которого меняется в зависимости от состояния
class Device {
    private DeviceState state;

    // Конструктор: начальное состояние — выключено
    public Device() {
        this.state = new OffState();
    }

    // Установка нового состояния
    public void setState(DeviceState state) {
        this.state = state;
    }

    // Делегирование вызова текущему состоянию
    public void pressPowerButton() {
        state.pressButton(this);
    }

    // Получение текущего статуса
    public String getCurrentStatus() {
        return state.getStatus();
    }
}

// Клиентский код:
// Создаёт объект Device (изначально в состоянии «Выключено»).
// Вызывает pressPowerButton(), чтобы имитировать нажатие кнопки питания.
// Отслеживает изменение состояния устройства через getCurrentStatus().

public class StateDemo {
    public static void main(String[] args) {
        Device device = new Device();

        System.out.println("Начальное состояние: " + device.getCurrentStatus());

        // Последовательно нажимаем кнопку питания
        device.pressPowerButton(); // Включение
        System.out.println("Текущее состояние: " + device.getCurrentStatus());

        device.pressPowerButton(); // Режим ожидания
        System.out.println("Текущее состояние: " + device.getCurrentStatus());

        device.pressPowerButton(); // Выключение
        System.out.println("Текущее состояние: " + device.getCurrentStatus());

        device.pressPowerButton(); // Снова включение
        System.out.println("Текущее состояние: " + device.getCurrentStatus());
    }
}
