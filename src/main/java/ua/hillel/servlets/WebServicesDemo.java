package ua.hillel.servlets;

public class WebServicesDemo {

    // web service - ?
    // API - application programming interface -> real application,
    // 2 types of API - low level or high level
    // low level API - linux Kernel API
    // high level API -  how to communicate? http request! command line (curl), browser, Postman
    // endpoints - unique path, findPhotoById -> id, returns a photo
    // request -> endpoint -> service -> repository -> DB <--- return the response to client
    // Approaches to organize the API:
    // SOAP (XML-based), REST (JSON-based), RPC - remote procedure call, 1 endpoint , GraphQL
    // REST (Representational state transfer) - architectural style, claims that each operation should
    // have separate endpoint. JSON, XML, CSV, XLS/XLSX, JPG,JPEG, byte[], text
    // REST principles:
    // 1. Client-server - client (UI, Postman etc.), server (written using 1 of the languages ,e.g. Java, Python, Ruby etc.)
    // 2. Uniform interface - /api/userManagement/user/1 /api/user-management/users/2
    // response body contains references to the all other endpoints of the same web-service (HATEOAS)
    // 3. Stateless - server uses only the information that was sent by user and
    // never stores it anywhere (cookie, cache, nowhere in the server)
    // 4. Cache - increase performance
    // 5. Layered system - 1 controller 2 service 3 jdbcOperations
    // Once following all the principles of REST, the web-app is called RESTful
    // create REST/RESTful web-service
    /*
    * {
    * {
    *   "name": "Vladyslav",
    *   "roles": [USER, ADMIN]
    * },
    * references: [
    *       Get all users: /api/user-management/users,
    *       create new user: /api/user-management/users,
    *       delete user by id: /api/user-management/users/{userId}
    * ]
    * }
    * */
}
