import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.xml.transform.Result;

import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
	//Instance Variable
	private ArrayList<E> myCon;
	
	//Constructor for UnsortedSet, initialize Arraylist
	public UnsortedSet () {
		myCon = new ArrayList<E>();
	}


	/**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
	// O (N)
	public boolean add (E item) {
		//Check preconditions
		if (item == null) {
			throw new IllegalArgumentException("Item is null!");
		}
		
		//Iterate through dataset, check for equality
		for (E data : myCon) {
			if (data.equals(item)) {
				return false;
			}
		}
		
		//If no equal found, add to con and return true
		myCon.add(item);
		return true;
	}
    

    
	/**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
	// O(1)
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
    //O(N^2)
    public ISet<E> difference(ISet<E> otherSet) {
    	//Check preconditions
    	if (otherSet == null) {
    		throw new IllegalArgumentException("OtherSet is null");
    	}
    	//Make new set to return
    	ISet<E> toReturn = new UnsortedSet<E>();
    	//Iterate through current data
    	for (E data: myCon) {
    		// if not in otherSet, add to toReturn
    		if (!otherSet.contains(data)) {
    			toReturn.add(data);
    		}
    	}
    	return toReturn;
    }
    
    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
    //O(N^2)
    public boolean equals(Object other) {
    	//If not an Iset, quickreturn false
    	if (!(other instanceof ISet<?>)) {
    		return false;
    	}
    	//If size is not equal, quickreturn false
    	if (((ISet<?>) other).size() != myCon.size()) {
    		return false;
    	}

    	//Iterate through otherset
    	for(Object data: (ISet<?>)other) {
    		boolean contained = false;
    		//Iterate through thisSet (Hence N^2), check for equality in all
    		for (E thisData : myCon) {
    			if (thisData.equals(data)) {
    				contained = true;
    			}   			
    		}
    		//If not found in set return false
    		if(!contained) {
				return false;
			}
    	}
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
    //O (N^2)
    public ISet<E> intersection(ISet<E> otherSet) {
    	//Check preconditions
    	if (otherSet == null) {
    		throw new IllegalArgumentException("OtherSet is null");
    	}
    	//Create new set toreturn
    	ISet<E> toReturn = new UnsortedSet <E>();
    	//Iterate through myCon
    	for (E thisData: myCon) {
    		//Add to toReturn if both sets have this data
    		if (otherSet.contains(thisData)) {
    			toReturn.add(thisData);
    		}
    	}
    	return toReturn;
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
    

	

}
