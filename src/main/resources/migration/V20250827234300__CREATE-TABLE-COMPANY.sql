CREATE TABLE m_companies
(
    id          text        NOT NULL DEFAULT public.uuid_generate_v4(),
    name        text        NOT NULL,
    catch_phrase text       NULL,
    bs          text        NULL,
    created_by  text        NOT NULL DEFAULT 'admin'::text,
    created_at  timestamptz NOT NULL DEFAULT now(),
    updated_by  text        NOT NULL DEFAULT 'admin'::text,
    updated_at  timestamptz NOT NULL DEFAULT now(),
    "version"   int8        NOT NULL DEFAULT 0,
    is_active   bool        NOT NULL DEFAULT true,
    deleted_at  timestamptz NULL,
    CONSTRAINT m_companies_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX m_companies_un ON m_companies USING btree (name) WHERE (deleted_at IS NULL);
