# ServletDBCommunication-project
The details entered in  this form will be posted in  the database. 
Servlet and database communication will happen.

1. Created a register.html form where user fills the data.

2. Using the request object we will get the detials filled by the user.

3. Later we will establish a connection. to establish a connection we need to add the MySQL connector jar to the build path and then to the Deployment assembly(WEB-INF/lib).

4. In web container, auto loading will not happen, we need to load the class manually using Class.forName("com.mysql.cj.jdbc.Driver")

5. Using response object, It will display Registration Successful in green color if data is inserted in database if not it will display Registration Failed in Red color and Register again link 
