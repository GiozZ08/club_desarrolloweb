package app.config;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase encargada de gestionar la conexión a la base de datos MySQL.
 * 
 * - Carga los parámetros de configuración desde el archivo database.properties.
 * - Establece una conexión con la base de datos utilizando JDBC.
 */

public class MYSQLConnection {
    // Variables para almacenar los datos de conexión
    private static String URL;
    private static String USER;
    private static String PASSWORD;

   
 /**
     * Bloque estático que se ejecuta una vez cuando la clase se carga en memoria.
     * Carga los valores de configuración desde el archivo `database.properties`.
     */
    static {
        try (InputStream input = MYSQLConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            // Verificar si el archivo de configuración fue encontrado
            if (input == null) {
                throw new RuntimeException("No se pudo encontrar el archivo database.properties");
            }


            // Cargar las propiedades desde el archivo
            prop.load(input);
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");


        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la configuración de la base de datos", e);
        }
    }

     /**
     * Método estático para obtener una conexión a la base de datos.
     * 
     * @return Connection objeto de conexión a la base de datos, o null si ocurre un error.
     */
        
        public static Connection getConnection() {
            Connection connection = null;
            try {
                 // Cargar el driver JDBC de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establecer la conexión con la base de datos
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa");


            } catch (ClassNotFoundException e) {
                System.out.println("❌ Error: No se encontró el driver de MySQL.");
                e.printStackTrace();
            }catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
            return connection;
        }
}
