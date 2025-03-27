// TODO - change strings male and female to M and F
/**
 * Handles all of the needed information and methoods for our dogs.
 * @param args
 */
public class Dog {
        private String name;
        private long microchip;
        private String breed;
        private String sex;
        private boolean deSexed;
        private int age;
        int minAge;
        int maxAge;

        public Dog(String name, long microchip, String breed, String sex, boolean deSexed, int age) {
            this.name = name;
            this.microchip = microchip;
            this.breed = breed;
            this.sex = sex;
            this.deSexed = deSexed;
            this.age = age;
        }

        public Dog(String breed, String sex, boolean deSexed, int minAge, int maxAge) {
            this.breed = breed;
            this.sex = sex;
            this.deSexed = deSexed;
            this.minAge = minAge;
            this.maxAge = maxAge;
        }

    // Getters

        /**
         * The getters for all the class attributes, all set to private at the moment
         * as I think that is the correct convention and I have no need to access them
         * from outside the class at the moment.
         * @return
         */
        public String getName(){ return name; }

        public Long getMicrochip(){return microchip; }

        public String getBreed(){return breed; }

        public String getSex(){return sex; }

        public boolean isDeSexed() {return deSexed; }

        public int age() {return age; }

        // Setters
        public void setDeSexed(boolean deSexed) {
                this.deSexed = deSexed;
        }

        // Comparison methods

        /**
         * Takes a dog object and compares their breed against the dog it was called on
         * @param dog1 = object of class Dog... dis class uwu
         * @return Treu or false
         */
        public boolean compareBreed(Dog dog) {
            return this.breed.equalsIgnoreCase(dog.breed);
        }

        /**
         * Takes a dog object and compares their sex against the dog it was called on
         * @param dog1 = object of class Dog... dis class uwu
         * @return Treu or false
         */
        public boolean compareSex(Dog dog) {
            return this.sex.equalsIgnoreCase(dog.sex);
        }

        /**
         * Takes a dog object and compares their DeSexed against the dog it was called on
         * @param dog1 = object of class Dog... dis class uwu
         * @return Treu or false
         */
        public boolean compareDeSexed(Dog dog) {
            return this.deSexed == dog.deSexed;
        }

        /**
         * Checks if a dogs age falls between a given set of numbers
         * @param min the minimum number for the dogs age
         * @param max the maximum age for the gods age
         * @return true or false depending on if the age falls in the range.
         */
        public boolean ageBetween(int min, int max) {
                return min < this.age && this.age < max;
        }
}
