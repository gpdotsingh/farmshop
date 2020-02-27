Download project from git by url https://github.com/gpdotsingh/tmClient.git 	

=================================================================================================

Steps to open project 
	
	
Clone this repo and start application by


# Build:

    mvn clean install 

# Run

    mvn spring-boot:run
	
=================================================================================================	
Note:=>	Only start Client and tm server application using the command. If the maven is not installed
then you can run application also by java -jar jarname.
Angular files are converted into static files and same is present in client code.
=================================================================================================

Project run on port 8081 , 8080  and 9001 

=================================================================================================


Hit URL 

http://localhost:8081/ after starting all the server 

Two tabs will appear Transaction Management and Request Monitoring

	Transaction Management tab functionality
	==============================
	
	Enter start date by which you want to see the transaction
	Enter end date till which you want to see the transaction
	
	On click of key inside origin an backend call from front end to tm client which will go to fetch matched names.
	
	FE will invoke URL below pattern to fetch distinct name to client server
	 http://localhost:8081/client/name?name={characters} which internally call server
	 
	 http://localhost:8080/api/name?name={characters}
	
	Select desired name
	
	Click search file button and internally it call from FE
	    http://localhost:8081/api/tm?startDate={startDate}&endtDate={endtDate}&name={name} 
	
	From Client it will call Server 
	
		http://localhost:8080/api/tm?startDate={startDate}&endtDate={endtDate}&name={name} 

	
	Server will bind result and return details which will contain 
	
	id
	email
    name
    transactioId
    transactionTime
    verifiedUser
	
	Request Monitoring functionality
	=================================
	
	It will go to http://localhost:8081/api/actuator/getHealthMetrics
	
	It contains Request Statistics
		which include of
			maxTime : Maximum time took by any request
			minTime : Minimum time took by any request
			totalCount : Total number of request
			statusOKCount : Number of sucessfull request
			status4XXCount : Number of request with status like 4
			status5XXCount : Number of request with status like 5
			
			
		and Request Status Code with its count.
		
	 npm install --save-dev @angular-devkit/build-angular	
	 
	 
## Functionality.

    Client is communicating with tmServer and for communicating securly they are using Oauth
    client credential authorization type.
    
    Server contain the in memory database 	 