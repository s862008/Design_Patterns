// Абстрактные фабрики и продукты
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

// Конкретные фабрики
class WinFactory implements GUIFactory {
    public Button createButton() {
        return new WinButton();
    }

    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Конкретные продукты для Windows
class WinButton implements Button {
    public void paint() {
        System.out.println("Отрисовка кнопки Windows.");
    }
}

class WinCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Отрисовка чекбокса Windows.");
    }
}

// Конкретные продукты для Mac
class MacButton implements Button {
    public void paint() {
        System.out.println("Отрисовка кнопки Mac.");
    }
}

class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Отрисовка чекбокса Mac.");
    }
}


public class AbstractFactoryPattern {
    private Button button;
    private Checkbox checkbox;

    public AbstractFactoryPattern(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }

    public static void main(String[] args) {
        GUIFactory factory;
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            factory = new WinFactory();
        } else {
            factory = new MacFactory();
        }

        AbstractFactoryPattern app = new AbstractFactoryPattern(factory);
        app.paint();
    }
}
