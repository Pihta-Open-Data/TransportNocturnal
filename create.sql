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

CREATE TABLE "Line" (
    "ID" integer NOT NULL,
    "Name" character varying(64)
);


--
-- Name: Station; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE "Station" (
    "ID" integer NOT NULL,
    "Name" character varying(64) NOT NULL,
    "LineID" integer NOT NULL,
    "Latitude" double precision,
    "Longitude" double precision,
    "OpenTime" time with time zone NOT NULL,
    "CloseTime" time with time zone NOT NULL,
    "TypeID" integer NOT NULL
);


--
-- Name: StationWay; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE "StationWay" (
    "ID" integer NOT NULL,
    "StationID" integer NOT NULL,
    "LastTrainOdd" time with time zone NOT NULL,
    "FirstTrainOdd" time with time zone NOT NULL,
    "NextWay" integer,
    "Transfers" integer[],
    "LastTrainEven" time with time zone NOT NULL,
    "FirstTrainEven" time with time zone NOT NULL,
    "Interval" interval
);


--
-- Name: Type; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE "Type" (
    "ID" integer NOT NULL,
    "Name" character varying(64)
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

ALTER TABLE ONLY "Line"
    ADD CONSTRAINT "LineID" PRIMARY KEY ("ID");


--
-- Name: StationID; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "Station"
    ADD CONSTRAINT "StationID" PRIMARY KEY ("ID");


--
-- Name: TypeID; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "Type"
    ADD CONSTRAINT "TypeID" PRIMARY KEY ("ID");


--
-- Name: id; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "StationWay"
    ADD CONSTRAINT id PRIMARY KEY ("ID");


--
-- Name: row_id; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY base_data
    ADD CONSTRAINT row_id PRIMARY KEY (row_id);


--
-- Name: LineID; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "Station"
    ADD CONSTRAINT "LineID" FOREIGN KEY ("LineID") REFERENCES "Line"("ID");


--
-- Name: StationType; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "Station"
    ADD CONSTRAINT "StationType" FOREIGN KEY ("TypeID") REFERENCES "Type"("ID");


--
-- Name: WayStation; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "StationWay"
    ADD CONSTRAINT "WayStation" FOREIGN KEY ("StationID") REFERENCES "Station"("ID");


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

