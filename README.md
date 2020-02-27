Download project from git by url https://github.com/gpdotsingh/farmshop.git 	

=================================================================================================

Steps to open project 
	
	
Clone this repo and start application by


# Build:

    mvn clean install 

# Run

    mvn spring-boot:run

=================================================================================================
	
Project run on port  8080  

=================================================================================================


URL used 

#####As a farmer, If you want to see an overview of your flock, with a JSON representation, using a HTTP REST service.
        http://localhost:8080/farmshop/flock
        Project is reading the input data from resources that is inputflock.xml, which is being used as stock accumulated by 
        farmer.


#####As a farmer and client of the webshop, If you want to see what stock of milk and wool is available.
        http://localhost:8080/farmshop/stock
        As a farmer if you want to see the stock you can hit the above URL, it will display te leftout stock
    
#####As a client of the webshop, If you want to be able to order milk or/and wool.    
        http://localhost:8080/farmshop/order
        As a client to place order send a post request to above URL with your orders in below format    
        
        {
        "customer": "Milk and Wool Trading Ltd",
        "order": {
        "milk": 90,
        "wool": 3
        }
        }
    
#####As a farmer, I'd like to see history of (fulfilled) orders    
        http://localhost:8080/farmshop/order
        To see the order history as client please use above URL

#####As a farmer if stock is completed and you want to refill the 
        http://localhost:8080/farmshop/stock 
        If stock is finished and you have a new stock and want to add to the left stock please post the above URL