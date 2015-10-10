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
    "CloseTime" time with time zone NOT NULL
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
    "FirstTrainEven" time with time zone NOT NULL
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
-- Name: id; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY "StationWay"
    ADD CONSTRAINT id PRIMARY KEY ("ID");


--
-- Name: LineID; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "Station"
    ADD CONSTRAINT "LineID" FOREIGN KEY ("LineID") REFERENCES "Line"("ID");


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

