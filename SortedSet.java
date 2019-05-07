import java.util.Iterator;
import java.util.ArrayList;

public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {
	//Storage Container
    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
    	myCon = new ArrayList<E>();
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * @param other != null
     */
    // code for mergesort from class slides
    //O(NlogN)
    public SortedSet(ISet<E> other) {
    	//initialize arraylist
    	myCon = new ArrayList<E>();
	    //iterate through data
    	for(E data : other) {
	    	myCon.add(data);
	    }
	    
	    //Credit to Scott 314 slides for MergeSort Code
	    if (!(other instanceof SortedSet<?>)) {
	    	//Temp array utility
    		ArrayList<E> temp = new ArrayList<E>(myCon);
    		//Call sort
    		sort(myCon, temp, 0, myCon.size() - 1);
    	}
    }
    
    //Helper for mergesort (Scott slides)
    //Converted to sort arraylist
    pr/*  Student information for assignment:
 *
 *  On MY honor, Adrian Melendez Relli, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose turnin account is being used)
 *  UTEID: arm5728
 *  email address: adrianmelendezrelli@gmail.com
 *  Grader name: ETHAN
 *  Section number: 51935
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are 
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet 
 * and override methods that can be done more efficiently. An ArrayList must 
 * be used as the internal storage container. For methods involving two set, 
 * if that method can be done more efficiently if the other set is also a 
 * SortedSet do so.
 */	
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {
	//Storage Container
    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
    	myCon = new ArrayList<E>();
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * @param other != null
     */
    // code for mergesort from class slides
    //O(NlogN)
    public SortedSet(ISet<E> other) {
    	//initialize arraylist
    	myCon = new ArrayList<E>();
	    //iterate through data
    	for(E data : other) {
	    	myCon.add(data);
	    }
	    
	    //Credit to Scott 314 slides for MergeSort Code
	    if (!(other instanceof SortedSet<?>)) {
	    	//Temp array utility
    		ArrayList<E> temp = new ArrayList<E>(myCon);
    		//Call sort
    		sort(myCon, temp, 0, myCon.size() - 1);
    	}
    }
    
    //Helper for mergesort (Scott slides)
    //Converted to sort arraylist
    private void sort(ArrayList<E> list, ArrayList<E> temp, int low, int high) {
    	//Continue recursion if low< high
    	if (low < high) {
    		//new center
    		int center = (low + high) / 2;
    		//sort btm
    		sort(list, temp, low, center);
    		//sort top
    		sort(list, temp, center + 1, high);
    		//then merge
    		merge(list, temp, low, center + 1, high);
    	}
    }

    //Helper for mergesort (Scott slides)
    private void merge (ArrayList<E> list, ArrayList<E> temp, int leftPos, int rightPos, int rightEnd) {
    	int leftEnd = rightPos - 1;
    	int tempPos = leftPos;
    	int numElements = rightEnd - leftPos + 1;
    	
    	//main loop
    	while( leftPos <= leftEnd && rightPos <= rightEnd) {
    		if( list.get(leftPos).compareTo(list.get(rightPos)) <= 0) {
    			//Convered to arraylist friendly temp.set
    			temp.set(tempPos, list.get(leftPos));
    			leftPos++;
    		}                  
    		else{
    			//Convered to arraylist friendly temp.set
    			temp.set(tempPos, list.get(rightPos));
    			rightPos++;
    		}
    		tempPos++;
    	}
    	//copy rest of left half
    	while( leftPos <= leftEnd){
    		temp.set(tempPos, list.get(leftPos));
    		tempPos++;
    		leftPos++;
    	}
    	//copy rest of right half
    	while( rightPos <= rightEnd){
    		temp.set(tempPos, list.get(rightPos));
    		tempPos++;
    		rightPos++;
    	}
    	//Copy temp back into list
    	for(int i = 0; i < numElements; i++, rightEnd--)
    		list.set(rightEnd, temp.get(rightEnd));
    }
    
    
    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    //O(1)
    public E min() {
    	//Check preconditions
    	if (this.size() == 0) {
    		throw new IllegalArgumentException("Size shouldn't be 0");
    	}
    	return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    //O(1)
    public E max() {
    	//Check preconditions
    	if (this.size() == 0) {
    		throw new IllegalArgumentException("Size shouldn't be 0");
    	}
    	//Be sure its size - 1
    	return myCon.get(size() - 1);
    }
    
    /**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    //O(N)
    public boolean add(E item) {
    	//Check preconditions
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null!");
    	
    	//If empty, add normally
    	} else if (this.size() == 0) {
    		myCon.add(item);
    		return true;
    	
    	//If this already contains item do nothing
    	} else if (this.contains(item)) {
    		return false;
    	}
    	
    	//Keep track of index to add in proper position
    	int index = 0;
    	for (E data: myCon) {
    		//If data is bigger, you can add here
    		if (data.compareTo(item) > 0) {
    			myCon.add(index, item);
    			return true;
    		}    		
    		index++;
    	}
    	//We've reached end, add at end (contains is already checked)
    	myCon.add(item);
    	return true;
    }


	/**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    // code for binary search from class slides
    // O(logN)
    public boolean contains(E item) {
    	//Check preconditions
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null!");
    	}
    	//Start and stop 
    	int start = 0, stop = myCon.size() - 1;
    	while (start <= stop) {
    		//declare middle
    		int medium = ( start + stop )/ 2;
    		//if middle is the item, found!
    		if (myCon.get(medium).equals(item)) {
    			return true;
    		}
    		
    		//If medium is less than item, item is above
    		if (myCon.get(medium).compareTo(item) < 0) {
    			start = medium + 1;
    		}
    		//else item is below
    		else {
    			stop = medium - 1;
    		}	
    	}
    	//else return false
    	return false;
    }
    
	/**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     */
    //O(n), there is a nested while but each set is only looped through once
    public boolean containsAll(ISet<E> otherSet) {
	   	//Check preconditions
	   	if(otherSet == null) {
	   		throw new IllegalArgumentException("Item is null!");
	   	}
	   	//if other is larger return false
    	if (otherSet.size() > this.size() ) {
    		return false;
    	}   
    	//Convert otherset to a sorted one if necessary
    	if (!(otherSet instanceof SortedSet)) {
    		otherSet = new SortedSet<E>(otherSet);
    	}
    	
    	//Iterators and temp containers
    	Iterator<E> thisIt = this.iterator();
    	Iterator<E> otherIt = otherSet.iterator();
    	E thisData = null;
    	E otherData = null;
    	
    	//Operate if possible
    	while (thisIt.hasNext() && otherIt.hasNext()) {
    		//move both
    		otherData = otherIt.next();
    		thisData = thisIt.next();
    		//if this is still smaller, move if possible
    		while (thisData.compareTo(otherData) < 0 && thisIt.hasNext()) {
    			thisData = thisIt.next();
    			//if reached and and still smaller, return false
    			if (thisData.equals(this.max()) && thisData.compareTo(otherData) < 0) {
        			return false;
        		}
        		
    		}	
    	} 
    	//return true if tests passed
    	return true;   	
    }
    
    
	/**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    //O(1)
    public Iterator<E> iterator() {
    	return myCon.iterator();
    }
    

	/**
     * Create a new set that is the difference of this set and otherSet. Return an ISet of 
     * elements that are in this Set but not in otherSet. Also called
     * the relative complement. 
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
     * A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W]. 
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    //O(n), each set only looped through once
    public ISet<E> difference(ISet<E> otherSet) {
	   	//Check preconditions
	   	if(otherSet == null) {
	   		throw new IllegalArgumentException("Item is null!");
	   	}
	   	//Convert to sorted set if needed
    	if (!(otherSet instanceof SortedSet)) {
			otherSet = new SortedSet<E>(otherSet);
		}
    	//Iterators and temp containers
		ISet<E> toReturn = new SortedSet<E>();
		Iterator<E> thisIt = this.iterator();
		Iterator<E> otherIt = otherSet.iterator();
		E thisData = null;
		E otherData = null;
		//Operate if possible
		while (thisIt.hasNext() && otherIt.hasNext()) {
			//move both
			otherData = otherIt.next();
			thisData = thisIt.next();
			//If this data is smaller, add it to the list
			if (thisData.compareTo(otherData) < 0) {
				toReturn.add(thisData);
			}
			//If this data is equal, or smaller, move if possible
			while (thisData.compareTo(otherData) <= 0 && thisIt.hasNext()) {
				thisData = thisIt.next();
				//If reached end of this, return
				if (thisData.equals(this.max()) && thisData.compareTo(otherData) <= 0) {
	    			return toReturn;
	    		}
	    		
			}	
		}    
		//return set
		return toReturn; 
    }
    
    

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exac tly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
    //O(N)
    public boolean equals(Object other) {
    	//Check if instance of
    	if (!(other instanceof SortedSet)) {
    		return false;
    	//If size is unequal return false
    	} else if (((SortedSet<?>) other).size() != this.size()) {
    		return false;
    	} else {
    		//Iterate through both simultaneously
    		Iterator<E> it = this.iterator();
    		Iterator<?> it2= ((SortedSet<?>) other).iterator();
    		while (it.hasNext() && it2.hasNext()) {
    			//if unequal return false
    			if (!(it.next().equals(it2.next()))) {
    				return false;
    			}
    		}
    	}
    	//defacto return true
    	return true;
    }
    
    
	/**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    //O(N)
    public ISet<E> intersection(ISet<E> otherSet) {
	   	//Check preconditions
	   	if(otherSet == null) {
	   		throw new IllegalArgumentException("Item is null!");
	   	}
    	//If not a sorted set, make it one
    	if (!(otherSet instanceof SortedSet)) {
			otherSet = new SortedSet<E>(otherSet);
		}
    	//Iterators and temporary data container
		ISet<E> toReturn = new SortedSet<E>();
		Iterator<E> otherIt = otherSet.iterator();
		E otherData = otherIt.next();
		//Keep a tracker for this operation
		int tracker = 0;
		//While this is below size and other it has next
		while (tracker < this.size() && otherIt.hasNext()) {
			//adjust this to the tracker
			E thisData = myCon.get(tracker);
			//if equal, add, and move both (tracker here)
			if (thisData.equals(otherData)) {
				toReturn.add(thisData);
				tracker++;
				otherData = otherIt.next();
			}
			//If other is smaller than data, move other
			else if (otherData.compareTo(thisData) < 0) {
				otherData = otherIt.next();
			}
			//If not, just move the tracker for this con
			else {
				tracker++;
			}
		}    
		//end of list has been reached, just add the other data
		if (myCon.get(tracker).equals(otherData)) {
			toReturn.add(otherData);
		}
		return toReturn;   
    }
    
	/**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise
     */
    //O(N)
    public boolean remove(E item) {
		//Check preconditions
    	if (item == null) {
			throw new IllegalArgumentException("Item is null!");
		}
    	//Loop through myCon and remove if equal to desired item
    	int index = 0;
    	while (index < myCon.size()) {
    		if (myCon.get(index).equals(item)) {
    			myCon.remove(index);
    			return true;
    		}
    	}
    	//If nothing found return false
    	return false;
    }
    
    
	/**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    //O(1)
    public int size() {
    	return myCon.size();
    }
    
    
	/**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    //O(N), uses difference that is also O(N)
    public ISet<E> union(ISet<E> otherSet){
	   	//Check preconditions
	   	if(otherSet == null) {
	   		throw new IllegalArgumentException("Item is null!");
	   	}
    	//Make new set to return
    	ISet<E> toReturn = new SortedSet<E>();
    	
    	//Sort if necessary
    	if (!(otherSet instanceof SortedSet)) {
    		otherSet = new SortedSet<E>(otherSet);
    	}
    	//Add needed data to toReturn
    	for(E data: myCon) {
    		toReturn.add(data);
    	}
    	for (E data: otherSet) {
    		toReturn.add(data);
    	}
    	//Make a new i set that is a difference of this and other
    	ISet<E> difference = otherSet.difference(this);
    	//Add its elements to toReturn
    	for(E data: difference) {
    		toReturn.add(data);
    	}
    	return toReturn;
    }
}ivate void sort(ArrayList<E> list, ArrayList<E> temp, int low, int high) {
    	//Continue recursion if low< high
    	if (low < high) {
    		//new center
    		int center = (low + high) / 2;
    		//sort btm
    		sort(list, temp, low, center);
    		//sort top
    		sort(list, temp, center + 1, high);
    		//then merge
    		merge(list, temp, low, center + 1, high);
    	}
    }

    //Helper for mergesort (Scott slides)
    private void merge (ArrayList<E> list, ArrayList<E> temp, int leftPos, int rightPos, int rightEnd) {
    	int leftEnd = rightPos - 1;
    	int tempPos = leftPos;
    	int numElements = rightEnd - leftPos + 1;
    	
    	//main loop
    	while( leftPos <= leftEnd && rightPos <= rightEnd) {
    		if( list.get(leftPos).compareTo(list.get(rightPos)) <= 0) {
    			//Convered to arraylist friendly temp.set
    			temp.set(tempPos, list.get(leftPos));
    			leftPos++;
    		}                  
    		else{
    			//Convered to arraylist friendly temp.set
    			temp.set(tempPos, list.get(rightPos));
    			rightPos++;
    		}
    		tempPos++;
    	}
    	//copy rest of left half
    	while( leftPos <= leftEnd){
    		temp.set(tempPos, list.get(leftPos));
    		tempPos++;
    		leftPos++;
    	}
    	//copy rest of right half
    	while( rightPos <= rightEnd){
    		temp.set(tempPos, list.get(rightPos));
    		tempPos++;
    		rightPos++;
    	}
    	//Copy temp back into list
    	for(int i = 0; i < numElements; i++, rightEnd--)
    		list.set(rightEnd, temp.get(rightEnd));
    }
    
    
    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    //O(1)
    public E min() {
    	//Check preconditions
    	if (this.size() == 0) {
    		throw new IllegalArgumentException("Size shouldn't be 0");
    	}
    	return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    //O(1)
    public E max() {
    	//Check preconditions
    	if (this.size() == 0) {
    		throw new IllegalArgumentException("Size shouldn't be 0");
    	}
    	//Be sure its size - 1
    	return myCon.get(size() - 1);
    }
    
    /**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    //O(N)
    public boolean add(E item) {
    	//Check preconditions
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null!");
    	
    	//If empty, add normally
    	} else if (this.size() == 0) {
    		myCon.add(item);
    		return true;
    	
    	//If this already contains item do nothing
    	} else if (this.contains(item)) {
    		return false;
    	}
    	
    	//Keep track of index to add in proper position
    	int index = 0;
    	for (E data: myCon) {
    		//If data is bigger, you can add here
    		if (data.compareTo(item) > 0) {
    			myCon.add(index, item);
    			return true;
    		}    		
    		index++;
    	}
    	//We've reached end, add at end (contains is already checked)
    	myCon.add(item);
    	return true;
    }


	/**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    // code for binary search from class slides
    // O(logN)
    public boolean contains(E item) {
    	//Check preconditions
    	if(item == null) {
    		throw new IllegalArgumentException("Item is null!");
    	}
    	//Start and stop 
    	int start = 0, stop = myCon.size() - 1;
    	while (start <= stop) {
    		//declare middle
    		int medium = ( start + stop )/ 2;
    		//if middle is the item, found!
    		if (myCon.get(medium).equals(item)) {
    			return true;
    		}
    		
    		//If medium is less than item, item is above
    		if (myCon.get(medium).compareTo(item) < 0) {
    			start = medium + 1;
    		}
    		//else item is below
    		else {
    			stop = medium - 1;
    		}	
    	}
    	//else return false
    	return false;
    }
    
	/**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     */
    //O(n), there is a nested while but each set is only looped through once
    public boolean containsAll(ISet<E> otherSet) {
	   	//Check preconditions
	   	if(otherSet == null) {
	   		throw new IllegalArgumentException("Item is null!");
	   	}
	   	//if other is larger return false
    	if (otherSet.size() > this.size() ) {
    		return false;
    	}   
    	//Convert otherset to a sorted one if necessary
    	if (!(otherSet instanceof SortedSet)) {
    		otherSet = new SortedSet<E>(otherSet);
    	}
    	
    	//Iterators and temp containers
    	Iterator<E> thisIt = this.iterator();
    	Iterator<E> otherIt = otherSet.iterator();
    	E thisData = null;
    	E otherData = null;
    	
    	//Operate if possible
    	while (thisIt.hasNext() && otherIt.hasNext()) {
    		//move both
    		otherData = otherIt.next();
    		thisData = thisIt.next();
    		//if this is still smaller, move if possible
    		while (thisData.compareTo(otherData) < 0 && thisIt.hasNext()) {
    			thisData = thisIt.next();
    			//if reached and and still smaller, return false
    			if (thisData.equals(this.max()) && thisData.compareTo(otherData) < 0) {
        			return false;
        		}
        		
    		}	
    	} 
    	//return true if tests passed
    	return true;   	
    }
    
    
	/**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    //O(1)
    public Iterator<E> iterator() {
    	return myCon.iterator();
    }
    

	/**
     * Create a new set that is the difference of this set and otherSet. Return an ISet of 
     * elements that are in this Set but not in otherSet. Also called
     * the relative complement. 
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
     * A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W]. 
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    //O(n), each set only looped through once
    public ISet<E> difference(ISet<E> otherSet) {
	   	//Check preconditions
	   	if(otherSet == null) {
	   		throw new IllegalArgumentException("Item is null!");
	   	}
	   	//Convert to sorted set if needed
    	if (!(otherSet instanceof SortedSet)) {
			otherSet = new SortedSet<E>(otherSet);
		}
    	//Iterators and temp containers
		ISet<E> toReturn = new SortedSet<E>();
		Iterator<E> thisIt = this.iterator();
		Iterator<E> otherIt = otherSet.iterator();
		E thisData = null;
		E otherData = null;
		//Operate if possible
		while (thisIt.hasNext() && otherIt.hasNext()) {
			//move both
			otherData = otherIt.next();
			thisData = thisIt.next();
			//If this data is smaller, add it to the list
			if (thisData.compareTo(otherData) < 0) {
				toReturn.add(thisData);
			}
			//If this data is equal, or smaller, move if possible
			while (thisData.compareTo(otherData) <= 0 && thisIt.hasNext()) {
				thisData = thisIt.next();
				//If reached end of this, return
				if (thisData.equals(this.max()) && thisData.compareTo(otherData) <= 0) {
	    			return toReturn;
	    		}
	    		
			}	
		}    
		//return set
		return toReturn; 
    }
    
    

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exac tly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
    //O(N)
    public boolean equals(Object other) {
    	//Check if instance of
    	if (!(other instanceof SortedSet)) {
    		return false;
    	//If size is unequal return false
    	} else if (((SortedSet<?>) other).size() != this.size()) {
    		return false;
    	} else {
    		//Iterate through both simultaneously
    		Iterator<E> it = this.iterator();
    		Iterator<?> it2= ((SortedSet<?>) other).iterator();
    		while (it.hasNext() && it2.hasNext()) {
    			//if unequal return false
    			if (!(it.next().equals(it2.next()))) {
    				return false;
    			}
    		}
    	}
    	//defacto return true
    	return true;
    }
    
    
	/**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    //O(N)
    public ISet<E> intersection(ISet<E> otherSet) {
	   	//Check preconditions
	   	if(otherSet == null) {
	   		throw new IllegalArgumentException("Item is null!");
	   	}
    	//If not a sorted set, make it one
    	if (!(otherSet instanceof SortedSet)) {
			otherSet = new SortedSet<E>(otherSet);
		}
    	//Iterators and temporary data container
		ISet<E> toReturn = new SortedSet<E>();
		Iterator<E> otherIt = otherSet.iterator();
		E otherData = otherIt.next();
		//Keep a tracker for this operation
		int tracker = 0;
		//While this is below size and other it has next
		while (tracker < this.size() && otherIt.hasNext()) {
			//adjust this to the tracker
			E thisData = myCon.get(tracker);
			//if equal, add, and move both (tracker here)
			if (thisData.equals(otherData)) {
				toReturn.add(thisData);
				tracker++;
				otherData = otherIt.next();
			}
			//If other is smaller than data, move other
			else if (otherData.compareTo(thisData) < 0) {
				otherData = otherIt.next();
			}
			//If not, just move the tracker for this con
			else {
				tracker++;
			}
		}    
		//end of list has been reached, just add the other data
		if (myCon.get(tracker).equals(otherData)) {
			toReturn.add(otherData);
		}
		return toReturn;   
    }
    
	/**
     * Remove the specified item from this set if it is present.
     * pre: item != null
     * @param item the item to remove from the set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise
     */
    //O(N)
    public boolean remove(E item) {
		//Check preconditions
    	if (item == null) {
			throw new IllegalArgumentException("Item is null!");
		}
    	//Loop through myCon and remove if equal to desired item
    	int index = 0;
    	while (index < myCon.size()) {
    		if (myCon.get(index).equals(item)) {
    			myCon.remove(index);
    			return true;
    		}
    	}
    	//If nothing found return false
    	return false;
    }
    
    
	/**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    //O(1)
    public int size() {
    	return myCon.size();
    }
    
    
	/**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    //O(N), uses difference that is also O(N)
    public ISet<E> union(ISet<E> otherSet){
	   	//Check preconditions
	   	if(otherSet == null) {
	   		throw new IllegalArgumentException("Item is null!");
	   	}
    	//Make new set to return
    	ISet<E> toReturn = new SortedSet<E>();
    	
    	//Sort if necessary
    	if (!(otherSet instanceof SortedSet)) {
    		otherSet = new SortedSet<E>(otherSet);
    	}
    	//Add needed data to toReturn
    	for(E data: myCon) {
    		toReturn.add(data);
    	}
    	for (E data: otherSet) {
    		toReturn.add(data);
    	}
    	//Make a new i set that is a difference of this and other
    	ISet<E> difference = otherSet.difference(this);
    	//Add its elements to toReturn
    	for(E data: difference) {
    		toReturn.add(data);
    	}
    	return toReturn;
    }
}
