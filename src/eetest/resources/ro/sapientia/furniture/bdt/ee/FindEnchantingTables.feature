Feature: Check if the find all endpoint works
    As an enchanting table user
    I want to be able to see all the enchanting tables

    Scenario: One element
    Given that we have the following enchanting tables:
    | magicLevel | material | owner    |
    | 20         | wood     | John Doe |
    When I invoke the enchanting table all endpoint
    Then I should get the material "wood" for the position "0"

    Scenario: Two elements in the table
    Given that we have the following enchanting tables:
    | magicLevel | material | owner      |
    | 20         | wood     | John Doe   |
    | 30         | stone    | Jane Smith |
    When I invoke the enchanting table all endpoint
    Then I should get the owner "Jane Smith" for the position "1"
