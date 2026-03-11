// Пример паттерна «Шаблонный метод» (Template Method) на Java
// Абстрактный класс с шаблонным методом
abstract class Beverage {
    // Шаблонный метод — определяет скелет алгоритма
    // final: нельзя переопределить, чтобы сохранить порядок шагов
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    // Общие шаги с реализацией по умолчанию
    void boilWater() {
        System.out.println("Кипятим воду");
    }

    void pourInCup() {
        System.out.println("Наливаем в чашку");
    }

    // Абстрактные шаги — должны быть реализованы в подклассах
    abstract void brew();
    abstract void addCondiments();

    // Хук (hook) — может быть переопределён, по умолчанию возвращает true
    boolean customerWantsCondiments() {
        return true;
    }
}

// Конкретная реализация 1: кофе
class Coffee extends Beverage {
    @Override
    void brew() {
        System.out.println("Завариваем кофе");
    }

    @Override
    void addCondiments() {
        System.out.println("Добавляем сахар и молоко");
    }

    // Переопределяем хук: кофе без добавок
    @Override
    boolean customerWantsCondiments() {
        return false;
    }
}

// Конкретная реализация 2: чай
class Tea extends Beverage {
    @Override
    void brew() {
        System.out.println("Завариваем чайные листья");
    }

    @Override
    void addCondiments() {
        System.out.println("Добавляем лимон");
    }
}


public class TemplateMethodDemo {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        Beverage tea = new Tea();

        System.out.println("Готовим кофе:");
        coffee.prepareRecipe();

        System.out.println("\nГотовим чай:");
        tea.prepareRecipe();
    }
}
