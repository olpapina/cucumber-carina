Feature: Swag Labs testing
  In order to use Cucumber in my project, I want to check processes of ordering on saucedemo.com

  @demo
  Scenario: Swag Labs checkout process is successful completed  - passing
    Given I am on login page as standard user
    When I log in using credentials from DB
    Then page 'Products' should be open
    And page 'Products' should contains 6 items

    Then I add the products from DB in a cart
    When I go to 'Cart' page
    Then page 'Cart' should be open
    And page 'Cart' should contains added products

    When I click Checkout button
    Then page with user data should be open
    When I enter data
    Then page 'Overview' should be open
    And  page 'Overview' should contains added products
    When I click 'Finish' button
    Then page 'Complete' should be open
    And  page 'Complete' should contains successful message

  Scenario: Swag Labs cannot login by locked user - passing
    Given I am on Login page as locked user
    When I try to log in my account
    Then I am still on login page
    And Error message is present on the page


