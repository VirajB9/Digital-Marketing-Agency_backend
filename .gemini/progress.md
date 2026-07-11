# 📊 Learning Progress Tracker

## Current Status
- **Current Phase**: Phase 1 — Enterprise Authentication & RBAC
- **Current Module**: Auth (actively being developed)
- **Currently Working On**: Authentication Service, JWT, Spring Security, Login API, Register API
- **Started On**: 2026-07-11
- **Target Completion**: ~60 active development days (~420 hours)
- **Last Session**: 2026-07-11 — Memory files created, design decisions resolved

## Design Decisions (Resolved)
| Decision | Choice | Rationale |
|----------|--------|-----------|
| Roles per user | **Single role** | Hierarchy model: Owner > Manager > Employee. Simpler. |
| Bootstrap mechanism | **CommandLineRunner** | Keep it simple — learning project for small agency |
| Role names | **Owner, Manager, Employee** | Owner is the bootstrap default |
| Architecture | **Feature-based packaging** | Chosen over layer-based |
| Mapper library | **MapStruct** | NOT yet in build.gradle — needs to be added |

## Session Log
| Date | Phase | Topic | What Was Covered | Status |
|------|-------|-------|-----------------|--------|
| 2026-07-11 | Setup | Init | Codebase scanned, memory files created, design decisions resolved | ✅ Done |

## Skill Assessment
| Skill | Level (1-5) | Notes |
|-------|-------------|-------|
| Java Basics (OOP) | TBD | Need to assess |
| Spring Boot | 2 | Has working auth module — needs deeper theory understanding |
| REST APIs | 2 | Has controllers built — needs design principles |
| MongoDB | 2 | Has repositories built — needs deep understanding |
| Spring Security | 2 | Has JWT + RBAC started — needs conceptual clarity |
| Testing | 1 | No tests written yet |
| Error Handling | 2 | Exception classes exist but not used properly in services |
| API Design | TBD | Need to assess |
| Git | TBD | Knows conventions, need to assess practice |

## What's Completed
### Project Setup
- [x] Spring Boot project created
- [x] MongoDB configured
- [x] Base project structure finalized
- [x] Feature-based architecture finalized

### Common Module
- [x] BaseEntity created (createdAt, updatedAt with auditing)
- [x] ApiResponse wrapper
- [x] PasswordGenerator utility
- [ ] ApiConstants — empty
- [ ] ResponseMessage — empty
- [ ] DateTimeUtil — empty
- [ ] StringUtil — empty
- [ ] ValidationUtil — empty

### Auth Module — Data Layer
- [x] User entity (id, firstName, lastName, email, phoneNumber, password, roleId, status)
- [x] Role entity (id, name, description, permissionIds)
- [x] Permission entity (id, permissionType, module, description)
- [x] UserRepository (findByEmail, existsByEmail)
- [x] RoleRepository (findByName)
- [x] PermissionRepository (findByPermissionType)
- [x] PermissionType enum (20 permissions: 5 modules × 4 CRUD)
- [x] UserStatus enum (ACTIVE, INACTIVE, SUSPENDED, LOCKED)

### Auth Module — Security Layer
- [x] CustomUserDetails (wraps User + authorities)
- [x] CustomUserDetailsService (loads user, role, permissions)
- [x] JwtFilter (extracts Bearer token, validates, sets SecurityContext)
- [x] JwtUtil (generate, extract, validate — BUT has hardcoded secret)
- [x] SecurityConfig (stateless, CSRF disabled, JWT filter, method security)
- [ ] JwtAuthenticationEntryPoint — EMPTY placeholder

### Auth Module — Service Layer
- [x] AuthService (login method — works but uses RuntimeException)
- [x] UserService (createUser — works but password is lost, uses RuntimeException)
- [ ] UserMapper — EMPTY placeholder
- [ ] PasswordValidator — EMPTY placeholder

### Auth Module — Controller Layer
- [x] AuthController (POST /api/auth/login — missing @Valid)
- [x] UserController (POST /api/users — missing @Valid, has @PreAuthorize)

### Auth Module — Bootstrap
- [x] OwnerBootstrap (CommandLineRunner — creates Owner role + owner user)
- [ ] Does NOT seed permissions yet
- [ ] Does NOT create Manager/Employee roles yet

### Config
- [x] SecurityConfig
- [x] OpenApiConfig (Swagger)
- [x] MongoConfig (@EnableMongoAuditing)
- [ ] JacksonConfig — EMPTY

### Exception Handling
- [x] GlobalExceptionHandler (@RestControllerAdvice)
- [x] BadRequestException
- [x] ResourceNotFoundException
- [x] UnauthorizedException
- [ ] Services don't USE these exceptions yet (throw RuntimeException instead)

### Other Modules (All Empty — directory scaffolding only)
- [ ] Client module (0%)
- [ ] Lead module (0%)
- [ ] Project module (0%)
- [ ] Invoice module (0%)

## Known Bugs / Learning Opportunities
These will be fixed as part of the curriculum:
- [ ] Package typo: `enmus` → should be `enums`
- [ ] Hardcoded JWT secret in JwtUtil (ignores YAML config)
- [ ] JWT YAML config indented under `server:` instead of custom namespace
- [ ] Missing `@Valid` on controller `@RequestBody` params
- [ ] Services throw `RuntimeException` instead of custom exceptions
- [ ] Generated passwords never returned to caller — they're lost
- [ ] `/health` endpoint requires authentication (not in permitAll)
- [ ] Empty `JwtAuthenticationEntryPoint` — no proper 401 response
- [ ] No CORS configuration
- [ ] Incomplete `application-prod.yml`
- [ ] Empty `UserMapper` — no DTO↔Entity conversion
- [ ] Bootstrap doesn't seed permissions or create Manager/Employee roles
- [ ] MapStruct not yet added to build.gradle

## ERP Roadmap Phases
1. ⬅️ **Enterprise Authentication & RBAC** (current)
2. Lead Management
3. Client Management
4. Project Management
5. Invoice Management
6. Analytics
7. File Management
8. SEO Tools
9. Social Scheduler
10. Production Hardening
11. Docker & Deployment

## Modules Completion Tracker
| Module | Entity | Repo | DTO | Mapper | Service | Controller | Validator | Overall |
|--------|--------|------|-----|--------|---------|------------|-----------|---------|
| auth | ✅ | ✅ | ✅ | ❌ | ✅ (bugs) | ✅ (bugs) | ❌ | ~70% |
| client | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | 0% |
| lead | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | 0% |
| project | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | 0% |
| invoice | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | ❌ | 0% |
