## MidTerm June 2024 Solutions

1. B
2. B
3. C
4. A
5. A
6. A
7. A
8. A
9. AccessToken is a short-lived string which is used to Authorize a client to access protected resources. It is usually set in the headers for every request.
- A refreshToken is a long-lived string which is used to obtain a new access token when it expires. It reduces the need for a user to keep logging in repeatedly.
10. Transit entity is an Object that has just been created and instantiated with the new keyword. At this point it is not stored in the database and is not associated with the persistence context. It is not yet managed by hibernate.
- Persist(managed) entity is an Object that has been stored in the database and is associated with the persistence context. In this stage, changes made to an object are synchronized with the database when a unit of work is completed.
11. We need LAZY loading when we do not want to fetch all children associations every time we fetch the parent. This ensures better performance and reduced memory consumption since child associations will be fetched only when they are requested.
12. - Scalability: stateless authentication does not store sessions on the server thus allowing applications to handle more clients efficiently. Since each request contains all the necessary information(JWTs), requests can be processed independently making it easier to scale across multiple servers.
- Improved Performance and Reduced Server Load: Since there is no need for session management, the server does not need to perform database lookups or maintain in-memory states, hence reducing overhead.
Overall, stateless authentication makes requests faster and it is ideal for distributed and microservices.
