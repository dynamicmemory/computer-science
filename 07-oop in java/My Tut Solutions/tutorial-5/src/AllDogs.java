
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllDogs {
    //fields
    private final List<Dog> allDogs = new ArrayList<>();

    //default constructor used, therefore no need to declare it

    //methods
    /**
     * method to add a Dog object to the database (allDogs)
     * @param dog a Dog object
     */
    public void addDog(Dog dog){
        this.allDogs.add(dog);
    }

    /**
     * a method to return a set of all breeds in the dataset (no duplicates)
     * @return Set</String> of available breeds
     */
    public Set<String> getAllBreeds(){
        Set<String> allBreeds = new HashSet<>();
        for(Dog d: allDogs){
            allBreeds.add(d.getDreamdog().getBreed());
        }
        return allBreeds;
    }

    /**
     * returns a collection of Dog objects that meet all the user's requirements
     * @param dogCriteria a Dog object representing a user's preferred Dog
     * @return a Dog object
     */
     public List<Dog> findMatch(DreamDog dogCriteria){
        List<Dog> compatibleDogs = new ArrayList<>();
        for(Dog dog: this.allDogs){
//            if(!dog.getDreamdog().getBreed().equals(dogCriteria.getBreed())) continue;
//            if(!dog.getDreamdog().getSex().equals(dogCriteria.getSex())) continue;
            if(dog.getAge()<dogCriteria.getMinAge() || dog.getAge()> dogCriteria.getMaxAge()) continue;
            if (!dog.getDreamdog().compareDreamDogs(dogCriteria)) continue;
//            if(!dog.getDreamdog().getDeSexed().equals(dogCriteria.getDeSexed())) continue;
//            if(!dogCriteria.getPurebred().equals(Purebred.NA)) {
//                if(!dog.getDreamdog().getPurebred().equals(dogCriteria.getPurebred())) continue; }
            compatibleDogs.add(dog);
        }
         return compatibleDogs;
    }

}
