public abstract class Identifier implements Comparable<Identifier>{
    private String id;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    @Override
    public int compareTo(Identifier i){
        return this.getId().compareTo(i.getId());
    }

}
