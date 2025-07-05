# spring-boot-rest-template-parallel-calls

A simple Spring Boot app showcasing how to make parallel API calls using RestTemplate and CompletableFuture, which can
also be used to implement the scatter-gather design pattern.

## Parallelism vs Concurrency

### Parallelism

- **Definition**: Parallelism is about doing multiple things simultaneously.
- **Execution**: Tasks are literally executed at the same time on multiple processors or cores.
- **Resources**: Requires multiple processors or cores to achieve true parallelism.
- **Focus**: Focuses on maximizing throughput and computational efficiency.
- **Example**: Processing different parts of a large dataset simultaneously on multiple CPU cores.

### Concurrency

- **Definition**: Concurrency is about dealing with multiple things at once.
- **Execution**: Tasks may start, run, and complete in overlapping time periods, but they might not be running at the
  exact same instant.
- **Resources**: Can be achieved even with a single processor through context switching.
- **Focus**: Focuses on managing access to shared resources and improving responsiveness.
- **Example**: A web server handling multiple client requests, switching between them as needed.

### Key Differences

- Parallelism is about **execution** (doing multiple things at once), while concurrency is about **structure** (handling
  multiple things at once).
- Parallelism requires hardware with multiple processing units, while concurrency can be achieved through software
  design.
- Parallelism aims to increase throughput and computational efficiency, while concurrency aims to improve responsiveness
  and resource utilization.

In this project, we use Java's CompletableFuture to achieve both concurrency and parallelism when making API calls.

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
