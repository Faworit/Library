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
        MAP_OF_SERVICE.put(SHOW_BOOK, new ShowBookService());
        MAP_OF_SERVICE.put("/setUser", new ForwardService());
        MAP_OF_SERVICE.put("/search", new SearchService());
        MAP_OF_SERVICE.put("/removeBook", new RemoveBookService());
        MAP_OF_SERVICE.put("/createUser", new CreateUserService());
    }

    public static ServiceFactory getInstance(){
        return SERVICE_FACTORY;
    }

    public Service getService(String request){
        return MAP_OF_SERVICE.get(request);
    }
}
