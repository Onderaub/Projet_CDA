--
-- PostgreSQL database dump
--

-- Dumped from database version 10.17
-- Dumped by pg_dump version 12.12 (Ubuntu 12.12-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: cram_0; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE cram_0 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';


ALTER DATABASE cram_0 OWNER TO postgres;

\connect cram_0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: createuserversion(); Type: FUNCTION; Schema: public; Owner: cram
--

CREATE FUNCTION public.createuserversion() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
    insert into userversion values(new.userid, 0) ;
    return null;
end;
$$;


ALTER FUNCTION public.createuserversion() OWNER TO cram;

--
-- Name: find_main_project(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.find_main_project(integer) RETURNS integer
    LANGUAGE plpgsql
    AS $_$
declare 
   prj  integer;
   prjp integer;
begin
   select projectid,projectparentid into prj,prjp from project where projectid=$1;
   if prj is null then return NULL;
   end if;
   if prjp is null then return prj;
   end if;
   return find_main_project(prjp);
end ; 
$_$;


ALTER FUNCTION public.find_main_project(integer) OWNER TO postgres;

--
-- Name: find_main_project(integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.find_main_project(integer, integer) RETURNS integer
    LANGUAGE plpgsql
    AS $_$
declare 
   i_userrid ALIAS FOR $1 ;
   i_projectid  ALIAS FOR $2;
   prj  integer;
   prjp integer;
   usr  integer;
begin
   select P.projectid, P.projectparentid, M.userrid 
   into prj, prjp, usr
   from project as P
   left join manager as M on (P.projectid = M.projectid and userrid = i_userrid) 
   where P.projectid=i_projectid;
   --
   if prj is null then return NULL;
   end if;
   if usr is not null then return prj;
   end if;
   if prjp is null then return NULL;
   end if;
   return find_main_project(i_userrid, prjp);
end ; 
$_$;


ALTER FUNCTION public.find_main_project(integer, integer) OWNER TO postgres;

--
-- Name: full_projectname(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.full_projectname(integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
declare 
   prj  integer;
   prjp integer;
   prjname varchar;
begin
   select projectid,projectparentid,projectname
   into prj,prjp,prjname from project where projectid=$1;
   if prj is null then return '';
   end if;
   if prjp is null then return prjname;
   end if;
   return full_projectname(prjp)||'.'||prjname;
end ; 
$_$;


ALTER FUNCTION public.full_projectname(integer) OWNER TO postgres;

--
-- Name: isparent(integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.isparent(integer, integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $_$
declare 
   prjp integer;
begin
   select projectparentid into prjp from project where projectid=$2;
   if $2 = $1 then return true;
   end if;
   if prjp = $1 then return true;
   end if;
   if prjp is null then return false;
   end if;
   return isparent($1, prjp);
end ; 
$_$;


ALTER FUNCTION public.isparent(integer, integer) OWNER TO postgres;

--
-- Name: list_subprojects(integer[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.list_subprojects(integer[]) RETURNS integer[]
    LANGUAGE plpgsql
    AS $_$
declare
   lst alias for $1 ; 
begin
   if lst=ARRAY[]::integer[] then return ARRAY[]::integer[];
   else return( 
   array(select projectid from project where projectid = any(lst)) ||  
   list_subprojects(array(select projectid from project where projectparentid=any(lst))));
   end if;
end ;
$_$;


ALTER FUNCTION public.list_subprojects(integer[]) OWNER TO postgres;

--
-- Name: list_subprojects(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.list_subprojects(integer) RETURNS SETOF integer
    LANGUAGE plpgsql
    AS $_$
declare
   id alias for $1 ; 
begin
   return query select unnest(list_subprojects(ARRAY[id]));
end 
$_$;


ALTER FUNCTION public.list_subprojects(integer) OWNER TO postgres;

--
-- Name: removemanagerproject(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.removemanagerproject() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
  delete from manager where projectid=old.projectid ;
  return old;
end;
$$;


ALTER FUNCTION public.removemanagerproject() OWNER TO postgres;

--
-- Name: upd_project(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.upd_project() RETURNS void
    LANGUAGE plpgsql
    AS $$
declare 
	id_prj integer;
	id_prj_p integer;
	prj_name varchar;
	prj_p_name varchar;
	new_id_prj_p integer;
	i integer;
begin
    for id_prj, id_prj_p, prj_name in
        select projectid, projectparentid,  projectname 
        from project 
        where projectname like '%.%'
    loop
        i := 1;
        new_id_prj_p := 0;
        
        while (true) 
        loop
            prj_p_name := split_part( substring(prj_name from '#"%#".%' for '#'), '.', i );
            if prj_p_name = '' then EXIT ;
            end if;
            
            select projectid into id_prj_p
            from project 
            where projectname= prj_p_name;
            
            if id_prj_p is null then
                insert into project(projectname) values (prj_p_name) 
                returning projectid into id_prj_p;
            end if;
            
            if i = 1 then 
               new_id_prj_p := id_prj_p;
            elsif i > 1 then
                update project set projectparentid= new_id_prj_p where projectid = id_prj_p;
                new_id_prj_p := id_prj_p;
            end if;
            i := i + 1 ; 
        end loop;
        update project set 
            projectparentid = new_id_prj_p, 
            projectname = substring(prj_name from '%.#"%#"' for '#') 
        where projectid = id_prj;
    end loop;      
end;
$$;


ALTER FUNCTION public.upd_project() OWNER TO postgres;

SET default_tablespace = '';

--
-- Name: activity; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.activity (
    activityid integer NOT NULL,
    activityname character varying(128) NOT NULL
);


ALTER TABLE public.activity OWNER TO cram;

--
-- Name: activity_activityid_seq; Type: SEQUENCE; Schema: public; Owner: cram
--

CREATE SEQUENCE public.activity_activityid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.activity_activityid_seq OWNER TO cram;

--
-- Name: activity_activityid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cram
--

ALTER SEQUENCE public.activity_activityid_seq OWNED BY public.activity.activityid;


--
-- Name: activityuser; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.activityuser (
    userid integer NOT NULL,
    activityid integer NOT NULL
);


ALTER TABLE public.activityuser OWNER TO cram;

--
-- Name: cramuser; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.cramuser (
    userid integer NOT NULL,
    username character varying(128) NOT NULL,
    userpwd character varying(128) NOT NULL,
    userstatut character varying(20) NOT NULL,
    userdatestatut date DEFAULT ('now'::text)::date NOT NULL,
    useradmin boolean DEFAULT false NOT NULL,
    userstartdate date DEFAULT ('now'::text)::date NOT NULL,
    usersynchrodate date,
    email character varying,
    name character varying(20),
    lastname character varying(20)
);


ALTER TABLE public.cramuser OWNER TO cram;

--
-- Name: cramuser_userrid_seq; Type: SEQUENCE; Schema: public; Owner: cram
--

CREATE SEQUENCE public.cramuser_userrid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cramuser_userrid_seq OWNER TO cram;

--
-- Name: cramuser_userrid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cram
--

ALTER SEQUENCE public.cramuser_userrid_seq OWNED BY public.cramuser.userid;


--
-- Name: manager; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.manager (
    userid integer NOT NULL,
    projectid integer NOT NULL
);


ALTER TABLE public.manager OWNER TO cram;

--
-- Name: project; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.project (
    projectid integer NOT NULL,
    projectname character varying(128) NOT NULL,
    projectenddate date,
    projectparentid integer
);


ALTER TABLE public.project OWNER TO cram;

--
-- Name: project_projectid_seq; Type: SEQUENCE; Schema: public; Owner: cram
--

CREATE SEQUENCE public.project_projectid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_projectid_seq OWNER TO cram;

--
-- Name: project_projectid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cram
--

ALTER SEQUENCE public.project_projectid_seq OWNED BY public.project.projectid;


--
-- Name: projectuser; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.projectuser (
    userid integer NOT NULL,
    projectid integer NOT NULL
);


ALTER TABLE public.projectuser OWNER TO cram;

--
-- Name: task; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.task (
    activityid integer,
    projectid integer,
    userid integer NOT NULL,
    taskdate date NOT NULL,
    taskam character(2) NOT NULL,
    taskdayoff boolean DEFAULT false NOT NULL,
    taskcomment character varying(500),
    taskid integer NOT NULL,
    updated timestamp with time zone DEFAULT now()
);


ALTER TABLE public.task OWNER TO cram;

--
-- Name: task_taskid_seq; Type: SEQUENCE; Schema: public; Owner: cram
--

CREATE SEQUENCE public.task_taskid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_taskid_seq OWNER TO cram;

--
-- Name: task_taskid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cram
--

ALTER SEQUENCE public.task_taskid_seq OWNED BY public.task.taskid;


--
-- Name: team; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.team (
    teamid integer NOT NULL,
    leader integer NOT NULL,
    label character varying(30)
);


ALTER TABLE public.team OWNER TO cram;

--
-- Name: team_teamid_seq; Type: SEQUENCE; Schema: public; Owner: cram
--

CREATE SEQUENCE public.team_teamid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.team_teamid_seq OWNER TO cram;

--
-- Name: team_teamid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: cram
--

ALTER SEQUENCE public.team_teamid_seq OWNED BY public.team.teamid;


--
-- Name: teamleader; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.teamleader (
    userid integer,
    teamid integer
);


ALTER TABLE public.teamleader OWNER TO cram;

--
-- Name: teamuser; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.teamuser (
    teamid integer NOT NULL,
    userid integer NOT NULL
);


ALTER TABLE public.teamuser OWNER TO cram;

--
-- Name: userversion; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.userversion (
    userid integer NOT NULL,
    centralversion integer NOT NULL
);


ALTER TABLE public.userversion OWNER TO cram;

--
-- Name: version; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.version (
    tablename character varying(50) NOT NULL,
    centralversion integer NOT NULL
);


ALTER TABLE public.version OWNER TO cram;

--
-- Name: versionappli; Type: TABLE; Schema: public; Owner: cram
--

CREATE TABLE public.versionappli (
    numversion integer NOT NULL,
    dateversion date NOT NULL,
    compatible boolean NOT NULL,
    message character varying,
    version character varying DEFAULT '0.3'::character varying NOT NULL
);


ALTER TABLE public.versionappli OWNER TO cram;

--
-- Name: activity activityid; Type: DEFAULT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.activity ALTER COLUMN activityid SET DEFAULT nextval('public.activity_activityid_seq'::regclass);


--
-- Name: cramuser userid; Type: DEFAULT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.cramuser ALTER COLUMN userid SET DEFAULT nextval('public.cramuser_userrid_seq'::regclass);


--
-- Name: project projectid; Type: DEFAULT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.project ALTER COLUMN projectid SET DEFAULT nextval('public.project_projectid_seq'::regclass);


--
-- Name: task taskid; Type: DEFAULT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.task ALTER COLUMN taskid SET DEFAULT nextval('public.task_taskid_seq'::regclass);


--
-- Name: team teamid; Type: DEFAULT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.team ALTER COLUMN teamid SET DEFAULT nextval('public.team_teamid_seq'::regclass);


--
-- Name: activity pk_activity; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.activity
    ADD CONSTRAINT pk_activity PRIMARY KEY (activityid);


--
-- Name: activityuser pk_activityuser; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.activityuser
    ADD CONSTRAINT pk_activityuser PRIMARY KEY (userid, activityid);


--
-- Name: cramuser pk_cramuser; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.cramuser
    ADD CONSTRAINT pk_cramuser PRIMARY KEY (userid);


--
-- Name: manager pk_manager; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.manager
    ADD CONSTRAINT pk_manager PRIMARY KEY (userid, projectid);


--
-- Name: project pk_project; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT pk_project PRIMARY KEY (projectid);


--
-- Name: projectuser pk_projectuser; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.projectuser
    ADD CONSTRAINT pk_projectuser PRIMARY KEY (userid, projectid);


--
-- Name: task pk_task; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT pk_task PRIMARY KEY (userid, taskdate, taskam);


--
-- Name: team pk_team; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT pk_team PRIMARY KEY (teamid);


--
-- Name: teamuser pk_teamuser; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.teamuser
    ADD CONSTRAINT pk_teamuser PRIMARY KEY (teamid, userid);


--
-- Name: userversion pk_userversion; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.userversion
    ADD CONSTRAINT pk_userversion PRIMARY KEY (userid);


--
-- Name: version pk_version; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.version
    ADD CONSTRAINT pk_version PRIMARY KEY (tablename);


--
-- Name: versionappli pk_versionappli; Type: CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.versionappli
    ADD CONSTRAINT pk_versionappli PRIMARY KEY (numversion);


--
-- Name: i_fk_manager_cramuser; Type: INDEX; Schema: public; Owner: cram
--

CREATE INDEX i_fk_manager_cramuser ON public.manager USING btree (userid);


--
-- Name: i_fk_manager_project; Type: INDEX; Schema: public; Owner: cram
--

CREATE INDEX i_fk_manager_project ON public.manager USING btree (projectid);


--
-- Name: i_fk_task_activity; Type: INDEX; Schema: public; Owner: cram
--

CREATE INDEX i_fk_task_activity ON public.task USING btree (activityid);


--
-- Name: i_fk_task_cramuser; Type: INDEX; Schema: public; Owner: cram
--

CREATE INDEX i_fk_task_cramuser ON public.task USING btree (userid);


--
-- Name: i_fk_task_project; Type: INDEX; Schema: public; Owner: cram
--

CREATE INDEX i_fk_task_project ON public.task USING btree (projectid);


--
-- Name: cramuser createuserversion; Type: TRIGGER; Schema: public; Owner: cram
--

CREATE TRIGGER createuserversion AFTER INSERT ON public.cramuser FOR EACH ROW EXECUTE PROCEDURE public.createuserversion();


--
-- Name: project removemanagerproject; Type: TRIGGER; Schema: public; Owner: cram
--

CREATE TRIGGER removemanagerproject BEFORE DELETE ON public.project FOR EACH ROW EXECUTE PROCEDURE public.removemanagerproject();


--
-- Name: activityuser fk_activityuser_activity; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.activityuser
    ADD CONSTRAINT fk_activityuser_activity FOREIGN KEY (activityid) REFERENCES public.activity(activityid);


--
-- Name: activityuser fk_activityuser_cramuser; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.activityuser
    ADD CONSTRAINT fk_activityuser_cramuser FOREIGN KEY (userid) REFERENCES public.cramuser(userid);


--
-- Name: manager fk_manager_cramuser; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.manager
    ADD CONSTRAINT fk_manager_cramuser FOREIGN KEY (userid) REFERENCES public.cramuser(userid);


--
-- Name: manager fk_manager_project; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.manager
    ADD CONSTRAINT fk_manager_project FOREIGN KEY (projectid) REFERENCES public.project(projectid);


--
-- Name: project fk_project_project; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT fk_project_project FOREIGN KEY (projectparentid) REFERENCES public.project(projectid);


--
-- Name: projectuser fk_projectuser_cramuser; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.projectuser
    ADD CONSTRAINT fk_projectuser_cramuser FOREIGN KEY (userid) REFERENCES public.cramuser(userid);


--
-- Name: projectuser fk_projectuser_project; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.projectuser
    ADD CONSTRAINT fk_projectuser_project FOREIGN KEY (projectid) REFERENCES public.project(projectid);


--
-- Name: task fk_task_activity; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT fk_task_activity FOREIGN KEY (activityid) REFERENCES public.activity(activityid);


--
-- Name: task fk_task_cramuser; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT fk_task_cramuser FOREIGN KEY (userid) REFERENCES public.cramuser(userid);


--
-- Name: task fk_task_project; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT fk_task_project FOREIGN KEY (projectid) REFERENCES public.project(projectid);


--
-- Name: team fk_team_leader; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT fk_team_leader FOREIGN KEY (leader) REFERENCES public.cramuser(userid);


--
-- Name: teamuser fk_teamuser_cramuser; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.teamuser
    ADD CONSTRAINT fk_teamuser_cramuser FOREIGN KEY (userid) REFERENCES public.cramuser(userid);


--
-- Name: teamuser fk_teamuser_team; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.teamuser
    ADD CONSTRAINT fk_teamuser_team FOREIGN KEY (teamid) REFERENCES public.team(teamid);


--
-- Name: userversion fk_userversion_cramuser; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.userversion
    ADD CONSTRAINT fk_userversion_cramuser FOREIGN KEY (userid) REFERENCES public.cramuser(userid);


--
-- Name: teamleader teamleader_teamid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.teamleader
    ADD CONSTRAINT teamleader_teamid_fkey FOREIGN KEY (teamid) REFERENCES public.team(teamid);


--
-- Name: teamleader teamleader_userrid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: cram
--

ALTER TABLE ONLY public.teamleader
    ADD CONSTRAINT teamleader_userrid_fkey FOREIGN KEY (userid) REFERENCES public.cramuser(userid);


--
-- Name: DATABASE cram_0; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON DATABASE cram_0 TO cram;


--
-- Name: TABLE activity; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.activity TO consult;


--
-- Name: SEQUENCE activity_activityid_seq; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON SEQUENCE public.activity_activityid_seq TO consult;


--
-- Name: TABLE activityuser; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.activityuser TO consult;


--
-- Name: TABLE cramuser; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.cramuser TO consult;


--
-- Name: SEQUENCE cramuser_userrid_seq; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON SEQUENCE public.cramuser_userrid_seq TO consult;


--
-- Name: TABLE manager; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.manager TO consult;


--
-- Name: TABLE project; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.project TO consult;


--
-- Name: SEQUENCE project_projectid_seq; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON SEQUENCE public.project_projectid_seq TO consult;


--
-- Name: TABLE projectuser; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.projectuser TO consult;


--
-- Name: TABLE task; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.task TO consult;


--
-- Name: SEQUENCE task_taskid_seq; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON SEQUENCE public.task_taskid_seq TO consult;


--
-- Name: TABLE team; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.team TO consult;


--
-- Name: SEQUENCE team_teamid_seq; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON SEQUENCE public.team_teamid_seq TO consult;


--
-- Name: TABLE teamuser; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.teamuser TO consult;


--
-- Name: TABLE userversion; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.userversion TO consult;


--
-- Name: TABLE version; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.version TO consult;


--
-- Name: TABLE versionappli; Type: ACL; Schema: public; Owner: cram
--

GRANT SELECT ON TABLE public.versionappli TO consult;


--
-- PostgreSQL database dump complete
--

