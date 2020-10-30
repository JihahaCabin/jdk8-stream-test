package com.haha;

import com.sun.deploy.util.StringUtils;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


    public static void main(String[] args) {
//        test1();
//        filterTest();
//        forEachTest();
//        distinctTest();
//        limitTest();
//        sortTest();
//        skipTest();
//        mapTest();
        flatMapTest();
    }
}
