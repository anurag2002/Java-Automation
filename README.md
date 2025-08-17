# Java Automation
## Invoking Browser:- 
In Selenium, the methodName for different browser drivers are same, i.e., irrespective of browser driver, all have same method name.
```java
	BrowserDriver obj = new BrowserDriver();
	//BrowserDriver - this can be any browserDriver - ChromeDriver, SafariDriver, ChromiumDriver, etc.
```
Q. 	How can different classes for different browsers have same method name?\
All the different classes for different browser drivers implements the same interface - WebDriver interface. This WebDriver interface contains all the methods which is being used by all the classes implementing this WebDriver parent class. The WebDriver interface contains these methods with empty bodies and the classes implementing this interface can define the methods accordingly.
		
Documentation - https:www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/WebDriver.html

These browserDriver classes can also have their own driver methods, which will be specific to their class. If we try to call these methods using the objects of another driver class, then it will throw error.
To make these methods invisible so that the object of the driver class can only access the common methods for all the browserDrivers or the methods defined in the WebDriver Interface, we create the object as - 
```java	
	WebDriver driver = new BrowserDriver();
	eg: WebDriver driver = new ChromeDriver();
```
By default selenium does not have direct access to invoke any browser. So, selenium invokes the driver file (chromedriver.exe in case of Chrome - Windows). This exe file is a third party executable file. So the executable file will interprete the code and then invoke the browser for us. There is a middle man proxy and selenium is not directly invoking the browser. 

How to invoke the exe file before executing any selenium test case: 
	1. Writing steps to invoke the chrome driver.
	2. Using the inbuilt library - Selenium Manager - This library will check if any steps are defined for invoking the chrome driver or not. If no steps will be found, then it will connect to the web and download the appropriate chrome driver accordingly and place the right path.
	
To manually set the path for the driver, add the driver name and path in the System.setProperty(String key, String value) method. This is the method were we set the global level properties and the script knows where to look for the driver. In key we write - webdriver.chrome.driver. In the value part, we have to enter the path of the externally downloaded exe file.

The difference between using the Selenium Manager and defining the path manually is, once we define the path of the driver exe file, selenium manager will not be in active state and will be disabled. So it will directly invoke the chrome driver and that chrome driver will be responsible for invoking the browser.

**Pros and Cons of using Selenium Manager:**
1. Selenium Manager will need to connect to the web and then download the driver and after that it will invoke the driver, which might take few seconds. If the driver is already present in the local and we manually define the path, then it will execute comparively faster.

**How to hit any website using Selenium:**
We can hit any url using the Selenium script. This is done with the help of get function. This get function contains the url of the website to be opened.

- get(url) - to open any url
- getTitle() - to get the title of the page
- getCurrentUrl() - to get the current URL of the page
- close() - to close the current window
- quit() - to close the current window along with all the associated windows

**Diff between close() and quit() -**
In close() only the original window which was opened due to automation/the current focused window will close. In quit() all the windows associated with the original window will also close.

## Locators in Selenium:
To perform operations in automation, like typing, clicking on HTML elements, we use locators.\
The locators are the way to identify an HTML element on a webpage. Selenium WebDriver uses any of the below locators to identify the element on the page and perform the action.
- ID - driver.findElement(By.id("unique_element_id")).operationToBePerformed(arguments(if any));
- Xpath - driver.findElement(By.xpath("//Tagname[@attribute='value']")).operationToBePerformed(arguments(if any));
- CSS Selector - driver.findElement(By.cssSelector("tagname.className")).operationToBePerformed(arguments(if any));
- name - driver.findElement(By.name("unique_element_id")).operationToBePerformed(arguments(if any));
- Class Name - driver.findElement(By.className("unique_element_id")).operationToBePerformed(arguments(if any));
- Tag Name - driver.findElement(By.tagName(tag_name)).operation_to_be_performed(arguments(if any));
- Link Text - driver.findElement(By.linkText("text_of_link")).operationToBePerformed(arguments(if any));
- Partial Link Text

**How to use Locators?**
1. Invoke the browser drivers in code
2. Hit the URL where automation is to be done
3. Get the ID of the field on which operation is to be performed
    - Right click on the element on which operation is to be performed
    - Click on inspect option.
    - In the HTML snippet of the element, find any unique element in the snippet which can be used
    - Enter the element in the findElement method.
4. Enter the operation which needs to be performed on the object selected.\
	Operations which can be performed:
    - sendKeys(string to be inserted)
    - click()
    - getText()
    - clear()
		
## CSS selector and XPath - 
- If we have a class name and to derive CSS from a class name, we can write it as -> tagname.className -> this becomes a CSS selector
- If we have an id and to derive CSS from an id, we can write it as -> tagname#id -> this becomes a CSS selector
- If we don't have class name or id then we can derive CSS as -> tagname[attribute='value'] -> this becomes a CSS selector
- Generally searches from top left
- if there are multiple tags with same attribute, then we can add index at the end of the xpath on which operation is to be performed.
```java
tagname[attribute='value']:nth-child(index)
```
Note: There should be no space between the class name. If there are spaces, then those spaces need to be replaced with a '.'

## Implicit Wait in Selenium:
Selenium performs operation very quickly in milliseconds. So, if there is any delay in the response of the operation performed, then it will throw error. This is due non-synchronized operations. So to synchronize these operation we add an implicit wait. This will add a wait time (as per the time added in arguments) after the operation is performed.
Syntax: 
```java
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(value));
```
- driver.manage() -> managing the driver settings
- We have to enter the number of seconds to wait using the method ofSeconds present in the duration class

## XPath: 
```java
//TagName[@attribute='value']
```

if there are multiple tags with same attribute, then we can add index at the end of the xpath on which operation is to be performed.
```java
//TagName[@attribute='value'][index]
```

The Xpath and CSS index might be different as Xpath can ignore the hidden attributes, if any but CSS would consider those as well.

## Parent-to-Child tag traversal Technique:
If we encounter any element which only has a tagname and no name or classname or id or any unique identification, then we can use Parent-to-Child tag traversal Technique.\
Syntax:

		//parentTag/childTag[index(if any)] -> XPath
		parentTag childTag -> CSS

## Thread Sleep in JAVA:
Since nowadays most of the websites are being made in Angular and React where a single page websites are being made and on clicking any button, the link doesn't change but a new screen is rendered. Most of the time, selenium will encounter clicking error due to this. This can be handled by adding Thread.sleep(time(in ms)) to wait for 1 sec so that the page gets rendered completely.

### Generating CSS Selectors based on Regular Expression:
Lets say if there are some elements whose class name or id might change everytime the screen renders/refreshes but in that a part of the name is common always, then in that case we can use the Regular expression to find the element and perform operation on it.

	TagName[attribute* = 'value'] -> CSS Selector
	//TagName[contains(@attribute,'value')] -> XPath
	
If we want to check assertion, i.e., whether a particular statement or line has appeared or not, then in that case we use Assert class methods, which throw error if assertion is false and will continue with the operation if it is true.

### Thread.sleep vs wait:
wait works a particular element is not present currently in the screen. If there is any element which has the same locator as mentioned, then it will try to perform the desired operation on that. So, when we know that the locator is already present but it would show up after rendering is done, in that case we use Thread.sleep() and when the element is not present and we want selenium to wait till the element appears, then we use wait()\
Syntax: 

		Assert.assertEquals(boolean actual, boolean expected);
		
To get the xpath for any button based on the text:

		//tagName[text()='value']
			-> if we use * instead of tagName, it will search for all the occurance
			
### Absolute xpath and Relative xpath:
- Whenever we start traversing in the xpath from the starting, i.e., from HTML and then traverse to the particular child element, this is called Absolute xpath. It starts with a single '/'.
- Whenever we start traversing in the xpath from any point, i.e., not from HTML but from any point and then traverse to the particular child element, this is called Relative xpath. It starts with a double '//'

### Parent to Child to Sibiling-child Traversing:
- If we want to traverse from a parent to child xpath, then we can use // and enter the tags where the operation is to be performed, in parent to child manner.
- If we want to select the element which is next to any particular element, then we can traverse the sibiling of that element by adding '/following-sibiling::' to it and then adding the tagname
	Syntax:
 
			//parentTag/childTag/following-sibiling::sibilingTag[index(if any)]
			
## Child to Parent Traversing:
If we want to traverse from child to parent then we have to use '/parent::'\
	Syntax:
 
			//childTag/parent::parentTag
			
Note: 
1. These traversing techniques cannot be used in CSS selector.
2. CSS is faster than xpath
	
### Window activities in Selenium
- To maximize the screen size - driver.manage().window().maximize();
- To open any URL - driver.get(URL);
- To navigate to any other URL - driver.navigate().to(URL);
- To navigate back - driver.navigate().back();
- To navigate forward - driver.navigate().forward();

**Note: Diff between navigate().to(URL) and get(URL):**\
- In get(URL), selenium will wait till all the components of the page are loaded before performing the next operation. It is synchronized.
- In navigate().to(URL), selenium will not wait and perform the next operation immediately. It is not synchronized.

## Dropdown Handling
### Handling Static dropdowns with Select:
Static dropdowns are the dropdowns whose list does not change and remains same. To select the dropdown value using selenium, we have to create an object of Select class and then pass it the WebElement of the dropdown and then choose the appropriate operation.

**Syntax for WebElement:**

		WebElement staticDropDown = driver.findElement(By.locator(value));
**Syntax for Select object:**

		Select dropdown = new Select(WebElement);		
Note: WebElement needs to be defined first

**Operations which can be performed by Select object:**
- selectByIndex(index); => selects the entry by index
- selectByVisibleText(value); => selects by the value which is being displayed in the dropdown.
- selectByValue(value); => select by the value defined in the HTML element
- getFirstSelectedOption(); => returns the current selected option
	
