package service.javassist;

import javassist.*;

import java.lang.reflect.Method;

/**
 * Created by zhanglinxing on 2018/10/14.
 */
public class Util {
    public static CtClass makeClass(String className) {
        if (className == null) {
            return null;
        }
        ClassPool pool = ClassPool.getDefault();
        return pool.makeClass(className);
    }

    public static CtClass getClass(String className) {
        if (className == null) {
            return null;
        }
        ClassPool pool = ClassPool.getDefault();
        CtClass result;
        try {
             result =  pool.get(className);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return  result;
    }

    public static boolean makeMethod(String stateMent, CtClass clazz) {
        if (clazz == null || stateMent == null) {
            return false;
        }
        try {
            CtMethod mthd = CtNewMethod.make(stateMent, clazz);
            clazz.addMethod(mthd);
        } catch (CannotCompileException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static boolean makeField(Type type,String fieldName,CtClass cc){
        CtField f = null;
        try {
            f = new CtField(Type.getCtClass(type), fieldName, cc);
            cc.addField(f);
        } catch (CannotCompileException e) {
            e.printStackTrace();
            return  false;
        }
        return  true;
    }


    public static void main(String[] args) throws Exception {
        final String getMethod="public char getChar() { return ch; }";
        final String setMethod="public void setChar() {  this.ch='F'; }";
        String classNmae="my.Poi";
        CtClass newClass = makeClass(classNmae);
        makeField(Type.MY_CHAR, "ch", newClass);
        makeMethod(getMethod, newClass);
        makeMethod(setMethod, newClass);
        Class clazz = newClass.toClass();
        Object instance = clazz.newInstance();
        Method setter = instance.getClass().getMethod("setChar");
        setter.invoke(instance);
        Method getter = instance.getClass().getMethod("getChar");
        System.out.println(getter.invoke(instance));

    }



    public enum Type {
        MY_INT("int"),
        MY_CHAR("char");
        private String name;
        private Type(String name) {
            this.name = name;
        }
        public static  CtClass getCtClass(Type type){
            if(type == null){
                return  null;
            }
            switch (type) {
                case MY_INT:
                    return CtClass.intType;
                case  MY_CHAR:
                    return  CtClass.charType;
                default:
                    return  null;
            }
        }
    }
}
