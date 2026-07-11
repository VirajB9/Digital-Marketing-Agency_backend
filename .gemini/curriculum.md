# 📚 Backend Mastery Curriculum

> Every concept is taught in the context of building this Digital Marketing Agency ERP.
> Every module follows the 13-step development order. Nothing is skipped.
> Architecture discussion ALWAYS comes before coding.

---

## Phase 1: Enterprise Authentication & RBAC ⬅️ CURRENT

### 1A: Understanding What Exists (Week 1)
**Goal**: Deeply understand every file in the auth module and overall architecture.
- **Theory**: Spring Boot architecture, dependency injection (IoC), annotations, application lifecycle, `@Component` vs `@Service` vs `@Repository` vs `@RestController`
- **Practice**: Walk through every file — explain what each annotation does and why
- **Bug Fix**: Fix package typo (`enmus` → `enums`) — understand Java package conventions
- **Milestone**: Can trace a login request: HTTP → Controller → Service → Repository → MongoDB → Response

### 1B: REST API Fundamentals (Week 2)
**Goal**: Master REST conventions and fix controller issues.
- **Theory**: HTTP methods (GET, POST, PUT, DELETE, PATCH), status codes, REST resource naming, idempotency, `@RequestBody`, `@PathVariable`, `@RequestParam`
- **Bug Fix**: Add `@Valid` on controller `@RequestBody` params — understand Bean Validation
- **Bug Fix**: Add `/health` to `permitAll()` in SecurityConfig
- **Theory**: Request validation lifecycle — how `@Valid` triggers `@NotBlank`, `@Email`, `@Pattern` etc.
- **Milestone**: Understands REST principles, can explain validation flow

### 1C: MongoDB & Spring Data (Week 3)
**Goal**: Deep understanding of NoSQL data modeling and Spring Data.
- **Theory**: NoSQL vs SQL, document model, embedding vs referencing, `@Document`, `@Id`, `@Indexed`, `MongoRepository`, derived queries, `@Query`, indexing strategy
- **Practice**: Understand existing entities (User, Role, Permission) and their relationships
- **Theory**: Why `permissionIds` is `List<String>` (referencing) instead of embedded documents
- **Milestone**: Can design MongoDB documents, write repository interfaces, explain indexing

### 1D: Service Layer & Error Handling (Week 4)
**Goal**: Master service pattern, DTOs, mappers, and proper error handling.
- **Theory**: Service pattern, separation of concerns, DTO pattern, why DTOs exist, mapper pattern
- **Bug Fix**: Replace `RuntimeException` → custom exceptions in AuthService and UserService
- **Bug Fix**: Fix lost password problem — return generated password in response
- **Task**: Implement `UserMapper` class
- **Task**: Implement `PasswordValidator`
- **Theory**: When to throw `BadRequestException` (400) vs `ResourceNotFoundException` (404) vs `UnauthorizedException` (401)
- **Milestone**: Clean service methods with proper error handling and DTO mapping

### 1E: Spring Security Deep Dive (Week 5)
**Goal**: Master the Spring Security filter chain.
- **Theory**: Authentication vs Authorization, SecurityFilterChain, `SecurityContext`, `UserDetails`, `GrantedAuthority`, `@PreAuthorize`, method-level security
- **Bug Fix**: Implement `JwtAuthenticationEntryPoint` — return proper 401 JSON response
- **Practice**: Trace a request through the security filter chain
- **Milestone**: Can explain Spring Security filter chain end-to-end

### 1F: JWT Mastery (Week 6)
**Goal**: Deep understanding of token-based authentication.
- **Theory**: JWT structure (header.payload.signature), HS256, `@Value` annotation, externalized configuration, why secrets shouldn't be hardcoded
- **Bug Fix**: Fix hardcoded JWT secret — use `@Value` to inject from YAML
- **Bug Fix**: Fix YAML indentation (`jwt.secret` under `server:`)
- **Task**: Proper JWT configuration with `@ConfigurationProperties` or `@Value`
- **Optional**: Plan refresh token architecture
- **Milestone**: JWT auth with proper configuration management

