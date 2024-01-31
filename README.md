# Design_Patterns
design patterns examples (JAVA)

Шаблоны проектирования, основанные на механизме создания объектов, помогают инкапсулировать процесс инстанцирования, делая его более гибким и менее привязанным к клиентскому коду. Вот список популярных порождающих шаблонов в Java:

– “Одиночка”: этот шаблон гарантирует, что существует только один экземпляр определенного класса.

      Шаблон проектирования «Одиночка» (Singleton) предписывает наличие единственного экземпляра класса, доступ к которому осуществляется через статический метод.
      Целью данного шаблона является обеспечение контроля над созданием экземпляров класса и
      предоставление глобальной точки доступа к этому экземпляру. Этот шаблон может быть полезен, 
      когда нужно контролировать количество объектов в системе или обеспечить глобальную точку доступа к некоторым ресурсам, таким как соединение с базой данных или файл настроек.
      Однако следует помнить, что использование шаблона «Одиночка» может привести к проблемам с масштабируемостью и тестируемостью кода, поэтому его следует применять с осторожностью.

– “Фабричный метод”: этот шаблон позволяет подклассам создавать объекты.

– “Абстрактная фабрика”: этот шаблон предоставляет семейство связанных объектов.

– “Строитель”: этот шаблон помогает пошагово создавать сложные объекты.

– “Прототип”: этот шаблон используется для клонирования объектов.

– “Цепочка обязанностей”: этот шаблон используется для перехвата запросов отправленых от одного обьекта к другому.

    Шаблон проектирования “Цепочка обязанностей” (Chain of Responsibility) позволяет передавать запрос по цепочке объектов, каждый из которых может обработать 
    его или передать следующему объекту в цепочке. Этот шаблон подходит в тех случаях, когда запрос может быть обработан несколькими объектами, 
    и мы не знаем заранее, какой именно объект должен обрабатывать запрос.
    Пример использования этого шаблона может быть в системе обработки ошибок. 
    У нас есть несколько уровней обработки ошибок, и мы хотим, чтобы запрос обрабатывался тем уровнем, который наиболее подходит для данной ситуации. Мы можем создать цепочку 
    объектов обработки ошибок, каждый из которых имеет свою логику обработки. Если первый объект не может обработать ошибку, он передаст запрос следующему объекту в цепочке и так далее,
    пока запрос не будет обработан или не дойдет до конца цепочки.


