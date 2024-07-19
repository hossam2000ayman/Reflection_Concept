/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reflection_concept;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author hp
 */
public class Reflection_Concept {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Cat myCat = new Cat("Stella", 6);
        //start to make reflection capabilities
        Field[] catFields = myCat.getClass().getDeclaredFields();

        System.out.println("=====================================================");
        System.out.println("Reflections with Fields : ");
        System.out.println("=====================================================");
        Arrays.stream(catFields).forEach(field -> {
            //we can show any properties (fields) on this instance using the reflection
            System.out.println(field.getName());
        }
        );

        Arrays.stream(catFields).forEach(field -> {
            //we can use reflection to force on change the cat name field which are final (mean that it cannot modified)
            if (field.getName().equals("name")) {
                field.setAccessible(true);
                try {
                    field.set(myCat, "Jimmy McGill");
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(Reflection_Concept.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        System.out.println(myCat.getName());
        System.out.println("=====================================================");
        System.out.println("Reflections with Methods : ");
        System.out.println("=====================================================");
        Method[] catMethods = myCat.getClass().getDeclaredMethods();

        //we can show any functions (methods) on this instance using the reflection
        String methodNames = Arrays.stream(catMethods).map(Method::getName).collect(Collectors.joining(" , "));
        System.out.println("Methods : [ " + methodNames + " ] ");

        //we can invoke our method public using reflection
        Arrays.stream(catMethods).forEach(method -> {
            if (method.getName().equals("meow")) {
                try {
                    System.out.print("Method (meow) : ");
                    method.invoke(myCat);//if you don't have argument for this method
//                method.invoke(myCat,args);//if you have argument for this method
                    System.out.println();
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(Reflection_Concept.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //we can invoke our method private using reflection
        Arrays.stream(catMethods).forEach(method -> {
            if (method.getName().equals("heyThisIsPrivate")) {
                try {
                    System.out.print("Method (heyThisIsPrivate) : ");
                    method.setAccessible(true);
                    method.invoke(myCat);//if you don't have argument for this method
//                method.invoke(myCat,args);//if you have argument for this method
                    System.out.println();
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(Reflection_Concept.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //we can invoke our method (public static) using reflection
        Arrays.stream(catMethods).forEach(method -> {
            if (method.getName().equals("thisIsAPublicStaticMethod")) {
                System.out.print("Method (thisIsAPublicStaticMethod) : ");
                try {
                    //if we have a static method that can call it self 
                    //then we can set the argument is null
                    method.invoke(null);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(Reflection_Concept.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("");
            }
        });

        //we can invoke our method (private static) using reflection
        Arrays.stream(catMethods).forEach(method -> {
            if (method.getName().equals("thisIsAPrivateStaticMethod")) {
                System.out.print("Method (thisIsAPrivateStaticMethod) : ");
                try {
                    //if we have a static method that can call it self 
                    //then we can set the argument is null
                    //in case if method is private then set Accessible is true
                    method.setAccessible(true);
                    method.invoke(null);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(Reflection_Concept.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("");
            }
        });
    }

}
