
public class Dog {
    //fields
    private final String name;
    private final long microchipNumber;
    private final int age;
    private final DreamDog dreamdog;
    private final double adoptionFee;
//    private final String breed;
//    private final Sex sex;
//    private final DeSexed deSexed;
//    private int minAge;
//    private int maxAge;

    /**
     * constructor to create a Dog object
     * @param name the dog's name
     * @param microchipNumber the dog's microchip number - unique 9-digit number
     * @param age the dog's age in years
     * @param breed the dog's breed
     * @param sex the dog's sex (male or female)
     * @param deSexed the dog's de-sexed status - true if de-sexed, false if not
     */
    public Dog(String name, long microchipNumber, int age, double adoptionFee, DreamDog dreamDog){
        this.name=name;
        this.microchipNumber=microchipNumber;
        this.age=age;
        this.dreamdog = dreamDog;
        this.adoptionFee = adoptionFee;
//        this.dreamDog(breed, sex, deSexed, 0, 0);
//        this.breed=breed;
//        this.sex=sex;
//        this.deSexed=deSexed;
    }

    //getters
    /**
     * @return the dog's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the dog's microchip number - unique 9-digit number
     */
    public long getMicrochipNumber() {
        return microchipNumber;
    }

    /**
     * @return the dog's age in years
     */
    public int getAge() {
        return age;
    }

    public DreamDog getDreamdog() {return dreamdog; }

    public double getAdoptionFee() {return adoptionFee; }

    //    /**
//     * @return the dog's breed
//     */
//    public String getBreed() {
//        return breed;
//    }
//
//    /**
//     * @return the dog's sex (male or female)
//     */
//    public Sex getSex() { return sex;}
//
//    /**
//     * @return the dog's de-sexed status
//     */
//    public DeSexed isDeSexed() { return deSexed; }
//
//    /**
//     * @param maxAge the max age a user is willing to adopt
//     */
//    public void setMaxAge(int maxAge) {
//        this.maxAge = maxAge;
//    }
//    /**
//     * @param minAge the min age a user is willing to adopt
//     */
//    public void setMinAge(int minAge) {
//        this.minAge = minAge;
//    }
//    /**
//     * @return a 'dream' dog's min age
//     */
//    public int getMinAge() {
//        return minAge;
//    }
//    /**
//     * @return a 'dream' dog's max age
//     */
//    public int getMaxAge() {
//        return maxAge;
//    }

    public StringBuilder getDogDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName()).append(" (").append(this.getMicrochipNumber()).append(") is a ").append(this.getAge()).
                append(" year old ").append(this.dreamdog.getDreamDogDescription()).append(this.adoptionFee).append("\n\n");
        return sb;
    }
}