import java.util.Map;

/**
 * Mokoto K
 * Made: Today
 * Tut 2 for OOP design in Java
 */

public class Main {

    public static void main(String[] args) {
//        Dog dog1 = new Dog("Fido", 45847589, "sasuage", "male", false, 5);
//        Dog dog2 = new Dog("Fido", 45847589, "poop", "male", false, 5);

//        System.out.println(dog1.isDeSexed());
//        dog1.setDeSexed(true);
//        System.out.println(dog1.isDeSexed());
        AdoptionRequest ar = new AdoptionRequest();
        Map<String ,Dog> returnMap = ar.loadDogData();
        System.out.println(returnMap);
        ar.getUserInput();
    }
}
