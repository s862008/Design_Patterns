// Пример паттерна «Наблюдатель» (Observer) на Java

import java.util.ArrayList;
import java.util.List;

// Интерфейс наблюдателя: определяет метод обновления
interface Observer {
    void update(String message);
}

// Субъект (издатель): хранит список наблюдателей и управляет уведомлениями
class Subject {
    private final List<Observer> observers = new ArrayList<>();

    private String state;

    // Добавление наблюдателя
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Удаление наблюдателя
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Уведомление всех наблюдателей об изменении состояния
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    // Установка нового состояния (запускает уведомления)
    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }
}

// Конкретный наблюдатель 1: выводит сообщение в консоль
class ConsoleLogger implements Observer {
    @Override
    public void update(String message) {
        System.out.println("ConsoleLogger: получено сообщение — " + message);
    }
}

// Конкретный наблюдатель 2: сохраняет сообщение в файл (упрощённо — вывод в консоль)
class FileLogger implements Observer {
    @Override
    public void update(String message) {
        System.out.println("FileLogger: сохранено в файл — " + message);
    }
}

// Конкретный наблюдатель 3: отправляет уведомление по email (упрощённо — вывод в консоль)
class EmailNotifier implements Observer {
    @Override
    public void update(String message) {
        System.out.println("EmailNotifier: отправлено email — " + message);
    }
}


public class ObserverDemo {
    public static void main(String[] args) {
        // Создаём субъект
        Subject subject = new Subject();

        // Создаём наблюдателей
        Observer consoleLogger = new ConsoleLogger();
        Observer fileLogger = new FileLogger();
        Observer emailNotifier = new EmailNotifier();

        // Подписываем наблюдателей на субъект
        subject.addObserver(consoleLogger);
        subject.addObserver(fileLogger);
        subject.addObserver(emailNotifier);


        // Изменяем состояние субъекта — все наблюдатели получат уведомление
        System.out.println("=== Первое обновление ===");
        subject.setState("Система запущена");


        System.out.println("\n=== Второе обновление ===");
        subject.setState("Обнаружена ошибка");


        // Отписываем одного наблюдателя
        System.out.println("\n=== Отписка FileLogger ===");
        subject.removeObserver(fileLogger);


        System.out.println("\n=== Третье обновление ===");
        subject.setState("Система остановлена");
    }
}
