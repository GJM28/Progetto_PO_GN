package com.project.OOP.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * classe utilizzata per applicare i filtri agli oggetti i filtri 
 * @param <T> Tipo generico
 *@author Nicolò Rossini
 *@author Mcdonald Gregory James
 */

public class FilterUtils<T> {
	
	 /**
     * Metodo che determina per ciscun'oggetto della collezione, in base al filtro specificato, se deve essere incluso nella risposta
     * @param value Oggetto che viene considerato nel test
     * @param operator filtro che deve essere applicato
     * @param th Oggetti che caratterizzano il filtro
     * @return Un booleano che determina se l'oggetto deve essere inserito o meno
     */
	public static boolean check(Object value, String operator, Object... th) {
		
		if (th.length == 1 && th[0] instanceof Number && value instanceof Number) 
		{	 
			Double thC = ((Number)th[0]).doubleValue();
			Double valuec = ((Number)value).doubleValue();
			if (operator.equals("$gt")) {
				return valuec > thC;
			}
			else if (operator.equals("$lt")) {
				return valuec < thC;
			}
		}
		else if(th.length > 1) 
		{
			 if (operator.equals("$bt")) 
			 {		
				if(th.length == 2 && th[0] instanceof Number && th[1] instanceof Number) 
	            {
	                    Double min = ((Number)th[0]).doubleValue();
	                    Double max = ((Number)th[1]).doubleValue();
	                    Double valuec = ((Number)value).doubleValue();
	                    return valuec >= min && valuec <= max;
	            }
			 }
		}
		return false;		
	}
	
	
	/**
     * data la collezione di oggetti ed il filtro, il metodo restituisce una collezione filtrata
     * @param src collezione di oggetti
     * @param fieldName Campo su cui opera il filtro
     * @param operator Condizione del filtro
     * @param value Oggetti che caratterizzano il filtro
     * @return Collezione risultante
     */
	
	public Collection<T> select(Collection<T> src, String fieldName, String operator, Object... value) {
		Collection<T> out = new ArrayList<T>();
		for(T item:src) {
			try {
				Method m = item.getClass().getMethod("get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1),null);
				try {
					Object tmp = m.invoke(item);
					if(FilterUtils.check(tmp, operator, value))
						out.add(item);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}					
		}
		return out;
	}

}
