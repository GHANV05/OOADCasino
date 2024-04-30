Feature: Casino StartMenu

  Scenario: Casino Register
    Given the user registers a new account
    When the user has not created an account previously
    Then the user should be able to create a customizable profile with First/Last names, account balance of $1000, and no win/loss record

  Scenario: Casino Login
    Given the user logins to an old account
    When the user has had a history of games and customized information
    Then the user should be able to retrieve their backed-up profile with First/Last names, account balance updated to most recent login, and an accurate win/loss record

Feature: Blackjack

  Scenario: Playing a Game of Blackjack
    Given the user’s account balance is 1000
    When the user wins or loses a game of Blackjack
    Then the user’s account balance should change depending on game result
    Then the user’s win/loss ratio should increase/decrease depending on game result

Feature: Slot Machine

  Scenario: Winning a Game of Blackjack
    Given the user’s account balance is $1000
    When the user wins or loses a game of slot machine
    Then the user’s account balance should change depending on game result
    Then the user’s win/loss ratio should increase/decrease depending on game result





