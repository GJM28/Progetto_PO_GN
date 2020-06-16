package com.project.OOP.utils;

import java.util.Collection;
/**
 * Intefaccia che mette a disposizione la dichiarazione del metodo per filtrare i dati di una classe
 * @param <E> oggetto che verrà filtrato
 * @param <T> valore che verrà utilizzato per filtrare l'oggetto
 */
public interface Filter <E,T>{
	abstract Collection<E> filterField(String fieldName, String operator, T value);
}
