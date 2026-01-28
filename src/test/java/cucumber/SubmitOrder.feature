@tag
Feature: Purchase the order from Ecommerce website

Background:
Given I landed on the Ecommerce Page

@RegressionTests
Scenario Outline: Positive Test of submitting the order
Given Loggedin with username <name> and password <password>
When I add product <productName> to cart
And Checkout <productName> and submit the order
Then "Thankyou for the order." message is displayed on confirmationPage

Examples:
| name				     |  password   | productName |
| tonybash@gmail.com	 |	P@ssword1  | ZARA COAT 3 |

