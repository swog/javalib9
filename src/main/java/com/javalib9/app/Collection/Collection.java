/*
 * HOW TO USE COLLECTION CLASS:
 *
 * "I need to add an item to my collection object! "
 * void addItem( Content item ) :: adds item of any content type (be careful) to current collection object
 *
 * "I need to find an item in this Collection with identifier IDENT"
 * Content getItemByIdentifier(Identifier IDENT)
 *
 * "I want the nth item in the collection!"
 * Content getItemByIndex(int n)
 *
 * "I need to remove an item from this Collection with identifier IDENT"
 * Content removeItemByIdentifier(Identifier IDENT)
 * >>>>> NOTE: this returns the object removed if you want it
 *
 *
 * "I need to sort this collection by title, identifier, or checkout status!"
 * sortCollectionByTitle()
 * sortCollectionByIdentifier()
 * sortCollectionByCheckoutStatus()
 *
 * "I need a Collection object featuring only Books, or only Lost items, etc. from a collection I already have"
 * Collection getISBNContentCollectionSubset()
 * Collection getISSNContentCollectionSubset()
 * Collection getBookCollectionSubset()
 * Collection getNewspaperCollectionSubset()
 * Collection getJournalCollectionSubset()
 * Collection getDVDCollectionSubset()
 * Collection getLostCollectionSubset()
 * Collection getCheckedOutCollectionSubset()
 * Collection getNotCheckedOutCollectionSubset()
 * >>>>>> NOTE: this does not change the original collection, it only returns a new collection object with what you need.
 * */
package com.javalib9.app.Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import com.javalib9.app.Identifier.Identifier;

import com.javalib9.app.Content.Content;
import com.javalib9.app.Content.ISBNContent;
import com.javalib9.app.Content.ISSNContent;
import com.javalib9.app.Content.Book;
import com.javalib9.app.Content.DVD;
import com.javalib9.app.Content.Journal;
import com.javalib9.app.Content.Newspaper;

public class Collection {

    private String collectionName; // books, newspapers, DVDs, or journals

    private Content[] item; // an array containing all items of the collection type

    private String sortedBy;

    public Collection( String collectionName ){ //for creating a collection with no items

        this.setCollectionName(collectionName);
        this.setContentArray(new Content[0]);
        this.setSortSetting("Identifier");
    }

    public Collection ( String collectionName, Content[] contentArray ){

        this.setCollectionName(collectionName);
        this.setContentArray(contentArray);
        this.setSortSetting("Identifier");

    }
    public Collection ( String collectionName, ArrayList<Content> contentArrayList ){
        this.setCollectionName(collectionName);
        this.setContentArray(contentArrayList);
        this.setSortSetting("Identifier");
    }

    public Collection (String collectionName, String sortSetting){
        if ( isValidSortSetting(sortSetting) ){
            this.setCollectionName(collectionName);
            this.setContentArray(new Content[0]);
            this.setSortSetting(sortSetting);
        }
        else {
            throw new InvalidCollectionSortSettingException("Collection sort setting invalid");
        }
    }
    public Collection (String collectionName, Content[] contentArray, String sortSetting){
        if ( isValidSortSetting(sortSetting) ){
            this.setCollectionName(collectionName);
            this.setContentArray(contentArray);
            this.setSortSetting(sortSetting);
        }
        else {
            throw new InvalidCollectionSortSettingException("Collection sort setting invalid");
        }
    }
    public Collection (String collectionName, ArrayList<Content> contentArrayList, String sortSetting){
        if ( isValidSortSetting(sortSetting) ){
            this.setCollectionName(collectionName);
            this.setContentArray(contentArrayList);
            this.setSortSetting(sortSetting);
        }
        else {
            throw new InvalidCollectionSortSettingException("Collection sort setting invalid");
        }
    }


    /* getters and setters */
    public int getCollectionLength(){
        Content[] col = getContentArray();
        return col.length;
    }
    public int length(){
        return getCollectionLength();
    }

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

