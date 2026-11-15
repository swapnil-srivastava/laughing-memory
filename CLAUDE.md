# laughing-memory

Spring Boot 3.2.2 on Java 21, built with Maven, targeting Heroku + Red Hat OpenShift. Owner: Swapnil
(`swapnil-srivastava`). Conference demo API with JPA on Postgres: `/api/v1/sessions`, `/api/v1/speakers` (CRUD), `/hello-spring`, `/hello-kafka`, `/version`, plus the actuator. Kafka client libraries are on the classpath but no producer or listener is wired yet.

## Commands

- Build and test: `./mvnw -B package` (what CI runs). Tests only: `./mvnw -B test`.
- Run locally: `./mvnw spring-boot:run` (port 8080). Health: `GET /actuator/health`.
- Docker: `./mvnw -B package && docker build -t swapnilsrivastava/heroku .` (the Dockerfile copies `target/*.jar`).
- The devcontainer has Java 21, Maven and Docker.

## Layout

- Code: `src/main/java/eu/swapnilsrivastava/swapnilsrivastava/`, base package
  `eu.swapnilsrivastava.swapnilsrivastava`. Tests mirror it under `src/test/java`.
- Config: `src/main/resources/application.properties`. `application.properties` points at a local Postgres (`conference_app`, see README for the Docker setup and `postgres/` + `mysql/` for the table and seed SQL). `spring.jpa.hibernate.ddl-auto=none`, so schema changes are SQL files in `postgres/` (and `mysql/`), not entity changes alone. `application-dev.properties` / `application-prod.properties` are profile overrides. `docker-compose.yml` starts a local Kafka broker and schema registry.

## CI and deploy

- `Java CI with Maven` runs on every push and pull request to `main`.
- When it succeeds on `main`, `Publish Docker image` pushes `swapnilsrivastava/heroku` to Docker Hub.
- Heroku builds from the repo (`system.properties` pins Java 21); the known URL is in the README. CI also pushes a Docker image. There is no deploy workflow in the repo.

## Things to know before changing anything

- **JDK mismatch:** `pom.xml` builds for Java 21 but the `Dockerfile` runs on `openjdk:17.0.2-jdk-slim` (Java 17), so the pushed image cannot start the jar. Known and not yet fixed; fix it (e.g. `eclipse-temurin:21-jre`) only when asked or when the task is to get the image running.
- The local database password in `application.properties` and the README is a throwaway for the local Docker container. Real credentials must come from env vars (the Heroku Java buildpack provides `JDBC_DATABASE_URL`, `JDBC_DATABASE_USERNAME`, `JDBC_DATABASE_PASSWORD`); never commit them. Note `application.properties` currently hard-codes the localhost database, so a deployed copy only reaches a database if those properties are overridden there.
- Secrets (database, Kafka, Docker Hub, cloud keys) live in GitHub secrets or the cloud
  console. Never commit or print them; tell Swapnil which ones to add.
- Don't deploy, create cloud resources or change cloud settings yourself. Open a PR
  and say what merging it will trigger.

## How this fits with the website

The main project is the website, `swapnil-srivastava/reimagined-guide`
(swapnilsrivastava.eu, Next.js + Supabase on Vercel). This repo is one of six small
backend services kept beside it as cloud/framework experiments. The website does not
depend on it for anything visitors use today. The agreed way to wire a backend in
later is a Next.js rewrite on the website with the backend URL in an env var
(`/svc/<name>/...`), so the browser never calls this service directly and CORS is
not needed.

For step-by-step work (new endpoint, config, shipping) use the `spring-service` skill
in `.claude/skills/`.
