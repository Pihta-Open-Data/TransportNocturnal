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
-- Name: station; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE station (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    latitude double precision,
    longitude double precision,
    open_time time with time zone NOT NULL,
    close_time time with time zone NOT NULL,
    type_id integer NOT NULL
);


--
-- Name: station_way; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE station_way (
    id integer NOT NULL,
    station_id integer,
    last_train_odd time with time zone NOT NULL,
    first_train_odd time with time zone NOT NULL,
    next_way integer,
    last_train_even time with time zone NOT NULL,
    first_train_even time with time zone NOT NULL,
    "interval" interval
);


--
-- Name: transfers; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE transfers (
    "start-id" integer NOT NULL,
    "finish-id" integer NOT NULL,
    id integer NOT NULL
);


--
-- Name: row_id; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY base_data
    ADD CONSTRAINT row_id PRIMARY KEY (row_id);


--
-- Name: station_id; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY station
    ADD CONSTRAINT station_id PRIMARY KEY (id);


--
-- Name: station_way_id; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY station_way
    ADD CONSTRAINT station_way_id PRIMARY KEY (id);


--
-- Name: transfer-id; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY transfers
    ADD CONSTRAINT "transfer-id" PRIMARY KEY (id);


--
-- Name: finish-id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY transfers
    ADD CONSTRAINT "finish-id" FOREIGN KEY ("finish-id") REFERENCES station_way(id);


--
-- Name: start-id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY transfers
    ADD CONSTRAINT "start-id" FOREIGN KEY ("start-id") REFERENCES station_way(id);


--
-- Name: way_station; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY station_way
    ADD CONSTRAINT way_station FOREIGN KEY (station_id) REFERENCES station(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM viktorlopatin;
GRANT ALL ON SCHEMA public TO viktorlopatin;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