### Handling Dynamic Dropdowns:
Dynamic dropdowns are the ones whose data loads only after the attribute/field is clicked. The problem with dynamic dropdown is if we click on multiple dropdowns and then try to find the value using XPath then it will return multiple attributes. So to select a specific attribute out of multiple attribute, we need to give the index of the attribute which we need to select.

**Xpath Syntax with index for dynamic dropdown:**

		//(a[@value='<value>'])[index] - this should be used if there are more than one occurance of the nodes.
		here the index starts from 1.		
If we don't want to use indexes, then we can use parent to child traversing.

		//parent_tagName[@parent_attribute='<value>'] //child_tagName[@child_attribute='<value>']
Using this, we can handle multiple instances in a page.\
If we use only the child xpath, then selenium will search for all the instances of the particular xpath. If we want to restrict the searching of child xpath, then we will need to add the parent xpath of the particular child. In this way, the scope of the xpath will be restricted and it will not search the complete webpage for the particular element.

### Handling Autosuggestive Dropdowns:
To handle autosuggestive dropdowns (dropdowns in which the entries change as per the text written at search), we need to fetch the common attribute for each element of the dropdown. To acheive this, we find the elements and put them in the list. Then, find the element to be used and once the element is found, select the element and break the loop.\
In this case, we would use: findElements - whose return type is a List of WebElements.

## Checkbox Handling
We need to find the checkbox element and then use the click method on it to select the checkbox. To check if the checkbox is selected or not, then we can use a method 'isSelected()' which returns the boolean value. \
Generally for the checkbox, the type remains same - 'checkbox'. With this, we can get the total count of the checkbox.\
Note: This can be different for other websites.

## Assertions in Selenium:
To use assertions, we need to use testing frameworks like TestNG or JENet. Assertions helps to handle if the result returned by Selenium is expected or not. If not, then it fails the test. Assertions are used to add validations in test.
```
Class Name - Assert

For True False validations:
Assert.assertTrue(true);  --> //In this, selenium will expect the condition given to return true. If false is returned, it will fail the test.
Assert.assertFalse(false); --> //In this, selenium will expect the condition given to return f. If true is returned, it will fail the test.
Assert.assertEquals(actual, expected); --> //It compares the value given with the value provided by Selenium. If true is returned, test is passed, else test will fail.

//TestNG is a testing framework used to build automation frameworks.
```

| Return Value | True | False |
| ------------ | ---- | -----|
| Assert.assertTrue(true) | Case Passed | Case Failed |
| Assert.assertFalse(false) | Case Failed | Case Passed |

Better to use Assertions than printing and checking the value manually.

## Calendar handling:
Calendar can be selected in the similar way as rest other elements are selected. To select the current date, look for a unique class/id which can be used.

### To check if any element is enabled/disabled:
**isEnabled()** -> this method is used to check if a particular element is enabled or not. This method is provided by selenium but is not working as expected with the current 	HTML. This means, even if the element is disabled, this method will return as true. This is because, on sites, the elements which look like they are disabled, are actually enabled. When clicked on those elements it will get enabled and it can be used. So the isEnabled() function returns a true value.

To handle this, we can check the attribute which is getting changed when the particular element is getting enabled and disabled and then use it to check if the element is enabled or disabled. This can be achieved by fetching the attribute of the particular element by using the getDomAttribute(..args) method.

**Syntax:**

		driver.findElement(By.locator(elementId)).getDomAttribute(attributeName);
		
## Alerts Handling:
Alerts can be handled in the similar way other HTML components are handled in a webpage. To handle this, we can use a class called alert().

Syntax:

		driver.switchTo().alert()

Alert class also has some other methods with it using which we can perform different operation like:
- driver.switchTo().alert().accept()  --> to accept the alert
- driver.switchTo().alert().getText() --> to print any text present in the alert
- driver.switchTo().alert().dismiss() --> to cancel the alert

**Note:** 
- Alert class is a wrapper class.
- Alert class cannot handle all types of pop-ups. Alert pop-ups like Window Authentication Popups which ask for username, password cannot be handled using the above said method. These are handled by tool called AutoIT.

### Naming Convention for JAVA code:
1. class name - first letter CAPS
2. variable - camelCase

## Indexing with Xpath
Always use indexing with the parent element and then use Parent-Child traversal to traverse through all the elements

## Synchronization in Selenium
Whenever we need Selenium to wait for few seconds before performing any operation due to the page not loaded completly or the elements take time to be visible, then we use some kinds of waits. This is called synchronization in selenium.\
There are 4 ways to achieve this:
- Implicit Wait - Defined Globally
- Explicit Wait - Targets Specific Element
- Thread.sleep - hold execution for some time
- Fluent Wait - Type of Explicit Wait
	
### Implicit Wait:
When we define the wait globally so that the script waits for a particular time period before throwing any error. then this wait is called Implicit Wait.

**Advantages:**
- Needs to be declared just once and will be applied to whole script.
- It keeps listening to the DOM of the web browser. Means if the wait is defined for 5 secs and the result is found in 2 secs then it will not wait for 5 secs. It will go for executing the next step.

**Disadvantages:**
- When we define the implicit wait for as 5 secs but another operation takes more than 5 secs to give result (due to large data) then it will throw an error. If we increase the wait for this case, then it will not be able to catch the perfomance issue for the first case.
- Also the test execution time will gradually increase

**Syntax:**
```java
    driver.manage().timeouts().implicitlyWait(<time>,TimeUnit.SECONDS);
```

### Explicit Wait:
When we want to target a specific element for wait, then this is called Explicit Wait. It will not have effect on any other element. This is used with the help of WebDriverWait class.

**Advantages:**
- Applied only targeted elements without any issue in Performance.

**Disadvantages:**
- Need to write more code.

**Syntax:**
```java
		WebDriverWait w = new WebDriverWait(driver,<no_of_seconds_to_wait>);
		w.until(ExpectedCondition.<method>(<..arguments>));
```
Webdriverwait is a class to use the explicit wait and the object of this class uses multiple methods. \
To add wait until the element is visible, we use visibilityOfElementLocatedBy(locator) method.
		

### Thread.sleep:
When we want to hold the execution for a particular time period, then we use Thread.sleep. This is the part of JAVA. This is not recommended to use.

### Fluent Wait:
Fluent wait finds the web eleemnt repeatedly at regular intervals of time until thetimeout or till the object gets found. Unlike WebDriver wait, we need to build customized wait methods based on Condition. Both WebDriverWait and FluentWait classes implement Wait Interface.

**Diff btw WebDriverWait and FluentWait**
- WebDriverWait 
    - Keeps on listening on DOM/Browser for the time provided, even if the object is not found. 
	- It will go to the next step only when the element is found.
	- It will keep on listening every millisecond.
- FluentWait 
    - For FluentWait we need to give a polling time, so that it only monitors at regular interval of time.
	- So instead of listening to the DOM every millisecond, it will listen once the polling time has elapsed.  

They both will exit once the element is found.

**Disadvantages:**
- unlike explicit wait, we need to custom build methods as per the requirement/conditions.

**Syntax:**
```java
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
```
**Note:** 
- we may or may not use the ignoring method.
- WebDriverWait and FluentWait are two seperate classes implementing same interface - Wait

```java
//Creating object of class wait for implementing Fluent Wait

Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);

//Defining on what object we need to put timeout and on what object we need to put polling
//Adding until so that it will wait till the element is found. Since we don't have a pre-defined method, so we need to custom build one.
//wait.until is a method of the interface Wait

WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	//This will wait until the method 'apply' will return a type WebElement. It also takes an input of WebDriver.
	//This is the main function/method

	public WebElement apply(WebDriver driver) {

		//This will check if the element is visible on the screen or not. Only return the web element when the element is visible on the screen
		//Else will return null which will show that the element is not yet found and we need to wait.

		if(driver.findElement(By.xpath("//div[@id='finish'] //h4")).isDisplayed()) {
			return driver.findElement(By.xpath("//div[@id='finish'] //h4"));
		}
		else {
			return null;
		}
	}
});
```

## Handling mouse and keyboard interaction in Selenium:
For Handling mouse and keyboard interaction, we use Actions class. Using this, we can perform multiple operations like, moving mouse over a particular element, writing text in CAPS, right click on element, double click, drag and drop element, etc.

**Syntax:**

		Actions a = new Actions(driver);

Now object 'a' can use all the methods of class Actions like:
- moveToElement(targetElement) - to move the cursor to a particular element
- doubleClick() - double clicks
- contextClick() - right click
- keyDown(Keys.<key_name>) - Press a key  

**Note:**
- Unlike other methods, we need to build this entire action, using build()
- To execute this step, we need to use perform()
- These two steps need to be performed with every action step.

Now this will execute. So to move a cursor to a particular element, we need to use the below syntax:

		a.moveToElement(WebElement).build().perform();
		
Send CAPITAL letters:
To send captial letters or to press a particular key, we use the keyDown() method. Within this, we pass an argument - Keys.<key_name>, for the key which we want to be pressed. Then we pass the elements and build and perform the operation.

If we want to perform operations on an element, one after another, then we just need to add the method for next operation ending with .build().perform();

## Window Handling:
Whenever we open any link in a new window, selenium driver will have the focus in the parent window only. We need to inform the driver about the new window. To achieve this,

**Syntax:**
```java
		Set<String> windows = driver.getWindowHandles();	-> //This will contain both parent and child window. getWindowHandles() returns a set of string.[parentId,childId]
		
		Iterator<String> it = windows.iterator();			-> //Iterates through the set to fetch the parent and child window
		String parentId = it.next();						-> //First it.next() will return the parent window id
		String childId = it.next();							-> //Next it.next() will return the child window id.
		
		Note: once we are at the last child window, it.next() will return the parent window id.
		//This is not working and throws error - Exception in thread "main" java.util.NoSuchElementException
		
		driver.switchTo().window(window_id);				-> //To switch to child window
```		

