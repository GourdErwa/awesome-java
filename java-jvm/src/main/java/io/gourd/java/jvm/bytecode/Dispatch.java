package io.gourd.java.jvm.bytecode;

/**
 * 单分派与多分派
 * <p>
 * Father.Fruit
 * Father.Fruit
 * Father.Apple
 * Son.Fruit
 * Son.Fruit
 * Son.Orange
 *
 * @author Li.Wei by 2020/1/15
 */
public class Dispatch {

    interface Fruit {
    }

    static class Apple implements Fruit {
    }

    static class Orange implements Fruit {
    }

    static class Father {

        public void eat(Fruit o) {
            System.out.println("Father.Fruit");
        }

        public void eat(Apple o) {
            System.out.println("Father.Apple");
        }

        public void eat(Orange o) {
            System.out.println("Father.Orange");
        }
    }

    static class Son extends Father {
        public void eat(Fruit o) {
            System.out.println("Son.Fruit");
        }

        @Override
        public void eat(Apple apple) {
            System.out.println("Son.Apple");
        }

        @Override
        public void eat(Orange apple) {
            System.out.println("Son.Orange");
        }
    }

    public static void main(String[] args) {
        final Fruit fruit = new Apple();
        final Fruit fruitImpl = new Fruit() {
        };
        final Apple apple = new Apple();
        final Orange orange = new Orange();

        final Father father = new Father();
        final Father son = new Son();

        father.eat(fruit);
        father.eat(fruitImpl);
        father.eat(apple);

        son.eat(fruit);
        son.eat(fruitImpl);
        son.eat(orange);
    }
}
