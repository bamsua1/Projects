package driver ;

import mmheap.* ;
import java.util.* ;

public class Proj3QHTest {

   public static void main (String [] args) {

      QHeap<Int4MMH> q ;

      q = new QHeap<Int4MMH>(new Int4MMH(), 2000) ;

      Random prg = new Random ( 38107 ) ;

      for (int i = 1 ; i <= 60 ; i++) {
         q.insert( new Int4MMH(prg.nextInt(100)) ) ;
      }

      q.dump() ;

   }

}
