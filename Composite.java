// Пример паттерна «Компоновщик» (Composite) на Java

import java.util.ArrayList;
import java.util.List;

// Общий интерфейс для отдельных элементов и групп
interface FileSystemComponent {
    void display(String indent);
    long getSize();
}

// Листовой элемент (файл)
class File implements FileSystemComponent {
    private final String name;
    private final long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "Файл: " + name + " (" + size + " КБ)");
    }

    @Override
    public long getSize() {
        return size;
    }
}

// Композитный элемент (папка)
class Folder implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    // Добавление элемента в папку
    public void add(FileSystemComponent component) {
        children.add(component);
    }

    // Удаление элемента из папки
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "Папка: " + name);
        for (FileSystemComponent component : children) {
            component.display(indent + "  ");
        }
    }

    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

// Клиентский код:
// Создаёт иерархию элементов (файлы и папки).
// Добавляет элементы в папки.
// Вызывает методы display() и getSize() у корневого элемента — структура обрабатывается рекурсивно.

public class Composite {
    public static void main(String[] args) {
        // Создаём корневую папку
        Folder root = new Folder("Корень");

        // Создаём файлы
        File file1 = new File("Документ.txt", 100);
        File file2 = new File("Изображение.jpg", 500);

        // Создаём вложенную папку и добавляем в неё файлы
        Folder subfolder = new Folder("Подпапка");
        subfolder.add(new File("Скрипт.js", 200));
        subfolder.add(new File("Стиль.css", 150));

        // Собираем структуру
        root.add(file1);
        root.add(file2);
        root.add(subfolder);

        // Отображаем всю структуру
        System.out.println("Структура файловой системы:");
        root.display("");

        System.out.println("\nОбщий размер корневой папки: " + root.getSize() + " КБ");
    }
}
