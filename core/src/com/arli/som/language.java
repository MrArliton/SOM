package com.arli.som;

public class language {
    static int lang = 1;
    public static String getText(String cod,int comp){ // Система с разделениями для более поиска по словам
        String otvet = cod;
        if(comp == 1) {
            if (cod.equalsIgnoreCase("defaultCellInfo")) {
                if(lang == 0){

                }else if(lang == 1){
                    otvet = "Пустая ячейка.\n" +
                            "Захвачена - ";
                }

            }else  if (cod.equalsIgnoreCase("build")) {
                if(lang == 0){

                }else if(lang == 1){
                    otvet = "Строить";
                }

            }else  if (cod.equalsIgnoreCase("upgrade")) {
                if(lang == 0){

                }else if(lang == 1){
                    otvet = "Улучшить";
                }

            }

        }else if(comp == 2){
            if (cod.equalsIgnoreCase("0")) {
                if(lang == 0){

                }else if(lang == 1){
                    otvet = "Никем.";
                }

            }else             if (cod.equalsIgnoreCase("1")) {
                if(lang == 0){

                }else if(lang == 1){
                    otvet = "Вами.";
                }

            }else             if (cod.equalsIgnoreCase("2")) {
                if(lang == 0){

                }else if(lang == 1){
                    otvet = "Красными.";
                }

            }else            if (cod.equalsIgnoreCase("3")) {
                if(lang == 0){

                }else if(lang == 1){
                    otvet = "Жёлтыми.";
                }

            }else
            if (cod.equalsIgnoreCase("4")) {
                if(lang == 0){

                }else if(lang == 1){
                    otvet = "Зелёными.";
                }

            }
        }
        return otvet;
    }
}
