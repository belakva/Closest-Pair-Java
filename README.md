# ClosestPairProblem-Java
Closest Pair Problem in Java. Divide and Conquer style.
Intellij IDEA Community Edition 14.1.5 project

Задача

    Минимальное расстояние
    Дан набор из N точек на плоскости (для простоты можно считать, что у всех
    точек целочисленные координаты). Найдите минимальное расстояние между двумя
    точками из этого набора.
    
    Пример входных данных:
    10 10
    20 10
    20 15
    
    Пример выходных данных:
    5
    
Доступно 2 способа ввода данных:

    1. В качестве аргументов командной строки.
        Пример: java com.none.Main points.txt
            где points.txt - имя файла с исходными данными для расчётов
            в src/main/resources.
                        
            Пример файла points.txt:
            10 10
            20 10
            20 15
            
            где каждая строка описывает точку
            (первое число - x-координта, второе - y-координата). 
                                  
    2. В меню консоли программы. Здесь доступны следующие опции:
        а. Ввод координат точек в консоль. Подразумевает следующие шаги:
            - Определение количества точек, подлежащих обработке в настоящей сессии;
            - Ввод x-координаты и y-координаты каждой из точек.
        
        б. Чтение точек из файла points.txt.
    
Общий алгоритм решения
    
    Тривиальный алгоритм — перебор всех пар и вычисление расстояния для каждой
    (брутфорс) — работает за O(n^2). В решении применен алгоритм, работающий за
    время O(n log n). Этот алгоритм был предложен Франко Препарата в 1975 г.
    
    Алгоритм Препарата строится по общей схеме алгоритмов "разделяй-и-властвуй":
    в виде рекурсивной функции, которой передаётся множество точек; эта
    рекурсивная функция разбивает множество пополам, вызывает себя рекурсивно от
    каждой половины, а затем выполняет операцию по объединению ответов. Операция
    объединения заключается в обнаружении случаев, когда одна точка оптимального
    решения попала в одну половину, а другая точка — в другую.