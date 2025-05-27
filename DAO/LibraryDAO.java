package DAO;

import java.util.List;

import model.*;

public interface LibraryDAO {
    // standard database operations:
    // CRUD aka RUDI
    // Create/Read/Update/Delete or Read/Update/Delete/Insert

    boolean insertData(Library library);

    Library getDataById(int borrowId);
    Library getDataByBookName(String bookName);
    List<Library> getAllData();

    boolean updateLibrary(int borrowId, Library library);

    boolean deleteLibrary(int borrowId);

    int lastDataNumber();
}

