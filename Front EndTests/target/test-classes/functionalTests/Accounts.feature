#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Account
  I want to use this template for my feature file

  @tag1
  Scenario: Create an account
    Given I am on the home page
    When I navigate to the login page
    And Enter some account details
    And login
    Then I will be taken to the account page
    And my username will be correctly displayed

  @tag1
  Scenario: Update my name
    Given I am on the account page
    When I click "update name"
    And I submit a new name
    Then My new name will be displayed
    
  @tag2
  Scenario: Update my username
    Given I am on the account page
    When I click "update username"
    And I submit a new username
    Then My new username will be displayed
    
  @tag3
  Scenario: change my password
    Given I am on the account page
    When I click "change password"
    And I submit a new password
    Then the password changed successfully alert appears
    When I click "ok"
    Then I will return to the account page
