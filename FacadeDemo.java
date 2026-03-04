// Пример паттерна «Фасад» (Facade) на Java

// Подсистема 1: Аудиосистема
class AudioSystem {
    public void turnOn() {
        System.out.println("Аудиосистема: включён режим объёмного звука 5.1");
    }

    public void turnOff() {
        System.out.println("Аудиосистема: выключено");
    }

    public void setVolume(int level) {
        System.out.println("Аудиосистема: громкость установлена на " + level);
    }
}

// Подсистема 2: Видеопроектор
class Projector {
    public void powerOn() {
        System.out.println("Проектор: включён, идёт прогрев лампы");
    }

    public void powerOff() {
        System.out.println("Проектор: выключен");
    }

    public void setInput(String source) {
        System.out.println("Проектор: источник сигнала — " + source);
    }

    public void focus() {
        System.out.println("Проектор: выполнена фокусировка изображения");
    }
}

// Подсистема 3: Освещение
class Lighting {
    public void dimAll() {
        System.out.println("Освещение: приглушено до 20%");
    }

    public void brightenAll() {
        System.out.println("Освещение: включено на 100%");
    }

    public void turnOff() {
        System.out.println("Освещение: выключено");
    }
}

// Фасад: Единый интерфейс для управления домашним кинотеатром
class HomeTheaterFacade {
    private AudioSystem audio;
    private Projector projector;
    private Lighting lights;

    // Конструктор принимает все подсистемы
    public HomeTheaterFacade(AudioSystem audio, Projector projector, Lighting lights) {
        this.audio = audio;
        this.projector = projector;
        this.lights = lights;
    }

    // Упрощённые методы для клиента
    public void watchMovie(String movie) {
        System.out.println("\n=== Подготовка к просмотру фильма: " + movie + " ===");
        lights.dimAll();
        projector.powerOn();
        projector.setInput("Blu‑ray");
        projector.focus();
        audio.turnOn();
        audio.setVolume(15);
        System.out.println("Фильм запущен! Приятного просмотра.\n");
    }

    public void endMovie() {
        System.out.println("\n=== Завершение просмотра ===");
        audio.turnOff();
        projector.powerOff();
        lights.brightenAll();
        System.out.println("Домашний кинотеатр выключен.\n");
    }

    public void emergencyStop() {
        System.out.println("\n!!! АВАРИЙНОЕ ВЫКЛЮЧЕНИЕ !!!");
        audio.turnOff();
        projector.powerOff();
        lights.turnOff();
        System.out.println("Все системы остановлены.\n");
    }
}

/*
Как это работает:
Подсистемы (AudioSystem, Projector, Lighting) реализуют сложную логику (включение, настройка, выключение).
Клиент напрямую с ними не взаимодействует.
Фасад (HomeTheaterFacade) хранит ссылки на все подсистемы.
Предоставляет упрощённые методы (watchMovie(), endMovie(), emergencyStop()).
Внутри вызывает нужные методы подсистем в правильной последовательности.
Клиент (FacadeDemo) работает только с фасадом.
Не знает о внутренней структуре подсистем.
Для запуска фильма достаточно вызвать theater.watchMovie("Название"). 
*/

public class FacadeDemo {
    public static void main(String[] args) {
        // Создаём подсистемы
        AudioSystem audio = new AudioSystem();
        Projector projector = new Projector();
        Lighting lights = new Lighting();

        // Создаём фасад, объединяющий все подсистемы
        HomeTheaterFacade theater = new HomeTheaterFacade(audio, projector, lights);


        // Используем упрощённый интерфейс фасада
        theater.watchMovie("Интерстеллар");
        theater.endMovie();

        // Аварийное отключение
        theater.emergencyStop();
    }
}