## Frames:
Frames are components hosted on the web pages. These are specific containers which are independent of the HTML code. Frames are prepared seperatly on HTML code and then hosted on the web pages, as a container/box.\
In case of frames, if we tell selenium to check a particular id, then it will consider it as a normal part of the HTML code and then search for the particular id and will return an error. To handle this, we need to tell this id is a part of frame, then it checks the frame, and identifies the element. \
To handle this, we use:

		driver.switchTo().frame(..arg)
		
- frame(int arg)
- frame(String arg)
- frame(WebElement locator)

**How to find frame?**\
Search for iframe tag in the HTML code.

## Drag and drop operations:
This can be performed by using the Action class along with the frames. This is achieved by using the dragAndDrop() method.

**Syntax:**
```java
		Actions a = new Actions(driver);
		a.dragAndDrop(source, destination).build().perform();
```
Here in source and destination, we pass the locator for the webelement.

**How to check no of frames in page?**\
Count the number of tags - `<iframe>`

**How to go back to default content/back from frames?**
```java
driver.switchTo().defaultContent();
```

## Scoping the limit of driver / Creating sub driver from parent driver:
We can limit the scope of our driver by creating a sub driver from the parent/main driver. With this, we can find all the elements which are present within any particular element, without worrying about the elements outside the scope.

**Syntax:**
```java
		WebDriver childDriver = driver.findElement(By.<locator>);
```		
Now we can perform all the similar operations with the childDriver, as we do with the main driver.\
We can create child driver of a child driver as well.

## Opening links in seperate tabs:
If we want to open the links in seperate tabs, we need to click the control key and then press enter/click on the particular link, so that it opens in a seperate window. To do this,

**Syntax:**
```java
		Keys.chord(Keys.CONTROL,Keys.ENTER) 
```
This will return a string value. Now to use this with links, just use the sendKeys() method instead of .click() method. This will open all the links in seperate windows, without going to any other window.

**Note:** To search for elements using the text visible in them, we can use xpath along with text() and tagname.

    //tagName[text()='<visibleText>']

Only the text which is present in the tags can be read by selenium getText() method.
	
## Handling scrolling webpages:
### Headless Mode:
In this, there will be no browser invokation. Test execution will work on the basis of chrome service. Chrome provides some inbuilt servies with which the test can be executed without opening the browser and does all the operation which needs to be performed.\
In headless mode, the test execution can fail as the element which needs to be inspected is not in visible mode. To ensure that execution does not break, we need to first shift the focus to the element by scrolling to the element and then perform the operation.

**How to scroll in selenium?**\
By default selenium does not provide any method for scrolling. For this we need to create the object of JavascripExector class, which helps us to execute JS related operations. The object is created by casting the driver to the object so that the created object can execute JS.

**Syntax:**
```java
		JavascripExector js = (JavascriptExecutor) driver;
		js.executeScript(arg0, arg1);		//to execute JS, only one argument is required. second argument is optional.
```		

### To scroll:
To scroll, we need to use the JS method - window.scrollBy(from,to). In the arguments, we need to pass the coordinates from where till where we want the driver to scroll.\
The from and to can be obtained by hit and trial method, on console.

### Scrolling on Component:
If we want to scroll on any particular element like table or scroll infinitely on the table as new rows keep on appearing as we scroll down, then we will have to use the DOM manipulation techniques.\
**Syntax:**
```java
		document.querySelector(<className>).scrollTop=coordinates
```		
- document - this will select the complete web page.
- querySelector() - this is similar to findElement() method of Selenium
- <className> - this is similar to locators - By.
- scrollTop - this is the method which will be used to scroll in the table. Since if we scroll down, the rows will go up, hence we can use the ScrollTop method in this case.
- scrollLeft - scrolls horizontally
- coordinates - we need to mention the coordinates for scrolling. This is done with hit and trial method on console.
- If we want to scroll horizontally, we will use scrollLeft.

## Handling Grids in WebPage:
To fetch the records from a particular row of a grid, we need to first locate the table class/unique element and then traverse to the column whose data needs to be obtained.

**Syntax - **

			tableUniqueElement td[columnNo]
			
**Note:** To validate the CSS/Xpath from console, write "$(xpath/css)" in console.

## Handling HTTPS certifications:
Whenever we try to access a site which is internal to our project or for which HTTPS certification error comes, then it stops the selenium execution. To handle this we have a class - 'ChromeOptions'. This class helps us to set the behaviour for the chrome browser. Until now, we were only invoking the browser and not setting any behaviour on how it should behave.\
Firstly, we need to create the object for the class - ChromeOptions. Then we have to use a method named - setAcceptInsecureCerts(bool arg).

**Syntax:**
```java
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true/false);
```		
If we pass true as argument, then it will accept all the certifications and proceed to opening the site.

But the chrome driver will not be able to know about the behaviour which is defined above. To handle this, we need to pass the ChromeOptions object as an argument to the ChromeDriver class. Then the driver object will be having the knowledge of how it needs to behave.\
Similarly for Firefox we have FirefoxOptions, for Edge we have EdgeOptions and for Safari, we have SafariOptions.

**Methods in ChromeOptions class:**
1. addExtentions(paths) - 
    - By default we do not have any extentions present in the automation browser as we have in our normal browsers. The browser will open normally and freshly as if it has been downloaded now. For this we can use the method - 'addExtentions()'
	- In this method, we pass path of the extention which we want to be present in the browser opened by automation. 
	- The required filepath can be obtained by downloading the extentions in the system from chrome and then giving its file path.

2. setCapability(capabilityName, value) - 
	- There might be few instance where the websites might require special proxy to access. This can be handled by a method - 'setCapability(capabilityName, value)'.
	**Some capabilityName are:**
		- proxy : 	in this case, if we use proxy, then the browser will be invoking with the proxy by default.\
		To handle the proxy related things, there is a special class in JAVA - 'Proxy'. We need to first create an object for this class, after which we can setup the proxy using the methods available within the proxy class. The proxy information can be obtained from the company.\
		This proxy object created needs to be passed as the second argument(value) in the setCapability() method along with the capabilityName 'proxy'.\
        Here we cannot directly send the proxy address in the method as argument, because in this case, it will still be considered as a string and not an object.
					
3. setExperimentalOptions() - 
	- Blocking popups - 
		If we want to block any pop ups from appearing - like allow notification or allow location pop ups, then we can use this method. This can be handled by 
        ```java
			options.setExperimentalOptions("excludeSwitches",Array.asList("disable-popup-blocking"));
        ```
        **Set download Directory -**\
            If we want to set the default download directory, then this can be handled by passing a hashmap to the setExperimentalOptions() method along with the capabilityName as pref and the hashmap created above.\
            **Syntax -**
        ```java
            Map<String,Object> prefs = new HashMap<String,Object>();
            prefs.put("download.default_directory",<directory_path>);
            options.setExperimentalOptions("prefs",prefs);
        ```

## Cookie deletion using Selenium - 
- To delete all the cookies - driver.manage().deleteAllCookies();
- To delete any specific cookie - driver.manage().deleteCookieNamed(cookie_name);
- To delete session cookie, we can use the deleteCookieNamed method.

## Screenshot in Selenium - 
To take a screenshot in Selenium, we need to convert the WebDriver object into ScreenShot object. This can be done by casting the object with (TakesScreenshot) method object, and then take the screenshot.

**Syntax - **
```java
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyToFile(src,new File(path));
```		
Here we are specifying the object that after taking the screenshot, we need to save it as a file, so that we can view. This gets stored in an object of File class (here it is src). To bring the screenshot saved in File object, we use FileUtils, which is a method to copy the object from src object to our local machine.\
For this, we need to download the jar from Apache Commons.IO and then add it to the project build path.\
Note: Store the screenshot in any driver other than C drive, as one would need Admin rights to save a file to C drive. Instead try using any other drive.\
Also you need to enter the file name of the screenshot in the path.
		
## Handling Broken Links:
Broken links mean the page url in the link is not opening and throwing some error. So to check if an URL is working or not, there are two ways:
1. opening each of the links manually - this is a timeconsuming process.
2. checking the status of the link from network tab - if any of the link has status > 400, then that link is a broken link

This can be done with the help of JAVA methods, which will call the URLs and get the status code. To get the URL, we will require Selenium to fetch the URL from the tags and then give it to the JAVA method to check the status code.\
JAVA method which is used for this is - openConnection() method of URL class. To use this method, we need to create the object of this class to access this method.\
After creating the object and connection for the URL, we need to declare the call type, i.e., GET, POST, HEAD, etc.\
Atlast, we have to connect with the URL.\
Once we connect to the URL, we will be able to receive the response in the same object created above.\

**Syntax:**
```java
HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
conn.setRequestMethod("HEAD");
conn.connect();
int responseCode = conn.getResponseCode();
```		
## Soft Assertions:
Soft Assertions is a method of TestNG in which we can check and verify all the elements even if the assertion fails. In the current scenario, we are using hard assertions, which stops the automation if any error is occured. Using soft assertions we can still be able to validate all the components even if any of the assertions returns false.\
For using soft assertions, we need to create an object for SoftAssert class first and then use it instead of Assert. Once all the assertions are completed, we have to use assertAll() method to validate all the assertions\
**Syntax:**
```java
SoftAssert sa = new SoftAssert();
sa.assertTrue(condition,message);
sa.assertAll();
```
		
## Java Streams:
Introduced from JAVA 8.

### What are Streams?
Stream API is new feature available from JAVA 8. By using streams, we can perform various aggregate operations on the data returned from collections classes by drastically reduce the complexity of code.\
In other words, streams reduce the complexity of code by combining and making it simpler\
Streams is a method with which we can convert any List<String> into Stream.\
**Syntax:**
```java
ArrayList<String> names = new ArrayList<String>();
names.stream() 		//this will convert the Arraylist to stream
```

