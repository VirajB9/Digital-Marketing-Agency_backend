# 🧠 Concepts Learned Journal

> This file tracks what concepts the student has demonstrated understanding of.
> Do NOT re-teach these unless the student explicitly asks for a refresher.
> Update this file at the end of every teaching session.

---

## ✅ Confirmed Understanding
*(Concepts the student has demonstrated they understand)*

### Architecture
- **Feature-based packaging** — chose this over layer-based architecture, understands why
- **Module structure** — each module has: controller, dto, entity, mapper, repository, service, validator
- **Controller → Service → Repository pattern** — understands the flow

### Design Decisions (Resolved)
- **Single role per user** — hierarchy model (Owner > Manager > Employee), senior roles inherit junior permissions
- **Keep it simple** — learning project for small agency, not over-engineering
- **DTO pattern** — never expose entities directly through APIs

### Data Layer
- **BaseEntity pattern** — shared createdAt/updatedAt with `@CreatedDate`/`@LastModifiedDate`
- **MongoDB document model** — entities as documents with `@Document`
- **Permission → Role → User chain** — RBAC relationship design
- **Bootstrap/seeding concept** — auto-create default data on startup

### Project Context
- **ERP scope** — knows the full roadmap (auth → lead → client → project → invoice → production)
- **13-step module development order** — architecture first, testing before commit
- **Git conventions** — conventional commits, one logical change = one commit

---

## 🔄 Needs Reinforcement
*(Concepts the student has been exposed to but may need deeper understanding)*

- Why `@Valid` is needed on `@RequestBody` (validation doesn't trigger without it)
- Custom exceptions vs RuntimeException — when to use which
- `@Value` annotation for externalized configuration
- JWT structure and token lifecycle

---

## ⚠️ Common Mistakes
*(Patterns of mistakes to watch for in code reviews)*

- Using `RuntimeException` instead of specific custom exceptions
- Forgetting `@Valid` on request body parameters
- Hardcoding configuration values instead of using YAML + `@Value`
- YAML indentation errors (e.g., `jwt` config under `server:`)

---

## 💡 Student's Strengths
*(What the student is naturally good at — leverage these in teaching)*

- Good architectural thinking — chose feature-based packaging, thought through RBAC hierarchy
- Practical mindset — keeps things simple, avoids over-engineering
- Self-aware — knows they need to understand theory, not just copy code
- Planning ability — has a full ERP roadmap planned out

---

## 📝 Questions the Student Has Asked
*(Track recurring questions to identify knowledge gaps)*

*(Will be filled as sessions progress)*
