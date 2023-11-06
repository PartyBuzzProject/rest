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
    organizer_id                      VARCHAR(255),
    image_url                         VARCHAR(255),
    sale_start_time                   TIMESTAMP WITHOUT TIME ZONE,
    allow_ticket_purchase_after_start BOOLEAN,
    is_featured                       BOOLEAN,
    CONSTRAINT pk_event PRIMARY KEY (id)
);

CREATE TABLE event_category
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(255),
    CONSTRAINT pk_event_category PRIMARY KEY (id)
);

CREATE TABLE event_eventCategories
(
    eventCategory_id UUID NOT NULL,
    event_id         UUID NOT NULL,
    CONSTRAINT pk_event_eventcategories PRIMARY KEY (eventCategory_id, event_id)
);

ALTER TABLE event_eventCategories
    ADD CONSTRAINT fk_eveeve_on_event FOREIGN KEY (event_id) REFERENCES event (id);

ALTER TABLE event_eventCategories
    ADD CONSTRAINT fk_eveeve_on_event_category FOREIGN KEY (eventCategory_id) REFERENCES event_category (id);
