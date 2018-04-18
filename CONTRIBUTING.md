Here are some guidelines if you are considering contributing code to this library

Code Style
----
Our team generally follows the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) which seems to be a fairly common default in most IDEs.  
A few things to highlight:
* 4 spaces (not tabs) used for indentation
* UNIX style line endings (watch out for git and/or editors silently converting line endings on Windows!)
* Keep getter/setter pairs together

Documentation
----
Methods exposed for public use should have good javadoc style comments on them. This includes:
* A brief description of the functionality provided by the method
* `@param` descriptions of method parameters
* `@return` descriptions of objects returned
* `@throws` descriptions of error conditions that result in exceptions

If methods are defined in an interface, the javadoc should go in the interface, not the implementing classes.

Submitting Changes
----
GitHub pull requests are the preferred way of getting your changes back into our upstream repository however we can also work with emailed git patches if needed.
Please review your changes before submitting them to make sure unintended changes did not get committed by accident (editor config files, test output, API keys, etc)

For small requests, we will try to review and merge or comment within 3 business days.
Larger requests may be delayed so as not to interrupt our planned work for the current sprint.
We will still try to review any submissions as quickly as possible but final merging may take up to 2 weeks.

We do not have a set release schedule for releasing changes to the Maven central repo.
If you are depending on public hard releases for your own builds, feel free to mention this in the pull request discussion and we can be sure to get a release built quickly.
