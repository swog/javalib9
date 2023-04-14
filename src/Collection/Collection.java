package Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Collection {


    private String collectionName; // books, newspapers, DVDs, or journals

    private Content[] item; // an array containing all items of the collection type

    Collection( String collectionName ){ //for creating a collection with no items

        this.setCollectionName(collectionName);
        this.item = new Content[0];
    }

    /* getters and setters */
    public String getCollectionName(){
        return this.collectionName;
    }

    public void setCollectionName(String collectionName){
        this.collectionName = collectionName;
    }

    public Content[] getContentArray(){
        return this.item;
    }

    public void setContentArray(Content[] contentArray){
        this.item = contentArray;
    }

    public void setContentArray(ArrayList<Content> contentArrayList){
        // CHECK THAT THIS WORKS
        this.item = contentArrayList.toArray( new Content[0] );
    }
    /* add, search, remove */
    public void addItem(Content item){
        ArrayList<Content> tempArrayList = new ArrayList<>(this.item.length+1);
        tempArrayList.addAll(Arrays.asList(this.item));
        tempArrayList.add(item);

        tempArrayList.sort(Comparator.naturalOrder());

        this.setContentArray(tempArrayList);
    }

    public int searchItemByIdentifierForIndex( Identifier identifier ){
       // returns index on found and -1 on not found
        Content[] contentArray = this.getContentArray();

        int maxIndex = contentArray.length;
        int minIndex = 0;
        int currentIndex = (maxIndex + minIndex)/2;


        while ( minIndex != currentIndex && maxIndex != currentIndex){
            Identifier potentiallyCorrectIdentifier = contentArray[currentIndex].getIdentifier();
            if ( potentiallyCorrectIdentifier.compareTo(identifier) == 0 ){
                return currentIndex;
            } else if ( potentiallyCorrectIdentifier.compareTo(identifier) < 0 ){ //if it's too low
                minIndex = currentIndex;
                currentIndex = ( maxIndex+minIndex )/2;
            } else if ( potentiallyCorrectIdentifier.compareTo(identifier) > 0 ){ //if it's too high
                maxIndex = currentIndex;
                currentIndex = ( maxIndex+minIndex )/2;
            }
        }

        return -1;

    }

    public Content searchItemByIdentifierForContent (Identifier identifier){

        Content[] contentArray = this.getContentArray();

        int maxIndex = contentArray.length;
        int minIndex = 0;
        int currentIndex = (maxIndex + minIndex)/2;


        while ( minIndex != currentIndex && maxIndex != currentIndex){
            Identifier potentiallyCorrectIdentifier = contentArray[currentIndex].getIdentifier();
            if ( potentiallyCorrectIdentifier.compareTo(identifier) == 0 ){
                return contentArray[currentIndex];
            } else if ( potentiallyCorrectIdentifier.compareTo(identifier) < 0 ){ //if it's too low
                minIndex = currentIndex;
                currentIndex = ( maxIndex+minIndex )/2;
            } else if ( potentiallyCorrectIdentifier.compareTo(identifier) > 0 ){ //if it's too high
                maxIndex = currentIndex;
                currentIndex = ( maxIndex+minIndex )/2;
            }
        }

        return null;

    }
    public Content getItemByIdentifier(Identifier identifier){
        return searchItemByIdentifierForContent(identifier);
    }

    public Content removeItemByIdentifier(Identifier identifier){
        int indexOfContentToRemove = searchItemByIdentifierForIndex(identifier);

        if ( indexOfContentToRemove == -1 ) return null; //nothing with that identifier found, do nothing

        Content[] contentArray = this.getContentArray();
        Content contentToReturn = contentArray[indexOfContentToRemove];

        ArrayList<Content> contentArrayList = new ArrayList<>();
        contentArrayList.addAll(Arrays.asList(contentArray));
        contentArrayList.remove(indexOfContentToRemove);
        this.setContentArray(contentArrayList);

        return contentToReturn;
    }
}
