
public abstract class ISBNContent extends Content implements Comparable<ISBNContent>{
    ISBN id;


    public ISBN getISBN(){
        return this.id;
    }

    public void setIdentifier(ISBN identifier){
        this.id = identifier;
    }

    public int compareTo(ISBNContent otherContent){
        return this.getISBN().compareTo(otherContent.getISBN());
    }
}
