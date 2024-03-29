package model;


public class Car {
	
	private int code;
	private String brand;
	private String model;
	private double price;
	private boolean unused;
	
	public Car() {
	}
	
	public Car(int code, String brand, String model, double price, boolean unused) {
		super();
		this.code = code;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.unused = unused;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isUnused() {
		return unused;
	}
	public void setUnused(boolean unused) {
		this.unused = unused;
	}

	@Override
	public String toString() {
		return "Car [code=" + code + ", brand=" + brand + ", model=" + model + ", price=" + price + ", unused=" + unused
				+ "]";
	}
	
	
	
	
}
