package com.haha;

import com.sun.deploy.util.StringUtils;

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


    public static void main(String[] args) {
//        test1();
//        filterTest();
        forEachTest();
//        distinctTest();
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
}