***Program - Count the number of names starting with alphabet A in List.***
Filter using Stream - \
**Syntax:**
```java
		names.stream().filter(s->s.startsWith("A"));
```

### What is Lambda Expression?
Lambda expression introduce the new arrow operator -> into JAVA. It divides the lambda expression in two parts:\
- The left side specifies the parameters required by the expression, which could also be empty if no parameters are required.
- The right side is the lambda body which specifies the action of the lambda expression.

### Working of Streams:
**Syntax -**
```java
		Long c = names.stream().filter(s->s.startsWith("A")).count();
```
The streams work in a 3 stage method:
1. Creation of Stream - names.stream()
2. Performing intermidiate operation with Stream -	Perform intermidiate operations on the initial stream to transform it into another stream and so on, on further intermediate operations - .filter(s->s.startsWith("A"))
3. Termination operation on stream - Perform terminal operation on final stream to get the result. - .count()
	
An important characterstic of intermediate operation is laziness. When executing this code snippet, nothing is printed on console.That is because intermediate operations will only be executed when a terminal operaton is present.

There is no life of intermediate operation if no terminal operation is present.\
Terminal operation will execute only if the intermediate operator(filter) returns true.

**Note:** 	
- The aggregate operations that we perform on the collection, array or any other data source do not change the data of the source, they simply return a new stream.
- This means, even if we are performing operations on the main list directly, it will not effect the main list/ArrayList. From the main list, we are deriving a new stream and then performing operations on the newly created stream and the main stream remains untouched.
		
It is not required to create a new list every time to create a stream compatible collection. We can directly create a stream using the stream pacakge and then perform operations directly on the created stream. But once a stream is modified it cannot be reused. We will have to create a new stream to perform further operations.

**Syntax -**
```java
Stream.of(<add names>).filter(s->s.startsWith("A"));
```
		
If the action is of more than one line, then we can add the mutiple code line in the same intermediate operation.

	.filter(s->{
		s.startsWith("A")
		})

If we deliberately return false in the filter operation, then the terminal operation will never execute.

**Other Terminal Operations**
1. count() -> return type Long -> returns the count from the intermediate operation
2. forEach(lambdaExpression) -> it will iterate on each element returned from the intermeidate operator and then perform the operation as mentioned in the lambda expression.
3. limit(arg) -> limits the result set -> other terminal operators can be used after this.
4. map(lambdaExpression) -> Helps to modify the output of the filter result set and then Maps the original string with the converted string. -> other terminal operations can be used after this
5. sorted() -> sorts the stream output
6. Streams.concat(stream1,stream2) -> Creates a new stream by combining both stream into one.
7. anyMatch(lambdaExpression) -> returns boolean -> returns true if any match is found. also no terminal operation is required.
8. collect(Collectors.toList()) -> returns List<String> -> converts the final result stream back to a new list -> this can be converted to any type (list, set, map, etc).
9. distinct() -> returns all the distinct elements in the array.
10. toList() -> returns an immutable list from stream, i.e., you cannot modify the list.

**Diff between filter and anyMatch**
1. filter returns a resultSet of stream and anyMatch returns boolean value
2. terminal operation is required in case of filter but not in case of anyMatch

We can also create and call custom methods inside the terminal opertors.

```
Assigment - (3,2,2,7,5,1,9,7)
//print the unique number from this array
//sort the array

Note: List.forEach(a->System.out.println(a)); 		//Prints all the elements of list
```

## Pagenation in JavaStreams

## Selenium 4.0
### Relative Locators:
1. above() -> element located above with respect to the specified element
2. below() -> element located below with respect to the specified element
3. toLeftOf() -> element located to the left of specified element.
4. toRightOf() -> element located to the right of specified element.

**Syntax:**
```java
		driver.findElement(with(By.tagName("XX")).above(WebElement));
```

By default eclipse will not suggest the static packages name. So for Relative locators, we need to import the package manually.\
Relative locators does not work with Flex element. It reads only the HTML element.\
We can find relative locator only on the basis of tag names.

### Invoking a new window with same driver object:
To invoke a new window while keeping the previous window open and using the same driver, we need to use a method - 'newWindow()'. Uisng this a new tab will be opened while the previous tab also remains open.

**Syntax:**
```java
		driver.switchTo().newWindow(WindowType.TAB);
```
		
Even if we open a new window, the driver will remain in the previous window. We need to tell the driver about the window and shift its focus to the new window. This can be achieved by using the Window Handling in Selenium. By fetching the IDs of parent and child window, we can shift the focus from parent window and back to child window.

## Get Partial Screenshots:
To get the partial screenshot/screenshot of a particular web element, getScreenshotAs(OutputType.FILE) method is used along with the webelement whose screenshot is requried.\
After this, the element is saved to the system using the FileUtils class.

**Syntax:**
```java
WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
File file = name.getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(file,new File("C:\\Users\\dell\\Desktop\\partial_screenshot.png"));
```

## Get Height and Width in Selenium:
To get the height and width of a web element in selenium, we have to use the below methods along with the web element whose dimensions is to be calculated.

**Syntax:**
```java
WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
int height = name.getRect().getDimension().getHeight();
int width = name.getRect().getDimension().getWidth();
```

# TestNG Framework:
TestNG is a testing framework designed on JUnit.

## Introduction:
For a JAVA program, we always use public static void main(String[] args), which means we will be using a JAVA compliler for our project. In case of TestNG, we won't be using this as TestNG itself will be acting as a complier for the Program.\
In the backend, TestNG needs JAVA to be functional, but in FrontEnd we need not write all the JAVA statements which we use in JAVA compilers.\
TestNG needs all the execution in methods/functions. But this will still not work, as we have still not given TestNG enough information to work. So this is a Test and hence we need to add the '@Test' annotation above the method and then add all the TestNG library into the project.

**If encounter any error - try removing module-info.java file as for selenium module-info file is not required.**

### What is '@Test'?
@Test is a TestNG annotation which defines that the method below the annotation is a test case. So TestNG will consider it as a program and even if public static void main(String[] args) is not given to the method, it will still execute the function.\
Earlier when we used to have the public static void main() method, it used to Run using the JAVA Application, but now, it will run using TestNG.

Java compiler gives a plain output, but TestNG output gives all the information like:
- output
-  Test status - PASSES/Failed
-  Test Case Name
-  Total No of Tests Run
	
**How to run tests with TestNG?**
- We need to have `@Test` annotation followed by method.
- We can define multiple test cases in a single class.

## Importance of XML in TestNG Configuration:
In JAVA, if we need to add multiple test cases, then we had to create new class for each of them. But in TestNG, we can create multiple Test cases in a single class file. \
If we write another @Test annotation, then it will be treated as a second/new test case, followed by a method.

To create a TestNG XML file, we need to convert the project to TestNG which will create an TestNG file in the project itself.\
Within the xml file, we have:
1. **Test Suite/Default Suite:** TestNG always wants a higher level as a suite, which should have a neat name, which should be what information or what test we are running in this suite.\
Syntax:
    ```xml
    <suite name="Suite"></suite>
    ```
2. **Test folder(Shell):** This is defined inside the Default suite. A collection of test folders becomes a test suite. Inside the test folder, actual test cases are written.\
**Syntax:**
    ```xml
    <test name="test"></test>
    ``` 
3. **Test Cases:** Test cases are the java class files created within the project/package, which contains the actual test cases. This is defined inside the Test Folder.\
For test cases, we have the classes tag before the class tag, as if we have multiple class files for the same test folder/test, then all the classes can be added into one single class file.\
Syntax:
    ```xml
    <classes>
            <class name="test.day1"/>
            <class name="test.day2"/>
    </classes>
    ```
4. **Test Module:** All the classes, test cases, test folders comes under a test module. A test suite can have multiple modules.
	
To run an xml file, we will be executing using the TestNG suite. Same way we execute the java file/testng file, in the same way a TestNG xml file will be executed using TestNG suite. This will run all the test cases together and print all the output in one go.

## Prioritizing Test Cases:
In real time scenario, we can have multiple test cases in a single class file/test module. TestNG gives us support to execute/prioritize any test cases we want, just like a switching mechanizm which switches on and off all the test cases as per our will. \
To include/exclude any particular test case from a class, we need to expand the class tag inside the classes tag, and then use a tag - 'methods' within the class tag.\
To exclude any method, we have the 'exclude' tag in which we provide the name of method, which we want to exclude.\
**Syntax:**
```xml
<classes>
    <class name="test.day3">
        <methods>
            <exclude name="methodName"/>
        </methods>
    </class>
</classes>
```
To include only specific method(s) in the test cases, we have the 'include' tag in which we provide the name of method, which we want to include in our test case.\
**Syntax:**
```xml
<classes>
    <class name="test.day3">
        <methods>
            <include name="methodName"/>
        </methods>
    </class>
</classes>
```
## Executing test cases at package level with regex:
If we have to exclude/include multiple test cases all which have some similar name convention across all the modules, then we can take all the modules at once by using regex expression with the exclude/include tag.\
**Syntax:**
```xml
<classes>
    <class name="test.day3">
        <methods>
            <exclude name="methodNameStarting.*"/>
        </methods>
    </class>
</classes>
```
The '.* ' means, it will exclude all the methods which are starting with the same name as defined before the '.*'

We can also trigger the test cases from package level instead of manually writing all the class files in the xml.
**Syntax:**
```xml
<suite name="suiteName">
    <test name ="testFolderName">
        <packages>
            <package name = "packageNameFromHierarchy" />
        </packages>
    </test>
</suite>
```		

