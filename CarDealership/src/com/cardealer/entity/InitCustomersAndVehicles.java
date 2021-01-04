package com.cardealer.entity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InitCustomersAndVehicles {

	/* *****NOTE, this method initializes data files, but saves them under the project workspace root folder.
	 * When running on a server, the project expects the data files to be in the user's desktop folder, so
	 * the files must be copied there in order to be found. 
	 */
	public static void main(String[] args) {
//		Address(String street1, String street2, String city, String state, int zip, int zipPlus4)
		Address address1 = new Address
				("123 Main", 
				"",
				"Anytown",
				"IL",
				62226,
				0);
		
//		PaymentMethod(String nameOnCard, String cardNum, LocalDate expiration, String cvv, String paypalEmail)
		PaymentMethod pay1 = new PaymentMethod
				("Jonathan Doe",
				"1234-5678-9010-1112",
				LocalDate.parse("2021-12-31"),
				"123",
				"jdoe@me.com");
		
		
//		Customer(long customerID, String customerName,  long customerPhone,
//				String customerEmail, String customerPassword, Address customerAddress, PaymentMethod pay) {
		Customer customer1 = new Customer
				(1,
				"John Doe",
				8529637410L,
				"jdoe@me.com",
				"p",
				address1,
				pay1);
		
//		Address(String street1, String street2, String city, String state, int zip, int zipPlus4)
		Address address2 = new Address
				("321 1st Street", 
				"",
				"Mycity",
				"MO",
				56789,
				1234);
		
//		PaymentMethod(String nameOnCard, String cardNum, LocalDate expiration, String cvv, String paypalEmail)
		PaymentMethod pay2 = new PaymentMethod
				("Jane Smith",
				"1112-9010-1234-5678",
				LocalDate.parse("2021-05-31"),
				"321",
				"janes@email.com");
		
//		System.out.println("Payment method data string: " + pay1.toDataFileString());
//		System.out.println("Correct credit card number " + pay1.isCorrectCardNum("1234567890101112"));
		
//		Customer(long customerID, String customerName,  long customerPhone,
//				String customerEmail, Address customerAddress, PaymentMethod pay) {
		Customer customer2 = new Customer
				(2,
				"Jane Smith",
				1384658795L,
				"janes@email.com",
				"p",
				address2,
				pay2);
		CustomerList clist = new CustomerList();
		// Capture the added version of the customer to ensure they have the correct customer number.
		customer1 = clist.addCustomer(customer1);
		customer2 = clist.addCustomer(customer2);

//		Vehicle(LocalDateTime lastModified, boolean isNew, String make, String model, String type, int year,
//		String vin, String color, int odometer, String tagline, String description, String pictureLink,
//		LocalDate dealerPurchased, BigDecimal dealerPurchasePrice, BigDecimal salePrice, LocalDate dateSold,
//		Customer soldToCustomer)
		Vehicle vehicle1 = new Vehicle
				(LocalDateTime.parse("2020-12-13T19:55"),
				false,
				"Toyota",
				"Sienna",
				"Minivan",
				2014,
				"HG4231423GF23TRE",
				"Silver",
				92500,
				"Wonderful family car",
				"This mighty vehicle easily seats 8 in three rows of seats. It features power windows and doors and a beautifully upholstered gray interior.",
				"images/Sienna.jpg",
				LocalDate.parse("2016-07-07"),
				new BigDecimal("22000.00"),
				new BigDecimal("24000.00"),
				LocalDate.parse("2020-12-13"),
				customer1.getCustomerID());
		
		Vehicle vehicle2 = new Vehicle
				(LocalDateTime.parse("2020-12-13T19:55"),
				false,
				"Honda",
				"CR-V",
				"SUV",
				2008,
				"98UO9873DGDFG9",
				"Blue",
				122500,
				"Perfect for a new family",
				"Drives like a car with plenty of power, but the cargo carrying capacity of a larger vehicle. Comfortably seats 4 people and all their valuables.",
				"images/CRV.png",
				LocalDate.parse("2020-11-22"),
				new BigDecimal("5000.00"),
				new BigDecimal("6000.00"),
				LocalDate.parse("2020-12-17"),
				customer2.getCustomerID());
		
		Vehicle vehicle3 = new Vehicle
				(LocalDateTime.parse("2020-12-14T20:03"),
				false,
				"Pontiac",
				"Grand Am",
				"Sedan",
				1996,
				"347576897K4J5NFJ",
				"Red",
				158520,
				"Sporty vehicle for any age group",
				"Don't let the mileage fool you, it drives like a dream. Impressive MPG! Immaculately maintained with new tires, shocks, struts, brake rotors and pads.",
				"images/GrandAm.jpg",
				LocalDate.parse("2020-10-08"),
				new BigDecimal("2000.00"),
				new BigDecimal("0.00"),
				LocalDate.parse("1900-01-01"),
				0L);
		
		Vehicle vehicle4 = new Vehicle
				(LocalDateTime.parse("2020-12-18T11:06"),
				false,
				"Chevrolet",
				"Camaro",
				"Sports Car",
				2015,
				"2G1FC1E34F9254490",
				"Black",
				53000,
				"Sports car with everything",
				"Manual Transmission, Navigation System, Bluetooth, 20 Inch Plus Alloy Wheels, Power Everything, Bluetooth, Overhead Airbags.",
				"images/Camaro.png",
				LocalDate.parse("2020-10-08"),
				new BigDecimal("16000.00"),
				new BigDecimal("0.00"),
				LocalDate.parse("1900-01-01"),
				0L);

		Vehicle vehicle5 = new Vehicle
				(LocalDateTime.parse("2020-12-18T11:06"),
				false,
				"Jeep",
				"Grand Cherokee",
				"SUV",
				2018,
				"1C4RJFAG6JC219712",
				"Silver",
				29000,
				"4WD/AWD to fit your sense of adventure",
				"Apple CarPlay, Heated Steering Wheel, Parking Sensors, Remote Start, Side Airbags, Rear View Camera, Alloy Wheels.",
				"images/Cherokee.png",
				LocalDate.parse("2020-10-08"),
				new BigDecimal("23000.00"),
				new BigDecimal("0.00"),
				LocalDate.parse("1900-01-01"),
				0L);
		
		Vehicle vehicle6 = new Vehicle
				(LocalDateTime.parse("2020-12-18T11:06"),
				false,
				"Fiat",
				"500 Pop",
				"Coupe",
				2013,
				"3C3CFFAR4DT531467",
				"Blue",
				66000,
				"Efficient commuter with lots to love",
				"31 City/41 Hwy MPG, Bluetooth Technology, Cloth Seats, Cruise Control, Satellite Radio Ready, Side Airbags, Power Locks and Mirrors.",
				"images/Fiat500.png",
				LocalDate.parse("2020-10-08"),
				new BigDecimal("7000.00"),
				new BigDecimal("0.00"),
				LocalDate.parse("1900-01-01"),
				0L);
		
		Vehicle vehicle7 = new Vehicle
				(LocalDateTime.parse("2020-12-18T11:06"),
				false,
				"Dodge",
				"Ram",
				"Pickup",
				2019,
				"1C6SRFJT9KN649142",
				"Blue",
				21000,
				"Fully loaded quad-cab built Ram tough",
				"Panoramic Sunroof, A/C Seat(s), 4WD/AWD, Navigation System, Apple CarPlay, 20 Inch Plus Alloy Wheels, Power everything.",
				"images/Ram.jpg",
				LocalDate.parse("2020-10-08"),
				new BigDecimal("42000.00"),
				new BigDecimal("0.00"),
				LocalDate.parse("1900-01-01"),
				0L);
		
		Vehicle vehicle8 = new Vehicle
				(LocalDateTime.parse("2020-12-18T11:06"),
				true,
				"Toyota",
				"Camry",
				"Sedan",
				2021,
				"4T1G11BKXMU025384",
				"Silver",
				53,
				"Toyota's latest synergy of sport and reliability",
				"Cloth heated front bucket seats, back up camera, automatic air conditioning, distance pacing, WiFi hotspot, 4DR sedan, 2.5L I-4 DOHC 16-Valve, automatic, AWD, 18\" black machined finish alloy wheels, engine auto stop-start feature, AM/FM audio system : 6 speakers / 7\" touch-screen / Android Auto and Apple CarPlay and Amazon Alexa compatible / USB media port / 2 USB charge ports / hands-free phone capability / advanced voice recognition and music streaming via Bluetooth wireless technology, sport leather/metal-look steering wheel with mounted audio and cruise controls, keyless entry, front and rear anti-roll bars, lane departure alert with steering assist lane keeping assist, dual stage driver and passenger front and side mounted airbags, curtain 1st and 2nd row airbags, engine immobilizer, and ABS and driveline traction control. Outstanding design defines the 2021 Camry SE.",
				"images/Camry.jpg",
				LocalDate.parse("2020-07-08"),
				new BigDecimal("25000.00"),
				new BigDecimal("0.00"),
				LocalDate.parse("1900-01-01"),
				0L);
		
		Vehicle vehicle9 = new Vehicle
				(LocalDateTime.parse("2020-12-18T11:06"),
				true,
				"Tesla",
				"Model S",
				"Sports Car",
				2021,
				"4T1GZZ3843FEU25384",
				"Black",
				3,
				"Tesla shatters the mold with innovation",
				"Tesla All-Wheel Drive has two independent motors for improved redundancy, each with only one moving part for minimal maintenance and maximum durability. Unlike traditional all-wheel drive systems, they digitally control torque to the front and rear wheels for far better handling and traction control.",
				"images/Tesla.jpg",
				LocalDate.parse("2020-10-08"),
				new BigDecimal("60000.00"),
				new BigDecimal("0.00"),
				LocalDate.parse("1900-01-01"),
				0L);
		
		Vehicle vehicle10 = new Vehicle
				(LocalDateTime.parse("2020-12-18T11:06"),
				true,
				"Honda",
				"Passport",
				"SUV",
				2021,
				"5FNYF8H28MB004021",
				"Black",
				53,
				"New touring model with great performance and safety",
				"This Honda Passport is well equipped and includes the following key features and benefits, Collision Avoidance System, Collision Warning System, *Adaptive Cruise Control, Heated Seats, Blind-Spot Monitors, Navigation System, Backup Camera, Keyless Access with Push Button Start, Automatic Headlights, Apple CarPlay, Android Auto, Bluetooth Hands-Free, Leather Seats, Sunroof/Moonroof, 10 Speakers, Radio: 540-Watt Premium Audio System w/10 Speakers.",
				"images/Passport.png",
				LocalDate.parse("2020-08-08"),
				new BigDecimal("38000.00"),
				new BigDecimal("0.00"),
				LocalDate.parse("1900-01-01"),
				0L);
		
		Inventory inv = new Inventory();
		inv.addVehicle(vehicle1);
		inv.addVehicle(vehicle2);
		inv.addVehicle(vehicle3);
		inv.addVehicle(vehicle4);
		inv.addVehicle(vehicle5);
		inv.addVehicle(vehicle6);
		inv.addVehicle(vehicle7);
		inv.addVehicle(vehicle8);
		inv.addVehicle(vehicle9);
		inv.addVehicle(vehicle10);
	}

}
