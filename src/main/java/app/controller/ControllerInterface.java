package app.controller;

/**
 * Interfaz que define el contrato para los controladores de la aplicación.
 * 
 * Cada clase que implemente esta interfaz deberá proporcionar una implementación
 * para el método {@code session()}, que se encarga de gestionar el flujo de interacción
 * o sesión del controlador.
 */
public interface ControllerInterface {
    
    /**
     * Inicia la sesión del controlador, gestionando el flujo de interacción con el usuario.
     * 
     * Este método es el punto de entrada para la lógica de control y debe manejar todas
     * las interacciones, entradas y salidas necesarias para la sesión del usuario.
     * 
     * @throws Exception si ocurre algún error durante la ejecución de la sesión.
     */
    void session() throws Exception;
}

