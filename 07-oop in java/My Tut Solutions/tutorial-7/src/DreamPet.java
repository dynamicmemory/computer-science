import java.util.*;

public class DreamPet {

    private final int minAge;
    private final int maxAge;
    private final Map<Criteria, Object> criteria;

    /**
     *
     * @param minAge lowest age user would be willing to adopt
     * @param maxAge highest age user would be willing to adopt
     */
    public DreamPet(Map<Criteria, Object> criteria, int minAge, int maxAge) {
        this.criteria = new HashMap<>(criteria);
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    /**
     *
     * @param
     */
    public DreamPet(Map<Criteria, Object> criteria) {
//        if (criteria==null) this.criteria=new LinkedHashMap<>();
        this.criteria = new HashMap<>(criteria);

        minAge = -1;
        maxAge = -1;
    }

    public Map<Criteria, Object> getAllCriteria() {
        return new HashMap<>(criteria);
    }

    public Object getCriteria(Criteria key) {return getAllCriteria().get(key); }

    /**
     * @return a 'dream' Pet's min age
     */
    public int getMinAge() {
        return minAge;
    }
    /**
     * @return a 'dream' Pet's max age
     */
    public int getMaxAge() {
        return maxAge;
    }

    /**
     * @return a formatted description of generic DreamPet features
     */
    public String getDreamPetDescription(Map<Criteria, Object> criteria){
        StringBuilder description = new StringBuilder();
        for (Criteria key : criteria.keySet()) {
            description.append("\n").append(key).append(": ").append(getCriteria(key));
        }
        return description.toString();
    }

    /**
     * method to compare two DreamPet objects against each other for compatibility
     * @param petCriteria an imaginary Pet representing the user's criteria
     * @return true if matches, false if not
     */
    public boolean compareDreamPets(DreamPet petCriteria) {
        for (Criteria key : petCriteria.getAllCriteria().keySet()) {
             if (this.getAllCriteria().containsKey(key)) {
                 if (getCriteria(key) instanceof Collection<?> && petCriteria.getCriteria(key) instanceof Collection<?>) {
                     Set<Object> intersect = new HashSet<>((Collection<?>) petCriteria.getCriteria(key));
                     intersect.retainAll((Collection<?>) petCriteria.getCriteria(key));
                     if (intersect.isEmpty()) return false;
                 }
                 else if (petCriteria.getCriteria(key) instanceof Collection<?> && !(getCriteria(key) instanceof Collection<?>)) {
                     if (!((Collection<?>) petCriteria.getCriteria(key)).contains(getCriteria(key))) return false;
                 }
                 else if (!getCriteria(key).equals(petCriteria.getCriteria(key).toString())) {
                     System.out.println("5");
                     return false;
                 }
             }
        }
        return true;
    }
}