package com.arli.som.GameElement;

import java.util.HashMap;
import java.util.Map;

public class InfoCountry { // Класс содержащий информацию о странах
    Map<String,Map<String,String>> bufferInfo = new HashMap<String, Map<String, String>>();
    public void addNewInfo(String name){
        bufferInfo.put(name,new HashMap<String, String>());
    }
    public String getInfoCountry(String name,String key){
        return bufferInfo.get(name).get(key);
    }
    public void putInfoCountry(String name,String key,String value){
        bufferInfo.get(name).put(key,value);
    }
    public void removeInfo(String name){
        bufferInfo.remove(name);
    }
}
