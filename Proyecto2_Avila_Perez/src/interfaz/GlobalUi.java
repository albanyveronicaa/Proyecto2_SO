package interfaz;

/**
 * La clase GlobalUi es una clase de utilidad que gestiona la interfaz gráfica de usuario principal.
 * Proporciona métodos estáticos para acceder y mostrar la página principal de la aplicación.
 */
public class GlobalUi {

    // Instancia estática de MainPage, la página principal de la interfaz
    private static final MainPage mainPage = new MainPage();

    /**
     * Obtiene la instancia de MainPage.
     *
     * @return La instancia actual de MainPage.
     */
    public static MainPage getMainPage() {
        return mainPage;
    }

    /**
     * Hace visible la página principal de la interfaz de usuario.
     */
    public static void openMainPage() {
        getMainPage().setVisible(true);
    }
}
