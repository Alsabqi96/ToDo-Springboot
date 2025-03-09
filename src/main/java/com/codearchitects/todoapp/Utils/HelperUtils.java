package com.codearchitects.todoapp.Utils;

import java.util.List;

public class HelperUtils {
    public static <F> Boolean isNull(F val) {
        return val == null;
    }
    public static <A> Boolean isNotNull(A val) {
        return val != null;
    }
    public static <T> T checkEquals(T oldVal, T newVal) {
        return oldVal == newVal ? oldVal : newVal;
    }
    public static <M> void printList(List<M> listToPrint){
        for (M m : listToPrint) {
            System.out.println(m);
        }
    }
}