## TestNG Annotations:
1. **@BeforeTest:**
		If we want to execute any block of code as a prerequisite before executing any test case, then we use the '@BeforeTest' annotations so that the method written inside the 'BeforeTest' annotation is executed first before starting any other execution.
2. **@AfterTest:**
		If we want to execute any block of code after executing all the test cases, then we use the '@AfterTest' annotations so that the method written inside the 'AfterTest' annotation is executed last after executing all other executions. \
		Used to generally remove the cookies.\

**Note:** both these annotation work at the module level, i.e., they will function either at the begining of the module or end of the same module as per the annotation used. if there are multiple modules in the same xml file then it will work only for the module in which this particular test class would be present.

3. **@BeforeSuite:**
		If we want to execute any block of code before any execution starts, i.e., at the begining of the suite, in that case we use the 'BeforeSuite' annotation. This annotation will execute at the start of the suite as suite is the parent to the test folders.\
		Used for loading all the environment variables.
4. **@AfterSuite:**
		If we want to execute any block of code after all the execution ends, i.e., at the end of the suite, in that case we use the 'AfterSuite' annotation. This annotation will execute at the end of the suite as suite is the parent to the test folders.

**Note:** All these are specific to TestNG level

5. **@BeforeMethod:**
		This will execute before starting any method.
		This is specific to class level
6. **@AfterMethod:**
		This will execute after a method executed.
		This is specific to class level
7. **@BeforeClass:**
		This will execute before starting any class.
		This is specific to class level
8. **@AfterClass:**
		This will execute after all the methods in the class are executed.
		This is specific to class level

**Note:** In TestNG, all the methods with same annotations are executed in alphabetical order.

## Groups in TestNG:
If we want to execute only specific methods everytime the execution is done, then to achieve this we use the concept of 'groups'. In this, we add the key group with the @Test annotation along with the group name.
**Syntax:**
```java
		@Test(group={"groupName"})
```		
So everytime we want to execute the methods having same group name, we have to add the `<groups><run></run></groups>` tag in the xml file inside the test folder and before the test cases/classes tag. After this, we need to include the group name in the run tag using include tag. This will make sure that before any class file is executed, only the class files having the group name mentioned in the run tag are executed.\
In similar way as we use the include tag, we can use the exclude tag also.\
**Syntax:**
```xml
<test name="testName">
    <groups>
        <run>
            <include name="groupName"/>
        </run>
    </groups>
    <classes>
        <class name="className"/>
    </classes>
</test>
```

## Helper Attributes in TestNG:
### DependsOnMethods in TestNG:
Generally in TestNG, methods are executed in alphabetical order for the methods having same annotation. If we want to execute any method before other, then we use the keyword - 'dependsOnMethods'. This is used in the same way as the 'group' keyword is used. This will tell the TestNG that the given method needs to be executed first as the following method is dependent on it. If we want to add more methods to it, these can be added in comma seperated manner.\
**Syntax:**
```java
@Test(dependsOnMethods={"methodName1","methodName2"})
```

### enabled in TestNG:
If we want to skip any method as the given method contains some bugs, then this can be achieved by using 'enabled' keyword.\
**Syntax:**
```java
@Test(enabled=false)
```

### timeOut in TestNG:
If any test case is failing due to some timeout error and we cannot apply implicit wait for this to all the test cases, in this case, we can add a 'timeOut' keyword to the annotation, so that the execution does not fails even if it takes some time. In this the time is entered in 'ms'.

## Parameterizing in TestNG:
If we have some environment variables which are constant for the complete duration of the execution (like username, password, urls), then we can pass these variables as parameters in the XML file.

**Note:** Main role of framework - There should be no hard coding in the test cases. All the main details should be derived from a centralized file.

### Centralized way - Applicable to all test folders:
**Syntax:**
```xml
<parameter name="parameterName" value="parameterValue" />
```
This needs to be added in the xml file after the suite and before the test tag. This will make sure the parameter is available for all the test folders/module.\
Now to use the parameter in the class file, we need to use an annotation - '@Parameters' before every test annotation, to make the parameter available for the use. It's scope is limited to that method only.\
**Syntax:**
```java
@Parameters({"parameterName"})
@Test
public void methodName(DataType parameterNameArg)
```

In the method, we will be using the 'parameterNameArg' which would be containing the value given in the xml file for the parameterName passed in the annotation.\
The parameter tag can also be used inside the test tag. In this case, the scope of the parameter would be limited to the mentioned test case only.

### Passing multiple values in Paramter:
We can pass multiple parameters from our xml file and catch them using the annotation by mentioning all the parameters in a comma seperated manner. Similarly we need to add these in the method argument in the same way.

### Parameterizing with multiple data sets by running tests with multiple combinations:
### DataProvider Annotation:
- If we want to run multiple test cases with multiple set data, then using DataProvider, we can perform this execution by defining the data once and then performing all the cases with different data.
- To use this, we need to use the multi dimentional object array.
- For this, we need to first define a multi dimensional array. In this, each data combination is a row and each column is a value.
- Then we define all the values, for the above created multi dimentional object array.
- At last we have to return the array.

**Syntax:**
```java
@DataProvider
public Object[][] getData()
{
    Object[][] data = new Object[no_of_cases][no_of_parameters];
    //give all the values to each row and column of data object.
    return data;
}
```		
After defining and declaring the DataProvider, we need to pass the dataProvider as argument to the Test annotation. and since we are having 2 columns/we are passing two values for each row/test case in the array, we need to add the same no of arguments in the method.

**Syntax:**
```java
@Test(dataProvider="getData")
public void methodName(String arg1, String arg2)
{
    
}
```
If we run the class file directly and it contains some parameters defined in the xml file, then it will throw an error as we would be requiring the data from the xml file for that particular method of class file.\
The test cases will run for as many times as we are giving it in the object array no of rows.\
DataProvider not only supports array. It also supports hashmaps.

## TestNG Listeners:
During execution, if we want to run a certain method each time we occur an error or everytime an operation/incident happens, then this can be achieved with the help of listeners. So whenever the test cases fail, it will reroute to that particular code and execute it.\
To implement Listeners in TestNG, there is an interface - 'ITestListener', which contains all the methods required for the TestNG listener\
**Syntax:**
```java
public class Listeners implements ITestListener {

}
```

1. **onTestFailure:** Whenever a test fails, it reroutes to the onTestFailure method in the Listeners class.
2. **onTestSuccess:** Whenever a test pass, it reroutes to the onTestSuccess method in the Listeners class.
3. **onTestStart:** Whenever a test starts, it reroutes to the onTestStart method in the Listeners class.

***Difference between (OnTestSuccess/OnTestFailure) and afterMethod:***\
OnTestSuccess/OnTestFailure run only when the test returns true or false respectively whereas afterMethod executes each time the method execution is completed, irrespective of pass or failure.

In our xml file, we need to tell where the listener class is located. To do this, we need to to mention the path of listener file in the listeners-listener tag, after suite tag and before test tag.\
**Syntax:**
```xml
<listeners>
    <listener class-name="test.Listeners"/>
</listeners>
```
		
Whenever a test case fails, the data of the test cases is present in the result object of ITestResult class which is passed as an argument in the onTestFailure method. So to get the name of the test cases which failed, we can use the - 'result.getName()' method which will return the name of the test case.

## Running Tests in Parallel:
To run multiple tests parallelly, we need to introduce an attribute - 'parallel' in the suite tag in xml file. In this we also need to provide the number of tests which we want to run parallelly using the - 'thread-count' attribute.\
**Syntax:**
```xml
<suite name="suiteName" parallel="tests" thread-count="2">
</suite>
```

Similarly, if we want to execute the classes parallely, we will add the attribute parallel and thread-count in the test tag.\
**Syntax:**
```xml
<test name="testName" parallel="classes" thread-count="2">
</test>
```

## Generating Report:
Everytime the test completes, we get an output report in the same project with the name - 'test-output'. This contains an 'index.html' file. We can open the file in our browser and then see all the test cases, which got failed, passes, errors, etc.

## Inheritance in JAVA:
Acquiring the properties of parent class into child class without creating an object of the parent class in child class.

### How Inheritance helps in TestNG
TestNG has some annotations like beforeMethod which executes before the function call takes place. whenever inheritance is used with TestNG, it will first search if any inheritance is present or not. If inheritance is present, then it will check the parent class for any before annotations. If it is present, then those methods having the BeforeMethod annotations will be executed first and after that it will return to the child class to execute the methods in that class. Even though we are not calling the BeforeMethods in the child class, TestNG will execute them first.\
Same goes for the AfterMethod also.

## Single Responsibility Principle:
If we are having some variables or values which might be in use for the complete class and would also be passed to the parent class. In this case, we create the object of the parent class in the child class and then pass the value in a parameterized constructor of that particular class. So instead of passing the value as argument for all the methods of the class, we just pass the value once while creating the method and then using it for all the methods called by the object of parent class.

**Note:** -> 'this' keyword refers to the current class variables. If there are two variables having same name, one declared outside the method and one declared in the method parameter, then this keyword is used with the global variable.

### 'super' keyword:
If we are having multiple class where one class inherits the second and the class class also inherits a third class. If we pass any value to the parameterized constructor of second class, it will not be able to used by the third class. So to make the value passed to the second class using the constructor, used by the third class, we need to use the super(arg) keyword and pass the value in the argument of super() keyword. This will send the parameter to the third class and would be available for use.
**Note:** 	
- super keyword can only be used with ineheritance.
- super keyword should be the first line in the parameterized constructor of the second class.
- we also need to create a parameterized constructor in the third class.

## Page object classes:
We have multiple locators in our project. We will be keeping all the locators at one place and then will use them in our test cases. This will help to maintain all the locators easily and if there is any update in the locators, then they can be easily updated without having any change in the test cases.

