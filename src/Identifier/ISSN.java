package src.Identifier;

public class ISSN extends Identifier{

    ISSN(String id){
        if ( id.length() != 8 ){
            throw new InvalidIdentifierException("Invalid length, ISSN is 8 characters long");
        } else {
            this.setId(id);
        }
    }
}
