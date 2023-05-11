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


  Scenario: Swag Labs Error message appears during checkout process- passing
    Given I am on Login page as problem user
    When I log in using credentials
    Then page 'Products' is opened
    And page 'Products' should contains 6 the same pictures instead of different product images

    When I click 'Add to cart' button for products from DB
    Then products are in the cart, check the quantity

    When I click 'Cart' button
    Then 'Cart' page is opened
    And 'Cart' page should contains added products
    When I click 'Checkout' button
    Then User data page should be open
    When I add user info
    And click Continue button
    Then Error message appears on the page
