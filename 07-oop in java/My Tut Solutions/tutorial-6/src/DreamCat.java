public class DreamCat extends DreamPet{

    private final Hair hair;

    public DreamCat(String breed, Sex sex, DeSexed deSexed, Purebred purebred, int minAge, int maxAge, Hair hair) {
        super(breed, sex, deSexed, purebred, minAge, maxAge);
        this.hair = hair;
    }

    public DreamCat(String breed, Sex sex, DeSexed deSexed, Purebred purebred, Hair hair) {
        super(breed, sex, deSexed, purebred);
        this.hair = hair;
    }

    public Hair getHair() {
        return hair;
    }

    @Override
    public boolean compareDreamPets(DreamPet petCriteria) {
        if (petCriteria instanceof DreamCat cat) {
            if (!super.compareDreamPets(petCriteria)) return false;

            if (cat.getHair().equals(Hair.NA)) return true;
            return this.getHair().equals(cat.getHair());
        }
        return false;
    }

    @Override
    public String getDreamPetDescription() {
        return super.getDreamPetDescription() + "\n Hairless: " + this.getHair() + "\n";
    }
}
