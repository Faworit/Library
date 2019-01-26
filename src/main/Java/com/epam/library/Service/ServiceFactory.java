package com.epam.library.Service;

import java.util.HashMap;
import java.util.Map;

import static com.epam.library.util.ConstantsOfLibrary.CHANGE_LANGUAGE;
import static com.epam.library.util.ConstantsOfLibrary.LOG_IN;

public class ServiceFactory {
    private static final Map<String, Service> MAP_OF_SERVICE = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    public ServiceFactory() {
        init();
    }

    private void init(){
        MAP_OF_SERVICE.put(CHANGE_LANGUAGE, new ChangeLanguageService());
        MAP_OF_SERVICE.put(LOG_IN, new LogInService());
    }

    public static ServiceFactory getInstance(){
        return SERVICE_FACTORY;
    }

    public Service getService(String request){
        return MAP_OF_SERVICE.get(request);
    }
}
