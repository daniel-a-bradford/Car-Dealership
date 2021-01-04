# Car-Dealership
A virtual car dealership dynamic web project developed for an assignment at Claim Academy.

It allows customer sign up, sign in for customers and dealership employees. Customers can browse and purchase vehicles, update their account information, as well as view their purchase history. Employees can add vehicles and look at transaction history. Passwords, card numbers and cvv are stored in encrypted form. 

Data files in the project root folder entitled Inventory.txt, CustomerList.txt, and EmployeeList.txt need to be copied to wherever Tomcat expects data files. In my case, I copy them to the current user's desktop. Files are uploaded to the relative path in the server, but also to the project folder on my computer. Change the backup path in ImageUpload.java to set it to your project root\WebContent\images. If you need the data files again, InitCustomersAndVehicles and InitEmployees will re-create them in the project root folder.

To see dealer functions, log in as ms@carsrus.com with password "a"
To log in as a customer, log in as jdoe@me.com or janes@email.com with a password "p"

Enjoy!

Dan Bradford
