package chainOfResponsability;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class chainOfResponsability {

	public static final Application app = new Application();

	public static void main(String[] args) {
		app.createUI();
	}
}

class Application {
	public void createUI() {
		Dialog dialog = new Dialog("http://223.10.32.231/siteAqui");
		Panel panel = new Panel("Painel A");
		Button ok = new Button("Botão OK");
		Button cancel = new Button("Botão Cancelar");

		panel.add(ok);
		panel.add(cancel);
		dialog.add(panel);
		System.out.println(dialog);
	}

	public void onF1KeyPress() {
		System.out.println("F1 pressionado!");
	}
}

interface ComponentWithContextualHelp {
	public void showHelp();
}

abstract class Component implements ComponentWithContextualHelp {
	protected String tooltipText;
	protected Container container;

	public void showHelp() {
		if (tooltipText != null) {
			System.out.println("Mostrando dica de contexto: " + tooltipText);
		} else if (container != null) {
			container.showHelp();
		}
	}
}

abstract class Container extends Component {
	protected List<Component> children = new ArrayList<>();

	public void add(Component child) {
		children.add(child);
	}

	@Override
	public String toString() {
		String childrenString = this.children.stream().map(c -> c.toString() + ",").collect(Collectors.joining())
				+ "!*!";
		childrenString = childrenString.replace(",!*!", "");
		return "{tooltipText:" + this.tooltipText + ", children:[" + childrenString + "]}";
	}
}

class Dialog extends Container {
	public String wikiPageURL;

	public Dialog(String wikiPageURL) {
		this.wikiPageURL = wikiPageURL;
	}

	public void showHelp() {
		if (wikiPageURL != null) {
			System.out.println("Abrindo página de ajuda do wiki: " + wikiPageURL);
		} else {
			super.showHelp();
		}
	}

	@Override
	public String toString() {
		String childrenString = this.children.stream().map(c -> c.toString() + ",").collect(Collectors.joining())
				+ "!*!";
		childrenString = childrenString.replace(",!*!", "");
		return "Dialog:{wikiPageURL: " + this.wikiPageURL + ", children: [" + childrenString + "]}";
	}

}

class Panel extends Container {
	private String modalHelpText;

	public Panel(String modalHelpText) {
		this.modalHelpText = modalHelpText;
	}

	public void showHelp() {
		if (modalHelpText != null) {
			System.out.println("Mostrando janela modal com texto de ajuda: " + modalHelpText);
		} else {
			super.showHelp();
		}
	}

	@Override
	public String toString() {
		String childrenString = this.children.stream().map(c -> c.toString() + ",").collect(Collectors.joining())
				+ "!*!";
		childrenString = childrenString.replace(",!*!", "");
		return "{tooltipText: " + this.tooltipText + ", modalHelpText: " + this.modalHelpText + ", children: ["
				+ childrenString + "]}";
	}
}

class Button extends Component {
	public Button(String tooltipText) {
		this.tooltipText = tooltipText;
	}

	@Override
	public String toString() {
		return "{tooltipText: " + this.tooltipText + "}";
	}
}
