# Employee Management Application

This Employee Management Application provides functionality for managing employee data, including saving employee records and retrieving eligible employees based on specific dates. It includes a RESTful API built with Spring Boot and uses JPA for database interactions.

## Features

- **Save Employees**: Save employee details to the database.
- **Retrieve Eligible Employees**: Fetch employees eligible for bonuses based on their joining and exit dates.

## Technologies Used

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL Database** 
- **JUnit** (For testing)

## Project Structure

- **models**: Contains entity classes for `Employee` and `Department`.
- **dto**: Contains Data Transfer Object classes for handling data.
- **repositories**: Contains repository interfaces for `Employee` and `Department`.
- **services**: Contains service classes implementing business logic.
- **controllers**: Contains RESTful controllers for handling HTTP requests.
- **exceptions**: Contains global exception handler.

## Getting Started

### Prerequisites

- **Java 11** or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/Kapil7982/test.git
```
2. Navigate to the project directory:
```bash
cd EmployeeManagement
```
## API Endpoints
- **Save Employees**
- URL: /tci/employee-bonus
- Method: POST
- Request Body:
```bash
  {
    "employees": [
        {
            "empName": "raj singh",
            "department": "accounts",
            "amount": 5000,
            "currency": "INR",
            "joiningDate": "May-20-2022",
            "exitDate": "May-20-2023"
        },
        {
            "empName": "pratap m",
            "department": "accounts",
            "amount": 3000,
            "currency": "INR",
            "joiningDate": "Jan-01-2021",
            "exitDate": "May-20-2023"
        },
        {
            "empName": "sushmita lal",
            "department": "IT",
            "amount": 4000,
            "currency": "INR",
            "joiningDate": "Jan-01-2021",
            "exitDate": "Dec-31-2021"
        },
        {
            "empName": "sam",
            "department": "Operations",
            "amount": 2500,
            "currency": "USD",
            "joiningDate": "May-20-2022",
            "exitDate": "May-20-2023"
        },
        {
            "empName": "john",
            "department": "Operations",
            "amount": 2500,
            "currency": "USD",
            "joiningDate": "Jan-20-2023",
            "exitDate": "Dec-30-2024"
        },
        {
            "empName": "susan",
            "department": "IT",
            "amount": 700,
            "currency": "USD",
            "joiningDate": "Jan-01-2022",
            "exitDate": "Dec-31-2022"
        }
    ]
 }
```

- Response: Employees saved successfully

- **Retrieve Eligible Employees**
- URL: /tci/employee-bonus
- Method: GET
- Query Parameter: date (format: MMM-dd-yyyy)
- Response:
```bash
{
	"errorMessage": "",
	"data": [
	  {
			"currency": "INR",
			"employees": [
				{
					"empName": "pratap m",
					"amount": 3000
				},
				{
					"name": "raj singh",
					"amount": 5000
				}
			]
		},
		{
			"currency": "USD",
			"employees": [
				{
					"empName": "sam",
					"amount": 2500
				},
				{
					"empName": "susan",
					"amount": 700
				}
			]
		}
	]
}
```
## Sample Test Data
### Sample request body for saving employees:
```bash
{
  "employees": [
    {
      "empName": "John Doe",
      "department": "Marketing",
      "amount": 5000,
      "currency": "USD",
      "joiningDate": "Jan-01-2024",
      "exitDate": "Dec-31-2024"
    },
    {
      "empName": "Jane Smith",
      "department": "Sales",
      "amount": 6000,
      "currency": "EUR",
      "joiningDate": "Feb-15-2024",
      "exitDate": "Dec-31-2024"
    }
  ]
}
```

