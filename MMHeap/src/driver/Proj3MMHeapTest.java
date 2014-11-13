package driver ;

import mmheap.* ;

public class Proj3MMHeapTest {

   public static void main( String [] args ) {
      boolean test = true ;
      int t ;
      
      // Testing small cases
      MMHeap<Int4MMH> mmH = new MMHeap<Int4MMH>(new Int4MMH(), 30) ;

      mmH.insert(new Int4MMH(18)) ;
      mmH.insert(new Int4MMH(94)) ;
      mmH.insert(new Int4MMH(74)) ;
      mmH.dump() ;
      mmH.deleteMax() ;
      mmH.dump() ; 


      mmH = new MMHeap<Int4MMH>(new Int4MMH(), 90) ;

      // Figure 6.57 from Weiss 3/e 
      mmH.insert ( new Int4MMH( 6) ) ;
      mmH.insert ( new Int4MMH(81) ) ;
      mmH.insert ( new Int4MMH(87) ) ;
      mmH.insert ( new Int4MMH(14) ) ;
      mmH.insert ( new Int4MMH(17) ) ;
      mmH.insert ( new Int4MMH(12) ) ;
      mmH.insert ( new Int4MMH(28) ) ;
      mmH.insert ( new Int4MMH(71) ) ;
      mmH.insert ( new Int4MMH(25) ) ;
      mmH.insert ( new Int4MMH(80) ) ;
      mmH.insert ( new Int4MMH(20) ) ;
      mmH.insert ( new Int4MMH(52) ) ;
      mmH.insert ( new Int4MMH(78) ) ;
      mmH.insert ( new Int4MMH(31) ) ;
      mmH.insert ( new Int4MMH(42) ) ;
      mmH.insert ( new Int4MMH(31) ) ;
      mmH.insert ( new Int4MMH(59) ) ;
      mmH.insert ( new Int4MMH(16) ) ;
      mmH.insert ( new Int4MMH(24) ) ;
      mmH.insert ( new Int4MMH(79) ) ;
      mmH.insert ( new Int4MMH(63) ) ;
      mmH.insert ( new Int4MMH(18) ) ;
      mmH.insert ( new Int4MMH(19) ) ;
      mmH.insert ( new Int4MMH(32) ) ;
      mmH.insert ( new Int4MMH(13) ) ;
      mmH.insert ( new Int4MMH(15) ) ;
      mmH.insert ( new Int4MMH(48) ) ;

      mmH.dump() ; 

      mmH.insert( new Int4MMH(53) ) ;
      mmH.insert( new Int4MMH(57) ) ;
      mmH.insert( new Int4MMH(13) ) ;
      mmH.insert( new Int4MMH(12) ) ;
      mmH.insert( new Int4MMH( 9) ) ;
      mmH.insert( new Int4MMH(10) ) ;

      mmH.dump() ; 

      mmH.deleteMin() ;
      mmH.deleteMin() ;
      mmH.deleteMin() ;

      mmH.deleteMax() ;
      mmH.deleteMax() ;
      mmH.deleteMax() ;

      mmH.dump() ; 
   }

}