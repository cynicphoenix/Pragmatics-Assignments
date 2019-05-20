package algorithms;

public class GetAlgorithmFactory {

    // use getAlgo method to get object of type
    public SortingAlgorithm getSortAlgo(String algoTechnique){  
		if(algoTechnique == null)  
			return null;  
		if(algoTechnique.equalsIgnoreCase("bubble"))  
			return new bubblesort();   
		else if(algoTechnique.equalsIgnoreCase("insertion"))
			return new insertionsort();  
		else if(algoTechnique.equalsIgnoreCase("selection"))
			return new selectionsort();
		else if(algoTechnique.equalsIgnoreCase("merge"))
			return new mergesort();
		else if(algoTechnique.equalsIgnoreCase("quick"))
			return new quicksort();
      	return null;
    }
    
    // use getAlgo method to get object of type
    public MathAlgorithm getMathAlgo(String algoTechnique){
        if(algoTechnique == null)  
			return null; 
        if(algoTechnique.equalsIgnoreCase("gcd"))
            return new gcd();
        else if(algoTechnique.equalsIgnoreCase("lcm"))
            return new lcm();
        else if(algoTechnique.equalsIgnoreCase("power"))
            return new power();
        return null;
    }
}