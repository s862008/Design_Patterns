// Прототипный интерфейс
interface Prototype {
    Prototype clone();
}

// Конкретная реализация прототипа
class ConcretePrototype implements Prototype {
  private Integer id;
  private String name;
  private String phone;

    public ConcretePrototype(Integer id,String name,String phone) {
      this.id = id;  
      this.name = name;
      this.phone = phone;
    }

    // Геттер для демонстрации
    public String getName() {
        return name;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(this.id,this.name,this.phone);
    }
}

// Класс для тестирования
public class PrototypePatternExample {
    public static void main(String[] args) {
        // Создаем оригинальный объект
        ConcretePrototype original = new ConcretePrototype(1,"Original","555-5753");

        // Клонируем объект
        ConcretePrototype clone = (ConcretePrototype) original.clone();

        // Выводим имена оригинала и клона
        System.out.println("Original Name: " + original.getName());
        System.out.println("Cloned Name: " + clone.getName());
    }
}
