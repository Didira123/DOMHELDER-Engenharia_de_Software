package builder;

public class BuilderMain {

	public static void main(String[] args) {
		Application.main(args);
	}

}

class Application {
	public static void main(String[] args) {
		Director director = new Director();

		CarBuilder carBuilder = new CarBuilder();
		director.constructSportsCar(carBuilder);
		Car car = carBuilder.getProduct();
		System.out.println(car);

		CarManualBuilder manualBuilder = new CarManualBuilder();
		director.constructManualSportsCar(manualBuilder);
		Manual manual = manualBuilder.getProduct();
		System.out.println(manual);

	}
}

class Director {
	public void constructSportsCar(Builder builder) {
		builder.reset();
		builder.setSeats(2);
		builder.setEngine("SportEngine");
		builder.setTripComputer(true);
		builder.setGPS(true);
	}

	public void constructManualSportsCar(Builder builder) {
		builder.reset();
		builder.setSeats(2);
		builder.setEngine("SportEngine");
		builder.setTripComputer(true);
		builder.setGPS(true);
	}

	// Outros métodos para construir carros
}

class Car {
	private int seats;
	private String engine;
	private boolean tripComputer;
	private boolean gps;

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public boolean isTripComputer() {
		return tripComputer;
	}

	public void setTripComputer(boolean tripComputer) {
		this.tripComputer = tripComputer;
	}

	public boolean isGPS() {
		return gps;
	}

	public void setGPS(boolean gps) {
		this.gps = gps;
	}

	@Override
	public String toString() {
		return "Car:" + super.toString();
	}

}

class Manual {
	private int seatsDescription;
	private String engineDescription;
	private boolean tripComputerDescription;
	private boolean gpsDescription;

	public int getSeatsDescription() {
		return seatsDescription;
	}

	public void setSeatsDescription(int seatsDescription) {
		this.seatsDescription = seatsDescription;
	}

	public String getEngineDescription() {
		return engineDescription;
	}

	public void setEngineDescription(String engineDescription) {
		this.engineDescription = engineDescription;
	}

	public boolean isTripComputerDescription() {
		return tripComputerDescription;
	}

	public void setTripComputerDescription(boolean tripComputerDescription) {
		this.tripComputerDescription = tripComputerDescription;
	}

	public boolean isGPSDescription() {
		return gpsDescription;
	}

	public void setGPSDescription(boolean gpsDescription) {
		this.gpsDescription = gpsDescription;
	}

	@Override
	public String toString() {
		return "Manual:{seats:" + getSeatsDescription() + ", engine:" + getEngineDescription() + ", tripComputer:"
				+ isTripComputerDescription() + ", GPS:" + isGPSDescription() + "}";
	}

}

interface Builder {
	void reset();

	void setSeats(int seats);

	void setEngine(String engine);

	void setTripComputer(boolean tripComputer);

	void setGPS(boolean GPS);
}

class CarBuilder implements Builder {
	private Car car;

	public CarBuilder() {
		reset();
	}

	public void reset() {
		car = new Car();
	}

	public void setSeats(int seats) {
		car.setSeats(seats);
	}

	public void setEngine(String engine) {
		car.setEngine(engine);
	}

	public void setTripComputer(boolean tripComputer) {
		car.setTripComputer(tripComputer);
	}

	public void setGPS(boolean GPS) {
		car.setGPS(GPS);
	}

	public Car getProduct() {
		Car product = car;
		reset();
		return product;
	}
}

class CarManualBuilder implements Builder {
	private Manual manual;

	public CarManualBuilder() {
		reset();
	}

	public void reset() {
		manual = new Manual();
	}

	@Override
	public void setSeats(int seats) {
		manual.setSeatsDescription(seats);
	}

	@Override
	public void setEngine(String engine) {
		manual.setEngineDescription(engine);
	}

	@Override
	public void setTripComputer(boolean tripComputer) {
		manual.setTripComputerDescription(tripComputer);
	}

	@Override
	public void setGPS(boolean GPS) {
		manual.setGPSDescription(GPS);
	}

	public Manual getProduct() {
		Manual product = manual;
		reset();
		return product;
	}
}
