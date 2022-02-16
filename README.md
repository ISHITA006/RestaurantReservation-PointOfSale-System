# CZ 2002 Project - Restaurant Reservation and Point-of-Sale System (RRPSS)

## Objectives
The main objective of this assignment is  
-to apply the Object-Oriented (OO) concepts you have learnt in the course  
-to model, design and develop an OO application  
-to gain familiarity with using Java as an object oriented programming language  
-to work collaboratively as a group to achieve a common goal   

## Information
1. Menu items should be categorized according to its type, eg, Main course, drinks,
dessert, etc.
2. Menu items can be added with details like name, description, price, etc.
3. Promotional set package comes in a single package price with descriptions of the
items to be served.
4. A customer may order a set package or ala carte menu items.
5. An order should indicate the staff who created the order.
6. Staff information can be in the form of name, gender, employee ID and job title.
7. Reservation is made by providing details like date, arrival time, #pax, name, contact
number, etc and the table status is ‘reserved’. The system should check availability
and allocate a suitable table.
8. Contact number is used to identify reservation.
9. When a reservation is made, the table is reserved till the reservation booking is
removed (eg time expired). Once a table is reserved, it cannot be booked for that
particular session (AM/PM).
10. Once an order is entered, the table status is ‘occupied’*.
11. Once an order invoice is printed, it is assumed that payment has been made and the
table statue is ‘vacated’*.
12. Table comes in different seating capacity, in even sizes, with minimum of 2 and
maximum of 10 pax ("Persons At Table").
13. Order invoice can be printed to list the order details (eg, table number, timestamp)
and a complete breakdown of order items details with taxes details.
14. Sale revenue report will detail the period, individual sale items (either ala carte or
promotional items) and total revenue. 

## Functional Requirements
1. Create/Update/Remove menu item
2. Create/Update/Remove promotion
3. Create order
4. View order
5. Add/Remove order item/s to/from order
6. Create reservation booking
7. Check/Remove reservation booking
8. Check table availability
9. Print bill invoice
10. Print sale revenue report by period (eg day or month) 

## Assumptions
1. Reservation can only be made in at most 1 month in advance.
2. Reservation will be automatically removed if the guest does not arrive within XX minutes
(eg 30 minutes) after the stated arrival time*.
3. The currency will be in Singapore Dollar (SGD) and Good and Services Tax (GST) and
service charge must be included in the order invoice.
4. There is no requirement for access control and there is no need for authentication
(login/logout) in order to use the application.
5. There is no need to interface with external system, eg Payment, printer, etc. Payment via
credit card will always be successful.
6. The restaurant has 30 tables – 5 x 10-seats, 5 x 8-seats, 10 x 4-seats, 10 x 2-seats.
7. Tables cannot be combined/joined (eg, join 3 x 2-seats table to form 6-seats table) and the
table ID is fixed. 
