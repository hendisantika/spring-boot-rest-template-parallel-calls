# spring-boot-rest-template-parallel-calls

A simple Spring Boot app showcasing how to make parallel API calls using RestTemplate and CompletableFuture, which can
also be used to implement the scatter-gather design pattern.

### Things todo list

1. Clone this repository: `git clone https://github.com/hendisantika/spring-boot-rest-template-parallel-calls.git`
2. Navigate to the folder: `cd spring-boot-rest-template-parallel-calls`
3. Run the application: `mvn clean spring-boot:run`
4. Import Postman Collection file

### Check console log

#### Endpoint to trigger single data fetching

Request

```shell
curl --location 'localhost:8080/api/fetchData'
```

Response

```shell
{
    "id": "1",
    "message": "Mock data for ID 1"
}
```

#### Endpoint to trigger parallel data fetching

Request

```shell
curl --location 'localhost:8080/api/parallel'
```

Response

```shell
[
    {
        "id": "1",
        "message": "Mock data for ID 1"
    },
    {
        "id": "2",
        "message": "Mock data for ID 2"
    },
    {
        "id": "3",
        "message": "Mock data for ID 3"
    }
]
```

#### Endpoint to trigger parallel data fetching with exception in one task

Request

```shell
curl --location 'localhost:8080/api/parallelWithExceptionIOneTask'
```

Response

```shell
[
    {
        "id": "1",
        "message": "Mock data for ID 1"
    },
    {
        "id": "3",
        "message": "Mock data for ID 3"
    }
]
```

#### Endpoint to trigger parallel data fetching with a global timeout

Request

```shell
curl --location 'localhost:8080/api/parallelWithGlobalTimeout'
```

Response

```shell
[]
```

#### Endpoint to trigger parallel data fetching with individual task timeout

Request

```shell
curl --location 'localhost:8080/api/parallelWithIndividualTaskTimeout'
```

Response

```shell
[]
```
