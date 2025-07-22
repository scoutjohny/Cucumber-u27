Feature: Login
  As a user I should be able to use login form

  Scenario: Login with valid credentials
  As a user I should be able to login using valid username and password

    #preduslov za izvršavanje testa
    Given I am on the sauce demo login page

    #sam test
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click on the login button

    #asertacija - provera rezultata testiranja
    Then I should be logged in
    And I should be able to see products


  Scenario: Login with invalid username
  As a user I should not be able to login using invalid username

    Given I am on the sauce demo login page
    When I enter username "standard_user1"
    And I enter password "secret_sauce"
    And I click on the login button
    Then I should get error message "Epic sadface: Username and password do not match any user in this service"


  Scenario: Login with invalid password
  As a user I should not be able to login using invalid password

    Given I am on the sauce demo login page
    When I enter username "standard_user"
    And I enter password "secret_sauce1"
    And I click on the login button
    Then I should get error message "Epic sadface: Username and password do not match any user in this service"


  Scenario Outline: Login with invalid credentials
  As a user i should not be able to login using invalid username and/or password

    Given I am on the sauce demo login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I click on the login button
    Then I should get error message "<errorMessage>"

    Examples:
      | username       | password      | errorMessage                                                              |
      | standard_user1 | secret_sauce  | Epic sadface: Username and password do not match any user in this service |
      | standard_user  | secret_sauce1 | Epic sadface: Username and password do not match any user in this service |
      | standard_user1 | secret_sauce1 | Epic sadface: Username and password do not match any user in this service |
      |                | secret_sauce  | Epic sadface: Username is required                                        |
      | standard_user  |               | Epic sadface: Password is required                                        |
      |                |               | Epic sadface: Username is required                                        |