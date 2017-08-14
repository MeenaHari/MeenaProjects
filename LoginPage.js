 describe("User Registration",function(){

	 it("Page title should be",function(){
		 browser.get("http://localhost:8080/#!/login");
		 browser.getTitle().then(function(text){       // get title of the page and pass the value in the function 
		 console.log(text);                            // to get the title in the console
		 expect(text).toEqual("AngularJS User Registration and Login Example");	


		 });
	 });  

	 // Login Page 
	 it("username",function(){
		 browser.get("http://localhost:8080/#!/login");
		 // verify heading login
		 element(by.xpath("/html/body/div[1]/div/div/div/div/h2")).getText().then(function(text){       
			 console.log(text);  // to get the heading in the console

		 });
		 //enter User name
		 element(by.model("vm.username")).sendKeys("Meenakshi");	
		 browser.sleep(3000);

		 // enter password
		 element(by.model("vm.password")).sendKeys("Meenakshi");	
		 browser.sleep(3000);

		 //click login button
		 element(by.buttonText("Login")).click();

	 });

 });