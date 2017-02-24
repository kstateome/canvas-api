# Canvas API Library for Java
This is a Java library which wraps the [Canvas LMS REST API](https://canvas.instructure.com/doc/api/index.html). It allows you to make requests to the API using Java domain objects while the low level details of the HTTP requests, authentication, etc are taken care of for you.

Some specific features include:
* Authentication using a supplied [OAuth token](https://canvas.instructure.com/doc/api/file.oauth.html)
* Handling of [API pagination](https://canvas.instructure.com/doc/api/file.pagination.html) 
  * Ability to specify pagination page size (Canvas default is 10)
  * Optional callback procedure that gives you data chunks as they come in without having to wait for the entire request to finish
* Masquerading as other users by Canvas user ID or SIS ID
* Requesting items by Canvas ID or SIS ID

## Requirements
To use this library, you must be using Java 8. We are making extensive use of [Java Streams](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html) as well as the [Optional](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html) class.

If you wish to contribute, you will need to be familiar with [Maven](https://maven.apache.org/) to build the project. [The Spring Framework](http://spring.io/) is also used but only for testing.

## Under Construction
This code is currently under very active development. We are implementing API calls as we need them for K-State applications so a lot of calls are still missing. However the core functionality of talking to Canvas is well established so adding individual API calls is not a difficult task. Given the [number of API calls](https://canvas.instructure.com/doc/api/all_resources.html) that Canvas provides and the rate at which they add more and change others, we may never achieve 100% parity on all niche features. But basic operations on accounts, users and courses should be stable over time.

## Library structure
The top level object needed to execute API calls is the `CanvasApiFactory` class which takes a Canvas instance URL to construct. From there you can request reader and writer objects which provide the functionality to actually execute calls to the Canvas API.

API calls are grouped into reader and writer objects by the object type they handle. So any call that returns `User` objects or a list of `User` objects will be found in the `UserReader` class. For example, both the account level "[List users in account](https://canvas.instructure.com/doc/api/users.html#method.users.index)" and the course level "[List users in course](https://canvas.instructure.com/doc/api/courses.html#method.courses.users)" API calls are accessed through the `UserReader` class.

## Usage
Every API call requires an OAuth token for authentication. It is up to you to acquire this token on behalf of the user. For LTI applications, this is typically done during the LTI launch process. Tokens can also be manually issued from a user's account settings page. More details on OAuth can be found in the [Canvas OAuth2 documentation](https://canvas.instructure.com/doc/api/file.oauth.html)

### Basic Query
Once you have a token, executing an API call can be as simple as creating a new `CanvasApiFactory`, getting a reader object from it and executing a call. A short example of retrieving an object representing the root Canvas account:

    String canvasBaseUrl = "https://<institution>.instructure.com";
    OauthToken oauthToken = new NonRefreshableOauthToken("mYSecreTtoKen932781");
    CanvasApiFactory apiFactory = new CanvasApiFactory(canvasBaseUrl);
    AccountReader acctReader = apiFactory.getReader(AccountReader.class, oauthToken);
    Account rootAccount = acctReader.getSingleAccount("1").get();

### API Options
Many of the API methods take some form of method-specific *Options* class as a parameter. This is done instead of just accepting multiple (possibly null) parameters because some API calls have a gigantic list of optional parameters. It also insulates the users of this library from compilation breaking changes just because Canvas added a new optional parameter to an API call. Information required to execute the API call is passed into the option class constructor while optional parameters are added to the option class via methods in a pseudo-builder pattern. For example the "[Get Users in Course](https://canvas.instructure.com/doc/api/courses.html#method.courses.users)" method has one required piece of information (`course ID`) and 8 optional parameters. To query all students and observers who match the search term "John" you would do as follows:

    GetUsersInCourseOptions options =
        new GetUsersInCourseOptions("1234") //required course ID
        .searchTerm("John")
        .enrollmentType(Arrays.asList(EnrollmentType.STUDENT, EnrollmentType.OBSERVER));
    List<User> users = userReader.getUsersInCourse(options);

### Masquerading
Canvas allows you to execute API calls while masquerading as another user. In order to do this, your OAuth token must have sufficient privileges. Details can be found in the [Canvas documentation](https://canvas.instructure.com/doc/api/file.masquerading.html).

To execute a read API call while masquerading, simply add a call to the `readAsCanvasUser("<canvas user ID>")` or `readAsSisUser("<SIS user ID>")` method on the reader object. Corresponding methods also exist in writer objects

    acctReader.readAsSisUser("1234567").getSingleAccount("1");

### Using SIS IDs
Many Canvas objects can be queried either by their numeric Canvas ID or by the SIS ID assigned to that object during the SIS to Canvas import process. Details can be found in the [Canvas documentation](https://canvas.instructure.com/doc/api/file.object_ids.html)

To request an object by its SIS ID, prepend the ID with the appropriate string, followed by a colon. For an account:

    acctReader.getSingleAccount("sis_account_id:12345");

### Pagination
All API calls that return a list of objects have the potential to require pagination to get all of the requested objects. If no `per_page` parameter is specified on a request, Canvas defaults to only returning 10 objects at a time which can cause problems if you are trying to query large lists in a timely fashion. Details on pagination can be found in the [Canvas documentation](https://canvas.instructure.com/doc/api/file.pagination.html)

To specify the pagination page size to be used on calls, an additional parameter is passed into the `CanvasApiFactory` when requesting a reader object. Note that while you can request any number you like, Canvas can also choose to ignore this number and return however many objects it wants. Currently, hosted instances of Canvas are set to max out at 100 objects per page but this is subject to change without warning. To set the pagination page size to 100:

    UserReader userReader = apiFactory.getReader(UserReader.class, oauthToken, 100);

All requests made using this `UserReader` object will request 100 objects per page.

### Pagination Callbacks
Calls that return a large list of objects can take a while even if you set the pagination page size to 100. Querying certain lists in large courses may take 15+ seconds. If you are trying to do this in real time to display to a user, you may want to take chunks of the data as they come in from Canvas and either display the partial list to the user or do some back end processing so that your results will be ready to display more quickly once the full list is done downloading. For this situation, the library provides optional callbacks for reader methods which will call a method you specify every time a page of results comes back from Canvas and pass the partial list in so you can start processing. To use this feature, it looks like this:

    GetUsersInCourseOptions options = new GetUsersInCourseOptions("1146");
    List<User> courseUsers = userReader.withCallback(this::processUserPage).getUsersInCourse(options);
    System.out.println("Total users in course: " + courseUsers.size());

    private void processUserPage(List<User> users) {
        System.out.println("Got a page of users back: " + users.size());
    }

If this code is run on a course with 325 users it would print the following:

    Got a page of users back: 100
    Got a page of users back: 100
    Got a page of users back: 100
    Got a page of users back: 25
    Total users in course: 325

## License
This software is licensed under the LGPL v3 license. Please see the [License.txt file](License.txt) in this repository for license details.
