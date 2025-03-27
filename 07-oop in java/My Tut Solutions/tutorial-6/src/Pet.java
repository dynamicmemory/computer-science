public record Pet(String name, long microchip, int age, DreamPet dreamPet, double adoptionFee) {

    @Override
    public String toString() {
        String sb = this.name() + " (" + this.microchip() + ") is a " + this.age() +
                " year old " + this.dreamPet().getDreamPetDescription() + this.adoptionFee() + "\n\n";
        return sb;
    }
}
