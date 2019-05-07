# project-two CINEMA

To setup the database credentials do the following:
	
	1. go to src/main/resources
	2. add a file database.properties
	3. add the following properties there

		hibernate.connection.url = jdbc:oracle:thin:@END_POINT_TO_THE_DB:1521:ORCL
		hibernate.connection.username = YOUR_USERNAME
		hibernate.connection.password = YOUR_PASSWORD

	4. DONE! Now hou should be able to connect to your database.