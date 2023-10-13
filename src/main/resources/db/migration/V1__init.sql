CREATE TABLE event
(
    id                                UUID         NOT NULL,
    created_at                        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at                        TIMESTAMP WITHOUT TIME ZONE,
    title                             VARCHAR(255) NOT NULL,
    description                       VARCHAR(255),
    location                          VARCHAR(255),
    start_date                        TIMESTAMP WITHOUT TIME ZONE,
    end_date                          TIMESTAMP WITHOUT TIME ZONE,
    organizer_id                      UUID,
    image_url                         VARCHAR(255),
    sale_start_time                   TIMESTAMP WITHOUT TIME ZONE,
    allow_ticket_purchase_after_start BOOLEAN,
    category                          VARCHAR(255),
    is_featured                       BOOLEAN,
    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE event_event_tag
(
    event_id     UUID NOT NULL,
    event_tag_id UUID NOT NULL
);

CREATE TABLE event_tag
(
    id         UUID         NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    tag        VARCHAR(255) NOT NULL,
    CONSTRAINT pk_event_tag PRIMARY KEY (id)
);

CREATE TABLE ticket
(
    id              UUID         NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    ticket_type     VARCHAR(255) NOT NULL,
    price           DECIMAL      NOT NULL,
    total_available INTEGER      NOT NULL,
    sold            INTEGER,
    event_id        UUID,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);

CREATE TABLE ticket_feature
(
    id         UUID         NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    feature    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_ticket_feature PRIMARY KEY (id)
);

CREATE TABLE ticket_ticket_feature
(
    ticket_feature_id UUID NOT NULL,
    ticket_id         UUID NOT NULL
);

ALTER TABLE event_tag
    ADD CONSTRAINT uc_event_tag_tag UNIQUE (tag);

ALTER TABLE ticket_feature
    ADD CONSTRAINT uc_ticket_feature_feature UNIQUE (feature);

ALTER TABLE ticket
    ADD CONSTRAINT FK_TICKET_ON_EVENT FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE event_event_tag
    ADD CONSTRAINT fk_eveevetag_on_event_entity FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE event_event_tag
    ADD CONSTRAINT fk_eveevetag_on_event_tag_entity FOREIGN KEY (event_tag_id) REFERENCES event_tag (id);

ALTER TABLE ticket_ticket_feature
    ADD CONSTRAINT fk_ticticfea_on_ticket_entity FOREIGN KEY (ticket_id) REFERENCES ticket (id);

ALTER TABLE ticket_ticket_feature
    ADD CONSTRAINT fk_ticticfea_on_ticket_feature_entity FOREIGN KEY (ticket_feature_id) REFERENCES ticket_feature (id);
