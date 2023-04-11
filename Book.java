public class Book extends Content{

    Book(String title, ISBN identifier){
        setTitle(title);
        setIdentifier(identifier);
    }

    public void setIdentifier(Identifier identifier){
        this.identifier = identifier;
    }

}
