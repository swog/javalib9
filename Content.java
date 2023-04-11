
public abstract class Content implements Comparable<Content>{
    String title;
    Identifier identifier;

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public Identifier getIdentifier(){
        return this.identifier;
    }
    public abstract void setIdentifier(Identifier identifier);

    @Override
    public int compareTo(Content c){ //two pieces of content are compared via the string order of their identifying number
        return this.getIdentifier().compareTo(c.getIdentifier());
    }


}