For this we will be creating a new package - pageobjects inside the main source folder. Within this we will be creating seperate classes for each of the pages and will put all the locators for that particular page at one place. \
But the newly created class will not be able to access the driver object directly and will be a lifeless driver.So for this, we will be creating a constructor with which we will be passing the driver to the locator class and giving life to the driver of the locator class.

## PageFactory 
- Simplified way to defining the webelements in the locator class. In this, we first initialize all the webelements and then construct them using the PageFactory.initElements(driver,this) method.

**Syntax -**
```java
PageFactor.initElements(driver,this);	//this needs to be added in the constructor

@FindBy(id="id_name")
WebElement weName;
```

The `@FindBy` annotation will search the element having the same id and then assign its value to the WebElement Variable just below the FindBy annotation.

### How does 'FindBy' knows about the driver?
Before writing the 'FindBy' annotation, we declare the driver in the initElements method of the PageFactory class. In this we pass two arguments - driver and this. Using this, the FindBy is able to search the element from the driver itself.\
We can use id, xpath, css, className, etc in the FindBy annotation

## Action Methods:
Instead of writing multiple steps of a particular operation, we can create an action method for this and perform the operation accordingly.

## Abstract Components:
If there are some operations (like wait, switch windows, etc) which would be used repeatedly, then those operations are added into an AbstractComponent class and then used in all the test cases. So instead of defining the operations for each test case, we will be declaring it once and then use it for all the test cases.\
This will be implemented with the concept of inheritance, were we would not require to create the object of the class. Instead, we will be directly using the methods of the class.

**Note:**
- We cannot apply PageFactory within WebElement.findElement
- If we know which page will come next, then instead of creating objects for each page, we can encapsulate them into one object by creating and returning the object of the particular page, from the last method which would be called.

As we have created an abstract class for all the elemetns which are common in all the pages, similary steps like invoking the driver and adding wait are also common to all the test cases. So for this also we can create a BaseTest class (abstract class) which will store all the common steps performed in the test execution.

## Creating global properties:
In RealTime we might be using different browsers, so to maintain a generic code flow, we add the browser name in the properties file of JAVA and from there we pick the global properties and invoke the browser accordingly\
The global properties file must be defined in the main folder of src. Here we create the globaldata file with the extention properties, so that this can be parsed with the help of properties class of JAVA and the data can be used further\
**Syntax:**
```java
Properties prop = new Properties();
prop.load(filePathObject);
DataType propVar = prop.getProperty(propertyName);
```
		
Now to get the properties folder, we need to pass the location of the properties file to the filePathObject. For this we will be using the FileInputStream Class\
**Syntax:**
```java
		FileInputStream fs = new FileInputStream(file_path);
```
		
once this file path is passed to the argument of prop.load() method, we will be able to use all the global properties variables 

The file path which we are passing is a very long path and would not be available with every user. So to shorten the path, we use the getProperty() method of the System class.\
**Syntax:**
```java
		System.getProperty("user.dir")
```
		
This will generate the path where the eclipse project is present. After this we need to define the folder path.

If we create a group and try to execute the group using the TestNG xml file, then in that case, it will return a failure. As TestNG treats the beforemethod and aftermethod like normal test annotation only. so if we specify to just execute a particular group and do not use the group annotation with the before and after method annotations, then these methods will not execute. Also we cannot add the group name to these annotations as these methods need to be executed before every method starts/ends, and if group name is specifie to this, then these will not execute for other group names and we will have to write all the group names.\
So to handle this, we use "alwaysRun=true" attribue with the annotation, so those methods will always execute irrespective of the grouping.\
**Syntax:**
```java
		@BeforeMethod(alwaysRun=true)
		@AfterMethod(alwaysRun=true)
```
		
## Reading Data from JSON File:
1. Create a JSON File
2. Converting JSON File to Hashmap:\
	To convert a JSON file to Hashmap, we first need to convert the JSON file to String. For this we have a JAVA class - FileUtils.readFileToString(file_path_object). Using this we can convert the JSON file to string format.\
	**Syntax:**
    ```java
    String jsonContent = FileUtils.readFileToString(new File(
    System.getProperty("user.dir") + "\\src\\test\\java\\seleniumAutomation\\data\\PurchaseOrder.json"),
    StandardCharsets.UTF_8);
    ```
3. Once the file is converted to String format, we have to convert it into HashMap. For this, we need to import a new Maven Dependency - Jackson Databind. Once added, this will first read the string value and then convert it into a list of hashmap containing all the data.\
	**Syntax:**\
    ```java
    ObjectMapper mapper = new ObjectMapper();
    List<HashMap<String, String>> data = mapper.readValue(jsonContent,
        new TypeReference<List<HashMap<String, String>>>() {
        });
    ```
4. In this, we first create an object of ObjectMapper class. Then we will use the readValue method of the ObjectMapper class and pass the jsonContent string as the first argument and the required output type - List of hashmap as the second argument.
	
## Extent Reports:
These reports are used to get the execution results.\
For Extent reports - these two classes are important - ExtentReports, ExtentSparkReporter\
**ExtentSparkReporter Class**
- The ExtentSparkReporter class expects a path where the report should be created. This is responsible for creating a report. So we have to explicitly give a path to store the reports. So to save the reports in a new folder in current directory we can use - 'System.getProperty("user.dir")'
- To add further, the path where the report will be stored, we need to add a new folder with a file name having .html extention - '\\reports\index.html'. This path variable needs to be stored and then passed as an argument to the ExtentSparkReporter class.
- The object of the ExtentSparkReporter class - 'reporter' is responsible for all the configurations of the report.
- To change the title of the report 		- reporter.config().setReportName("New Report Name");
- To change the page title of the report 	- reporter.config().setDocumentTitle("Document Title");
- All the configs to be done in the report is done with the help of the ExtentSparkReporter class.

**ExtentReports Class**
- For all the reporting execution, we use the ExtentReports class. This is the main class. This is responsible to derive all the reporting execution. After this, we need to attach the report created using the ExtentSparkReporter class to the main class - ExtentReports class. So ExtentSparkReporter is a helper class which will help to create report for the main class. Report is attached by using the - attachReporter(reporter) method of the ExtentReports class. Here we need to pass the object of the ExtentSparkReporter class as an argument.
- To give the name of the tester, this can be done with the help of ExtentReports class method - 
    ```java
    setSystemInfo("Tester", "Tester Name");
	extent.setSystemInfo("Tester", "Tester Name");
    ```
	
Now to make the report class object available to all the tests, outside the config method, we need to globally declare the ExtentReports class and object. After declaring, we need to use the createTest(testName) method. This method needs to be used at the start of the test. After this, it will keep on monitoring the test and store the output of the test if the test passed or failed. Once the test is completed, we need to use the 'extent.flush()' method at the end so that the class will stop monitoring the test.

If we want to tweek the report for some particular test, then we can use the ExtentTest class and then perform the operation specific to that test.
```java
ExtentTest test = extent.createTest("Initial Demo");
test.fail("Result do not match"); //Failing the test explicitly
```
	
## TestNG Listeners:
Listeners are executed before any test starts. So if we want to add the test name, then we add it in the onTestStart method\
It is not mandatory to add test pass as it will always give test pass in report once the test is completed. If the test fails, then we need to explicitly tell that the test has failed in the onTestFail method.\
So Listeners are created in the TestComponents and all the reports are created from the listeners as whenever a test starts, ends, pass, or fails, it will go to the listener and the code added in the listeners will be executed.

## Thread Safe:
When we are running the tests in series, then the report is showing some abnormality - like adding the screen shot of failed test in pass test. But this issue will not occur with series test execution.

## Concurrency issue in reports with parallel execution
When the tests are executed in series, then the object 'test' of ExtentTest class is created for one test at a time. So once the test is completed, only then the next test starts and the object is assigned for the next test. \
But in case of parallel execution, tests start executing subsequently, due to which the value of object gets overridden everytime. So, when the test fails, it is the object of another test, and therefore the reports shows incorrect data.

## ThreadLocal: 
This can be handled with the help of 'ThreadLocal' class. In this, we pass the object once it is created to make it thread safe. The ThreadLocal class maps a unique thread id to object. This unique thread id is unique to every execution/process. So when we need to use the object, we will use the get method to get the object and then perform all the desired operation. \
When we use the get method, it will first fetch the thread id of the current execution and then search it in the map and return the object which is mapped with this thread id so that further operations can be performed. Also we need to mention which object type we will be passing in the ThreadLocal class during the time of initialization.

## Rerun failed Tests:
If we want to re-run all the failed tests, so that we can be sure if the failed tests are actually failed tests or not, then this can be achieved by using the IRetryAnalyzer Interface.\
So once all the test execution are completed, it will go to the Retry class and check, if any test needs to be re-exeucted or not. If it returns true, then it will execute all the failed test cases again and check if they still fail or not. If Failure is still there, it will again check if the test needs to be re-executed or not. For this we also add a counter variable which counts how many times the test is re-executed and how many times it should execute.\
For listeners, we used to declare the listeners in the listener tag in the xml file of the suite. For RetryAnalyzer, we need to go to the class of the test which we want to re-run and if we know which test can fail, then add the retryAnalyzer attribute to the test annotation and mention the class where the retry method is present.\
**Syntax -**
```java
@Test(retryAnalyzer=Retry.class)
```

## Test Execution using Jenkins:
Tests can be executed with the help of Jenkins Pipeline. We need to install jenkins on our local system. Then mention the path of the directory and create a new jenkins job for the build.

### Key points for Jenkins:
1. In jenkins, if we want to add any custom parameter to the build command, then it needs to be written in double quotes and the parameter name should start with '$' symbol.\
**Syntax -** 
    ```bash
    "$parameterName"
    ```

