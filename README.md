# SchoolReview <html>
Hello, My name is Zubas Bogdan and
I am using JAVA language, OOP techniques and  SQL  language knowledge in order to build a School Management System , 
where you can add, delete, modify students/ teachers, add classes and courses,search function and many more . 
I added a review based system for students where they can review any teacher by giving them a note(star) and 
optionally, a written review. You can log in as an user to add a review of a teacher, and logging in as an administrator 
gives you all the functions listed above including seeing all the reviews made by the users/students.

 The backend of this program is made with MySQL and the database is stored locally ! Please, before TESTING, you NEED TO CREATE
 a local database named "school" in your SQL, preferably with MySQL, then execute all these queries in order for the program 
 to WORK as it should :
 
  First:<br/><br/>
  -------Creating database:<br/>
"<i>CREATE DATABASE school</i>"</br><br/>
 Second:<br/><br/>
---------Creating all tables with all the values :<br/>
 ---------------------------Table users(storing all the users):
 
<i>CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user` varchar(50) DEFAULT NULL,
  `nume` varchar(50) DEFAULT NULL,
  `prenume` varchar(50) DEFAULT NULL,
  `parola` varchar(50) DEFAULT NULL);</i> <br/><br/>
  --------------------------------Table elevi(storing students):<br/>
  
  <i>CREATE TABLE `elevi` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `numele_elevului` varchar(100) NOT NULL,
  `prenumele_elevului` varchar(100) DEFAULT NULL,
  `clasa_elevului` char(3) NOT NULL);</i><br/><br/>
  -------------------------------------------Table profesori(storing teachers)<br/>
  
  <i>CREATE TABLE `profesori` (
  `id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `Nume` varchar(50) DEFAULT NULL,
  `Prenume` varchar(50) DEFAULT NULL,
  `Materie` varchar(50) DEFAULT NULL,
  `Nota` double DEFAULT NULL);</i><br/><br/>
  ------------------------------------------------------Table recenzii(storing reviews)<br/>
  
   CREATE TABLE `recenzii` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PROF` int(11) DEFAULT NULL,
  `Recenzie` varchar(1000) DEFAULT NULL,
  `nota` int(11) DEFAULT NULL,
  `User` varchar(50) DEFAULT NULL,
  `Data` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;<br/><br/>

Third:
------Now we have all tables, we just need to insert administrator login details to the users table so we can login as admin:<br/>

<i>insert into users(user,parola) values('administrator','administrator');</i><br/><br/>
 
 If all these are executed then you are good to go and start up the program ! Dont forget logging in as an administrator with these
 login details : <br/>
 
 USER : <i>administrator</i> <br/>
 PASS : <i>administrator</i> <br/><br/>
 
 AND THEN if you want to review a teacher you just added from the administrator panel, please register in the MAIN LOGIN PANEL and then LOG IN with those details !
 
  Thank you for taking your precious time reading this, Zubas Bogdan.
 
 <html>
