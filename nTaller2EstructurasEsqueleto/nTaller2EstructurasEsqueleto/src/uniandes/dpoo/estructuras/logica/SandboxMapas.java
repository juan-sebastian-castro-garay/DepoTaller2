package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre mapas.
 *
 * Todos los métodos deben operar sobre el atributo mapaCadenas que se declara como un Map.
 * 
 * En este mapa, las llaves serán cadenas y los valores serán también cadenas. La relación entre los dos será que cada llave será igual a la cadena del valor, pero invertida.
 * 
 * El objetivo de usar el tipo Map es que sólo puedan usarse métodos de esa interfaz y no métodos adicionales provistos por la implementación concreta (HashMap).
 * 
 * No pueden agregarse nuevos atributos.
 */
public class SandboxMapas
{
    /**
     * Un mapa de cadenas para realizar varias de las siguientes operaciones.
     * 
     * Las llaves del mapa son cadenas, así como los valores.
     * 
     * Las llaves corresponden a invertir la cadena que aparece asociada a cada llave.
     */
    private Map<String, String> mapaCadenas;

    /**
     * Crea una nueva instancia de la clase con las dos listas inicializadas pero vacías
     */
    public SandboxMapas( )
    {
        mapaCadenas = new HashMap<String, String>( );
    }

    /**
     * Retorna una lista con las cadenas del mapa (los valores) ordenadas lexicográficamente
     * @return Una lista ordenada con las cadenas que conforman los valores del mapa
     */
    public List<String> getValoresComoLista() {
        List<String> listaValores = new ArrayList<>();
        for (String valor : mapaCadenas.values()) {
            listaValores.add(valor);
        }

        int n = listaValores.size();
        boolean intercambiado;

        do {
            intercambiado = false;

            for (int i = 1; i < n; i++) {
                if (listaValores.get(i - 1).compareTo(listaValores.get(i)) > 0) {
                   
                    String temp = listaValores.get(i - 1);
                    listaValores.set(i - 1, listaValores.get(i));
                    listaValores.set(i, temp);
                    intercambiado = true;
                }
            }

            n--;

        } while (intercambiado);

        return listaValores;
    }


    /**
     * Retorna una lista con las llaves del mapa ordenadas lexicográficamente de mayor a menor
     * @return Una lista ordenada con las cadenas que conforman las llaves del mapa
     */
    public List<String> getLlavesComoListaInvertida( )
    {
    	Set<String> keysSet = mapaCadenas.keySet();
    	String[] keysArray = keysSet.toArray(new String[0]);
    	Arrays.sort(keysArray, (s1, s2) -> s2.compareTo(s1));

    	List<String> result = new ArrayList<>(keysArray.length);
    	for (String key : keysArray) {
    	    result.add(key);
    	}
    	return result;

    }

    /**
     * Retorna la cadena que sea lexicográficamente menor dentro de las llaves del mapa .
     * 
     * Si el mapa está vacío, debe retornar null.
     * @return
     */
    public String getPrimera( )
    {
    	if (mapaCadenas.isEmpty()) {
    	    return null;
    	}

    	String primera = null;

    	for (String key : mapaCadenas.keySet()) {
    	    if (primera == null || key.compareTo(primera) < 0) {
    	        primera = key;
    	    }
    	}

    	return primera;

    }

    /**
     * Retorna la cadena que sea lexicográficamente mayor dentro de los valores del mapa
     * 
     * Si el conjunto está vacío, debe retornar null.
     * @return
     */
    public String getUltima( )
    {
    	if (mapaCadenas.isEmpty()) {
    	    return null;
    	}

    	String ultima = null;

    	for (String value : mapaCadenas.values()) {
    	    if (ultima == null || value.compareTo(ultima) > 0) {
    	        ultima = value;
    	    }
    	}

    	return ultima;

    }

    /**
     * Retorna una colección con las llaves del mapa, convertidas a mayúsculas.
     * 
     * El orden de las llaves retornadas no importa.
     * @return Una lista de cadenas donde todas las cadenas están en mayúsculas
     */
    public Collection<String> getLlaves( )
    {
    	Set<String> llavesMayusculas = new HashSet<>();

    	for (String key : mapaCadenas.keySet()) {
    	    llavesMayusculas.add(key.toUpperCase());
    	}

    	return llavesMayusculas;

    }

    /**
     * Retorna la cantidad de *valores* diferentes en el mapa
     * @return
     */
    public int getCantidadCadenasDiferentes( )
    {
    	Set<String> valoresUnicos = new HashSet<>(mapaCadenas.values());
    	return valoresUnicos.size();

    }

    /**
     * Agrega un nuevo valor al mapa de cadenas: el valor será el recibido por parámetro, y la llave será la cadena invertida
     * 
     * Este método podría o no aumentar el tamaño del mapa, dependiendo de si ya existía la cadena en el mapa
     * 
     * @param cadena La cadena que se va a agregar al mapa
     */
    public void agregarCadena( String cadena )
    {
    	String llave = new StringBuilder(cadena).reverse().toString();
    	mapaCadenas.put(llave, cadena);

    }

    /**
     * Elimina una cadena del mapa, dada la llave
     * @param cadena La llave para identificar el valor que se debe eliminar
     */
    public void eliminarCadenaConLLave( String llave )
    {
    	mapaCadenas.remove(llave);

    }

    /**
     * Elimina una cadena del mapa, dado el valor
     * @param cadena El valor que se debe eliminar
     */
    public void eliminarCadenaConValor( String valor )
    {
    	List<String> keysToRemove = new ArrayList<>();

    	for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
    	    if (entry.getValue().equals(valor)) {
    	        keysToRemove.add(entry.getKey());
    	    }
    	}

    	for (String key : keysToRemove) {
    	    mapaCadenas.remove(key);
    	}

    }

    /**
     * Reinicia el mapa de cadenas con las representaciones como Strings de los objetos contenidos en la lista del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Una lista de objetos
     */
    public void reiniciarMapaCadenas( List<Object> objetos )
    {
    	mapaCadenas.clear();

    	for (Object objeto : objetos) {
    	    agregarCadena(objeto.toString());
    	}

    }

    /**
     * Modifica el mapa de cadenas reemplazando las llaves para que ahora todas estén en mayúsculas pero sigan conservando las mismas cadenas asociadas.
     */
    public void volverMayusculas( )
    {
    	Map<String, String> nuevoMapa = new HashMap<>();

    	for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
    	    String nuevaLlave = entry.getKey().toUpperCase();
    	    nuevoMapa.put(nuevaLlave, entry.getValue());
    	}

    	mapaCadenas = nuevoMapa;

    }

    /**
     * Verifica si todos los elementos en el arreglo de cadenas del parámetro hacen parte del mapa de cadenas (de los valores)
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si todos los elementos del arreglo están dentro de los valores del mapa
     */
    public boolean compararValores( String[] otroArreglo )
    {
    	for (String cadena : otroArreglo) {
    	    if (!mapaCadenas.containsValue(cadena)) {
    	        return false;
    	    }
    	}

    	return true;

    }

}
