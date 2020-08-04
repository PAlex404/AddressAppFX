package it.paprojects.addressapp.model;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private static final Map<BeansEnum, Object> beansMap = new HashMap<>();

    public static void putBean(BeansEnum key, Object bean) {
        beansMap.put(key, bean);
    }

    public static Object getBean(BeansEnum key) {
        return beansMap.get(key);
    }
}
