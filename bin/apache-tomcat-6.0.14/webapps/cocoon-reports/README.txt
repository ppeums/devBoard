          AWESOME EXCEL REPORTS WITH APACHE COCOON AND POI

Copyright (c) 2003.  All Rights Reserved.
Candlelight Software
Steven P. Punte
steve@candlelightsoftware.com
http://www.candlelightsoftware.com

INTRODUCTION:

	This is a demonstration Apache Cocoon Web Application that
	is written in conjunction with the tutorial article titled
	"Awesome Excel Reports With Apache Cocoon and POI" that
	can be found at the on-line magazine xml.com.

	This software demonstrates rendering MS Excel "reports,"
	or really for that matter just any MS Excel document,
	though a browser preferably Internet Explorer.

	The report actually is in two parts: an MS Excel document
	(i.e. <report>.xsl) that contains the report template
	such as header, column names, various fixed text with
	font, size, etc...  The second part is called the
	"Report Scheme" document.  It contains the query  statement
	to retrive data from a relational database or similar, and
	the information as to where this data is to be rendered in
	the report.

	This application solution contains three pipelines:  a "list
	available reports" pipeline, a "input parameters" pipeline,
	and finally a "render report pipeline."

JX REPORTS

	There has been so very much interest in this sweet combinantion
	of technology, that myself and an associate have launched
	a "Custom Report Solutions" business called "JXReports."  We
	can be found at http://www.jxreports.com.  Our specality is
	constructing semi-custom solutions for situation where 
	standard report solutions just don't meet the needs.  
	Please come visit us.  Our technology has advanced considerably.  



INSTALLATION FROM WAR (Tested so far with Tomcat 4.1.12)

	0.	Download war file from http://candlelightsoftware.com/downloads/cocoon-reports.war

	1.	Place cocoon-report in ~jakarta-tomcat-4.1.12/webapps/cocoon-reports.war

	2.	Start up Tomcat

	3.	Place browser URL to http://localhost:8080/cocoon-reports
				You should now see selection for the two demo report.
				This will also un-archive the war file so the additional
				configuration and setup steps below can be performed.

	4.	Create MySql database table:  mysql < ~/cocoon-reports/demo.sql on command line.

	5.	Adjust Cocoon Sitemap file:

			5.1	Adjust URL for database if needed: see line 230 of site map file

			5.2	Adjust line 237 of sitemap file (i.e. parameter "absolute-path"
					to reflect your installation.


INSTALLATION FROM ZIP

	1.	First have a working instance of Apache Cocoon up and operational.
			See the Apache Cocoon web site for integration instructions with
			a wide range of web servers.  This software should work with
			any 2.+ release of Cocoon, but has only been tested against 2.0.4.

	2.	In a separate location, unzip downloaded file reports.zip.  This
			unfolds to a web application with all the necessary application
			specific file.

	3.	Copy all jar files in location ~/cocoon-app/WEB-INF/lib/*.jar of
			the original test Cocoon web application to directory
			location ~/reports-app/WEB-INF/lib/.

	4.  The examples operates in a conjunction with a database.
			Thus, operation of the SQLTransformer and initialization of a
			database is required, or a re-write of this pipeline and usage
			of some other data supplying component possible.

			This example has been constructed using the MySql database.  Some
			additional configuration information specific to MySql is:

			4.1		Populate the database using file demo.sql.

			4.2		Create a "datasource" in file cocoon.xconf:

         <!-- Datasources: -->
         <datasources>
        	<jdbc logger="core.datasources.demodb" name="demodb">
        		<pool-controller min="5" max="10"/>
        		<dburl>jdbc:mysql://localhost/test</dburl>
        	</jdbc>
         </datasources>

      4.3   Add the appropriate library loading construct in web.xml

          <init-param>
      			<param-name>load-class</param-name>
      			<param-value>org.gjt.mm.mysql.Driver</param-value>
      		</init-param>

      4.4		And of course add a suitable jdbc driver jar file in
            ~/WEB-INF/lib.

	5.	Bring alive the web server and Cocoon framework for this application.

	6.	Point a browser to http://localhost (or equivalent).  A list of
			report hyperlinks show be rendered.  Clicking on these links
			then brings the operator to the input parameter page.

	7.	Note, this solution has been developed and tested using the
			MS Internet Explorer browser.  It will not operate using
			the Netscape browser, and will operate but not as smoothly
			on the Apple OS.
