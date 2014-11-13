package driver ;

import mmheap.* ;

public class CensusCity implements WorksWithMMHeap<CensusCity> {

   String city ;        // name of city
   String state ;       // the state that city is in
   int census2010 ;     // population according to 2010 census
   int estimate2011 ;   // population according to 2011 estimate
   boolean use2011 ;    // compare() uses 2011 estimated population?


   // default constructor
   //
   public CensusCity() {
      city = null ;
      state = null ;
      census2010 = 0 ;
      estimate2011 = 0 ;
      use2011 = false ;
   }


   // this constructor remembers whether to use 2011 estimate
   //
   public CensusCity(boolean tf) {
      city = null ;
      state = null ;
      census2010 = 0 ;
      estimate2011 = 0 ;
      use2011 = tf ;
   }


   // constructor with data supplied
   //
   public CensusCity (String aCity, String aState, int n1, int n2) {
      city = aCity ;
      state = aState ;
      census2010 = n1 ;
      estimate2011 = n2 ;
      use2011 = false ;
   }


   public String toString() {
      return city + ", " + state + " (" + census2010 + ", " + estimate2011 + ")" ;
   }


   public CensusCity newObject() {
      return new CensusCity() ;
   }


   public CensusCity [] newArray(int n) {
      return new CensusCity[n] ;
   }

   // compare populations. The use2011 variable says which population field to use.
   //
   public int compare(CensusCity c1, CensusCity c2) {
      if (use2011) {
         if (c1.estimate2011 < c2.estimate2011) return -1 ;
         if (c1.estimate2011 > c2.estimate2011) return 1 ;
         return 0 ;
      } else {
         if (c1.census2010 < c2.census2010) return -1 ;
         if (c1.census2010 > c2.census2010) return 1 ;
         return 0 ;
      }
   }

}