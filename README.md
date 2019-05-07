# project-two CINEMA

To setup the database credentials do the following:
	
	1. go to src/main/resources
	2. add a file database.properties
	3. add the following properties there

		hibernate.connection.url = jdbc:oracle:thin:@END_POINT_TO_THE_DB:1521:ORCL
		hibernate.connection.username = YOUR_USERNAME
		hibernate.connection.password = YOUR_PASSWORD

	4. DONE! Now you should be able to connect to your database.

HOW TO USE:

1. Clone the repository.
2. Create e branch of your own and work on that branch.
3. DO NOT MARGE TO MASTER.