        int oldCurrentIndex = -1;
        while ( oldCurrentIndex != currentIndex ){
            Identifier potentiallyCorrectIdentifier = contentArray[currentIndex].getIdentifier();
            oldCurrentIndex = currentIndex;
            if ( potentiallyCorrectIdentifier.compareTo(identifier) == 0 ){
                return currentIndex;
            } else if ( potentiallyCorrectIdentifier.compareTo(identifier) > 0 ){ //if it's too low
                minIndex = currentIndex;
                currentIndex = ( maxIndex+minIndex )/2;
            } else if ( potentiallyCorrectIdentifier.compareTo(identifier) < 0 ){ //if it's too high
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

    public Content getItemByIndex(int index){
        Content[] contArray = getContentArray();

        if ( index >= contArray.length ){
            return null;
        }

        return contArray[index];
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

    /* getting a subset of Collection */

    public Collection getISBNContentCollectionSubset(String newCollectionName){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();

        for ( int i = 0 ; i < currentContentArray.length ; i++ ){

            if ( currentContentArray[i] instanceof ISBNContent ){

                contentArrayList.add(currentContentArray[i]);
            }
        }

        Collection newCollection = new Collection(newCollectionName, contentArrayList);

        return newCollection;
    }
    public Collection getISSNContentCollectionSubset(String newCollectionName){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();

        for ( int i = 0 ; i < currentContentArray.length ; i++ ){

            if ( currentContentArray[i] instanceof ISSNContent ){

                contentArrayList.add(currentContentArray[i]);
            }
        }

        Collection newCollection = new Collection(newCollectionName, contentArrayList);

        return newCollection;
    }
    public Collection getBookCollectionSubset(String newCollectionName){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();

        for ( int i = 0 ; i < currentContentArray.length ; i++ ){

            if ( currentContentArray[i] instanceof Book ){

                contentArrayList.add(currentContentArray[i]);
            }
        }

        Collection newCollection = new Collection(newCollectionName, contentArrayList);

        return newCollection;
    }
    public Collection getDVDCollectionSubset(String newCollectionName){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();

        for ( int i = 0 ; i < currentContentArray.length ; i++ ){

            if ( currentContentArray[i] instanceof DVD ){

                contentArrayList.add(currentContentArray[i]);
            }
        }

        Collection newCollection = new Collection(newCollectionName, contentArrayList);

        return newCollection;
    }
    public Collection getNewspaperCollectionSubset(String newCollectionName){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();

        for ( int i = 0 ; i < currentContentArray.length ; i++ ){

            if ( currentContentArray[i] instanceof Newspaper ){

                contentArrayList.add(currentContentArray[i]);
            }
        }

        Collection newCollection = new Collection(newCollectionName, contentArrayList);

        return newCollection;
    }
    public Collection getJournalCollectionSubset(String newCollectionName){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();

        for ( int i = 0 ; i < currentContentArray.length ; i++ ){

            if ( currentContentArray[i] instanceof Journal ){

                contentArrayList.add(currentContentArray[i]);
            }
        }

        Collection newCollection = new Collection(newCollectionName, contentArrayList);

        return newCollection;
    }

    public Collection getNotCheckedOutCollectionSubset(String newCollectionName){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();

        for ( int i = 0 ; i < currentContentArray.length ; i++ ){

            if ( currentContentArray[i].getCheckoutStatus().equals("Not Checked Out")){

                contentArrayList.add(currentContentArray[i]);
            }
        }

        Collection newCollection = new Collection(newCollectionName, contentArrayList);

        return newCollection;

    }
    public Collection getCheckedOutCollectionSubset(String newCollectionName){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();

        for ( int i = 0 ; i < currentContentArray.length ; i++ ){

            if ( currentContentArray[i].getCheckoutStatus().equals("Checked Out")){

                contentArrayList.add(currentContentArray[i]);
            }
        }

        Collection newCollection = new Collection(newCollectionName, contentArrayList);

        return newCollection;

    }
    public Collection getLostCollectionSubset(String newCollectionName){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();

        for ( int i = 0 ; i < currentContentArray.length ; i++ ){

            if ( currentContentArray[i].getCheckoutStatus().equals("Lost")){

                contentArrayList.add(currentContentArray[i]);
            }
        }

        Collection newCollection = new Collection(newCollectionName, contentArrayList);

        return newCollection;

    }


    /* sorting a collection object */

    public void sortCollectionByTitle(){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();
        contentArrayList.addAll(Arrays.asList(currentContentArray));

        contentArrayList = sortArrayList(contentArrayList,"Title");

        this.setContentArray(contentArrayList);
        this.setSortSetting("Title");

    }
    public void sortCollectionByIdentifier(){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();
        contentArrayList.addAll(Arrays.asList(currentContentArray));

        contentArrayList = sortArrayList(contentArrayList,"Title");

        this.setContentArray(contentArrayList);
        this.setSortSetting("Identifier");
    }

    public void sortCollectionByCheckoutStatus(){
        Content[] currentContentArray = this.getContentArray();

        ArrayList<Content> contentArrayList = new ArrayList<>();
        contentArrayList.addAll(Arrays.asList(currentContentArray));

        contentArrayList = sortArrayList(contentArrayList,"Title");

        this.setContentArray(contentArrayList);
        this.setSortSetting("Checkout Status");
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

    private boolean isValidSortSetting(String sortSetting){
        if ( !sortSetting.equals("Title") && !sortSetting.equals("Identifier") && !sortSetting.equals("Checkout Status")){
            return false;
        }

        return true;
    }

}