## Headless mode execution:
Headless mode execution means execution will take place without invoking the chrome browser and all the execution will take place in the chrome engine.

## Jenkins Job Scheduling:
**Syntax -** 
```bash
* * * * *
```

This field follows the syntax of cron (with minor differences). Specifically, each line consists of 5 fields separated by TAB or whitespace:
- MINUTE HOUR DOM MONTH DOW
- MINUTE	Minutes within the hour (0–59)
- HOUR	The hour of the day (0–23)
- DOM	The day of the month (1–31)
- MONTH	The month (1–12)
- DOW	The day of the week (0–7) where 0 and 7 are Sunday.

To specify multiple values for one field, the following operators are available. In the order of precedence,
- '*' specifies all valid values
- M-N specifies a range of values
- M-N/X or */X steps by intervals of X through the specified range or whole valid range
- A,B,...,Z enumerates multiple values

To allow periodically scheduled tasks to produce even load on the system, the symbol H (for “hash”) should be used wherever possible. For example, using 0 0 * * * for a dozen daily jobs will cause a large spike at midnight. In contrast, using H H * * * would still execute each job once a day, but not all at the same time, better using limited resources.

The H symbol can be used with a range. For example, H H(0-7) * * * means some time between 12:00 AM (midnight) to 7:59 AM. You can also use step intervals with H, with or without ranges.

The H symbol can be thought of as a random value over a range, but it actually is a hash of the job name, not a random function, so that the value remains stable for any given project.

Beware that for the day of month field, short cycles such as */3 or H/3 will not work consistently near the end of most months, due to variable month lengths. For example, */3 will run on the 1st, 4th, …31st days of a long month, then again the next day of the next month. Hashes are always chosen in the 1-28 range, so H/3 will produce a gap between runs of between 3 and 6 days at the end of a month. (Longer cycles will also have inconsistent lengths but the effect may be relatively less noticeable.)

Empty lines and lines that start with # will be ignored as comments.

In addition, @yearly, @annually, @monthly, @weekly, @daily, @midnight, and @hourly are supported as convenient aliases. These use the hash system for automatic balancing. For example, @hourly is the same as H * * * * and could mean at any time during the hour. @midnight actually means some time between 12:00 AM and 2:59 AM.

Examples:

- Every fifteen minutes (perhaps at :07, :22, :37, :52):\
H/15 * * * *
- Every ten minutes in the first half of every hour (three times, perhaps at :04, :14, :24):\
H(0-29)/10 * * * *
- Once every two hours at 45 minutes past the hour starting at 9:45 AM and finishing at 3:45 PM every weekday:\
45 9-16/2 * * 1-5
- Once in every two hour slot between 8 AM and 4 PM every weekday (perhaps at 9:38 AM, 11:38 AM, 1:38 PM, 3:38 PM):\
H H(8-15)/2 * * 1-5
- Once a day on the 1st and 15th of every month except December:\
H H 1,15 1-11 *

### Time zone specification
Periodic tasks are normally executed at the scheduled time in the time zone of the Jenkins master JVM (currently Asia/Calcutta). This behavior can optionally be changed by specifying an alternative time zone in the first line of the field. Time zone specification starts with TZ=, followed by the ID of a time zone.

Complete example of a schedule with a time zone specification:

    TZ=Europe/London
    # This job needs to be run in the morning, London time
    H 8 * * *
    # Butlers do not have a five o'clock, so we run the job again
    H(0-30) 17 * * *
	

# Selenium Interview Questions:
1. **What is the Design Pattern you have used in writing the tests in the Framework?**\
	Page Object Model - In the framework, all the classes are made as per the pages, where all the methods are defined. Every line present in the test cases, are derived from the page object classes. The action methods used in these are made in the java classes which are made based on these pages. \
	Apart from Page Object Model, we have also used Page Factory Pattern to create the objects (locators)\
		Page Factory Pattern Questions - 
    - How it is declared - using the initElements
    - How list is return.
	- How it is diffrentiated from WebElement and WebElements.

    Also, we have incorporated the next page class objects with the last method, if we are sure, it takes us to the next page. 
		
2. **How are reusable utilities handled in your framework?**\
	There are two differnet ways of handling this:
    1. **Core Selenium Methods:** We have a class - 'AbstractComponents' inside the 'AbstractComponents' package. Here we are writing all the reusable elements, like - wait for elements to appear, go to cart page, etc, which are all common methods. These are used by inheriting the AbstractComponents class in our page object files, so that we can access the methods from all the files.
    2. **Test Utilities Methods:** We store all the reusable test methods inside a 'BaseTest' class. Here we define all the test related initialization which are reused. And all the test cases will inherit the BaseTest class.
		
3. **Where did you use the Inheritance OOPS Concept in the framework?**\
	We have some reusable methods and we do not want to create the method for each and every class. So we created a parent class where we defined all the reusable methods and then inherited the parent class to all the child class.
	
4. **How did you derive the data from external files in the framework?**\
	We used JSON Files to parse the data. In the JSON File, we created number of data for which the test will execute.\
    **How is this achieved?**\
    We used the concept of DataProvider in TestNG, which helped us to parameterize the test data. This DataProvider also accepts HashMap as an object. So I Created multiple HashMap objects and send from my dataProvider to iterate them multiple times. So I created a JSON file and returned a utility which converts and returns my JSON file as a HashMap and once it is converted, I am passing the HashMap to my data provider.
    **How is the data converted?**\
    There is a dependency called Jackson Databind. SO using this utility, there are clases like 'ObjectMapper' which converts the JSON string contents to hashmaps. 
					
5. **Did you use interface in your framework? If so, what is the scope of it?**\
	Yes, ITestListener is one of the interface used in the framework. 
    **Why using ITestListener?**
    It provides methods which are helpful to write the code which we can avoid writing in our test cases. For Eg, for every test case, we need to create an entry in the report. So we use the onTestStart method of the ITestListener to write the code. Similarly I have a requirement to take a screenshot at test failure. So it is only possible if I have a listener which gets called whenever my test fails, which is provided by ITestListener and I can write the code for when my test fails. \
	Also apart from this, WebDriver is also an interface and we are using differnet classes to implement methods in this WebDriver interface, like - ChromeDriver (to invoke chrome browser), FirefoxDriver
	
6. **How are you achieving Encapsulation in the framework?**\
	By keeping the fields as private and action methods as public, so that no one can access those fields without the action methods. 

7. **Does your framework support parallel runs? How are you writing Thread Safe Code?**\
	For our framework to support parallel run, we are adding parallel = "tests" to our testng.xml file. which means we are asking each test to run parallelly. and each tests constitues of a single class name. So the two classes are running parallely with each thread. So at a time, only one test in each class will run and there will be no conflict. So there will be 2 tests running at a time with each running in its own thread. \
    **Will running tests in parallel not override the driver initialization?**\
    No running the tests parallel will not override the driver initialization as each test is running in its own thread and driver will be seperate for each test. \
	While working with reporting, thread synchronization issue was faced, when the report object was getting overridden by each test. So to resolve this, we used the concept of Thread Safe, with the help of ThreadLocal class, which stored the information of the thread for that particular test execution as a thread id. 
	
8. **Do you have static keywords in the framework? If so, its usage?**\
	If we use the static with drivers then in this case we cannot achieve parallel testing. Because thread is shared with all the threads. And in parallel execution, we need to have seperate driver instances for each thread, all the variables are freshly created so that there is no colliding. Issue with static is, if we mark any variable as static, it will be shared across all the threads and if we use the static keyword, with driver, then the same driver will be used for both the threads. \
	So whenever we need to have parallel execution, we will avoid the static keyword. We can have static keyword for some variables which are independent of the test execution, but will have to avoid for main initialization, configuration, drivers, teardowns. \
	We can make some variables as static which are just for reading purpose. 
	
9. **How are you sending Global Properties to your test at run time?**\
	1. **Properties file -** Creating a java properties file and then reading the file in the basetest class. with the help of a class - Properties - which have the ability to read all the files having .properties extention. Then we are loading the file and then reading it at run time with getProperty and if it matches, then we are setting up the browsername. \
    Which variables can be declared globally:
        1. browser to be used. 
        2. URL
        3. Timeouts
	2. **Maven Terminal -** We can send global parameters through maven commands by writing -D and adding the value to be passed after it. We can catch it in our code by writing System.getProperty and then passing the property name as argument and then catch it. 
	
10. **What is the mechanizm you use to run only selected set of tests inside the framework?**\
**TestNG groups:**\
So all the test which we want to run, we will provide a group name to those particular test in the helper attibute of the @Test annotation and then create a new xml file, where we will mention in the groups tag, to run only the groups which have that particualar name with it. Also in the pom.xml file, we create a seperate profile for that group. so taht if we wnat to execute only that particular profile, then it can be done.
	
11. **How are you handling flaky tests in the framework?**\
	Flaky tests means the test which fail in the first attempt due to any reason. So to handle the flaky tests, we use the IRetryAnalyzer interface of TestNG, as a listener. So in the retry method of the interface, which gets called when the test fails, we return true if we want the test to retry and false if we don't. This is handled by a counter variable which counts the number of times the test has been retried, and a variable to store the max number of attempts. Also for which test we want to retry, we add the keyword retryAnalyzer with value `<classNameOfTheRetryClass>.class` to the @Test annotation helper attribute. 
	
12. **Does your Framework takes screenshot on test failure? How did you implement it?**\
	So whenever the test fails, we know it will go to the onTestFailure method of the Listeners. And here we are maintaing the screenshot code to take the screenshot whenever the test fails and then add it to the report. 

13. Explain Framework Achitechture Diagram.
	Do finally

# Cucumber Terminologies:
## What is Gherkin?
It is a business readable, domain specific language that lets you describe software's behavior.\
It is a language to define the test cases. \
Example pop up message is displayed when buttons are clicked and errors are gone. 

