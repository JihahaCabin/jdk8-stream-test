package com.haha;

import com.sun.deploy.util.StringUtils;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static List<Student> students = new ArrayList<>();

    static {
        Student student1 = new Student(1, "张三", "高一一班");
        Student student2 = new Student(2, "李四", "高一二班");
        Student student3 = new Student(3, "王五", "高一一班");
        Student student4 = new Student(4, "赵六", "高一二班");
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

    }


    /**
     * 数组与列表相互转换
     */
    public static void test1() {
        //将数组转为列表
        String sArray[] = new String[]{"A", "B", "C"};
        //写法一
        List<String> list = Arrays.asList(sArray);
        System.out.println(list);
        // 使用该方法转换出的List,实际为Arrays的一个嵌套类ArrayList，是Arrays类利用asList()方法产生的一个长度不可变的容器
        // 如果使用add方法，会报错
        //list.add("w");

        //写法二
        List<String> list2 = new ArrayList<String>(Arrays.asList(sArray));
        System.out.println(list2);

        int iArray[] = new int[]{1, 2, 3};
        //写法三
        List<Integer> list3 = Arrays.stream(iArray).boxed().collect(Collectors.toList());
        System.out.println(list3);

        //列表转array
        String[] strs = list.toArray(new String[list.size()]);
        System.out.println(strs);

    }

    /**
     * 在Student集合中，筛选出高一一班的同学，并将其封装成为一个新的List
     */
    public static void filterTest() {

        List<Student> list = students.stream().filter(student -> student.getClassName().equals("高一一班")).collect(Collectors.toList());
        System.out.println(list);//输出结果 [高一一班, 高一二班]
    }

    /**
     * 在一个放有多个Student对象的list集合中，将每个Student对象的名字取出，组合成一个新的集合
     */
    public static void forEachTest() {
        //写法一
        List<String> studentNames = new ArrayList<>();
        students.forEach(student -> studentNames.add(student.getName()));
        System.out.println(studentNames);

        //写法二
        List<String> names = students.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(names);
    }

    /**
     * 去重测试，获取出所有学生的班级
     */
    public static void distinctTest() {
        List<String> list = students.stream().map(Student::getClassName).distinct().collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 返回前n个元素，当集合元素少于n个，则返回所有元素
     */
    public static void limitTest() {

        List<Student> collect = students.stream().limit(2).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 排序，根据id，从大到小排序
     */
    public static void sortTest() {
        List<Student> collect = students.stream().sorted((s1, s2) -> s2.getId() - s1.getId()).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 跳过前n个元素
     */
    public static void skipTest() {
        List<Student> collect = students.stream().skip(3).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 映射 map,取出名称
     */
    public static void mapTest() {
        List<String> collect = students.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(collect);

        int sum = students.stream().mapToInt(Student::getId).sum();
        System.out.println(sum);
    }


    /**
     * flatMap 将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流
     */
    public static void flatMapTest() {
        String[] strings = {"good", "data"};

        //如果直接拆分，获取到的会是一个数组列表，
        List<String[]> collect = Arrays.asList(strings).stream().map(s -> s.split("")).collect(Collectors.toList());
        System.out.println(collect);
        //使用flatMap,使多个流，变成一个流
        List<String> collect2 = Arrays.asList(strings).stream().map(s -> s.split("")).flatMap(strings1 -> Arrays.stream(strings1)).collect(Collectors.toList());
        System.out.println(collect2);

    }

    /**
     * 检测是否全部都满足指定的参数行为，如果全部满足则返回true，例如判断是否所有的学生都属于高一一班
     */
    public static void allMatchTest() {
        boolean isMatch = students.stream().allMatch(student -> student.getClassName().equals("高一一班"));
        System.out.println(isMatch);
    }

    /**
     * 检测是否存在至少一个满足指定的参数行为，如果存在则返回true，例如判断学生中，是否有属于高一一班的学生
     */
    public static void anyMatchTest() {
        boolean hasMatch = students.stream().anyMatch(student -> student.getClassName().equals("高一一班"));
        System.out.println(hasMatch);
    }

    /**
     * 检测是否全部都不满足指定的参数行为，如果全部都不满足则返回true，例如判断是否所有的学生都不属于高二一班
     */
    public static void noneMatchTest() {
        boolean isMatch = students.stream().noneMatch(student -> student.getClassName().equals("高二一班"));
        System.out.println(isMatch);
    }

    /**
     * 返回符合条件的第一个数据
     */
    public static void findFirstTest() {
        Optional<Student> firstOption = students.stream().filter(student -> student.getClassName().equals("高一一班")).findFirst();
        //使用get，如果没有查找到，会抛出异常 NoSuchElementException
        Student student = firstOption.get();
        System.out.println(student);

        //使用orElse或者orElseGet,如果没有查找到，则使用默认值返回，例如这里默认返回一个空的学生对象
        Student student1 = firstOption.orElse(new Student());
        System.out.println(student1);
        Student student2 = firstOption.orElseGet(() -> new Student());
        System.out.println(student2);
    }


    /**
     * 返回符合条件的任意一个数据
     */
    public static void findAnyTest() {
        Optional<Student> firstOption = students.stream().filter(student -> student.getClassName().equals("高一一班")).findAny();
        //使用get，如果没有查找到，会抛出异常 NoSuchElementException，可以使用orElse或者orElseGet,当没有查找到，使用默认值返回
        Student student = firstOption.get();
        System.out.println(student);
    }


    /**
     * 计算数量，例如计算高一一班人数
     */
    public static void countTest() {
        long size = students.stream().filter(student -> student.getClassName().equals("高一一班")).count();
        System.out.println(size);
    }

    /**
     * 获取最大最小值
     */
    public static void minAndMaxTest() {
        //两种方式获取id最大的学生
        Student student1 = students.stream().collect(Collectors.maxBy((s1, s2) -> s1.getId() - s2.getId())).get();
        Student student2 = students.stream().collect(Collectors.maxBy(Comparator.comparing(Student::getId))).get();
        System.out.println(student1);
        System.out.println(student2);

        //两种方式获取id最小的学生
        Student student3 = students.stream().collect(Collectors.minBy((s1, s2) -> s1.getId() - s2.getId())).get();
        Student student4 = students.stream().collect(Collectors.minBy(Comparator.comparing(Student::getId))).get();
        System.out.println(student3);
        System.out.println(student4);
    }

    /**
     * 计算某个字段值总和
     */
    public static void sumTest() {
        Integer sum = students.stream().collect(Collectors.summingInt(Student::getId));
        System.out.println(sum);

        int sum1 = students.stream().mapToInt(Student::getId).sum();
        System.out.println(sum1);
    }

    /**
     * 计算某个字段的平均值
     */
    public static void averageTest() {
        Double average = students.stream().collect(Collectors.averagingInt(Student::getId));
        System.out.println(average);

        double asDouble = students.stream().mapToInt(Student::getId).average().getAsDouble();
        System.out.println(asDouble);
    }

    /**
     * 一次性活出元素个数，总和，最大值，最小值和平均值
     */
    public static void summaryTest() {
        IntSummaryStatistics collect = students.stream().collect(Collectors.summarizingInt(Student::getId));
        System.out.println(collect);
    }

    /**
     * 字符串拼接
     */
    public static void joiningTest() {
        String names = students.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(names);
    }

    /**
     * 分组，例如根据班级分组
     */
    public static void groupingByTest() {
        Map<String, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getClassName));
        System.out.println(collect);
    }

    /**
     * 分区，可以认为是分组的一种特殊情况，根据返回值为true/false,将集合按条件一分为二
     */
    public static void partitionByTest() {
        Map<Boolean, List<Student>> map = students.stream().collect(Collectors.partitioningBy(student -> student.getClassName().equals("高一一班")));
        System.out.println(map);
    }

    /**
     * 将列表转为map
     */
    public static void toMapTest() {
        //使用toMap,如果key重复，会报错
        Map<Integer, String> collect = students.stream().collect(Collectors.toMap(Student::getId, Student::getName));
        System.out.println(collect);
        //指定key冲突解决方法，如果key重复，使用第二个key覆盖第一个key
        Map<String, Student> collect1 = students.stream().collect(Collectors.toMap(Student::getClassName, Student -> Student, (key1, key2) -> key2));
        System.out.println(collect1);

    }


    public static void main(String[] args) {
//        test1();
//        filterTest();
//        forEachTest();
//        distinctTest();
//        limitTest();
//        sortTest();
//        skipTest();
//        mapTest();
//        flatMapTest();
//        allMatchTest();
//        anyMatchTest();
//        noneMatchTest();
//        findFirstTest();
//        findAnyTest();
//        countTest();
//        minAndMaxTest();
//        sumTest();
//        averageTest();
//        summaryTest();
//        joiningTest();
//        groupingByTest();
//        partitionByTest();
        toMapTest();
    }
}
