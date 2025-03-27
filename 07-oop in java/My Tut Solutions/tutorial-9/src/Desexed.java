public enum Desexed {
    YES, NO;

    public String toString(){
        return switch (this){
            case YES -> "Desexed";
            case NO -> "";
        };
    }

}
