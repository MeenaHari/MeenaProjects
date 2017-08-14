       
            describe("User Registration",function(){

                it("Page title should be",function(){
                     browser.get("http://localhost:8080/#!/login");
                     browser.getTitle().then(function(text){       // get title of the page and pass the value in the function 
                     console.log(text);                // to get the title in the console
                     expect(text).toEqual("AngularJS User Registration and Login Example");	
                     	
                   
                         });
                }); 
                //Steps to register cancel the registration
               it("register",function(){
                    browser.get("http://localhost:8080/#!/login");
                   
                    // click Register link
                    element(by.linkText("Register")).click();
                   
         			 browser.sleep(3000);
         			
         			 //verify the heading Register
                     element(by.xpath("/html/body/div[1]/div/div/div/div/h2")).getText().then(function(text){       
                     console.log(text);  // to get the heading in the console
                        
                          });
                     
         			// enter first name
         			element(by.model("vm.user.firstName")).sendKeys("Meenakshi");	
        		//	 browser.sleep(3000);
        			 
         		// enter last name
         			element(by.model("vm.user.lastName")).sendKeys("Thiyagarajan");	
        		//	 browser.sleep(3000);
         			
         		// enter user name
         			element(by.model("vm.user.username")).sendKeys("Meena");	
        		//	 browser.sleep(3000);
         		// enter password
         			element(by.model("vm.user.password")).sendKeys("Meena");	
        		//	 browser.sleep(3000);
         			
        			 //click Register button
        			element(by.buttonText("Register")).click();
        			browser.sleep(3000);
        			// click Cancel link
                   // element(by.linkText("Cancel")).click();
                    
                    //After clicking register verify web page directs to login page and verify the title
                    browser.getTitle().then(function(text){       // get title of the page and pass the value in the function 
                    console.log(text);                // to get the title in the console
                    expect(text).toEqual("AngularJS User Registration and Login Example");	
             	
           
                      });
                    browser.sleep(3000);
                    // After successful registration, it will display a flash message "Registration successful"
                   element(by.binding("flash.message")).getText().then(function(data){
                    console.log("result:"+data);
                    expect(data).toEqual("Registration successful");	
                  	   
             	       });
                   // browser.sleep(3000);
                         
             });
             //Steps to cancel the registration
               it("register",function(){
                    browser.get("http://localhost:8080/#!/login");
                   
                    // click Register link
                    element(by.linkText("Register")).click();
                   
         			 browser.sleep(3000);
         			
         			 //verify the heading Register
                     element(by.xpath("/html/body/div[1]/div/div/div/div/h2")).getText().then(function(text){       
                     console.log(text);  // to get the heading in the console
                        
                          });
                     
         			// enter first name
         			element(by.model("vm.user.firstName")).sendKeys("Mee");	
        		//	 browser.sleep(3000);
        			 
         		// enter last name
         			element(by.model("vm.user.lastName")).sendKeys("Thiy");	
        		//	 browser.sleep(3000);
         			
         		// enter user name
         			element(by.model("vm.user.username")).sendKeys("Mee");	
        		//	 browser.sleep(3000);
         		// enter password
         			element(by.model("vm.user.password")).sendKeys("Meena");	
        		//	 browser.sleep(3000);
         			
        			 //click Register button
        			//element(by.buttonText("Register")).click();
        			//browser.sleep(3000);
        			// click Cancel link
                    element(by.linkText("Cancel")).click();
                    
                    //After clicking cancel verify web page directs to login page and verify the title
                    browser.getTitle().then(function(text){       // get title of the page and pass the value in the function 
                    console.log(text);                // to get the title in the console
                    expect(text).toEqual("AngularJS User Registration and Login Example");	
             	
           
                      });
                    browser.sleep(3000);
                    //element(by.binding("flash.message")).getText().then(function(data){
                   // console.log("result:"+data);
                  	   
             	    //   });
                    browser.sleep(3000);
                         
             });
               
    
             
 });