### 1G: Complete Bootstrap & RBAC (Week 7)
**Goal**: Complete the bootstrap seeder and RBAC system.
- **Theory**: Application startup hooks, data seeding strategies, idempotency
- **Task**: Update OwnerBootstrap to seed all 20 permissions
- **Task**: Create Owner role with ALL permissions, Manager with subset, Employee with minimal
- **Task**: Permission hierarchy — Owner > Manager > Employee
- **Practice**: Test RBAC via Swagger — create users with different roles, verify access
- **Milestone**: Complete RBAC working end-to-end, testable via Swagger

### 1H: MapStruct & Auth Completion (Week 8)
**Goal**: Add MapStruct, complete all auth endpoints, polish the module.
- **Theory**: MapStruct vs manual mapping, annotation processors, `@Mapper`, `@Mapping`
- **Task**: Add MapStruct to `build.gradle`
- **Task**: Implement `UserMapper` using MapStruct
- **Task**: Add remaining auth endpoints (get users, get user by id, update user, deactivate user)
- **Task**: Fill empty util classes (`ApiConstants`, `ResponseMessage`) or remove them
- **Milestone**: Auth module 100% complete, all endpoints tested
- **Git**: `feat: complete auth module` → commit → push

---

## Phase 2: Lead Management (Weeks 9-10)

Following the 13-step order:
1. **Architecture discussion** — What is a Lead? Fields, statuses, business rules
2. **Database design** — Lead document schema, indexes, relationships
3. **Entity** — Lead entity with proper annotations and validation
4. **Repository** — LeadRepository with custom queries
5. **DTOs** — CreateLeadRequest, UpdateLeadRequest, LeadResponse
6. **Mapper** — LeadMapper using MapStruct
7. **Service** — LeadService with full CRUD + status workflow
8. **Controller** — LeadController with REST endpoints
9. **Validation** — LeadValidator for business rules
10. **Exception handling** — Proper exceptions for lead operations
11. **Testing** — Swagger + Postman testing
12. **Git Commit** — `feat: add lead management module`
13. **Git Push**

**Lead Status Workflow**: NEW → CONTACTED → QUALIFIED → PROPOSAL_SENT → CONVERTED → LOST

---

## Phase 3: Client Management (Weeks 11-12)

Same 13-step order. A Client may be converted from a Lead.
- Client entity with company details, contact info, status
- Relationship: Lead → Client (optional conversion reference)
- Full CRUD + search/filter

---

## Phase 4: Project Management (Weeks 13-14)

Same 13-step order. A Project belongs to a Client.
- Project entity with details, budget, timeline, team members
- Relationship: Client → Project (one client, many projects)
- Status workflow: PLANNING → IN_PROGRESS → ON_HOLD → COMPLETED → CANCELLED
- Cross-module queries

---

## Phase 5: Invoice Management (Weeks 15-16)

Same 13-step order. Invoice linked to Client and Project.
- Invoice entity with line items (embedded documents)
- Invoice number generation
- Financial data handling (BigDecimal)
- Status workflow: DRAFT → SENT → PAID → OVERDUE → CANCELLED

---

## Phase 6: Production Hardening (Weeks 17-18)

- CORS configuration
- Complete `application-prod.yml` with environment variables
- `JacksonConfig` for proper JSON serialization
- API documentation polish
- Performance review

---

## Phase 7: Testing (Weeks 19-20)

- Unit tests with JUnit 5 + Mockito
- Controller tests with `@WebMvcTest`
- Repository tests with `@DataMongoTest`
- Integration testing
- Test coverage review

---

## Phase 8: Docker & Deployment (Weeks 21-22)

- Dockerfile
- docker-compose (app + MongoDB)
- CI/CD basics
- Environment-based configuration

---

## Stretch Goals (If Time Permits)
- Analytics module with MongoDB aggregation pipelines
- File management / document uploads
- SEO tools module
- Social media scheduler
- Email service for sending passwords
- Redis caching
- Rate limiting
- PDF generation for invoices
- Background workers / scheduling
