CREATE TABLE m_users
(
    id          text        NOT NULL DEFAULT public.uuid_generate_v4(),
    name        text        NOT NULL,
    username    text        NOT NULL,
    email       text        NOT NULL,
    address_id  text        NULL,
    company_id  text        NULL,
    phone       text        NULL,
    website     text        NULL,
    created_by  text        NOT NULL DEFAULT 'admin'::text,
    created_at  timestamptz NOT NULL DEFAULT now(),
    updated_by  text        NOT NULL DEFAULT 'admin'::text,
    updated_at  timestamptz NOT NULL DEFAULT now(),
    "version"   int8        NOT NULL DEFAULT 0,
    is_active   bool        NOT NULL DEFAULT true,
    deleted_at  timestamptz NULL,
    CONSTRAINT m_users_pk PRIMARY KEY (id),
    CONSTRAINT m_users_address_fk FOREIGN KEY (address_id) REFERENCES m_addresses (id),
    CONSTRAINT m_users_company_fk FOREIGN KEY (company_id) REFERENCES m_companies (id)
);

CREATE UNIQUE INDEX m_users_un ON m_users USING btree (username) WHERE (deleted_at IS NULL);
