

import java.text.DecimalFormat;
import java.util.Map;

/**
 * @param name            the Pet's name
 * @param microchipNumber the Pet's microchip number - unique 9-digit number
 * @param age             the Pet's age in years
 * @param adoptionFee     a double representing the adoption fee for this Pet
 * @param dreamPet        a pet's 'dream' properties
 */
public record Pet(String name, long microchipNumber, int age, double adoptionFee, DreamPet dreamPet) {

    /**
     * toString to return a description of a Pet, including all its unique and generic features
     * @return String
     */
    public String toString(Criteria[] criteria){
        DecimalFormat df = new DecimalFormat("0.00");
        return this.name()+" ("+this.microchipNumber()+ ") is a "+this.age()+" year old "+
                this.dreamPet().getDreamPetDescription(criteria)+
                ".\n > Adoption fee: $"+df.format(this.adoptionFee())+"\n";
    }
}