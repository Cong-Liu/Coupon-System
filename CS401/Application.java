package CS401;

import CS401.UI.MainWindow;

//  application class, used for running the whole project.

public class Application {

	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			CouponSystem system = new CouponSystem(window);
			window.bindEvent(system);
			system.display();
			window.show();
		} catch (Exception ex) {
			MainWindow.showError(ex.getMessage());
		}
	}
}
