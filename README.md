# BankCardSimulation

## Description
(Watch the demo_video for demonstration)
The Bank Management System is a virtual simulation of a banking interface designed to mimic real-world banking operations. This application initiates with a login page that offers two options:

Sign In: Users can enter their card number and PIN to access their accounts.
Sign Up: New users can create an account by completing a three-page registration process that collects details like age, nationality, and income level.
Account Creation
Upon signing up, users are assigned a unique card number and PIN, which are stored in an SQL database for future authentication. After account creation, users are directed to a simulated ATM interface.

ATM Features
The ATM interface provides several banking options:

Deposit: Add funds to your account.
Withdrawal: Remove funds from your account.
Balance Enquiry: Check the current account balance.
Bank History: View recent transactions similar to a mini-statement.
PIN Change: Update your PIN for enhanced security.
Data Management
All transactions, including deposits and withdrawals, are tracked and recorded in the SQL database to ensure accurate financial records and user histor

##Installation:

JDK version required 19/17
MySQL community server (on macOS) and MySQL Workbench version 8.0.39


This project uses a two JAR files in /lib/ that must be manually installed into your local Maven repository. To install this JAR, run the following command:

mvn install:install-file -Dfile=path/to/your/file.jar -DgroupId=org.example -DartifactId=example-jar -Dversion=1.0 -Dpackaging=jar

Alternatively doing it manually on MacOS IntelliJ:  File => Project Structure => Libraries => add java => "choose on your local machine" 
