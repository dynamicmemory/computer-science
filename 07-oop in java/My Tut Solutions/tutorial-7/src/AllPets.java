
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPets {

    private final List<Pet> allPets = new ArrayList<>();
    /**
     * method to add a Pet object to the database (allPets)
     * @param pet a Pet object
     */
    public void addPet(Pet pet){
        this.allPets.add(pet);
    }

    /**
     * a method to return a set of all cat/dog breeds in the dataset (no duplicates)
     * @param type a String representing the type of pet the user is interested in (Cat or Dog)
     * @return Set</String> of available breeds
     */
    public Set<String> getAllBreeds(String type){
        Set<String> allBreeds = new HashSet<>();
        for(Pet p: allPets){
            System.out.println(p.dreamPet().getCriteria(Criteria.TYPE));
            if(p.dreamPet().getCriteria(Criteria.TYPE).equals(type)) {
                allBreeds.add(p.dreamPet().getCriteria(Criteria.BREED).toString());
            }
        }
        allBreeds.add("NA");
        return allBreeds;
    }

    /**
     * returns a collection of Pet objects that meet all the user's requirements
     * @param petCriteria a DreamPet object representing a user's preferred Pet
     * @return a Pet object
     */
     public List<Pet> findMatch(DreamPet petCriteria){
        List<Pet> compatiblePets = new ArrayList<>();
        for(Pet pet: this.allPets){
            if(!pet.dreamPet().compareDreamPets(petCriteria)) continue;
            if(pet.age()<petCriteria.getMinAge() || pet.age()> petCriteria.getMaxAge()) continue;
            compatiblePets.add(pet);
        }
         return compatiblePets;
    }

}
