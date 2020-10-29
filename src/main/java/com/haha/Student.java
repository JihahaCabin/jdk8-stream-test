package com.haha;

public class Student {

    private Integer Id;

    private String name;

    private String className;


    public Student() {
    }

    public Student(Integer id, String name, String className) {
        Id = id;
        this.name = name;
        this.className = className;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
