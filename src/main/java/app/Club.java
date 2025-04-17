
package app;

import app.config.MYSQLConnection;
import app.controller.ControllerInterface;
import app.controller.LoginController;
import java.sql.Connection;

public class Club {
    public static void main(String[] args) {
        ControllerInterface controller = new LoginController();
        try {
            // Intentamos establecer la conexión antes de iniciar la sesión
            Connection connection = MYSQLConnection.getConnection();
            if (connection != null) {
                System.out.println("Conexión establecida correctamente.");
            } else {
                System.out.println("Error: No se pudo establecer la conexión a la base de datos.");
                return; // Salimos si no hay conexión
            }

            // Ejecutamos la sesión después de confirmar la conexión
            controller.session();

        } catch (Exception e) {
            System.out.println("Error en la aplicación: " + e.getMessage());
            e.printStackTrace(); // Para depuración
        }
    }
}
