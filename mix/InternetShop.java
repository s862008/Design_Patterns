// Пример комбинации паттернов проектирования в Java: интернет‑магазин
//   Фабричный метод (Factory Method) — создание заказов разных типов;
//   Наблюдатель (Observer) — уведомления о статусе заказа;
//   Стратегия (Strategy) — разные способы оплаты;
//   Декоратор (Decorator) — добавление услуг к заказу.
  
  
import java.util.ArrayList;
import java.util.List;

// 1. ПАТТЕРН «СТРАТЕГИЯ» — способы оплаты
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата картой: " + amount + " руб.");
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Оплата наличными: " + amount + " руб.");
    }
}

// 2. ПАТТЕРН «ФАБРИЧНЫЙ МЕТОД» — создание заказов
abstract class OrderFactory {
    public abstract Order createOrder();

    public Order processOrder(double amount, PaymentStrategy payment) {
        Order order = createOrder();
        order.setAmount(amount);
        order.setPaymentStrategy(payment);
        return order;
    }
}

class StandardOrderFactory extends OrderFactory {
    @Override
    Order createOrder() {
        return new StandardOrder();
    }
}

class ExpressOrderFactory extends OrderFactory {
    @Override
    Order createOrder() {
        return new ExpressOrder();
    }
}

// 3. БАЗОВЫЙ КЛАСС ЗАКАЗА
abstract class Order {
    protected double amount;
    protected PaymentStrategy paymentStrategy;
    protected List<OrderObserver> observers = new ArrayList<>();

    public void setAmount(double amount) { this.amount = amount; }
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    protected void notifyObservers(String status) {
        for (OrderObserver observer : observers) {
            observer.update(this, status);
        }
    }

    public abstract void process();
}

class StandardOrder extends Order {
    @Override
    public void process() {
        System.out.println("Обработка стандартного заказа...");
        paymentStrategy.pay(amount);
        notifyObservers("Заказ обработан");
    }
}

class ExpressOrder extends Order {
    @Override
    public void process() {
        System.out.println("Экстренная обработка заказа...");
        paymentStrategy.pay(amount * 1.2); // +20 % за экспресс
        notifyObservers("Экспресс‑заказ обработан");
    }
}

// 4. ПАТТЕРН «НАБЛЮДАТЕЛЬ» — уведомления
interface OrderObserver {
    void update(Order order, String status);
}

class EmailNotifier implements OrderObserver {
    @Override
    public void update(Order order, String status) {
        System.out.println("EMAIL: Заказ на " + order.amount + " руб. — " + status);
    }
}

class SMSNotifier implements OrderObserver {
    @Override
    # void update(Order order, String status) {
        System.out.println("SMS: Ваш заказ — " + status);
    }
}

// 5. ПАТТЕРН «ДЕКОРАТОР» — дополнительные услуги
abstract class OrderDecorator extends Order {
    protected Order decoratedOrder;

    public OrderDecorator(Order order) {
        this.decoratedOrder = order;
    }

    @Override
    public void process() { decoratedOrder.process(); }

    @Override
    public void addObserver(OrderObserver observer) { decoratedOrder.addObserver(observer); }
}

class InsuranceDecorator extends OrderDecorator {
    public InsuranceDecorator(Order order) { super(order); }

    @Override
    public void process() {
        System.out.println("Добавляем страховку к заказу...");
        decoratedOrder.process();
    }
}


public class InternetShop {
    public static void main(String[] args) {
        // Создаём фабрики
        OrderFactory standardFactory = new StandardOrderFactory();
        OrderFactory expressFactory = new ExpressOrderFactory();

        // Создаём наблюдателей
        OrderObserver emailNotifier = new EmailNotifier();
        OrderObserver smsNotifier = new SMSNotifier();

        System.out.println("=== Пример 1: Стандартный заказ с картой ===");
        Order standardOrder = standardFactory.processOrder(1000, new CreditCardPayment());
        standardOrder.addObserver(emailNotifier);
        standardOrder.addObserver(smsNotifier);
        standardOrder.process();

        System.out.println("\n=== Пример 2: Экспресс‑заказ с наличными ===");
        Order expressOrder = expressFactory.processOrder(2000, new CashPayment());
        expressOrder.addObserver(smsNotifier);
        expressOrder.process();

        System.out.println("\n=== Пример 3: Заказ со страховкой ===");
        Order insuredOrder = new InsuranceDecorator(
            standardFactory.processOrder(1500, new CreditCardPayment())
        );
        insuredOrder.addObserver(emailNotifier);
        insuredOrder.process();
    }
}
/*
Как работают паттерны в связке

Фабричный метод:
StandardOrderFactory и ExpressOrderFactory создают разные типы заказов;
клиентский код не зависит от конкретных классов заказов.
Наблюдатель:
к заказу можно подключить несколько наблюдателей (EmailNotifier, SMSNotifier);
при изменении статуса заказа все наблюдатели получают уведомление.

Стратегия:
способ оплаты выбирается динамически (CreditCardPayment, CashPayment);
легко добавить новые способы оплаты без изменения основного кода.

Декоратор:
позволяет динамически добавлять функциональность (страховку) к заказу;
не требует создания новых подклассов для каждой комбинации услуг.
*/
