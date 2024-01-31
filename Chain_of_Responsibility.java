//Пример реализации паттерна Цепочка обязанностей (Chain of Responsibility) на языке Java:

// Абстрактный обработчик
abstract class Handler {
private Handler nextHandler;

public void setNextHandler(Handler handler) {
this.nextHandler = handler;
}

public void handleRequest(Request request) {
if (canHandle(request)) {
processRequest(request);
} else if (nextHandler != null) {
nextHandler.handleRequest(request);
} else {
System.out.println("Ни один обработчик не может обработать запрос.");
}
}

protected abstract boolean canHandle(Request request);
protected abstract void processRequest(Request request);
}

// Конкретные обработчики
class ConcreteHandler1 extends Handler {
protected boolean canHandle(Request request) {
return request.getType().equals("Type1");
}

protected void processRequest(Request request) {
System.out.println("Обработчик 1 обрабатывает запрос: " + request.getDescription());
}
}

class ConcreteHandler2 extends Handler {
protected boolean canHandle(Request request) {
return request.getType().equals("Type2");
}

protected void processRequest(Request request) {
System.out.println("Обработчик 2 обрабатывает запрос: " + request.getDescription());
}
}

class ConcreteHandler3 extends Handler {
protected boolean canHandle(Request request) {
return request.getType().equals("Type3");
}

protected void processRequest(Request request) {
System.out.println("Обработчик 3 обрабатывает запрос: " + request.getDescription());
}
}

// Класс запроса
class Request {
private String type;
private String description;

public Request(String type, String description) {
this.type = type;
this.description = description;
}

public String getType() {
return type;
}

public String getDescription() {
return description;
}
}

// Пример использования
public class Main {
public static void main(String[] args) {
// Создание обработчиков
Handler handler1 = new ConcreteHandler1();
Handler handler2 = new ConcreteHandler2();
Handler handler3 = new ConcreteHandler3();

// Установка следующего обработчика в цепочке
handler1.setNextHandler(handler2);
handler2.setNextHandler(handler3);

// Создание запросов
Request request1 = new Request("Type1", "Запрос 1");
Request request2 = new Request("Type2", "Запрос 2");
Request request3 = new Request("Type3", "Запрос 3");

// Обработка запросов
handler1.handleRequest(request1);
handler1.handleRequest(request2);
handler1.handleRequest(request3);
}
}

/*
В этом примере абстрактный класс `Handler` представляет обработчик в цепочке. У него есть метод `setNextHandler`, который устанавливает следующий обработчик в цепочке, и метод `handleRequest`, 
который обрабатывает запрос, проверяет, может ли текущий обработчик обработать его, и если нет, передает запрос следующему обработчику в цепочке.
Конкретные классы обработчиков (`ConcreteHandler1`, `ConcreteHandler2`, `ConcreteHandler3`) наследуют абстрактный класс `Handler` и реализуют методы `canHandle` и `processRequest`. 
Метод `canHandle` проверяет, может ли текущий обработчик обработать запрос, а метод `processRequest` выполняет фактическую обработку запроса.
Класс `Request` представляет запрос, который содержит тип и описание. В методе `main` создаются обработчики, устанавливаются следующие обработчики в цепочке и создаются запросы, 
которые затем обрабатываются с помощью вызова метода `handleRequest` у первого обработчика в цепочке.
*/
