@tag
Feature: Error Validation


@ErrorValidation
Scenario Outline: Negative test of verifying the error message upon login with incorrect credentials
Given I landed on the Ecommerce Page
When Loggedin with username <name> and password <password>
Then "Incorrect email or password." message is displayed

Examples:
| name				     |  password   | 
| tonybash@gmail.com	 |	P@sswor    |

