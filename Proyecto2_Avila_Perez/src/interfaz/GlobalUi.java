package interfaz;

public class GlobalUi {

    private static final MainPage mainPage = new MainPage();

    public static MainPage getMainPage() {
        return mainPage;
    }

    public static void openMainPage() {
        getMainPage().setVisible(true);
    }
}
