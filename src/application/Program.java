package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please type the file path: ");
		String path = sc.nextLine();
		File file = new File(path);
		boolean out = new File(file.getParent() + "/out").mkdir();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			List<String> lines = new ArrayList<>();
			
			while (line != null) {
				lines.add(line);
				line = br.readLine();
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file.getParent() + "/out/summary.csv")))) {
				for (String l : lines) {
					String[] productInfo = l.split(",");
					String name = productInfo[0];
					double price = Double.parseDouble(productInfo[1]);
					int quantity = Integer.parseInt(productInfo[2]);
					
					Product product = new Product(name, price, quantity);
					bw.write(product.getName() + "," + product.totalValue(price, quantity));
					bw.newLine();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();

	}

}
