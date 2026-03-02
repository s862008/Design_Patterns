//Пример паттерна «Мост» (Bridge) на Java 

// Иерархия реализации: цвета
interface Color {
    void applyColor();
}

class RedColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Красный цвет нанесён");
    }
}

class GreenColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Зелёный цвет нанесён");
    }
}

// Иерархия абстракции: фигуры
abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw();
}

class Circle extends Shape {
    private int radius;

    public Circle(Color color, int radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    void draw() {
        System.out.print("Круг радиусом " + radius + " — ");
        color.applyColor();
    }
}

class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(Color color, int width, int height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    void draw() {
        System.out.print("Прямоугольник " + width + "x" + height + " — ");
        color.applyColor();
    }
}

// Клиентский код
//  Создаёт конкретные реализации (RedColor, GreenColor).
//  Создаёт конкретные абстракции (Circle, Rectangle), передавая им реализации.
//  Вызывает метод draw(), который использует делегирование к Color.
public class BridgeDemo {
    public static void main(String[] args) {
        // Создаём реализации цветов
        Color red = new RedColor();
        Color green = new GreenColor();

        // Комбинируем фигуры и цвета независимо
        Shape redCircle = new Circle(red, 5);
        Shape greenCircle = new Circle(green, 3);
        Shape redRectangle = new Rectangle(red, 10, 20);
        Shape greenRectangle = new Rectangle(green, 8, 12);

        // Отрисовываем все комбинации
        System.out.println("=== Отрисовка фигур с разными цветами ===");
        redCircle.draw();
        greenCircle.draw();
        redRectangle.draw();
        greenRectangle.draw();
    }
}
