package adapter;

public class Adapter {
	public static void main(String[] args) {
		RoundHole hole = new RoundHole(5);
		RoundPeg circuloPequeno = new RoundPeg(5);
		System.out.println("circulo pequeno cabe? " + hole.fits(circuloPequeno));
		RoundPeg circuloGrande = new RoundPeg(6);
		System.out.println("circulo grande cabe? " + hole.fits(circuloGrande));

		SquarePeg quadradoPequeno = new SquarePeg(5);
		SquarePegAdapter quadradoPequenoAdapter = new SquarePegAdapter(quadradoPequeno);
		System.out.println("quadrado pequeno cabe? " + hole.fits(quadradoPequenoAdapter));

		SquarePeg quadradoMaior = new SquarePeg(10);
		SquarePegAdapter quadradoMaiorAdapter = new SquarePegAdapter(quadradoMaior);
		System.out.println("quadrado grande cabe? " + hole.fits(quadradoMaiorAdapter));
	}
}

class RoundHole {
	private double radius;

	public RoundHole(int radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public boolean fits(RoundPeg peg) {
		return getRadius() >= peg.getRadius();
	}
}

class RoundPeg {
	private double radius;

	public RoundPeg(int radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}
}

class SquarePeg {
	private double width;

	public SquarePeg(int width) {
		this.width = width;
	}

	public double getWidth() {
		return width;
	}
}

class SquarePegAdapter extends RoundPeg {
	private SquarePeg peg;

	public SquarePegAdapter(SquarePeg peg) {
		super(0);
		this.peg = peg;
	}

	@Override
	public double getRadius() {
		return peg.getWidth() * Math.sqrt(2) / 2;
	}
}