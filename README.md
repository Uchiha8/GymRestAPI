## Gym Spring MVC REST API Application

### How to run

* Configure java 21 version
* Developed using PostgresSQL relational database(databse name = 'hibernate', password = #####)
* Recommended Tomcat 10.1 server in order to deploy our artifact files

### Architecture of Application

#### Domain (Domain is a package which includes 5 entites of our model)

  * User
  * Trainee
  * Trainer
  * TrainingType
  * Training

#### DTO (DTO is a package which includes our Request and Response dto inorder to hide our actual models, as we know our dto classes should me immutable class therefore they are implemented in record class type) 

#### Request

* StatusRequest
* ChangeLogin
* Login
* TraineeRegistrationRequest
* TraineeTrainingsRequest
* TrainerRegistrationRequest
* TrainerTrainingsRequest
* TrainingRequest
* UpdateTraineeRequest
* UpdateTrainerRequest

#### Response

* RegistrationResponse
* TraineeList
* TraineeProfileResponse
* TraineeTrainingsResponse
* TrainersList
* TrainerProfile
* TrainerTrainingsResponse
* UpdateTraineeResponse
* UpdateTrainerResponse

#### Repository (Repository is layer which stands between Database and Busnees logic layer. Implemented using Hibernate ORM using SessionFactory interface I executed crud operations on modules)

* UserRepository
* TraineeRepository
* TrainingRepository
* TrainingTypeRepository
* TrainerRepository

#### Service (Service layer which is localed between DAO and Controller. The main purpose of this layer is hide complaxity and busines logic of the request from client side. I mean Controller is for only handling request and return responce toward REST APIs. However, service classes is only for busines logic part)

* UserService
* TraineeService
* TrainerService
* TrainingService
* TrainingTypeService

#### Controller (Controller layer is for handling request from client side and return proper Response account condition of the result it provides proper status code and proper logger message about success or failer of operations to depeloper)

*  UserController
*  TraineeController
*  TrainerController
*  TrainingController
* TrainingTypeController

#### Configuration (In case it is not SPring Boot therefore all configurations implemented manually, database connections, and configuration with tomcat server in order to deploy our jar file of project to localhost and send request to RestAPI)

* AppConfiguration
* SpringInitializer

#### resources (In resources class path I have mainly two configuration file)

* hibernate.cfg.xml (This configuration file mainly for connection with PostgresSQL database and Mapping entities into table)
* log4j2.xml (Logger configuration)

### Required Tasks for this module
 1. During Create Trainer/Trainee profile username and password should be generated as 
  described in previous modules.
 2. Not possible to register as a trainer and trainee both.
 3. All functions except Create Trainer/Trainee profile. Should be executed only after 
  Trainee/Trainer authentication (on this step should be checked username and password 
  matching).
 4. Implement required validation for each endpoint.
 5. Users Table has parent-child (one to one) relation with Trainer and Trainee tables.
 6. Training functionality does not include delete/update possibility via REST
 7. Username cannot be changed.
 8. Trainees and Trainers have many to many relations.
 9. Activate/De-activate Trainee/Trainer profile not idempotent action.
 10. Delete Trainee profile is hard deleting action and bring the cascade deletion of relevant 
  trainings.
 11. Training duration have a number type.
 12. Training Date, Trainee Date of Birth have Date type.
 13. Is Active field in Trainee/Trainer profile has Boolean type.
 14. Training Types table include constant list of values and could not be updated from the 
  application.
 15. Implement error handling for all endpoints.
 16. Cover code with unit tests.
 17. Two levels of logging should be implemented:
    1. Transaction level (generate transactionId by which you can track all operations 
    for this transaction the same transactionId can later be passed to downstream 
    services)
    2. Specific rest call details (which endpoint was called, which request came and the 
    service response - 200 or error and response message) 
 18. Implement error handling.
 19. Document methods in RestController file(s) using Swagger 2 annotations



