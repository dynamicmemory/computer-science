import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPets {

    private List<Pet> allPets = new ArrayList<>();

    public void addPet(Pet pet) {
        allPets.add(pet);
    }

    public Set<String> getAllBreeds(String animal) {
        Set<String> allBreeds = new HashSet<>();

        // tutorial has a different way of doing this, look for ass when doing ass
        if (animal.equals("Cat")) {
            for(Pet pet : allPets) {
                if (pet.dreamPet() instanceof DreamCat) {
                    allBreeds.add(pet.dreamPet().getBreed());
                }
            }
        }
        else {
            for(Pet pet : allPets) {
                if (pet.dreamPet() instanceof DreamDog) {
                    allBreeds.add(pet.dreamPet().getBreed());
                }
            }
        }
        allBreeds.add("NA");
        return allBreeds;
    }

    public List<Pet> findMatch(DreamPet dreamPet) {
        List<Pet> matches = new ArrayList<>();
        for (Pet pet : allPets) {
            if (pet.age() < dreamPet.getMinAge() || pet.age() > dreamPet.getMaxAge()) { continue; }
            if (!pet.dreamPet().compareDreamPets(dreamPet)) { continue; }
            matches.add(pet);
            }
        return matches;
        }
    }

