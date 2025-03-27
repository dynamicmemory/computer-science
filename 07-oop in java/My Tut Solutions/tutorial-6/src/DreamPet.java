public class DreamPet {

    private final String breed;
    private final Sex sex;
    private final DeSexed deSexed;
    private final Purebred purebred;
    private int minAge;
    private int maxAge;

    public DreamPet(String breed, Sex sex, DeSexed deSexed, Purebred purebred, int minAge, int maxAge) {

        this.breed = breed;
        this.sex = sex;
        this.deSexed = deSexed;
        this.purebred = purebred;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public DreamPet(String breed, Sex sex, DeSexed deSexed, Purebred purebred) {

        this.breed = breed;
        this.sex = sex;
        this.deSexed = deSexed;
        this.purebred = purebred;
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

    public Purebred getPurebred() {
        return purebred;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public String getDreamPetDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getSex()).append(" ");

        if (this.getPurebred() == Purebred.YES) {
            sb.append("Purebred ");
        }
        sb.append(this.getBreed()).append(". \n> De-Sexed: ").append(this.getDeSexed()).
                append("\n> Adoption Fee: ");

        return sb.toString();
    }

    public boolean compareDreamPets(DreamPet petCriteria) {
        if (!petCriteria.getBreed().equals("NA")) {
            if (!this.getBreed().equals(petCriteria.getBreed())) return false; }
        if(!this.getSex().equals(petCriteria.getSex())) return false;
        if(!this.getDeSexed().equals(petCriteria.getDeSexed())) return false;
        if(!petCriteria.getPurebred().equals(Purebred.NA)) {
            if(!this.getPurebred().equals(petCriteria.getPurebred())) return false; }
        return true;
    }
}
