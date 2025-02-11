<h1 align="center">Утилита фильтрации содержимого файлов</h1>
<h2>Инструкция</h2>
<p>Запуск утилиты осуществляется через консоль в соответствии с требаваниями тестового задания.</p>
<h4>Версия JDK: 14.0.2 2020-07-14</h4>
<h4>Версия Apache Maven: 3.6.1</h4>
<h2>Используемые библиотеки</h2>
<h3 align="center">Spring Framework</h3>
<div>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.3.20</version>
    </dependency>
</div>
<div>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.20</version>
    </dependency>
        </div>
<div>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>5.3.20</version>
    </dependency>
</div>      
<h3 align="center">Project Lombok</h3>
<div>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
    </dependency>
</div>
<h3 align="center">Apache Log4j</h3>
<div>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.17.1</version>
    </dependency>
</div>
<h3 align="center">Apache Commons CLI</h3>
<div>
    <dependency>
        <groupId>commons-cli</groupId>
        <artifactId>commons-cli</artifactId>
        <version>1.4</version>
    </dependency>
</div>
<h2></h2>
<p>Согласно тех. заданию программа должна считывать строки последовательно в соответсвии с декларацией порядка 
входных файлов в командной строке. В соответствии в программе не реализовано распараллеливание.</p>
<p>Согласно т.з. нет требований по объему используемой памяти. Чтение файлов происходит порциями по 1_000_000 строк 
за один проход обработки данных.</p>
<p>В итоговой статистике, выводимой на консоль вычисление среднего арифметического происходит с округлением до двух
чисел после запятой</p>
<p>Числа 0,1; 0.1; ,1; .1 считаются вещественными и равными между собой и учитываются в статистике вещественных чисел.</p>