/*
 * HOW TO USE COLLECTION CLASS:
 *
 * get/set collection name
 * get/set contentArray ( NOT RECOMMENDED )
 *
 * "I need to add an item to my collection object! "
 * void addItem( Content item ) :: adds item of any content type (be careful) to current collection object
 *
 * "I need to find an item in this Collection with identifier IDENT"
 * Content getItemByIdentifier(Identifier IDENT)
 *
 * "I need to remove an item from this Collection with identifier IDENT"
 * Content removeItemByIdentifier(Identifier IDENT)
 * >>>>> NOTE: this returns the object removed if you want it
 *
 * "I need to sort this collection by title, identifier, or checkout status!"
 * sortCollectionByTitle()
 * sortCollectionByIdentifier()
 * sortCollectionByCheckoutStatus()
 * */
package src.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import src.Content.Content;
import src.Identifier.Identifier;
import src.Content.ISBNContent;
import src.Content.ISSNContent;

public class Collection {

    private String collectionName; // books, newspapers, DVDs, or journals

    private Content[] item; // an array containing all items of the collection type

    private String sortedBy;

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

    public String getSortSetting(){
        return this.sortedBy;
    }

    public void setSortSetting(String newSortMethod){
        if ( newSortMethod != "Title" && newSortMethod != "Identifier" && newSortMethod != "Checkout Status" ){
            throw new InvalidCollectionSortSettingException("May only sort by 'Title', 'Identifier', or 'Checkout Status', no changes made");
        }
        else {
            this.sortedBy = newSortMethod;
        }
    }

    /* add, search, remove */
    public void addItem(Content item){
        ArrayList<Content> tempArrayList = new ArrayList<>(this.item.length+1);
        tempArrayList.addAll(Arrays.asList(this.item));
        tempArrayList.add(item);

        tempArrayList = Collection.sortArrayList(tempArrayList, this.getSortSetting());

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

    /* sorting a collection object */

    public void sortCollectionByTitle(){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();
        contentArrayList.addAll(Arrays.asList(currentContentArray));

        contentArrayList = sortArrayList(contentArrayList,"Title");

        this.setContentArray(contentArrayList);

    }
    public void sortCollectionByIdentifier(){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();
        contentArrayList.addAll(Arrays.asList(currentContentArray));

        contentArrayList = sortArrayList(contentArrayList,"Title");

        this.setContentArray(contentArrayList);
    }

    public void sortCollectionByCheckoutStatus(){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();
        contentArrayList.addAll(Arrays.asList(currentContentArray));

        contentArrayList = sortArrayList(contentArrayList,"Title");

        this.setContentArray(contentArrayList);
    }

    private static ArrayList<Content> sortArrayList(ArrayList<Content> myArrayList, String sortSetting){ // DO NOT USE THE PASSED ARRAY LIST AS INCONSISTENT THINGS HAPPEN, USE THE RETURNED ARRAY LIST
        // NOTE: kinda inefficient whoops
        if ( sortSetting.equals("Title") ){

            /* insertion sort i think*/
            for ( int i = 0 ; i < myArrayList.size() ; i++ ){
                for ( int j = i-1 ; j >= 0 ; j-- ){
                    if ( myArrayList.get(j+1).getTitle().compareTo(myArrayList.get(j).getTitle()) < 0 ){ // if item[i] smaller than item[i-1]

                        //swapping
                        Content temp = myArrayList.get(j+1);
                        myArrayList.set(j+1, myArrayList.get(j));
                        myArrayList.set(j, temp);

                    }
                    else {
                        // we sorted up to i, we can move on
                        break;
                    }
                }
            }

            return myArrayList;

        } else if ( sortSetting.equals("Identifier") ){

            ArrayList<ISBNContent> ISBNContentArrayList = new ArrayList<>();
            ArrayList<ISSNContent> ISSNContentArrayList = new ArrayList<>();

            for (int i = 0; i < myArrayList.size(); i++) {
                if (myArrayList.get(i) instanceof ISBNContent) {
                    ISBNContentArrayList.add((ISBNContent) myArrayList.get(i));
                } else if (myArrayList.get(i) instanceof ISSNContent) {
                    ISSNContentArrayList.add((ISSNContent) myArrayList.get(i));
                }
            }

            ISBNContentArrayList.sort(Comparator.naturalOrder());
            ISSNContentArrayList.sort(Comparator.naturalOrder());

            ArrayList<Content> newSortedArrayList = new ArrayList<>();
            newSortedArrayList.addAll(ISBNContentArrayList);
            newSortedArrayList.addAll(ISSNContentArrayList);

            return newSortedArrayList;

        } else if ( sortSetting.equals("Checkout Status") ){
            ArrayList<Content> notCheckedOutList = new ArrayList<>();
            ArrayList<Content> checkedOutList = new ArrayList<>();
            ArrayList<Content> lostList = new ArrayList<>();

            for ( int i = 0 ; i < myArrayList.size() ; i++){
                Content currentItem = myArrayList.get(i);
                if ( currentItem.getCheckoutStatus().equals("Not Checked Out") ){
                    notCheckedOutList.add(currentItem);
                } else if ( currentItem.getCheckoutStatus().equals("Checked Out") ){
                    checkedOutList.add(currentItem);
                } else if ( currentItem.getCheckoutStatus().equals("Lost") ){
                    lostList.add(currentItem);
                }
            }

            ArrayList<Content> returningArrayList = new ArrayList<>();
            returningArrayList.addAll(notCheckedOutList);
            returningArrayList.addAll(checkedOutList);
            returningArrayList.addAll(lostList);

            return returningArrayList;
        } else {
            throw new InvalidCollectionSortSettingException("Your passed sort setting is invalid");
        }


    }
}
