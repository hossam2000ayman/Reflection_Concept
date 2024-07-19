/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package reflection_concept;

/**
 *
 * @author hp
 */
public class Cat {

    private final String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void meow() {
        System.out.print("Meow");
    }

    private void heyThisIsPrivate() {
        System.out.print("How did you call this??");
    }

    public static void thisIsAPublicStaticMethod() {
        System.out.print("I'm public and static!");
    }

    private static void thisIsAPrivateStaticMethod() {
        System.out.print("Hey, I'm private and static!");
    }

}