**Keywords used in Cucumber -**\
Scenario, Feature, Feature File, Scenario Outline, Step Definition

## Scenario:
In cucumber, test cases are represented as scenarios. 
Scenarios contains steps which are equivalent to test steps and use the following keywords (Gherkin Syntax) to denote them: Given, When, Then, But and And(case Sensitive)
- Given - Preconditions are mentioned in the Given Keywords
- When - The purpose of the When Steps is to describe the user action
- Then - The purpose of Then steps is to observe the expected output. The observation should be related to the business value/benefit of your feature description.
- And - This is used for statements that are an addition to the previous steps and represent positive statements
- But - This is used for statements that are an addition to the previous steps and represent negative statements

**Example:**\
Scenario - Make Minimum Due Payment\
	-> Given user is on Pay credit card page\
	-> When user fills all details and select Minimum amount option\
	-> And user clicks on pay button\
	-> Then credit card confirmation page is displayed
	
## Feature and Feature File:
Feature represents Business requirement.\
Feature File acts as a Test Suite which consists of all Scenarios.

In cucumber, feature files contain scenarios. We can simply create feature file with .feature extention. Scenarios belonging to specific area of application will be grouped into one feature fiel.

The text that immediately follows the feature keyword, and is in the same line, is the Title of the Feature file.\
Feature file should contain either scenario or scenario outline. the naming conventions for feature files hsould be lowercase with .feature extention.

**Example:** Feature: Credit Card Payment

## Scenario outline:
It is same as scenario, just if we want to define for multiple data sets, then we use the scenario outline instead of scenario.

With cucumber we cannot use the features which are specific to TestNG, like dataproviders, groups.

In cucumber, we use testng only to execute the tests. Rest all are cucumber features and scenarios.

# Selenium Grid:
## What is selenium grid?
Selenium Grid is a smart proxy server that makes it easy to run tests in parallel on multiple machines.

Selenium hub is a place where all the scode and script is present along with the Nodes(different machines). It selects the desired machine as per the requirement. If the script demands chrome browser, then the machine with chrome browser will be selected. If the script demands mac machine, it will select the mac machine. If two machines have the same browser, then it will load balance the traffic to both the machines.

## Selenium Grid Architecture:
1. **Client -** This is where the script is present. Local System.
2. **Router -** Once a request is made, it will go to this processor or router which is an internal component of selenium grid. The role of router is to redirect the request to the physical machines, but during the first request, the router will not know to which physical machine this request is to be routed. So it will send the request to the distributor.
3. **Distributor -** Distributor reads the scripts and then selects which is the appropriate machine for the current script. And if it is a common machine, then it will check which load balance it has to check. Once the node is selected, distributor sends the request to the Node. When the distributor sends the request to nodes, it also sends the data to the Session Map. This information will contain the session id of the session created for execution along with the node ip.
4. **Node -** It is the physical machine where the scripts are executed. 
5. **Session Map -** Stores the information related to the Session ID and Node IP provided by the Distributor and then share it with Router. So for the subsequent requests, the router will not ask the distributor to provide a node. It will check in the session map, if any existing node is present for the current session, and then assign that node to the current request. 

https://www.selenium.dev/documentation/grid/getting_started/

# Data driven testing from Excel:
**API used -** Apache POI - used to convert Excel test cases to JAVA Test cases.

## POI API Strategy
- Identity TestCases column by scanning the entire 1st row
- once the column is identified, then scan the entire column to identify
- purchase testcase row
- after you grab purchase testcase row, pull all the data of the row and feed it to test

## Classes Used:
- For Reading the workbook - XSSFWorkbook class is used. in this we pass the file path of the xl file
- For Reading the sheet - XSSFSheet
- For Reading the row - XSSFRow
- For Reading the column - XSSFColumn
- For Reading the cell - XSSFCell

Sequencing will be --> Workbook -> Sheet -> Row -> Column -> Cell(Using Row and Column)

For Editing the workbook -> We need to use the FileOutputStream class. 

# DataProvider + Excel Integration: 

## File Upload and Download:
Selenium cannot handle the file explorer window during upload as it is not a part of the browser window. To handle this, we need to locate the upload button. All the upload buttons have the type as 'file'. So we will use the send keys method and send the file path of the downloaded file.

# ChromeDevTools:
https://chromedevtools.github.io/devtools-protocol/

Selenium has introduced wrapper commands around Chrome Developer Tool Protocol(CDP) domains, to grant access to Chrome DevTools directly from your automated tests.

Examples - 
- Capture, monitor and stub the network requests and response
- inject session cookies and perform basic auth
- mock device coordinates for mobile/tabs view
- check and monitor the site's performance
- mock geolocations of the user
- block the network requests
- mock faster/slower networks speeds
- execute and debug JS
- View console logs
	
## Understanding Device Metrics -
To use CDT - we need to use ChromeDriver instead of WebDriver during initialization

## Steps for using ChromeDevTools
1. Initialize Chromium Driver 
    ```java
    ChromeDriver driver = new ChromeDriver();
    ```
2. Initialize Devtools are initialized by using:
    ```java
    DevTools devTools = driver.getDevTools();
    ```
3. After initialization, a session is created:
    ```java
    devtools.createSession();
    ```
4. Call the neccessary methods: (Used for Methods)
    ```java
    devtools.send(<command>);
    ```
5. Use add Listeners to listen any trigger event - (Used for Event)
    ```java
    devtools.addListenters(Event, handler);
    ```
	In the handler, we define what needs to be done once the event is listened.

# AutoIT
## Window Authentication Handling:
Window authentication pop ups are the non-JS pop ups which come when opening any site. Since they are different from other JS pop-ups, so they can't be handled by using the normal window actions. For this, we need to pass the username, password with the url in the 'Driver.get()' method to work on the pop up.\
**Syntax:**

		URL - 'https://username:password@site'
		Driver.get(URL);

## Handle File upload using AutoIT:
The file upload pop up is not an HTML pop up, so this cannot be handled using Selenium directly. To handle this, we need to integrate 'AutoIT' software with Selenium.

In AutoIT, we first need to enable its recorder - 'Au3info' and then using the finder tool of AutoIT, we will get the information regarding all the buttons which needs to be clicked or on which element operation needs to be performed on windows level. The AutoIT script is written in its editor and the file is saved with an extention on '*.au3'. Once saved, to make this file usable by selenium, we need to compile this file and then its extention will change to '*.exe', which can now be used by selenium and java.

Syntax: 
```java
ControlFocus("Open","","Edit1");
ControlSetText("Open","","Edit1","D:\Resume\resume_v1.pdf");
ControlClick("Open","","Button1");
```
		
- ControlFocus 	
    - sets the focus to a particular field. arguments given 	
    - Title, Optional(hence empty), Class+instance
- ControlSetText 	
    - sets the text in the edit field
    - Title, Optional(hence empty), Class+instance, file_path
- ControlClick 	
    - clicks the element mentioned
    - Title, Optional(hence empty), Class+instance

The exe file is called in the code by following JAVA class.\
**Syntax:** 
```java
Runtime.getRuntime().exec("G:\\SELF\\Java Automation\\Java-Automation\\Introduction\\src\\windowAuthentication\\fileupload.exe");
```

### Verify Downloaded file:
**Syntax:**
```java
File f = new File(file_path);
f.exists();
``` 

## Set download path for chrome browser:
This can be done using ChromeOptions class.\
**Syntax:**
```java
String downloadPath = System.getProperty("user.dir");
HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
chromePrefs.put("profiel.default_content_settings.popups",0);
chromePrefs.put("download.default_directory",downloadPath);
ChromeOptions options = new ChromeOptions();
options.setExperimentalOption("prefs",chromePrefs);

WebDriver driver = new ChromeDriver(options);
```

# MAVEN:
Apache Maven is a software project management and build management tool for Java Framework.

## Why Maven
1. Central repository to get dependencies: To run any project, we need to have some JARs installed in our machine and added to the project. But if we need to updgrade/downgrade or share the project with other members, then they will have to download all the JARs seperately and then run the project. MAVEN instead has a centralized repository of dependencies, from where all the JARs of all the versions can be downloaded and installed to the project by just entering some basic details of the JAR in the project. The same JARs will also be installed in other machines as well where the project is shared.
2. Maintaining common structure accross the organization: MAVEN provides a common template which can be maintained across all the platform. 
3. Flexibility in integrating with CI tools
4. Plugins for Test framework execution.

## MAVEN Repository:
All the JARs are present here.
- groupId - project name
- artifactId - JAR name
- version

## Maven SureFire Plugin:
This plugin is used to execute all the test cases.

This needs to be added in the pom.xml file above the dependencies tag.\
**Syntax:**
```xml
<build>
<pluginManagement>
    <plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.5.3</version>
    </plugin>
    </plugins>
</pluginManagement>
</build>
```

## Maven Commands:
1. mvn clean 	-> 	cleans the project
2. mvn compile 	-> 	compiles the code and returns error if there is any syntax error in the code.
3. mvn test		-> 	executes the test cases. On execution, it will check for all the dependencies in the local maven repository. If the JARs are not present the in local repo, it will connect with the maven repository and then download all the required JARs.

**Note:** 
- If we do not use clean and compile before the test, even then it will check for all the syntax errors and clean the build by default.
- For MAVEN to pick the test files, all the test files should end with 'test', as MAVEN search algorithm searches for all the class files ending with the word 'TEST'. 

## Integration of TestNG with MAVEN:
Add to pom.xml:\
**Syntax:**
```xml
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-surefire-plugin</artifactId>
<version>3.5.3</version>
<configuration>
    <suiteXmlFiles>
    <suiteXmlFile>testng.xml</suiteXmlFile>
    </suiteXmlFiles>
</configuration>
</plugin>
```
