Команды и проекты
=================

Уже взятые проекты из [списка идей](https://github.com/nvamelichev/hse-java-spring-2021/blob/main/project-ideas.md) ~~вычёркиваются~~.

| Команда | Проект | Ближайший дедлайн | GitHub |
| ------- | ------ | ----------------- | ------ |
| Екатерина Стержанова, <br> Юлия Царева, <br> Зуфар Сафиуллин, <br> Георгий Колесников | 8. Симулятор игры "Жизнь" | Четвёртый релиз до **21.05**: https://github.com/yutsareva/game-of-life/issues/6 | [game-of-life](https://github.com/yutsareva/game-of-life) |
| Сабина Даянова, <br> Дарья Барановская, <br> Юлия Кошелева | 3. Экстрактор текста | Четвёртый релиз до **21.05**: https://github.com/sabinadayanova/text-extractor/issues/13 | [text-extractor](https://github.com/sabinadayanova/text-extractor) |
| Алексей Илюхов, <br> Михаил Анопренко, <br> Алексей Соколовский | 4. RPN-калькулятор | Четвёртый релиз до **21.05**: https://github.com/livace/rpn-calculator/issues/13 | [rpn-calculator](https://github.com/livace/rpn-calculator) |
| Петр Молодык, <br> Данила Седашов, <br> Екатерина Булатова, <br> Наталья Денисенко | **\*** Мод для Minecraft | Четвёртый релиз до **21.05**: https://github.com/pmolodyk/MinecraftFabricMod/issues/6 | [Minecraft Mod](https://github.com/pmolodyk/MinecraftFabricMod) |
| Александр Латышев, <br> Искандер Ямбулатов, <br> Максим Минченок | 1. Консольный органайзер | **[ДВАЖДЫ пропустили дедлайн]** Третий релиз до **14.05**: https://github.com/hypersousage/console-organizer/issues/5 | [console-organizer](https://github.com/hypersousage/console-organizer) |
| Елена Семерова, <br> Константин Еленик, <br> Игорь Глушков | 11. Трекер времени по системе Pomodoro | Четвёртый релиз до **21.05**: https://github.com/igoroogle/pomodoros/issues/5 | [pomodoros](https://github.com/igoroogle/pomodoros) |
| Константин Матвеев, <br> Андрей Ветров, <br> Тимур Ваньков, <br> Артём Самарин | 5. Статистика по исходному коду на C-like языках программирования | Четвёртый релиз до **21.05**: https://github.com/vetand/code-statistics/issues/25 | [code-statistics](https://github.com/vetand/code-statistics) |
| Борис Шошин, <br> Илья Зюляев | 10. Карточки-запоминалки (Flash Cards) | Четвёртый релиз до **21.05**: https://github.com/ilya2204/flash-cards/issues/5 | [flash-cards](https://github.com/ilya2204/flash-cards) |

**\*** &mdash; повышенный уровень сложности

Дедлайны
=================
<details><summary>Прошедшие</summary>

- **Тема проекта:** soft deadline 24.02, hard deadline 03.03.
  - Идеи проектов: https://github.com/nvamelichev/hse-java-spring-2021/blob/main/project-ideas.md.
  - Можно взять свою тему (по согласованию с преподавателем)
  - **Сменить тему** можно до 03.03 включительно, если в команде большинство участников «за».
- **Проектная документация (Product Vision, User Stories):** soft deadline 26.02, hard deadline 05.03.
  - FAQ, как её писать: https://github.com/nvamelichev/hse-java-spring-2021/blob/main/requirements-faq.md
  - Product Vision:
    - https://leadstartup.ru/db/product-vision (Agile)
    - https://intuit.ru/studies/courses/2188/174/lecture/4724?page=2 (более формальный подход из методологий RUP и MSF)
  - User Stories:
    - https://ru.wikipedia.org/wiki/Пользовательские_истории (сухая теория)
    - https://pmclub.pro/articles/user-story-pora-primenyat-pravilno (немного практики)
- **К семинару 05.03, все темы проектов и документация должны быть утверждены**.
- **Модель предметной области:** soft deadline 26.03, hard deadline 04.04. Варианты представления модели:
  - CRC-карточки или иная модель системы "с высоты птичьего полёта"
  - Более подробная ОО-модель системы, одно из:
    - [**рекомендуется**] UML-диаграммы (Use Cases, Class, Sequence и/или Statechart) или [ECore](https://www.eclipse.org/ecoretools/overview.html), плюс краткий поясняющий текст, если необходимо.
      - Для работы с UML я рекомендую PlantUML, потому что у него удобный текстовый синтаксис. 
      - Но подойдёт любой редактор (ArgoUML, Violet, dia, MS Visio, ...)
    - Диаграммы произвольного вида ("буйство квадратиков и стрелочек" (c)) + краткий поясняющий текст
    - [не рекомендуется] Код на Java или псевдокод, если кода немного и он хорошо документирован (классы + методы без тел, все с документацией)
- **Первый релиз:** soft deadline 09.04, hard deadline 16.04.
  - Критерий оценки: «Код **локально собирается** в исполняемый JAR-файл с помощью Maven/Gradle. JAR-файл **успешно запускается** `java -jar <jarfile.jar>`. Покрыт **хотя бы один ключевой пользовательский сценарий (User Story)**. Наличие работающих юнит-тестов к первому релизу будет преимуществом»
- **Второй релиз:** soft deadline 23.04, hard deadline 30.04.
  - Критерий оценки: «Реализовано **хотя бы два пользовательских сценария (User Story)**: ключевой и дополнительный. **Появилась обработка ошибок и неожиданных ситуаций** (например, файл не найден). **Есть юнит-тесты для всех основных классов** (кроме классов консольных команд), и тестовое покрытие (line/statement) &mdash; 70% или выше.»
- **Третий релиз:** soft deadline 30.04, hard deadline 21.05.
  - Критерий оценки: «Реализованы **все пользовательские сценарии (User Story)**. Будет преимуществом (но не строго обязательно), если ваш проект **упакован любым из следующих способов**: [Docker-образ](https://github.com/nvamelichev/hse-java-spring-2021/blob/main/8_containers/demo/pom.xml#L60-L180), GraalVM [native-image](https://www.graalvm.org/reference-manual/native-image/), [jlink](https://medium.com/azulsystems/using-jlink-to-build-java-runtimes-for-non-modular-applications-9568c5e70ef4)).»
</details>

Предстоящие:
- **Четвёртый, финальный релиз**: deadline 21.05, hard deadline 28.05. Критерии оценки: 
    - **Исправлены все проблемы**, обнаруженные в предыдущих релизах. 
    - Проект **упакован любым из следующих способов**: [Docker-образ](https://github.com/nvamelichev/hse-java-spring-2021/blob/main/8_containers/demo/pom.xml#L60-L180), GraalVM [native-image](https://www.graalvm.org/reference-manual/native-image/), [jlink](https://medium.com/azulsystems/using-jlink-to-build-java-runtimes-for-non-modular-applications-9568c5e70ef4). 
    - В **системе непрерывной интеграции**, интегрированной с GitHub (Github Actions, Travis CI, ...), успешно настроен **запуск тестов** на каждый коммит в ветке `main` и в пул-реквестах. 
    - [необязательно] Если в CI дополнительно настроена сборка проекта из `main` в исполняемый артефакт (докер-образ, native-image, jlink), **это будет преимуществом**.»

Общие требования
================
Цель проекта – создать **простой, но законченный продукт** вида «Java-библиотека + CLI к ней» (не мобильное и не веб-приложение).

**Продукт должен**:
  * делать одно дело/один класс дел, но хорошо (Unix way);
  * быть нетривиальным — не просто обёрткой над известной Java-библиотекой, методом из библиотеки классов JDK и т.п.;
  * быть достаточно гибким, чтобы можно было опробовать в нём паттерны проектирования, подключить Java-библиотеки для логгирования/сериализации/организации Command-Line Interface и т.д.

Будем практиковать **итеративную, гибкую (Agile) разработку**. Преподаватель будет **Product Owner'ом («владельцем продукта»)**, который:
* уточняет видение проекта, требования и приоритеты (но в пределах разумного, != «тут надо всё переделать»);
* предлагает и отклоняет идеи по развитию продукта;
* «щупает руками» релизы продукта;
* репортит баги (и продукта, и проектной документации);
* смотрит код и может рекомендовать улучшения архитектуры/code style/... (хотя реальный Product Owner не смотрел бы, конечно :-D);
* общается с командой **через комментарии в пул-реквестах на GitHub'е, на семинарах и лично (Telegram, Slack)**.

С каждой команды – публичное **мини-демо релиза на 5-7 мин.**. Демонстрации проводятся "через семинар", **начиная с 09.04**: ~~09.04~~ -> ~~23.04~~ -> ~~30.04~~ -> 21.05.

Требования к коду
=================

**ОБЯЗАТЕЛЬНО** должны быть:
  * Сборка Maven или Gradle (предпочтительнее Maven)
    * https://github.com/nvamelichev/hse-java-spring-2021/blob/main/1_maven/1_maven.pdf
  * Юнит-тесты. **В первом релизе &mdash; желательно, начиная со второго релиза &mdash; обязательно.**
  * Исполняемый артефакт: Docker-образ/исполняемый JAR-файл+скрипт для запуска/GraalVM native-image/jlink. **В третьем релизе &mdash; желательно, в четвёртом &mdash; обязательно.**
  * Непрерывная интеграция (GitHub Actions). **К четвёртому, финальному релизу**.

**НЕЛЬЗЯ**:
  * Сделать продукт-обёртку над готовой чужой библиотекой/чужим кодом со StackOverflow. *Например*, шаблонизатор не должен использовать Apache FreeMarker (Java-библиотеку **именно для** шаблонизации)

**Можно и нужно** юзать:
  * Популярные библиотеки, *например*, Google Guava, Apache Log4J 2, &hellip;
  * Паттерны, абстракции
  * Библиотеки, которые сделает в процессе работы над проектом соседняя команда. Но преподаватель должен про это знать :-)
