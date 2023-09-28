package factorymethod;

public class FactoryMethod {

	public static final Application application = new Application();

	public static void main(String[] args) {
		application.render();
	}
}

class Application {

	public Dialog dialog;

	public void initialize() {
		String os = readApplicationConfigFile().getOS();

		if ("Windows".equals(os)) {
			dialog = new WindowsDialog();
			System.out.println("Windows");
		} else if ("Web".equals(os)) {
			dialog = new WebDialog();
			System.out.println("Web");
		} else {
			throw new IllegalArgumentException("Sistema Operacional não reconhecido!");
		}
	}

	public void render() {
		this.initialize();
		dialog.render();
		dialog.botao.render();
		dialog.botao.onclick();
	}

	private Config readApplicationConfigFile() {
		return new Config("Web");
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

abstract class Dialog {

	public Button botao;

	public abstract Button createButton();

	public Button render() {
		Button botao = createButton();
		return botao;
	}
}

class WindowsDialog extends Dialog {
	@Override
	public Button createButton() {
		botao = new ButtonWindows();
		return botao;
	}

}

class WebDialog extends Dialog {
	@Override
	public Button createButton() {
		botao = new ButtonHTML();
		return botao;
	}

}

abstract class Button {

	public abstract Button render();

	public abstract Button onclick();

}

class ButtonWindows extends Button {
	@Override
	public Button render() {
		System.out.println("Botão 'Windows' Criado!");
		return this;
	}

	@Override
	public Button onclick() {
		System.out.println("Botão 'Windows' Clicado!");
		return this;
	}
}

class ButtonHTML extends Button {

	@Override
	public Button render() {
		System.out.println("Botão 'HTML' Criado!");
		return this;
	}

	@Override
	public Button onclick() {
		System.out.println("Botão 'HTML' Clicado!");
		return this;
	}

}
