CREATE TABLE m_addresses
(
    id          text        NOT NULL DEFAULT public.uuid_generate_v4(),
    street      text        NOT NULL,
    suite       text        NOT NULL,
    city        text        NOT NULL,
    zipcode     text        NOT NULL,
    lat         text        NULL,
    lng         text        NULL,
    created_by  text        NOT NULL DEFAULT 'admin'::text,
    created_at  timestamptz NOT NULL DEFAULT now(),
    updated_by  text        NOT NULL DEFAULT 'admin'::text,
    updated_at  timestamptz NOT NULL DEFAULT now(),
    "version"   int8        NOT NULL DEFAULT 0,
    is_active   bool        NOT NULL DEFAULT true,
    deleted_at  timestamptz NULL,
    CONSTRAINT m_addresses_pk PRIMARY KEY (id)
);
