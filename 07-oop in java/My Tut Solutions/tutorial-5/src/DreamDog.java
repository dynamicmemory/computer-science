public class DreamDog {

    private final String breed;
    private final Sex sex;
    private final DeSexed deSexed;
    private final Purebred purebred;
    private int minAge;
    private int maxAge;

    public DreamDog(String breed, Sex sex, DeSexed deSexed, Purebred purebred, int minAge, int maxAge) {

        this.breed = breed;
        this.sex = sex;
        this.deSexed = deSexed;
        this.purebred = purebred;
        this.minAge = minAge;
        this.maxAge = maxAge;

    }

    public String getBreed() {
        return breed;
    }

    public Sex getSex() {
        return sex;
    }

    public DeSexed getDeSexed() {
        return deSexed;
    }

    public Purebred getPurebred() { return purebred; }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public StringBuilder getDreamDogDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getSex()).append(" ");

        if (this.getPurebred() == Purebred.YES) {
            sb.append("Purebred ");
        }
        sb.append(this.getBreed()).append(". \n> De-Sexed: ").append(this.getDeSexed()).
                append("\n> Adoption Fee: ");
        return sb;
    }

    public boolean compareDreamDogs(DreamDog dogCriteria) {
        if(!this.getBreed().equals(dogCriteria.getBreed())) return false;
        if(!this.getSex().equals(dogCriteria.getSex())) return false;
        if(!this.getDeSexed().equals(dogCriteria.getDeSexed())) return false;
        if(!dogCriteria.getPurebred().equals(Purebred.NA)) {
            if(!this.getPurebred().equals(dogCriteria.getPurebred())) return false; }
        return true;
    }
}
