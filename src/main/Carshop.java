package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Car;

public class Carshop {
	private static ArrayList<Car> cars =  new ArrayList<>();
	
	public static void main(String[] args) {
		Carshop carshop = new Carshop();
		
		carshop.loadInventory();
		for (Car car : cars) {
			System.out.println(car);
		}
		
		carshop.writeReport();
	}
	
	/**
	 * read cars from file
	 */
	private void loadInventory() {
		// locate file, path and name
		File f = new File(System.getProperty("user.dir") + File.separator + "files/inputCars.txt");
		
		try {			
			// wrap in proper classes
			FileReader fr;
			fr = new FileReader(f);				
			BufferedReader br = new BufferedReader(fr);
			
			// read first line
			String line = br.readLine();
			
			// process and read next line until end of file
			while (line != null) {
				// split in sections
				String[] data = line.split(",");
				
				Car car = new Car();
				
				// read each sections
				for (int i = 0; i < data.length; i++) {
					
					// format value
					switch (i) {
					case 0:
						// format car code
						car.setCode(Integer.parseInt(data[0]));
						break;
						
					case 1:
						// format brand
						car.setBrand(data[1]);
						break;
						
					case 2:
						// format model
						car.setModel(data[2]);
						break;
						
					case 3:
						// format model
						car.setPrice(Double.parseDouble(data[3]));
						break;
					case 4:
						// format model
						boolean value = false;
						if ("nuevo".equalsIgnoreCase(data[4])) {
							value = true;
						}						
						car.setUnused(value);
						break;
						
					default:
						break;
					}
				}
				// add car to carshop to inventory
				cars.add(car);
				
				// read next line
				line = br.readLine();
			}
			fr.close();
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * write in file the sales done
	 */
	private void writeReport() {
		// define date
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = date.format(myFormatObj);
		
		// locate file, path and name
		File f = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator + "outputCars.txt");
				
		try {
			// wrap in proper classes
			FileWriter fw;
			fw = new FileWriter(f, true);
			PrintWriter pw = new PrintWriter(fw);
			
			// write line by line
			// write header
			StringBuilder headerLine = new StringBuilder("Reporte Precio medio, fecha " + formattedDate + ";");
			pw.write(headerLine.toString());
			pw.write("\n");
			
			
			// write details
			double priceNew=0.0;
			double priceOld=0.0;
			int counterNew=0;
			int counterOld=0;
			for (Car car : cars) {
				// group by unused value
				if (car.isUnused()) {
					priceNew += car.getPrice();
					counterNew++;
				} else {
					priceOld += car.getPrice();
					counterOld++;
				}				
			}			
			// write new cars
			StringBuilder newLine = new StringBuilder("Coches nuevos= " + (priceNew/counterNew) + ";");
			pw.write(newLine.toString());
			pw.write("\n");
			
			//write no new cars
			StringBuilder oldLine = new StringBuilder("Coches no nuevos= " + (priceOld/counterOld) + ";");
			pw.write(oldLine.toString());
			
			// close files
			pw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	
}

