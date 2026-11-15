---
name: spring-service
description: Use when adding or changing endpoints, configuration, dependencies, tests, the Dockerfile or CI in this Spring Boot service (laughing-memory, Java 21, Maven, Heroku + Red Hat OpenShift). Covers where code goes, how to test, and what merging to main triggers.
---

# Working on laughing-memory

Read `CLAUDE.md` first for what this service is and its known issues.

## Adding an endpoint

1. Controller in `controller/` (`@RestController`), logic in a `@Service` under
   `services/`, data access in a repository. Create the package if it does not exist
   yet, under `eu.swapnilsrivastava.swapnilsrivastava`.
2. Constructor injection, not field `@Autowired`. Return `ResponseEntity` or a typed
   record/DTO; no raw `Map` or raw types in new code.
3. Paths are lower-case with hyphens. Versioned resource APIs go under `/api/v1/...`.
4. Validate input (`spring-boot-starter-validation` + `@Valid` if a body is taken;
   add the starter when first needed).
5. Add a test: `@WebMvcTest` for a controller with mocked services, plain JUnit for
   logic. Keep `contextLoads` passing.
6. This service is reachable from the internet once deployed. Anything that writes
   data, sends messages or costs money needs authentication; ask Swapnil how before
   exposing it.

## Configuration

- Environment-specific values are `${ENV_VAR}` placeholders in
  `application.properties`, with a safe local default where one makes sense
  (`${PORT:8080}`). No secrets or cloud hostnames in the repo.
- List every new env var or GitHub secret in the PR description so Swapnil can set it.

## Database and Kafka (this repo)

- Entities in `model/`, Spring Data repositories in `repository/`, controllers in
  `controller/`. `ddl-auto=none`: a new column or table needs SQL in
  `postgres/create_table.sql` (and `mysql/`) as well as the entity change.
- Controllers currently return entities directly and use `saveAndFlush`; keep that
  style for small changes, and validate ids/bodies on anything new.
- Local Kafka: `docker compose up -d` then create topics with `docker exec broker ...`
  as in the README. A producer/consumer should read its bootstrap server from a
  property with a local default, never a hard-coded cloud address.

## Dependencies

Let the Spring Boot parent manage versions; add `<version>` only for things outside
its BOM. A Spring Boot upgrade is its own PR.

## Before you say it works

- `./mvnw -B package` passes locally, with the real output reported. If it cannot
  run here (no network for Maven, missing env vars), say exactly that instead of
  claiming green.
- For a Dockerfile change, build the image and start it once.

## Shipping

- Work on the session's branch, open a PR to `main`. CI (`Java CI with Maven`) runs
  on the PR.
- Heroku builds from the repo (`system.properties` pins Java 21); the known URL is in the README. CI also pushes a Docker image. There is no deploy workflow in the repo.
- So the PR description states: what changes, new env vars/secrets, and what merging
  triggers. Never push to `main` directly and never run cloud deploy commands yourself.
