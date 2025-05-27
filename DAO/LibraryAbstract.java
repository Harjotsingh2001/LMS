package DAO;

import model.Library;

public abstract class LibraryAbstract implements LibraryDAO {
    
     @Override
    public boolean updateLibrary(int borrowId, Library library) {
        if ( ! deleteLibrary(borrowId) )  return false;
        return insertData(library);
   }

}
