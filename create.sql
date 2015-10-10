--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: Line; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE "line" (
    "id" integer NOT NULL,
    "name" character varying(64)
);


--
-- Name: Station; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE "station" (
    "id" integer NOT NULL,
    "name" character varying(64) NOT NULL,
    "line_id" integer NOT NULL,
    "latitude" double precision,
    "longitude" double precision,
    "open_time" time with time zone NOT NULL,
    "close_time" time with time zone NOT NULL,
    "type_id" integer NOT NULL
);


--
-- Name: StationWay; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE "station_way" (
    "id" integer NOT NULL,
    "station_id" integer NOT NULL,
    "last_train_odd" time with time zone NOT NULL,
    "first_train_odd" time with time zone NOT NULL,
    "next_way" integer,
    "transfers" integer[],
    "last_train_even" time with time zone NOT NULL,
    "first_train_even" time with time zone NOT NULL,
    "interval" interval
);


--
-- Name: Type; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE "type" (
    "id" integer NOT NULL,
    "name" character varying(64)
);


--
-- Name: base_data; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE base_data (
    row_id integer NOT NULL,
    hall_name character varying(64),
    longitude double precision,
    latitude double precision,
    name_of_station character varying(64),
    line character varying(64),
    odd_open_varchar character varying(64),
    close_odd_varchar character varying(64),
    odd_first_train_way_1 character varying(64),
    odd_first_train_way_2 character varying(64),
    odd_last_train_way_1 character varying(64),
    odd_last_train_way_2 character varying,
    even_open_varchar character varying(64),
    even_close_varchar character varying(64),
    even_first_train_way_1 character varying(64),
    even_first_train_way_2 character varying(64),
    even_last_thain_way_1 character varying(64),
    even_last_train_way_2 character varying(64)
);


--
-- Name: LineID; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "line"
    ADD CONSTRAINT "line_id" PRIMARY KEY ("id");


--
-- Name: StationID; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "station"
    ADD CONSTRAINT "station_id" PRIMARY KEY ("id");


--
-- Name: TypeID; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "type"
    ADD CONSTRAINT "type_id" PRIMARY KEY ("id");


--
-- Name: id; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "station_way"
    ADD CONSTRAINT station_way_id PRIMARY KEY ("id");


--
-- Name: row_id; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY base_data
    ADD CONSTRAINT row_id PRIMARY KEY (row_id);


--
-- Name: LineID; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "station"
    ADD CONSTRAINT "line_id" FOREIGN KEY ("line_id") REFERENCES "line"("id");


--
-- Name: StationType; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "station"
    ADD CONSTRAINT "station_type" FOREIGN KEY ("type_id") REFERENCES "type"("id");


--
-- Name: WayStation; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "station_way"
    ADD CONSTRAINT "way_station" FOREIGN KEY ("station_id") REFERENCES "station"("id");


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM pihta;
GRANT ALL ON SCHEMA public TO pihta;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

