// Пример приминенния шаблона Декоратор: создаём систему заказа пиццы с динамическим добавлением начинок (декораторов)

// Интерфейс компонента
interface Pizza {
    String getDescription();
    double getCost();
}

// Конкретный компонент (базовая пицца)
class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Простая пицца";
    }

    @Override
    public double getCost() {
        return 8.0;
    }
}

// 3. Абстрактный декоратор (обёртка)
abstract class PizzaDecorator implements Pizza {
    protected Pizza decoratedPizza;

    public PizzaDecorator(Pizza decoratedPizza) {
        this.decoratedPizza = decoratedPizza;
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost();
    }
}

// Конкретные декораторы (начинки)
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", сыр";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 1.5;
    }
}

class PepperoniDecorator extends PizzaDecorator {
    public PepperoniDecorator(Pizza decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", пепперони";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 2.0;
    }
}

class MushroomDecorator extends PizzaDecorator {
    public MushroomDecorator(Pizza decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", грибы";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 1.0;
    }
}

// Пример использования:
// Создаёт базовую пиццу (PlainPizza).
// Последовательно оборачивает её в декораторы, добавляя начинки.
// Каждый вызов getDescription() и getCost() учитывает все добавленные декораторы.

public class DecoratorDemo {
    public static void main(String[] args) {
        // Базовая пицца
        Pizza pizza = new PlainPizza();
        System.out.println(pizza.getDescription() + " — $" + pizza.getCost());

        // Добавляем сыр
        pizza = new CheeseDecorator(pizza);
        System.out.println(pizza.getDescription() + " — $" + pizza.getCost());

        // Добавляем пепперони
        pizza = new PepperoniDecorator(pizza);
        System.out.println(pizza.getDescription() + " — $" + pizza.getCost());

        // Добавляем грибы
        pizza = new MushroomDecorator(pizza);
        System.out.println(pizza.getDescription() + " — $" + pizza.getCost());
    }
}
