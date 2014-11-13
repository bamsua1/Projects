package driver;
import mmheap.* ;

public class HWTest {

    
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
	     MMHeap<Int4MMH> fig1 = new MMHeap<Int4MMH>(new Int4MMH(), 60) ;
	     
	     
	    
	    int [] elements = {6,81,87,14,17,12,28,71,25,80,20,52,78,31,42,31,59,16,24,79,63,18,19,32,13,15,48};
	   
	    for(int i = 0; i<elements.length; i++)
	    {
	    	fig1.insert(new Int4MMH(elements[i]));
	    }
	    System.out.println("This is Figure 1:");
	    fig1.dump();
	    
	    int [] insert = { 29, 4, 90, 5, 41, 9, 93, 67};
	    for(int i = 0; i<insert.length; i++)
	    {
	    	fig1.insert(new Int4MMH(insert[i]));
	    }
	    System.out.println("Insert 29, 4, 90, 5, 41, 9, 93, 67");
	    fig1.dump();
	    
		// TODO Auto-generated method stub
	    
	    fig1 = new MMHeap<Int4MMH>(new Int4MMH(), 60) ;
	    
	    for(int i = 0; i<elements.length; i++)
	    {
	    	fig1.insert(new Int4MMH(elements[i]));
	    }
	    System.out.println("Reset figure 1:");
	    fig1.dump();
	    
	    fig1.deleteMin();
	    System.out.println("Dudueeee After 1 deletemin's");
	    fig1.dump();
	    fig1.deleteMin();
	    System.out.println("After 2 deletemin's");
	    fig1.dump();
	    
	    
	    MMHeap <Int4MMH>fig2 = new MMHeap<Int4MMH>(new Int4MMH(), 60) ;
	    
	    int[] elements2 = {6,53,80,21,20,63,64,46,47,48,49,71,72,73,74,30,31,32,33,34,35,36,37,67};
	    for(int i = 0; i<elements2.length; i++)
	    {
	    	fig2.insert(new Int4MMH(elements2[i]));
	    }
	    System.out.println("Figure 2:");
	    fig2.dump();
	    fig2.deleteMin();
	    System.out.println("Figure 2 after 1 deletemin");
	    fig2.dump();
	    
	    
	    MMHeap <Int4MMH>fig3 = new MMHeap<Int4MMH>(new Int4MMH(), 60) ;
	    
	    int[] elements3 = {5,30,45,17,16,19,21,25,24,20,23,40,34,27,37};
	    for(int i = 0; i<elements3.length; i++)
	    {
	    	fig3.insert(new Int4MMH(elements3[i]));
	    }
	    System.out.println("Figure 3:");
	    fig3.dump();
	    fig3.deleteMin();
	    System.out.println("Figure 3 after 1 deletemin");
	    fig3.dump();
	    
	    
	     fig1 = new MMHeap<Int4MMH>(new Int4MMH(), 60) ;
	    
	    for(int i = 0; i<elements.length; i++)
	    {
	    	fig1.insert(new Int4MMH(elements[i]));
	    }
	    System.out.println("Reset Figure 1:");
	    fig1.dump();
	    fig1.deleteMax();
	    fig1.deleteMax();
	    System.out.println("Figure 1 after 2 deletemax's");
	    fig1.dump();
	    */
	    MMHeap <Int4MMH>fig4 = new MMHeap<Int4MMH>(new Int4MMH(), 60) ;
	    
	    int[] elements4 = {18,90,74,39,46,29,38,45,56,58,55,62,34,49,54,42,40,44,49,47,52,48,51,41};
	    for(int i = 0; i<elements4.length; i++)
	    {
	    	fig4.insert(new Int4MMH(elements4[i]));
	    }
	    
	    System.out.println("Figure 4");
	    fig4.dump();
	    fig4.deleteMax();
	    System.out.println("Figure 4 after 1 deletemax");
	    fig4.dump();
	
	}

}
