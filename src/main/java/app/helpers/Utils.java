package app.helpers;

import java.util.Date;
import java.util.Scanner;

/**
 * Clase de utilidades para la aplicación.
 *
 * Proporciona métodos estáticos para acceder a un Scanner único para la entrada de datos
 * y para obtener la fecha actual. Esto es útil en la capa Controller (por ejemplo, en un flujo
 * de consola) y en otras partes donde se requiera información temporal o lectura de datos.
 */
public abstract class Utils {
    
    // Scanner único para la entrada de datos desde la consola
    private static final Scanner READER = new Scanner(System.in);
    
    /**
     * Devuelve el objeto Scanner para la lectura de la entrada estándar.
     *
     * @return El Scanner de la consola.
     */
    public static Scanner getReader() {
        return READER;
    }
    
    /**
     * Devuelve la fecha y hora actual.
     *
     * @return Un objeto {@link Date} con la fecha y hora actual.
     */
    public static Date getDate() {
        return new Date(System.currentTimeMillis());
    }
    
    // Puedes agregar otros métodos utilitarios que necesites para la aplicación.
}
