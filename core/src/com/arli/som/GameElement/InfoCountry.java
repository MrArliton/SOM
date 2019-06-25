package com.arli.som.GameElement;

import java.util.HashMap;
import java.util.Map;

public class InfoCountry { // Класс содержащий информацию о странах
    static Map<Integer,Map<String,String>> bufferInfo = new HashMap<Integer, Map<String, String>>();
    public void addNewInfo(Integer name){
        bufferInfo.put(name,new HashMap<String, String>());
    }
    public String getInfoCountry(Integer name,String key){
        return bufferInfo.get(name).get(key);
    }
    public void putInfoCountry(Integer name,String key,String value){
        bufferInfo.get(name).put(key,value);
    }
    public  void removeInfo(String name){
        bufferInfo.remove(name);
    }
}
