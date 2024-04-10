# ScratchCard Service for Shopping Application

## Problem Statement
You are required to build ScratchCard module for a Shopping application. The application have following tables:
1. Users (ID, UserID, Firstname, Lastname, IsActive, Password)
2. Items(ID, Name, Rate, IsActive)
3. ScratchCards (ScratchCardGUID, Discount, ScratchCardExpiryDate, IsActive) 

Each ScratchCard is allocated to a Single User and can be used on a single item. i.e. Assuming that a user is ordering 8 items on a single order, and he has 4 different cards allotted to him, he will be able to use one card per Item.


## Requirements
1. Define an API GenerateScratchCards to
    - Generate X numbers of new scratch cards with expiry date of Y days after the day of generation. Value for X and Y will need to be passed to API request.
    - All existing unused scratch cards will need to deactivate during new generation based on flag parameter passed in the Request body.
2. Define an API AllocateScratchCardToUser to
    - Link user to un-used ScratchCard. Single scratch card can be assigned to User at a time.
    - User can have zero or more scratch cards assigned to him over a period of time.
4. Define an API OrderTransaction that should
    - Accept an Order from a valid, active User.
    - Order request should take one or more Item ID for a valid item, Qty and ScratchCardID (optional) and calculate amount after applying Discount where applicable.
    - On successful order creation, it should return the total amount payable by the User.
