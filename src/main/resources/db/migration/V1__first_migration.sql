CREATE SEQUENCE IF NOT EXISTS public.process_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.process (
    id BIGINT NOT NULL,
    millis_left_to_process BIGINT NOT NULL,
    name VARCHAR(255),

    PRIMARY KEY (id)
);