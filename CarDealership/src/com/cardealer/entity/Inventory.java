package com.cardealer.entity;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Inventory {
	private StringChecker check = new StringChecker(true);

	private String inventoryFileName = "Inventory.txt";
	private ArrayList<Vehicle> inventory = new ArrayList<Vehicle>();

	public Inventory() {
		// Upon creation, restore the inventory from a file.
		restoreInventory(this.inventoryFileName);
	}

	/**
	 * Method addVehicle takes a Vehicle object, validates it as usable, and updates
	 * the inventory list with the new Vehicle included as the last element.
	 * 
	 * @param Vehicle - the Vehicle object to be added.
	 * @return boolean - true if the vehicle is added, false if the vehicle was not
	 *         added successfully.
	 */
	public boolean addVehicle(Vehicle newVehicle) {
		if (newVehicle == null) {
			System.out.println("Vehicle object is null.");
			return false;
		}
		// If new customer information is valid for a new customer (has an ID of zero),
		// add the new customer and update this.inventory
		if (newVehicle.isValidVehicle()) {
			// If the vehicle VIN is not found, add the vehicle.
			if (findVehicle(newVehicle.getVin()) == null) {
				// TODO check to see if any vehicles in the file are more current than this
				// customer list
				// if so, load the list from the file before adding this vehicle and saving.
				this.inventory.add(newVehicle);
				this.inventory = sortByVIN(this.inventory, true);
				// Save the inventory to a file
				// TODO check to see if the save was successful.
				saveInventory(this.inventoryFileName);
				return true;
			} else {
				System.out.println("A vehicle with this VIN is already in the inventory.");
			}
		}
		System.out.println("Vehicle was not valid and was not added.");
		return false;
	}

	/**
	 * Method updateVehicle takes a Vehicle object, validates it as usable, then
	 * checks the existing inventory for a duplicate VIN. If found, it removes the
	 * old vehicle and replaces that vehicle with updatedVehicle.
	 * 
	 * @param Vehicle - the Vehicle object to be updated.
	 * @return boolean - true if the vehicle is updated, false if not.
	 */
	public boolean updateVehicle(Vehicle updatedVehicle) {
		if (updatedVehicle == null) {
			System.out.println("Vehicle object is null.");
			return false;
		}
		// If updated customer information is valid, add the new customer and update
		// this.inventory
		if (updatedVehicle.isValidVehicle()) {
			Vehicle existingVehicle = findVehicle(updatedVehicle.getVin());
			if (existingVehicle != null) {
				// TODO check to see if any customers in the file are more current than this
				// customer list
				// if so, load the list from the file before adding this customer and saving.
				this.inventory.remove(existingVehicle);
				this.inventory.add(updatedVehicle);
				this.inventory = sortByVIN(this.inventory, true);
				// Save the updated customer list to a file
				// TODO check to see if the save was successful.
				saveInventory(this.inventoryFileName);
				return true;
			}
			System.out.println("Vehicle ID was not found in the current inventory.");
			return false;
		}
		System.out.println("Vehicle was not valid and was not added.");
		return false;
	}

	/**
	 * Method removeVehicle takes a Vehicle object, checks the existing inventory
	 * for a duplicate VIN. If found, it removes the old vehicle.
	 * 
	 * @param removeThisVehicle - the Vehicle object to be removed.
	 * @return boolean - true if the customer is removed, false if not.
	 */
	public boolean removeVehicle(Vehicle removeThisVehicle) {
		if (removeThisVehicle == null) {
			System.out.println("Vehicle object is null.");
			return false;
		}
		Vehicle existingVehicle = findVehicle(removeThisVehicle.getVin());
		if (existingVehicle != null) {
			// TODO check to see if any customers in the file are more current than this
			// customer list
			// if so, load the list from the file before adding this customer and saving.
			this.inventory.remove(existingVehicle);
			// Save the updated customer list to a file
			// TODO check to see if the save was successful.
			saveInventory(this.inventoryFileName);
			return true;
		}
		System.out.println("Vehicle ID was not found in the current inventory.");
		return false;
	}

	/**
	 * Method findVehicle searches the inventory for a matching VIN and returns the
	 * matching vehicle. If no match is found returns null.
	 * 
	 * @param searchVIN - the VIN for which to search
	 * @return Vehicle - the Vehicle which was found, or null if there was no
	 *         matching ID.
	 */
	public Vehicle findVehicle(String searchVIN) {
		if (!check.isValidString(searchVIN)) {
			return null;
		}
		searchVIN = searchVIN.trim().toUpperCase();
		String tempString = "";
		for (Vehicle tempVehicle : this.inventory) {
			tempString = tempVehicle.getVin().toUpperCase();
			if (tempString.contains(searchVIN)) {
				return tempVehicle;
			}
		}
		return null;
	}

	/**
	 * Method findNew returns an ArrayList of vehicles which are new.
	 * 
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findNew() {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (tempVehicle.isNewVehicle()) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findNew returns an ArrayList of vehicles which are new. For nested
	 * searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findNew(ArrayList<Vehicle> inputArrayList) {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (tempVehicle.isNewVehicle()) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findUsed returns an ArrayList of vehicles which are used.
	 * 
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findUsed() {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (!tempVehicle.isNewVehicle()) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findUsed returns an ArrayList of vehicles which are used. For nested
	 * searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findUsed(ArrayList<Vehicle> inputArrayList) {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (!tempVehicle.isNewVehicle()) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findSold returns an ArrayList of vehicles which are sold.
	 * 
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findSold() {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (tempVehicle.isSold()) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findSold returns an ArrayList of vehicles which are sold. For nested
	 * searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findSold(ArrayList<Vehicle> inputArrayList) {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (tempVehicle.isSold()) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findForSale returns an ArrayList of vehicles which are not sold (on
	 * the lot).
	 * 
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findForSale() {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (!tempVehicle.isSold()) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findForSale returns an ArrayList of vehicles which are not sold (on
	 * the lot). For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findForSale(ArrayList<Vehicle> inputArrayList) {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (!tempVehicle.isSold()) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByMake searches the inventory for a partial or full matching
	 * vehicle make and returns an ArrayList of vehicles it finds. Match is not case
	 * sensitive.
	 * 
	 * @param make - the make for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByMake(String make) {
		if (!check.isValidString(make)) {
			return new ArrayList<Vehicle>();
		}
		make = make.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : this.inventory) {
			tempString = tempVehicle.getMake().toLowerCase();
			if (tempString.contains(make)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByMake searches the inputArrayList for a partial or full matching
	 * vehicle make and returns an ArrayList of vehicles it finds. Match is not case
	 * sensitive. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param make           - the make for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByMake(ArrayList<Vehicle> inputArrayList, String make) {
		if (!check.isValidString(make)) {
			return new ArrayList<Vehicle>();
		}
		make = make.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : inputArrayList) {
			tempString = tempVehicle.getMake().toLowerCase();
			if (tempString.contains(make)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByModel searches the inventory for a partial or full matching
	 * vehicle model and returns an ArrayList of vehicles it finds. Match is not
	 * case sensitive.
	 * 
	 * @param model - the model for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByModel(String model) {
		if (!check.isValidString(model)) {
			return new ArrayList<Vehicle>();
		}
		model = model.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : this.inventory) {
			tempString = tempVehicle.getModel().toLowerCase();
			if (tempString.contains(model)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByModel searches the inputArrayList for a partial or full matching
	 * vehicle model and returns an ArrayList of vehicles it finds. Match is not
	 * case sensitive. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * 
	 * @param model          - the model for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByModel(ArrayList<Vehicle> inputArrayList, String model) {
		if (!check.isValidString(model)) {
			return new ArrayList<Vehicle>();
		}
		model = model.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : inputArrayList) {
			tempString = tempVehicle.getModel().toLowerCase();
			if (tempString.contains(model)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByType searches the inventory for a partial or full matching
	 * vehicle type and returns an ArrayList of vehicles it finds. Match is not case
	 * sensitive.
	 * 
	 * @param type - the type for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByType(String type) {
		if (!check.isValidString(type)) {
			return new ArrayList<Vehicle>();
		}
		type = type.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : this.inventory) {
			tempString = tempVehicle.getType().toLowerCase();
			if (tempString.contains(type)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByType searches the inputArrayList for a partial or full matching
	 * vehicle type and returns an ArrayList of vehicles it finds. Match is not case
	 * sensitive. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param type           - the type for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByType(ArrayList<Vehicle> inputArrayList, String type) {
		if (!check.isValidString(type)) {
			return new ArrayList<Vehicle>();
		}
		type = type.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : inputArrayList) {
			tempString = tempVehicle.getType().toLowerCase();
			if (tempString.contains(type)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByYear searches the inventory for a specific vehicle year and
	 * returns an ArrayList of vehicles it finds.
	 * 
	 * @param This - search for this year
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByYear(int This) {
		return findByYear(This, This);
	}

	/**
	 * Method findByYear searches the inventory for vehicle year readings between
	 * fromThis and toThis (inclusive) and returns an ArrayList of vehicles it
	 * finds.
	 * 
	 * @param fromThis - search for years at or above this
	 * @param toThis   - search for years at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByYear(int fromThis, int toThis) {
		if (toThis < fromThis) {
			// Values are reversed, so call the method in the correct order.
			return findByYear(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (tempVehicle.getOdometer() >= fromThis && tempVehicle.getOdometer() <= toThis) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByYear searches the inputArrayList for a specific vehicle year and
	 * returns an ArrayList of vehicles it finds. For nested searches, searches
	 * inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param This           - search for this year
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByYear(ArrayList<Vehicle> inputArrayList, int This) {
		return findByYear(inputArrayList, This, This);
	}

	/**
	 * Method findByYear searches the inputArrayList for vehicle year readings
	 * between fromThis and toThis (inclusive) and returns an ArrayList of vehicles
	 * it finds. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param fromThis       - search for years at or above this
	 * @param toThis         - search for years at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByYear(ArrayList<Vehicle> inputArrayList, int fromThis, int toThis) {
		if (toThis < fromThis) {
			// Values are reversed, so call the method in the correct order.
			return findByYear(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (tempVehicle.getOdometer() >= fromThis && tempVehicle.getOdometer() <= toThis) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByColor searches the inventory for a partial or full matching
	 * vehicle color and returns an ArrayList of vehicles it finds. Match is not
	 * case sensitive.
	 * 
	 * @param color - the color for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByColor(String color) {
		if (!check.isValidString(color)) {
			return new ArrayList<Vehicle>();
		}
		color = color.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : this.inventory) {
			tempString = tempVehicle.getColor().toLowerCase();
			if (tempString.contains(color)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByColor searches the inputArrayList for a partial or full matching
	 * vehicle color and returns an ArrayList of vehicles it finds. Match is not
	 * case sensitive. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param color          - the color for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByColor(ArrayList<Vehicle> inputArrayList, String color) {
		if (!check.isValidString(color)) {
			return new ArrayList<Vehicle>();
		}
		color = color.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : inputArrayList) {
			tempString = tempVehicle.getColor().toLowerCase();
			if (tempString.contains(color)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByOdometer searches the inventory for an exact vehicle odometer
	 * reading and returns an ArrayList of vehicles it finds.
	 * 
	 * @param This - search for this odometer reading
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByOdometer(int This) {
		return findByOdometer(This, This);
	}

	/**
	 * Method findByOdometer searches the inventory for vehicle odometer readings
	 * between fromThis and toThis (inclusive) and returns an ArrayList of vehicles
	 * it finds.
	 * 
	 * @param fromThis - search for odometer values at or above this
	 * @param toThis   - search for odometer values at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByOdometer(int fromThis, int toThis) {
		if (toThis < fromThis) {
			// Values are reversed, so call the method in the correct order.
			return findByOdometer(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (tempVehicle.getOdometer() >= fromThis && tempVehicle.getOdometer() <= toThis) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByOdometer searches the inputArrayList for an exact vehicle
	 * odometer reading and returns an ArrayList of vehicles it finds. For nested
	 * searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param This           - search for this odometer reading
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByOdometer(ArrayList<Vehicle> inputArrayList, int This) {
		return findByOdometer(inputArrayList, This, This);
	}

	/**
	 * Method findByOdometer searches the inputArrayList for vehicle odometer
	 * readings between fromThis and toThis (inclusive) and returns an ArrayList of
	 * vehicles it finds. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param fromThis       - search for odometer values at or above this
	 * @param toThis         - search for odometer values at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByOdometer(ArrayList<Vehicle> inputArrayList, int fromThis, int toThis) {
		if (toThis < fromThis) {
			// Values are reversed, so call the method in the correct order.
			return findByOdometer(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (tempVehicle.getOdometer() >= fromThis && tempVehicle.getOdometer() <= toThis) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByTagline searches the inventory for a partial or full matching
	 * vehicle tagline and returns an ArrayList of vehicles it finds. Match is not
	 * case sensitive.
	 * 
	 * @param tagline - the tagline for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByTagline(String tagline) {
		if (!check.isValidString(tagline)) {
			return new ArrayList<Vehicle>();
		}
		tagline = tagline.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : this.inventory) {
			tempString = tempVehicle.getType().toLowerCase();
			if (tempString.contains(tagline)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByTagline searches the inputArrayList for a partial or full
	 * matching vehicle tagline and returns an ArrayList of vehicles it finds. Match
	 * is not case sensitive. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param tagline        - the tagline for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByTagline(ArrayList<Vehicle> inputArrayList, String tagline) {
		if (!check.isValidString(tagline)) {
			return new ArrayList<Vehicle>();
		}
		tagline = tagline.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : inputArrayList) {
			tempString = tempVehicle.getType().toLowerCase();
			if (tempString.contains(tagline)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByDescription searches the inventory for a partial or full
	 * matching vehicle description and returns an ArrayList of vehicles it finds.
	 * Match is not case sensitive.
	 * 
	 * @param tagline - the tagline for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDescription(String description) {
		if (!check.isValidString(description)) {
			return new ArrayList<Vehicle>();
		}
		description = description.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : this.inventory) {
			tempString = tempVehicle.getType().toLowerCase();
			if (tempString.contains(description)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByDescription searches the inputArrayList for a partial or full
	 * matching vehicle description and returns an ArrayList of vehicles it finds.
	 * Match is not case sensitive. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param tagline        - the tagline for which to search
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDescription(ArrayList<Vehicle> inputArrayList, String description) {
		if (!check.isValidString(description)) {
			return new ArrayList<Vehicle>();
		}
		description = description.trim().toLowerCase();
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		String tempString = "";
		for (Vehicle tempVehicle : inputArrayList) {
			tempString = tempVehicle.getType().toLowerCase();
			if (tempString.contains(description)) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByDealerPurchased searches the inventory for a specific dealer
	 * purchased date returns an ArrayList of vehicles it finds.
	 * 
	 * @param This - search for this date
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDealerPurchased(LocalDate This) {
		return findByDealerPurchased(This, This);
	}

	/**
	 * Method findByDealerPurchased searches the inventory for dealer purchased date
	 * between fromThis and toThis (inclusive) and returns an ArrayList of vehicles
	 * it finds.
	 * 
	 * @param fromThis - search for date values at or after this
	 * @param toThis   - search for date values at or before this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDealerPurchased(LocalDate fromThis, LocalDate toThis) {
		if (toThis.isBefore(fromThis)) {
			// Values are reversed, so call the method in the correct order.
			return findByDealerPurchased(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if ((tempVehicle.getDealerPurchased().isBefore(fromThis)
					|| tempVehicle.getDealerPurchased().isEqual(fromThis))
					&& (tempVehicle.getDealerPurchased().isBefore(toThis)
							|| tempVehicle.getDealerPurchased().isEqual(toThis))) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByDealerPurchased searches the inputArrayList for a specific
	 * dealer purchased date returns an ArrayList of vehicles it finds. For nested
	 * searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param This           - search for this date
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDealerPurchased(ArrayList<Vehicle> inputArrayList, LocalDate This) {
		return findByDealerPurchased(inputArrayList, This, This);
	}

	/**
	 * Method findByDealerPurchased searches the inputArrayList for dealer purchased
	 * date between fromThis and toThis (inclusive) and returns an ArrayList of
	 * vehicles it finds. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param fromThis       - search for date values at or after this
	 * @param toThis         - search for date values at or before this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDealerPurchased(ArrayList<Vehicle> inputArrayList, LocalDate fromThis,
			LocalDate toThis) {
		if (toThis.isBefore(fromThis)) {
			// Values are reversed, so call the method in the correct order.
			return findByDealerPurchased(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if ((tempVehicle.getDealerPurchased().isBefore(fromThis)
					|| tempVehicle.getDealerPurchased().isEqual(fromThis))
					&& (tempVehicle.getDealerPurchased().isBefore(toThis)
							|| tempVehicle.getDealerPurchased().isEqual(toThis))) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByDealerPurchasePrice searches the inventory for a specific
	 * vehicle dealer purchase price and returns an ArrayList of vehicles it finds.
	 * 
	 * @param This - search for this recommended price
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDealerPurchasePrice(BigDecimal This) {
		return findByListPrice(This, This);
	}

	/**
	 * Method findByDealerPurchasePrice searches the inventory for vehicle dealer
	 * purchase prices between fromThis and toThis (inclusive) and returns an
	 * ArrayList of vehicles it finds.
	 * 
	 * @param fromThis - search for recommended price at or above this
	 * @param toThis   - search for recommended price at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDealerPurchasePrice(BigDecimal fromThis, BigDecimal toThis) {
		if (toThis.compareTo(fromThis) < 0) {
			// Values are reversed, so call the method in the correct order.
			return findByListPrice(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (tempVehicle.getDealerPurchasePrice().compareTo(fromThis) >= 0
					&& tempVehicle.getDealerPurchasePrice().compareTo(toThis) <= 0) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByDealerPurchasePrice searches the inputArrayList for a specific
	 * vehicle dealer purchase price and returns an ArrayList of vehicles it finds.
	 * For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param This           - search for this recommended price
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDealerPurchasePrice(ArrayList<Vehicle> inputArrayList, BigDecimal This) {
		return findByListPrice(inputArrayList, This, This);
	}

	/**
	 * Method findByDealerPurchasePrice searches the inputArrayList for vehicle
	 * dealer purchase prices between fromThis and toThis (inclusive) and returns an
	 * ArrayList of vehicles it finds. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param fromThis       - search for recommended price at or above this
	 * @param toThis         - search for recommended price at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDealerPurchasePrice(ArrayList<Vehicle> inputArrayList, BigDecimal fromThis,
			BigDecimal toThis) {
		if (toThis.compareTo(fromThis) < 0) {
			// Values are reversed, so call the method in the correct order.
			return findByListPrice(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (tempVehicle.getDealerPurchasePrice().compareTo(fromThis) >= 0
					&& tempVehicle.getDealerPurchasePrice().compareTo(toThis) <= 0) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByRecommendedPrice searches the inventory for a specific vehicle
	 * recommended price and returns an ArrayList of vehicles it finds.
	 * 
	 * @param This - search for this recommended price
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByRecommendedPrice(BigDecimal This) {
		return findByListPrice(This, This);
	}

	/**
	 * Method findByRecommendedPrice searches the inventory for vehicle recommended
	 * prices between fromThis and toThis (inclusive) and returns an ArrayList of
	 * vehicles it finds.
	 * 
	 * @param fromThis - search for recommended price at or above this
	 * @param toThis   - search for recommended price at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByRecommendedPrice(BigDecimal fromThis, BigDecimal toThis) {
		if (toThis.compareTo(fromThis) < 0) {
			// Values are reversed, so call the method in the correct order.
			return findByListPrice(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (tempVehicle.getListPrice().compareTo(fromThis) >= 0
					&& tempVehicle.getListPrice().compareTo(toThis) <= 0) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByRecommendedPrice searches the inputArrayList for a specific
	 * vehicle recommended price and returns an ArrayList of vehicles it finds. For
	 * nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param This           - search for this recommended price
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByRecommendedPrice(ArrayList<Vehicle> inputArrayList, BigDecimal This) {
		return findByListPrice(inputArrayList, This, This);
	}

	/**
	 * Method findByRecommendedPrice searches the inputArrayList for vehicle
	 * recommended prices between fromThis and toThis (inclusive) and returns an
	 * ArrayList of vehicles it finds. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param fromThis       - search for recommended price at or above this
	 * @param toThis         - search for recommended price at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByRecommendedPrice(ArrayList<Vehicle> inputArrayList, BigDecimal fromThis,
			BigDecimal toThis) {
		if (toThis.compareTo(fromThis) < 0) {
			// Values are reversed, so call the method in the correct order.
			return findByListPrice(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (tempVehicle.getListPrice().compareTo(fromThis) >= 0
					&& tempVehicle.getListPrice().compareTo(toThis) <= 0) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByListPrice searches the inventory for a specific vehicle list
	 * price and returns an ArrayList of vehicles it finds.
	 * 
	 * @param This - search for this recommended price
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByListPrice(BigDecimal This) {
		return findByListPrice(This, This);
	}

	/**
	 * Method findByListPrice searches the inventory for vehicle list prices between
	 * fromThis and toThis (inclusive) and returns an ArrayList of vehicles it
	 * finds.
	 * 
	 * @param fromThis - search for list prices at or above this
	 * @param toThis   - search for list prices at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByListPrice(BigDecimal fromThis, BigDecimal toThis) {
		if (toThis.compareTo(fromThis) < 0) {
			// Values are reversed, so call the method in the correct order.
			return findByListPrice(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (tempVehicle.getListPrice().compareTo(fromThis) >= 0
					&& tempVehicle.getListPrice().compareTo(toThis) <= 0) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByListPrice searches the inputArrayList for a specific vehicle
	 * list price and returns an ArrayList of vehicles it finds. For nested
	 * searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param This           - search for this recommended price
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByListPrice(ArrayList<Vehicle> inputArrayList, BigDecimal This) {
		return findByListPrice(inputArrayList, This, This);
	}

	/**
	 * Method findByListPrice searches the inputArrayList for vehicle list prices
	 * between fromThis and toThis (inclusive) and returns an ArrayList of vehicles
	 * it finds. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param fromThis       - search for list prices at or above this
	 * @param toThis         - search for list prices at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByListPrice(ArrayList<Vehicle> inputArrayList, BigDecimal fromThis,
			BigDecimal toThis) {
		if (toThis.compareTo(fromThis) < 0) {
			// Values are reversed, so call the method in the correct order.
			return findByListPrice(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (tempVehicle.getListPrice().compareTo(fromThis) >= 0
					&& tempVehicle.getListPrice().compareTo(toThis) <= 0) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findBySoldPrice searches the inventory for a specific vehicle sold
	 * price and returns an ArrayList of vehicles it finds.
	 * 
	 * @param This - search for this sold price
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findBySoldPrice(BigDecimal This) {
		return findByListPrice(This, This);
	}

	/**
	 * Method findBySoldPrice searches the inventory for vehicle sold prices between
	 * fromThis and toThis (inclusive) and returns an ArrayList of vehicles it
	 * finds.
	 * 
	 * @param fromThis - search for sold price at or above this
	 * @param toThis   - search for sold price at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findBySoldPrice(BigDecimal fromThis, BigDecimal toThis) {
		if (toThis.compareTo(fromThis) < 0) {
			// Values are reversed, so call the method in the correct order.
			return findByListPrice(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (tempVehicle.getSoldPrice().compareTo(fromThis) >= 0
					&& tempVehicle.getSoldPrice().compareTo(toThis) <= 0) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findBySoldPrice searches the inputArrayList for a specific vehicle
	 * sold price and returns an ArrayList of vehicles it finds. For nested
	 * searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param This           - search for this sold price
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findBySoldPrice(ArrayList<Vehicle> inputArrayList, BigDecimal This) {
		return findByListPrice(inputArrayList, This, This);
	}

	/**
	 * Method findBySoldPrice searches the inputArrayList for vehicle sold prices
	 * between fromThis and toThis (inclusive) and returns an ArrayList of vehicles
	 * it finds. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param fromThis       - search for sold price at or above this
	 * @param toThis         - search for sold price at or below this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findBySoldPrice(ArrayList<Vehicle> inputArrayList, BigDecimal fromThis,
			BigDecimal toThis) {
		if (toThis.compareTo(fromThis) < 0) {
			// Values are reversed, so call the method in the correct order.
			return findByListPrice(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (tempVehicle.getSoldPrice().compareTo(fromThis) >= 0
					&& tempVehicle.getSoldPrice().compareTo(toThis) <= 0) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByDateSold searches the inventory for a specific date sold and
	 * returns an ArrayList of vehicles it finds.
	 * 
	 * @param This - search for this date values
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDateSold(LocalDate This) {
		return findByDateSold(This, This);
	}

	/**
	 * Method findByDateSold searches the inventory for dates sold between fromThis
	 * and toThis (inclusive) and returns an ArrayList of vehicles it finds.
	 * 
	 * @param fromThis - search for date values at or after this
	 * @param toThis   - search for date values at or before this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDateSold(LocalDate fromThis, LocalDate toThis) {
		if (toThis.isBefore(fromThis)) {
			// Values are reversed, so call the method in the correct order.
			return findByDateSold(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if ((tempVehicle.getDateSold().isBefore(fromThis) || tempVehicle.getDateSold().isEqual(fromThis))
					&& (tempVehicle.getDateSold().isBefore(toThis) || tempVehicle.getDateSold().isEqual(toThis))) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findByDateSold searches the inputArrayList for a specific date sold
	 * and returns an ArrayList of vehicles it finds. For nested searches, searches
	 * inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param This           - search for this date values
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDateSold(ArrayList<Vehicle> inputArrayList, LocalDate This) {
		return findByDateSold(inputArrayList, This, This);
	}

	/**
	 * Method findByDateSold searches the inputArrayList for dates sold between
	 * fromThis and toThis (inclusive) and returns an ArrayList of vehicles it
	 * finds. For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param fromThis       - search for date values at or after this
	 * @param toThis         - search for date values at or before this
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findByDateSold(ArrayList<Vehicle> inputArrayList, LocalDate fromThis, LocalDate toThis) {
		if (toThis.isBefore(fromThis)) {
			// Values are reversed, so call the method in the correct order.
			return findByDateSold(toThis, fromThis);
		}
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if ((tempVehicle.getDateSold().isBefore(fromThis) || tempVehicle.getDateSold().isEqual(fromThis))
					&& (tempVehicle.getDateSold().isBefore(toThis) || tempVehicle.getDateSold().isEqual(toThis))) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findBySoldToCustomer searches the inventory for vehicles sold to a
	 * specific customer ID number and returns an ArrayList of vehicles it finds.
	 * 
	 * @param ID - search for this customer ID number
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findBySoldToCustomer(long ID) {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : this.inventory) {
			if (tempVehicle.getSoldToCustomer() == ID) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method findBySoldToCustomer searches the inputArrayList for vehicles sold to
	 * a specific customer ID number and returns an ArrayList of vehicles it finds.
	 * For nested searches, searches inputArrayList.
	 * 
	 * @param inputArrayList - ArrayList to search.
	 * @param ID             - search for this customer ID number
	 * @return ArrayList<Vehicle> - an ArrayList of all matching Vehicle objects
	 */
	public ArrayList<Vehicle> findBySoldToCustomer(ArrayList<Vehicle> inputArrayList, long ID) {
		ArrayList<Vehicle> results = new ArrayList<Vehicle>();
		for (Vehicle tempVehicle : inputArrayList) {
			if (tempVehicle.getSoldToCustomer() == ID) {
				results.add(tempVehicle);
			}
		}
		return results;
	}

	/**
	 * Method sortByVIN sorts an ArrayList of Vehicle by VIN as specified by
	 * alphabeticalOrder. If alphabeticalOrder is true: Bubble sort from in
	 * alphabetical order. If alphabeticalOrder is false: Bubble sort from in
	 * reverse alphabetical order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByVIN(ArrayList<Vehicle> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getVin();
				String current = inputList.get(in).getVin();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByMake sorts an ArrayList of Vehicle by make as specified by
	 * alphabeticalOrder. If alphabeticalOrder is true: Bubble sort from in
	 * alphabetical order. If alphabeticalOrder is false: Bubble sort from in
	 * reverse alphabetical order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByMake(ArrayList<Vehicle> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getMake();
				String current = inputList.get(in).getMake();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByModel sorts an ArrayList of Vehicle by model as specified by
	 * alphabeticalOrder. If alphabeticalOrder is true: Bubble sort from in
	 * alphabetical order. If alphabeticalOrder is false: Bubble sort from in
	 * reverse alphabetical order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByModel(ArrayList<Vehicle> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getModel();
				String current = inputList.get(in).getModel();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByType sorts an ArrayList of Vehicle by type as specified by
	 * alphabeticalOrder. If alphabeticalOrder is true: Bubble sort from in
	 * alphabetical order. If alphabeticalOrder is false: Bubble sort from in
	 * reverse alphabetical order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByType(ArrayList<Vehicle> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getType();
				String current = inputList.get(in).getType();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByYear sorts an ArrayList of Vehicle by year made depending on
	 * leastToGreatest. If leastToGreatest is true: Bubble sort from least to
	 * greatest. If leastToGreatest is false: Bubble sort from greatest to least.
	 * 
	 * @param inputList       - the list to be sorted
	 * @param leastToGreatest - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByYear(ArrayList<Vehicle> inputList, boolean leastToGreatest) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ((leastToGreatest && inputList.get(in - 1).getYear() > inputList.get(in).getYear())
						|| (!leastToGreatest && inputList.get(in - 1).getYear() < inputList.get(in).getYear())) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByColor sorts an ArrayList of Vehicle by color as specified by
	 * alphabeticalOrder. If alphabeticalOrder is true: Bubble sort from in
	 * alphabetical order. If alphabeticalOrder is false: Bubble sort from in
	 * reverse alphabetical order.
	 * 
	 * @param inputList         - the list to be sorted
	 * @param alphabeticalOrder - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByColor(ArrayList<Vehicle> inputList, boolean alphabeticalOrder) {
		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				String previous = inputList.get(in - 1).getColor();
				String current = inputList.get(in).getColor();
				// Depending on direction, check to see if a swap is necessary.
				if ((alphabeticalOrder && previous.compareToIgnoreCase(current) < 0)
						|| (!alphabeticalOrder && previous.compareToIgnoreCase(current) > 0)) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByOdometer sorts an ArrayList of Vehicle by odometer reading
	 * depending on leastToGreatest. If leastToGreatest is true: Bubble sort from
	 * least to greatest. If leastToGreatest is false: Bubble sort from greatest to
	 * least.
	 * 
	 * @param inputList       - the list to be sorted
	 * @param leastToGreatest - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByOdometer(ArrayList<Vehicle> inputList, boolean leastToGreatest) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ((leastToGreatest && inputList.get(in - 1).getOdometer() > inputList.get(in).getOdometer())
						|| (!leastToGreatest
								&& inputList.get(in - 1).getOdometer() < inputList.get(in).getOdometer())) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByDealerPurchased sorts an ArrayList of Vehicle by the date the
	 * dealer purchased the vehicle depending on firstToLast. If firstToLast is
	 * true: Bubble sort from the earliest date to the latest date. If firstToLast
	 * is false: Bubble sort from the latest date to the earliest date.
	 * 
	 * @param inputList   - the list to be sorted
	 * @param firstToLast - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByDealerPurchased(ArrayList<Vehicle> inputList, boolean firstToLast) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				LocalDate previous = inputList.get(in - 1).getDealerPurchased();
				LocalDate current = inputList.get(in).getDealerPurchased();
				// Depending on direction, check to see if a swap is necessary.
				if ((firstToLast && previous.isAfter(current)) || (!firstToLast && previous.isBefore(current))) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByDealerPurchasedPrice sorts an ArrayList of Vehicle by the dealer
	 * purchase price of the vehicle depending on leastToGreatest. If
	 * leastToGreatest is true: Bubble sort from least to greatest. If
	 * leastToGreatest is false: Bubble sort from greatest to least.
	 * 
	 * @param inputList       - the list to be sorted
	 * @param leastToGreatest - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByDealerPurchasedPrice(ArrayList<Vehicle> inputList, boolean firstToLast) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				BigDecimal previous = inputList.get(in - 1).getDealerPurchasePrice();
				BigDecimal current = inputList.get(in).getDealerPurchasePrice();
				// Depending on direction, check to see if a swap is necessary.
				if ((firstToLast && (previous.compareTo(current)) > 0)
						|| (!firstToLast && (previous.compareTo(current) < 0))) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByRecommendedPrice sorts an ArrayList of Vehicle by the
	 * recommended price of the vehicle depending on leastToGreatest. If
	 * leastToGreatest is true: Bubble sort from least to greatest. If
	 * leastToGreatest is false: Bubble sort from greatest to least.
	 * 
	 * @param inputList       - the list to be sorted
	 * @param leastToGreatest - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByRecommendedPrice(ArrayList<Vehicle> inputList, boolean firstToLast) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				BigDecimal previous = inputList.get(in - 1).getRecommendedPrice();
				BigDecimal current = inputList.get(in).getRecommendedPrice();
				// Depending on direction, check to see if a swap is necessary.
				if ((firstToLast && (previous.compareTo(current)) > 0)
						|| (!firstToLast && (previous.compareTo(current) < 0))) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByListPrice sorts an ArrayList of Vehicle by the list price of the
	 * vehicle depending on leastToGreatest. If leastToGreatest is true: Bubble sort
	 * from least to greatest. If leastToGreatest is false: Bubble sort from
	 * greatest to least.
	 * 
	 * @param inputList       - the list to be sorted
	 * @param leastToGreatest - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByListPrice(ArrayList<Vehicle> inputList, boolean firstToLast) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				BigDecimal previous = inputList.get(in - 1).getListPrice();
				BigDecimal current = inputList.get(in).getListPrice();
				// Depending on direction, check to see if a swap is necessary.
				if ((firstToLast && (previous.compareTo(current)) > 0)
						|| (!firstToLast && (previous.compareTo(current) < 0))) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortByDateSold sorts an ArrayList of Vehicle by the date the vehicle
	 * was sold depending on firstToLast. If firstToLast is true: Bubble sort from
	 * the earliest date to the latest date. If firstToLast is false: Bubble sort
	 * from the latest date to the earliest date.
	 * 
	 * @param inputList   - the list to be sorted
	 * @param firstToLast - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortByDateSold(ArrayList<Vehicle> inputList, boolean firstToLast) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				LocalDate previous = inputList.get(in - 1).getDateSold();
				LocalDate current = inputList.get(in).getDateSold();
				// Depending on direction, check to see if a swap is necessary.
				if ((firstToLast && previous.isAfter(current)) || (!firstToLast && previous.isBefore(current))) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method sortBySoldToCustomer sorts an ArrayList of Vehicle by the customer ID
	 * depending on leastToGreatest. If leastToGreatest is true: Bubble sort from
	 * least to greatest. If leastToGreatest is false: Bubble sort from greatest to
	 * least.
	 * 
	 * @param inputList       - the list to be sorted
	 * @param leastToGreatest - sort order
	 * @return ArrayList<Vehicle> - the sorted list
	 */
	public ArrayList<Vehicle> sortBySoldToCustomer(ArrayList<Vehicle> inputList, boolean leastToGreatest) {

		for (int out = 0; out < inputList.size(); out++) {
			boolean anySwaps = false;
			// Loop through the array and swap any elements which are out of order.
			for (int in = 1; in < inputList.size() - 1; in++) {
				// Depending on direction, check to see if a swap is necessary.
				if ((leastToGreatest
						&& inputList.get(in - 1).getSoldToCustomer() > inputList.get(in).getSoldToCustomer())
						|| (!leastToGreatest
								&& inputList.get(in - 1).getSoldToCustomer() < inputList.get(in).getSoldToCustomer())) {
					Vehicle temp = inputList.get(in - 1);
					inputList.set(in - 1, inputList.get(in));
					inputList.set(in, temp);
					anySwaps = true;
				}
			}
			if (!anySwaps) {
				// Exit the loop if the last search through the array did not result in any
				// swaps.
				break;
			}
		}
		return inputList;
	}

	/**
	 * Method saveInventory checks to see if the inventoryFileName is writeable and
	 * then replaces the entire contents of that file with all the vehicles in the
	 * current inventory attribute. Each vehicle is stored on a separate line in the
	 * file. It returns false if the file was not valid or writable
	 * 
	 * @param inventoryFileName
	 * @return
	 */
	private boolean saveInventory(String inventoryFileName) {
		// Check to see if the inventory data file is an existing file.
		File inventoryFile = new File(inventoryFileName);
		if (!inventoryFile.isFile() && !inventoryFile.canWrite()) {
			System.out.println(inventoryFileName + " does not exist or is not accessible for writing.");
			FileReadWrite.createOutputFile(inventoryFileName);
		}
		String fileOutputString = "";
		for (Vehicle tempVehicle : inventory) {
			fileOutputString += tempVehicle.toDataFileString() + System.lineSeparator();
		}
		return FileReadWrite.writeToFile(inventoryFile, fileOutputString);
	}

	/**
	 * method restoreInventory checks if the file indicated by the string
	 * inventoryFileName is present and readable then populates the inventory
	 * attribute with the values found in the file. It returns false if the file
	 * does not exist or is not readable. The console will show the number of errors
	 * encountered reading the file.
	 * 
	 * @param inventoryFileName
	 * @return boolean
	 */
	private boolean restoreInventory(String inventoryFileName) {
		ArrayList<String> fileStringArray = new ArrayList<String>();
		File inventoryFile = new File(inventoryFileName);
		if (inventoryFile.isFile() && inventoryFile.canRead()) {
			fileStringArray = FileReadWrite.readFileToStringList(inventoryFile.getName());
		} else {
			System.out.println(inventoryFileName + " does not exist or is not accessible for reading.");
			return false;
		}
		Vehicle readVehicle = new Vehicle();
		int errorCount = 0;
		for (int index = 0; index < fileStringArray.size(); index++) {
			if (!readVehicle.parseVehicleString(fileStringArray.get(index))) {
				errorCount++;
			} else {
				this.inventory.add(readVehicle);
				readVehicle = new Vehicle();
			}
		}
		System.out.println(inventoryFileName + " import produced " + errorCount + " errors.");
		return true;

	}

}
