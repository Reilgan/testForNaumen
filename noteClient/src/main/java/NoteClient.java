import java.awt.*;


public class NoteClient {
    private static MainWindow mainWindow;
    public static void main(String[] args) {
        // Создаем инстанцию класса MainWindow
        mainWindow = new MainWindow();
        // Упаковываем все элементы с нашей формы
        mainWindow.pack();
        // Изменяем размеры окна
        mainWindow.setSize(new Dimension(400, 400));
        // Отображаем созданное окно
        mainWindow.setVisible(true);
    }
}
