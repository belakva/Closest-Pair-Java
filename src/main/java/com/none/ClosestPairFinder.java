package com.none;

import java.util.*;

public class ClosestPairFinder {

    private static final Comparator<Point> COMPARATOR_X = (p1, p2) -> Integer.compare(p1.x, p2.x);
    private static final Comparator<Point> COMPARATOR_Y = (p1, p2) -> Integer.compare(p1.y, p2.y);

    // Метод перебора всех пар и вычисления расстояния для каждой

    public static Pair bruteForce(List<Point> points) {

        final int numPoints = points.size();
        if (numPoints < 2)
            return null;

        Pair pair = new Pair(points.get(0), points.get(1));

        if (numPoints > 2) {

            for (int i = 0; i < numPoints - 1; i++) {

                final Point point1 = points.get(i);
                for (int j = i + 1; j < numPoints; j++) {

                    final Point point2 = points.get(j);

                    if (distance(point1,point2) < pair.getDistance()) {

                        pair = new Pair(point1, point2);;
                    }
                }
            }
        }
        return pair;
    }

    public Pair divideAndConquer(List<Point> points) {

        List<Point> pointsSortedByX = new ArrayList<>(points);
        Collections.sort(pointsSortedByX, COMPARATOR_X);
        List<Point> pointsSortedByY = new ArrayList<>(points);
        Collections.sort(pointsSortedByY,COMPARATOR_Y);

        return divideAndConquer(pointsSortedByX, pointsSortedByY);
    }

    private Pair divideAndConquer(List<Point> pointsSortedByX, List<Point> pointsSortedByY) {

        // Если точек всего 2 или 3, используем брутфорс.

        final int numPoints = pointsSortedByX.size();
        if (numPoints <= 3)
            return bruteForce(pointsSortedByX);

        final int dividingIndex = numPoints/2;

        List<Point> leftOfCenterSortedX = pointsSortedByX.subList(0, dividingIndex);
        List<Point> rightOfCenterSortedX = pointsSortedByX.subList(dividingIndex, numPoints);

        final Point midPoint = pointsSortedByX.get(dividingIndex);

        // Вариант сортировки 1.
        /*
        List<Point> leftOfCenterSortedY = new ArrayList<>(leftOfCenterSortedX);
        List<Point> rightOfCenterSortedY = new ArrayList<>(rightOfCenterSortedX);
        sortByY(leftOfCenterSortedY);
        sortByY(rightOfCenterSortedY);
        */
        // Вариант сортировки 2.
        // Другой вариант наполнения списков, без вызова метода sort, позволяет увеличить скорость процедуры
        // примерно на 70%.

        List<Point> leftOfCenterSortedY = new ArrayList<>();
        List<Point> rightOfCenterSortedY = new ArrayList<>();

        for (Point p : pointsSortedByY) {
            if (p.x < midPoint.x) {
                leftOfCenterSortedY.add(p);
            } else {
                rightOfCenterSortedY.add(p);
            }
        }

        // Вызываем функцию рекурсивно для левого и правого подмножеств, и находим по паре наиболее близко
        // расположенных друг к другу точек в каждом из этих подможножеств.

        Pair closestPair = divideAndConquer(leftOfCenterSortedX, leftOfCenterSortedY);
        final Pair closestPairRight = divideAndConquer(rightOfCenterSortedX, rightOfCenterSortedY);

        // Вычисляем, какая из этих пар имеет меньшее расстояние между точками.

        if (closestPairRight.getDistance() < closestPair.getDistance())
            closestPair = closestPairRight;
        final double shortestDistance = closestPair.getDistance();

        // Создаем список strip, в который добавляем все точки, находящиеся от разделительной линии на расстоянии
        // меньшем, чем shortestDistance.

        List<Point> strip = new ArrayList<>();
        for (Point point : pointsSortedByY) {
            if (Math.abs(midPoint.x - point.x) < shortestDistance)
                strip.add(point);
        }
        // Метод stripClosest проверяет все точки в списке strip, и, если найдёт среди них пару с меньшим расстоянием
        // между точками, чем в closestPair, заменит значение closestPair на новое.

        return stripClosest(strip, closestPair);
    }


    // Метод stripClosest проверяет все точки в списке strip, и, если найдёт среди них пару с меньшим расстоянием
    // между точками, чем в closestPair, заменит значение closestPair на новое. Выполнение метода занимает не O(n^2), а
    // только O(n) времени, т.к. внутренний луп не может быть запущен более 7 раз.

    // Доказательство, что луп не будет запущен более 7 раз:

    //Дано:

    //Рассматриваемая точка - p_i;
    //Множество точек слева от разделительной линии - A_1;
    //Множество точек слева от разделительной линии - A_2;
    //Наиболее короткое расстояние между двумя точками в множествах A_1 и A_2 - h;
    //Множество точек, находящихся в пределах расстояния h от точки p_i - C(p_i);

    //Решение:

    //Все точки множества C(p_i) лежат в полосе шириной 2h. Иными словами, рассматриваемые нами точки p_i и C(p_i)
    //лежат в прямоугольнике размера 2h * h. Наша задача — оценить максимальное количество точек, которое может лежать
    //в этом прямоугольнике 2h * h; тем самым мы оценим и максимальный размер множества C(p_i). При этом при оценке
    //надо не забывать, что могут встречаться повторяющиеся точки.

    //Вспомним, что h получалось как минимум из двух результатов рекурсивных вызовов — от множеств A_1 и A_2,
    //причём A_1 содержит точки слева от линии раздела, A_2 — точки линии раздела и точки справа от неё.
    //Для любой пары точек из A_1, равно как и из A_2, расстояние не может оказаться меньше h — иначе бы это означало
    //некорректность работы рекурсивной функции.

    //Для оценки максимального количества точек в прямоугольнике 2h * h разобьём его на два квадрата h * h, к первому
    //квадрату отнесём все точки C(p_i) из A_1, а ко второму — все остальные, т.е. C(p_i) из A_2. Из приведённых
    //выше соображений следует, что в каждом из этих квадратов расстояние между любыми двумя точками не менее h.

    //Покажем, что в каждом квадрате не более четырёх точек. Это можно сделать следующим образом: разобьём
    //квадрат на 4 подквадрата со сторонами h/2. Тогда в каждом из этих подквадратов не может быть больше одной точки
    //(т.к. даже диагональ равна h/sqrt{2}, что меньше h). Следовательно, во всём квадрате не может быть более 4 точек.

    //Итак, мы доказали, что в прямоугольнике 2h * h не может быть больше 4 * 2 = 8 точек, а, следовательно, размер
    //множества C(p_i) не может превосходить 7, что и требовалось доказать.

    //источник: http://e-maxx.ru/algo/nearest_points (на 11.10.2015 21:28 GMT+3)


    private Pair stripClosest(List<Point> strip, Pair closestPair) {
        for (int i = 0; i < strip.size() - 1; i++) {

            final Point point1 = strip.get(i);
            for (int j = i + 1; j < strip.size(); j++) {

                final Point point2 = strip.get(j);
                if ((point2.y - point1.y) >= closestPair.getDistance())
                    break;

                if (distance(point1,point2) < closestPair.getDistance()) {

                    closestPair = new Pair(point1, point2);
                }
            }
        }
        return closestPair;
    }

    private static double distance(final Point point1, final Point point2) {
        return Math.hypot(point2.x - point1.x, point2.y - point1.y);
    }

}
