
public class DogInformation {
    public static void main(String args[]) {

        String name = "Cujo";
        long chipNum = 52330648523343L;
        char gender = 'X';
        int age = 5;
        String breed = "Jack Russell";
        double height = 54;
        double weight = 19.5;

        // Checks for desexed status and assigns variables correctly
        boolean isDeSexed = false;
        String deSexed;
        if (!isDeSexed) deSexed = " not de-sexed";
        else deSexed = " de-sexed";

        // Checks for purebred status and assigns variables correctly
        boolean isPureBred = true;
        String pureBred;
        if (!isPureBred) pureBred = "";
        else pureBred = "purebred";

        String pronoun1;
        String pronoun2;
        String sex;
        if (gender == 'F') {
            pronoun1 = "Her";
            pronoun2 = "She is";
            sex = " female";
        }
        else if (gender == 'M') {
            pronoun1 = "His";
            pronoun2 = "He is";
            sex = " male";
        }
        else {
            pronoun1 = "Their";
            pronoun2 = "They are";
            sex = "";
        }

        String one = name + " is a " + age + " year old " + pureBred + sex + " " + breed + ". ";
        String two = pronoun1 + " microchip number is " + chipNum + ". ";
        String three = pronoun2 + deSexed + ". ";
        String four = pronoun1 + " height is " + height + "cm. " + pronoun1 + " weight is " + weight + "kg.";
        System.out.println(one + two + three + four);

    }
}
