package com.epam.library.Service;

import java.util.HashMap;
import java.util.Map;

import static com.epam.library.util.ConstantsOfLibrary.*;

public class ServiceFactory {
    private static final Map<String, Service> MAP_OF_SERVICE = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    static{
        MAP_OF_SERVICE.put(CHANGE_LANGUAGE, new ChangeLanguageService());
        MAP_OF_SERVICE.put(LOG_IN, new LogInService());
        MAP_OF_SERVICE.put("/showBook", new ShowBookService());
        MAP_OF_SERVICE.put("/Forward", new ForwardService());
        MAP_OF_SERVICE.put("/search", new SearchService());
        MAP_OF_SERVICE.put("/removeBook", new RemoveBookService());
        MAP_OF_SERVICE.put("/createUser", new AddUserService());
        MAP_OF_SERVICE.put("/createBook", new AddBookService());
        MAP_OF_SERVICE.put("/showAddBookMenu", new ShowAddBookMenuService());
        MAP_OF_SERVICE.put("/addGenre", new AddGenreService());
        MAP_OF_SERVICE.put("/editBookMenu", new ShowEditBookMenuService());
        MAP_OF_SERVICE.put("/editBook", new EditBookService());
    }

    public static ServiceFactory getInstance(){
        return SERVICE_FACTORY;
    }

    public Service getService(String request){
        return MAP_OF_SERVICE.get(request);
    }
}
