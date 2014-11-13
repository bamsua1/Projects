package driver ;

import mmheap.* ;

public class Int4MMH implements WorksWithMMHeap<Int4MMH> {

   int data ;

   public Int4MMH() {
      data = 0 ;
   }

   public Int4MMH(int n) {
      data = n ;
   }

   public String toString() {
      return Integer.toString(data) ;
   }

   public Int4MMH newObject() {
      return new Int4MMH() ;
   }

   public Int4MMH [] newArray(int n) {
      return new Int4MMH[n] ;
   }

   public int compare(Int4MMH n1, Int4MMH n2) {
      if (n1.data < n2.data) return -1 ;
      if (n1.data > n2.data) return 1 ;
      return 0 ;
   }

}