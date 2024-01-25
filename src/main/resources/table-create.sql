/*
Created: 2/27/2022
Modified: 2/27/2022
Model: RRAK
Database: PostgreSQL 12
*/

-- Create tables section -------------------------------------------------

-- Table plant

CREATE TABLE "plant"
(
    "id" bigserial NOT NULL,
    "name" text,
    "description" text,
    "researchers" text,
    "status" text
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "plant" ADD CONSTRAINT "PK_plant" PRIMARY KEY ("id")
;

-- Table preserve

CREATE TABLE "preserve"
(
    "id" bigserial NOT NULL,
    "name" text,
    "location" text
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "preserve" ADD CONSTRAINT "PK_preserve" PRIMARY KEY ("id")
;

-- Table preserves_plant

CREATE TABLE "preserves_plant"
(
    "id" bigserial NOT NULL,
    "id_plant" bigint NOT NULL,
    "id_preserve" bigint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship2" ON "preserves_plant" ("id_preserve")
;

ALTER TABLE "preserves_plant" ADD CONSTRAINT "PK_preserves_plant" PRIMARY KEY ("id_plant","id")
;

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE "preserves_plant"
    ADD CONSTRAINT "Relationship1"
        FOREIGN KEY ("id_plant")
            REFERENCES "plant" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "preserves_plant"
    ADD CONSTRAINT "Relationship2"
        FOREIGN KEY ("id_preserve")
            REFERENCES "preserve" ("id")
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
;

