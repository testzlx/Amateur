package service.javassist;

import javassist.CtClass;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by zhanglinxing on 2018/10/14.
 */
public class Teacher {
    private String name;

    private int age;

    public Teacher(int age, String name) {
        this.name = name;
        this.age = age;
    }

    public Teacher() {
        this.name = "zhanglinxing";
        this.age = 34;
    }

    public static void main(String[] args) throws Exception {
        String clazz = "service.javassist.Teacher";
        CtClass cc = Util.getClass(clazz);
        final String getMethod = "public int getAge() { return age; }";
        Util.makeMethod(getMethod, cc);
        cc.writeFile();
        //getClassLoad();

        String path = "service.javassist.Teacher";
        MyClassLoader loader = new MyClassLoader();
        Thread.currentThread().setContextClassLoader(loader);
        Class<?> aClass = loader.findClass(path);
        Object teacher =  aClass.newInstance();
        //Teacher teacher = new Teacher(23,"zhang");
        Method getter = teacher.getClass().getMethod("getAge");
        System.out.println(getter.invoke(teacher));

    }
}
