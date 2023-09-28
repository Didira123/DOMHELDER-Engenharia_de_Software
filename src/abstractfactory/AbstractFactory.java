package abstractfactory;

public class AbstractFactory {

	public static void main(String[] args) {

		Application app = Application.initialize();
		app.paint();
	}

}

class Application {
	private GUIFactory factory;
	private Button button;
	private Checkbox checkbox;

	public Application(GUIFactory factory) {
		this.factory = factory;
		createUI();
	}

	public void createUI() {
		button = factory.createButton();
		checkbox = factory.createCheckbox();
	}

	public void paint() {
		button.paint();
		checkbox.paint();
	}

	public static Application initialize() {
		String os = readApplicationConfigFile().getOS();
		GUIFactory factory;
		if (os.equals("Windows")) {
			factory = new WinFactory();
		} else if (os.equals("Mac")) {
			factory = new MacFactory();
		} else {
			throw new IllegalArgumentException("Sistema Operacional não reconhecido!");
		}

		Application app = new Application(factory);
		return app;
	}

	private static Config readApplicationConfigFile() {
		return new Config("Mac");
	}
}

class Config {
	private String os;

	public Config(String os) {
		this.os = os;
	}

	public String getOS() {
		return os;
	}
}

interface Button {
	void paint();
}

class WinButton implements Button {
	@Override
	public void paint() {
		System.out.println("Botão 'Windows' pintado!");
	}
}

class MacButton implements Button {
	@Override
	public void paint() {
		System.out.println("Botão 'MacOS' pintado!");
	}
}

interface Checkbox {
	void paint();
}

class WinCheckbox implements Checkbox {
	@Override
	public void paint() {
		System.out.println("Checkbox 'Windows' pintado!");
	}
}

class MacCheckbox implements Checkbox {
	@Override
	public void paint() {
		System.out.println("Checkbox 'MacOS' pintado!");
	}
}

interface GUIFactory {
	Button createButton();

	Checkbox createCheckbox();
}

class WinFactory implements GUIFactory {
	@Override
	public Button createButton() {
		return new WinButton();
	}

	@Override
	public Checkbox createCheckbox() {
		return new WinCheckbox();
	}
}

class MacFactory implements GUIFactory {
	@Override
	public Button createButton() {
		return new MacButton();
	}

	@Override
	public Checkbox createCheckbox() {
		return new MacCheckbox();
	}
}