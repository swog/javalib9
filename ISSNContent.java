public abstract class ISSNContent extends Content implements Comparable<ISSNContent>{
    ISSN id;


    public ISSN getISSN(){
        return this.id;
    }

    public void setIdentifier(ISSN identifier){
        this.id = identifier;
    }

    public int compareTo(ISSNContent otherContent){
        return this.getISSN().compareTo(otherContent.getISSN());
    }
}
