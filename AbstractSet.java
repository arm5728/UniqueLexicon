import java.util.Iterator;

public abstract class AbstractSet<E> implements ISet<E> {
   
 	/**
      * A union operation. Add all items of otherSet that are not already present in this set
      * to this set.
      * @param otherSet != null
      * @return true if this set changed as a result of this operation, false otherwise.
      */
	//O(N^2)
    public boolean addAll(ISet<E> otherSet) {
    	//Check preconditions
	   	if (otherSet == null) {
	   		throw new IllegalArgumentException("OtherSet is null!");
	   	}
	   	//Boolean toReturn
	   	boolean changed = false;
	   	//Iterate through otherData, add using subclass add methods
	   	for (E otherData: otherSet) {
	   		this.add(otherData);
	   		changed = true;
	   	}
	   	return changed;
    }
    

	/**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    //O(N)
    public void clear() {
    	//Iterate through and remove each
    	Iterator it = this.iterator();
    	while(it.hasNext()) {
    		it.next();
    		it.remove();
    	}
    }

    
	/**
     * Determine if item is in this set. 
     * <br>pre: item != null
     * @param item element whose presence is being tested. Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    //O(N)
    public boolean contains(E item) {
    	//Check preconditions
    	if (item == null) {
    		throw new IllegalArgumentException("Item is null!");
    	}
    	//Iterate through this
    	for (E data: this) {
    		//If data is equal, found,  return true
    		if (data.equals(item)) {
    			return true;
    		}
    	}
    	return false;
    }

    
	/**
     * Determine if all of the elements of otherSet are in this set.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet, 
     * false otherwise.
     */
    //O(N^2)
    public boolean containsAll(ISet<E> otherSet) {
    	//Check preconditions
    	if (otherSet == null) {
    		throw new IllegalArgumentException("Otherset is null!");
    	}
    	//Iterate through otherSet
    	for (E data: otherSet) {
    		//f thisdata is not in otherset return false
    		if (!this.contains(data)) {
    			return false;
    		}
    	}
    	return true;
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
    		throw new IllegalArgumentException("Item is null");
    	}
    	
    	//Iterator for this container    	
    	Iterator<E> thisIt = this.iterator();
    	//LOop through
    	while (thisIt.hasNext()) {
    		//Store data
    		E data =  thisIt.next();
    		//If equal, remove and return true
    		if (data.equals(item)) {
    			thisIt.remove();
    			return true;
    		}
    	}
    	//Given nothing removed, return false
    	return false;
    }
    
	/**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    // O(N), overridden in subclasses
    public int size() {
    	int count = 0;
    	//Add to count for every element present
    	for (E data: this) {
    		count++;
    	}
    	return count;
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
    	//Only operate if other is an Abstract Set
    	if (other instanceof AbstractSet) {
    		return (((AbstractSet<?>)other).equals(this));
    	}
    	//defacto return false
    	return false;
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
    //O(N^2) 
    public ISet<E> union(ISet<E> otherSet) {
    	//Check preconditions
    	if (otherSet == null) {
    		throw new IllegalArgumentException("otherSet is null!");
    	}
    	
    	//Make new iset to return using the difference method in the subclasses
    	ISet<E> difference = otherSet.difference(this);
    	//Loop Add this data to the difference 
    	for (E data: this) {
    		difference.add(data);
    	}
    	return difference;

    }
    
    //Mike's method
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0)
            result.setLength(result.length() - seperator.length());

        result.append(")");
        return result.toString();
    }
}
